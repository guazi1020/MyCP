package com.li.sssp.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.DataNode;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.li.sssp.entites.Game;
import com.li.sssp.entites.PageGame;
import com.li.sssp.entites.SP;
import com.li.sssp.factory.BeanEnum.NetDataBean;
import com.li.sssp.inter.INetData;
import com.li.sssp.until.ComparatorGames;

@Component
public class NetData_One implements INetData {

	@Autowired
	ComparatorGames comparatorGames;
	
	
	//private static String URL_ZC = "http://www.qcw.com/buy/jczq/hh?data=&ot=2";
	private static String URL_ZC = "http://www.huancai66.com/buy/jczq/hh?data=&ot=2";
	private static String URL_LEAGUEH = "http://info.qcw.com/league";
	private static Integer TIMEOUT = 100000;

	private Document doc = null;
	private  List<Game> games;

	/** 
	 * Jsounp实现获取所有game数据
	 */
	@Override
	public List<Game> GetAllData() {
		// TODO Auto-generated method stub

		String cs_data = null;
		// 获取源
		try {
			// 1 获取比赛内容
			doc = Jsoup.connect(URL_ZC).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:23.0) Gecko/20100101 Firefox/23.0")
					.ignoreContentType(true).timeout(10*1000).maxBodySize(0).get();

			//System.out.println(doc);
			// 1.1 获取比赛数据
			cs_data = getZCData(doc);

			// 1.2 根据比赛数据查找球队信息
			if (cs_data != null) {
				searchByGameList(cs_data);
			} else {

			}
			// System.out.println(cs_data);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return games;
	}

	/**
	 * 根据gemeNumbers来查找球队数据,并填充game
	 * 
	 * @param gameNumbers 比赛编号
	 */
	public void searchByGameList(String gameNumbers) {
		games = new ArrayList<Game>();
		String str_league; // 赛制
		int int_leagueNumber = 0; // 赛制编号
		String str_introduce; // 赛事简报
		int int_gameNumber = 0; // 比赛编号
		Date dt_beginDate = null; // 开赛时间
		Date dt_endTime = null; // 投注结束时间

		String st_homeName; // 主队名称
		String st_homeRank; // 主队排名
		Integer inte_homeIntegrate = null;// 主队六轮积分

		String st_guestName;// 客队名称
		String st_guestRank;// 客队排名
		Integer inte_guestIntegrate = null; // 客队六轮积分

		List<SP> sps; // SPS
		double homeE = 0; // 主E
		double pingE = 0; // 平E
		double guestE = 0;// 客E

		for (String item : gameNumbers.split(",")) {

			Game _game = new Game();
			String id = item.replace("\"", ""); // check 一下
			if("".equals(id)){continue;}
			//System.out.println("测试"+id);
			Element base_element = doc.getElementById(id);

			// ----------------------------获取比赛信息------------------------------------------------
			str_league = base_element.attr("league");
			String url = base_element.select("a[title=" + str_league + "]").get(0).attr("href");
			int length = url.length();
			// System.out.println("http".equals(url.substring(0, 4)));
			if ("http".equals(url.substring(0, 4))) { // 获取赛事编号
				int_leagueNumber = Integer.parseInt(url.substring(26, length - 1));
			}

			str_introduce = base_element.attr("abbr");

			int_gameNumber = Integer.parseInt(base_element.attr("id"));

			String str_beginDate = base_element.getElementsByClass("b_time hide").first().attr("title");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

			try {
				dt_beginDate = sdf.parse(str_beginDate.substring(5, str_beginDate.length()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			String str_endTime = base_element.getElementsByClass("e_time").first().attr("title");
			try {
				dt_endTime = sdf.parse(str_endTime.substring(5, str_beginDate.length()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/**
			 * --------------------------以下是获取球队信息的内容---------------------------------------------------
			 */

			// 获取主队信息
			st_homeName = base_element.getElementsByClass("zhu").first().select("div[title]").text();
			st_homeRank = base_element.getElementsByClass("zhu").first().select("p[class=p2]").text().replace("[", "")
					.replace("]", "");

			// 获取客队信息
			st_guestName = base_element.getElementsByClass("fu").first().select("div[title]").text();
			st_guestRank = base_element.getElementsByClass("fu").first().select("p[class=p2]").text().replace("[", "")
					.replace("]", "");

			// try {
			//
			// inte_homeIntegrate = SixIntegrals(
			// Team_num(int_leagueNumber, st_homeName,
			// Integer.parseInt(st_homeRank)), int_leagueNumber);
			// inte_guestIntegrate = SixIntegrals(
			// Team_num(int_leagueNumber, st_guestName,
			// Integer.parseInt(st_guestRank)), int_leagueNumber);
			//
			// //System.out.println(inte_homeIntegrate+"|"+inte_guestIntegrate);
			// } catch (NumberFormatException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (IOException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			/**
			 * -----------------------------------获取SP---------------------------------------------------------------
			 */
			sps = new ArrayList<>();
			Double sp_3 = Double // homeSP
					.parseDouble(base_element.getElementById(id + "_brqspf_3").getElementsByAttribute("sp").attr("sp"));
			Double sp_1 = Double // pingSP
					.parseDouble(base_element.getElementById(id + "_brqspf_1").getElementsByAttribute("sp").attr("sp"));
			Double sp_0 = Double // guestSP
					.parseDouble(base_element.getElementById(id + "_brqspf_0").getElementsByAttribute("sp").attr("sp"));
			// ystem.out.println(int_gameNumber+"|"+sp_3+"|"+sp_1+"|"+sp_0);
			
			sps.add(new SP(null,int_gameNumber, 0, sp_3, sp_1, sp_0,null,null));

			Double spf_3 = Double
					.parseDouble(base_element.getElementById(id + "_spf_3").getElementsByAttribute("sp").attr("sp"));
			Double spf_1 = Double
					.parseDouble(base_element.getElementById(id + "_spf_1").getElementsByAttribute("sp").attr("sp"));
			Double spf_0 = Double
					.parseDouble(base_element.getElementById(id + "_spf_0").getElementsByAttribute("sp").attr("sp"));
			String str_concedePoints = base_element.getElementsByClass("rangqiuzhen").text();
			// System.out.println(str_concedePoints);
			int concedePoints = 0;
			if (!str_concedePoints.equals("")) {
				concedePoints = Integer.parseInt(str_concedePoints);
			} else {
				concedePoints = Integer.parseInt(base_element.getElementsByClass("rangqiu").text());
				// System.out.println(concedePoints);
			}
			sps.add(new SP(null,int_gameNumber, concedePoints, spf_3, spf_1, spf_0,null,null));
			/**
			 * -----------------------------------------获取所有E------------------------------------------------------------
			 */
			int homePaim = 0;
			if (!st_homeRank.equals("")) {
				try{
				homePaim = Integer.parseInt(st_homeRank);
				}catch(Exception e){
					
				}
				
			}
			int guestPaim = 0;
			if (!st_guestRank.equals("")) {
				try {
					guestPaim = Integer.parseInt(st_guestRank);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			homeE = (0.45 - 0.0053 * (homePaim - guestPaim)) * sp_3;
			pingE = (0.45 - 0.0053 * (homePaim - guestPaim)) * sp_1;
			guestE = (0.45 - 0.0053 * (guestPaim - homePaim)) * sp_0;

			/**
			 * ----------------------------创建game--------------------------------------------------
			 */
			_game.setLeague(str_league);
			_game.setLeagueNumber(int_leagueNumber);
			_game.setIntroduce(str_introduce);
			_game.setGameNumber(int_gameNumber);
			_game.setGameData(dt_beginDate);
			_game.setCloseData(dt_endTime);
			_game.setHomeName(st_homeName);
			_game.setHomeRank(st_homeRank);
			// _game.setHomeIntegrate(inte_homeIntegrate);

			_game.setGuestName(st_guestName);
			_game.setGuestRank(st_guestRank);
			// _game.setGuestIntegrate(inte_guestIntegrate);
			_game.setSps(sps);

			_game.setHomeE(homeE);
			_game.setPingE(pingE);
			_game.setGuestE(guestE);

			// if sp<2.0 then not add in games
			if(isLessThan2(sps)) {
                games.add(_game);
            }
			System.out.println("NetData_One row:253");
			System.out.println(_game.getSps().get(0).getResult());
		}
	}
	// ==================================辅助game填充函数Begin==============================================================

	/**
	 * 判定sp所有的值是否均小于2.0
	 */
	public boolean isLessThan2(List<SP> sps){
        for (SP sp:sps
             ) {
           if(sp.getHomeSP()>2.0&&sp.getPingSP()>2.0&&sp.getGuestSP()>2.0&&sp.getBall()==0){
               return true;
           }
        }
        return false;
	}

	/**
	 * 最近六轮积分 未用 2017-06-07
	 * 
	 * @return
	 * @throws IOException
	 */
	public Integer SixIntegrals(Integer team_number, Integer league_number) throws IOException {
		Integer source = 0;
		if (team_number != null) {
			String url = "http://info.qcw.com/team/" + String.valueOf(team_number) + "/panlu";
			Document doc = Jsoup.connect(url).get();
			Element element = doc.select("tbody[class=jTrInterval his_table]").get(0);
			int count = 0;
			for (Element _a : element.select("a[href=http://info.qcw.com/league/" + league_number + "]")) {
				count += 1;
				if (count < 7) {
					String bs_data = _a.parent().parent().select("td").get(1).text(); // 比赛时间
					String key = _a.parent().parent().select("td").get(5).text(); // 比赛结果
					// System.out.println(key);
					switch (key) {
					case "胜":
						source += 3;
						break;
					case "平":
						source += 1;
					default:
						break;
					}
				}
			}
		}
		return source;
	}

	/**
	 * 球队编号
	 * 
	 * @param leagueNum：赛事编号
	 * @return
	 * @throws IOException
	 */
	public Integer Team_num(Integer leagueNum, String teamName, Integer paim) throws IOException {
		// 通过leaguenum来get数据
		Document doc = Jsoup.connect(URL_LEAGUEH + "/" + leagueNum.toString()).get();
		String teamnum = "";
		for (Element element : doc.getElementById("one_team_select").select("option")) {

			int index1 = teamName.trim().indexOf(element.text().trim());
			if (index1 != -1) {
				teamnum = element.attr("value");
			}
		}

		if (teamnum != "") {
			if (doc.getElementById("hot_score_div") == null) {
				return null;
			}
			Elements elements = doc.getElementById("hot_score_div").getElementById("hot_score_tbody").select("tr");
			for (Element element : elements) {
				// System.out.println(element);

				if (paim == Integer.parseInt(element.select("td").get(1).text())) {
					// System.out.println(element.select("td[class=td_qiud]").get(0).select("a"));
					String _id = element.select("td[class=td_qiud]").get(0).select("a").get(0).attr("id");
					teamnum = _id.substring(4, _id.length());
				}
			}
		}
		if (teamnum == "") {
			return null;
		}
		return Integer.parseInt(teamnum);
	}

	/**
	 * 获取数据的比赛编号id列表
	 * 
	 * @param doc
	 * @return
	 * @throws IOException
	 */
	public String getZCData(Document doc) throws IOException {
		String cs_data = ""; // 需要解析的数据源

		// Document doc = Jsoup.connect(URL_ZC).get();
		Elements scripts = doc.select("script");
		for (Element script : scripts) {
			for (DataNode dataNode : script.dataNodes()) {
				String[] s = dataNode.toString().split("var");
				for (String string : s) {
					if (string.split("dgMatch").length != 1) {
						String str_data = string.substring(12);
						int num = str_data.trim().length();
						cs_data = str_data.substring(0, num - 2);
					}
				}
			}
		}
		return cs_data;
	}

	// ====================================辅助game填充函数End============================================================
	public NetDataBean getCode() {
		return NetDataBean.网络数据操作实现_One;
	}

	/**
	 * 重写组装页面数据类
	 */
	@Override
	public PageGame getAllPageGame(PageGame pageGame, String searchPhrase,String sortColumn,Boolean isRefresh) {
		if(isRefresh){games=null;}
		
		if (games == null) {
			System.out.println("this is GetAllData");
			GetAllData();
			pageGame.setRows(games);
			pageGame.setTotal(games.size());
		}

		if (searchPhrase!=null) {
			try {
				List<Game> games = searchPhrase(pageGame, searchPhrase);
//				for (Game game : games) {
//					System.out.println("row:359");
//					System.out.println(game.toString());
//				}
			//	pageGame.setRows(null);
				pageGame.setRows(games);
				pageGame.setRowCound(games.size());
			} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(sortColumn!=null){
		Collections.sort(games,comparatorGames);
			
		}
		//games.sort(Comparator<Gme>);
		//games.sort
		return pageGame;
	}

	/**
	 * 全字段查询
	 * @param pageGame 
	 * @param searchPhrase 查询字段
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	List<Game> searchPhrase(PageGame pageGame, String searchPhrase) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
//System.out.println("row:387");
//System.out.println(games.toString());
		List<Game> rows = new ArrayList<>();

		for (Game game : games) {
			Field[] field = game.getClass().getDeclaredFields();
			for (int i = 0; i < field.length; i++) {
				String _name = field[i].getName();
				_name = _name.substring(0, 1).toUpperCase() + _name.substring(1);
				String type = field[i].getGenericType().toString(); // 获取属性的类型
				//System.out.println(type);
				if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class
																// "，后面跟类名
					Method m = game.getClass().getMethod("get" + _name);
					String value = (String) m.invoke(game); // 调用getter方法获取属性值
					//System.out.println("字段名:"+_name+"值："+value);
					if (value.indexOf(searchPhrase) != -1) {
//						System.out.println("row:403");
//						System.out.println(i);
//						System.out.println(_name);
						rows.add(game);
						
						break;
					}
				}
			}
		}
		return rows;

	}

}
