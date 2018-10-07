package com.li.sssp.impl;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.li.sssp.entites.Game;
import com.li.sssp.entites.SP;
import com.li.sssp.inter.INetResults;

@Component
public class NetResults implements INetResults {

	private static String code_Flag = "NetResults.java";
	private static String URL = "http://cp.zgzcw.com/lottery/jchtplayvsForJsp.action?lotteryId=47&type=jcmini";
	private static Integer TIMEOUT = 5000;
	private Document doc = null;
	private List<String> urls;
	private List<Game> games;

	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");

	/**
	 * 组装games集合
	 */
	List<Game> ReadUrls() {
		SMT_URL();
		if (urls != null) {
			games = new ArrayList<>();
			for (String url : urls) {
				try {
					Document docfordate = Jsoup.connect(url)
							.userAgent("Mozilla/5.0 (Windows NT 6.1; rv:23.0) Gecko/20100101 Firefox/23.0")
							.ignoreContentType(true).timeout(TIMEOUT).maxBodySize(0).get();
					System.out.println(code_Flag + "44:" + url);
					for (Element element : docfordate.getElementsByClass("endBet")) {
						Game game = new Game();
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
						String st_guestRank = null;// 客队排名
						Integer inte_guestIntegrate = null; // 客队六轮积分

						List<SP> sps; // SPS
						double homeE = 0; // 主E
						double pingE = 0; // 平E
						double guestE = 0;// 客E

						try {
							dt_beginDate = sdf.parse(element.attr("t"));
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						str_league = element.attr("m");
						game.setGameData(dt_beginDate);
						game.setLeague(str_league);

						//convertGameNumber(
						try {
							int_gameNumber=Integer.parseInt(convertGameNumber(dt_beginDate,Integer.parseInt(element.select("i").first().text())));
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						 //= Integer.parseInt(element.select("i").first().text());
						game.setGameNumber(int_gameNumber);

						str_league = element.select("td[class=wh-2]").first().attr("title");
						game.setLeague(str_league);

						st_homeRank = element.select("td[class=wh-4 t-r]").select("em").text();
						if (st_homeRank != null && !st_homeRank.isEmpty()) {
							// System.out.println(code_Flag+"81: "+st_homeRank);
							st_homeRank = st_homeRank.split("]")[0].substring(1);
							// st_homeRank=st_homeRank.substring(1)
						}
						game.setHomeRank(st_homeRank);

						st_homeName = element.select("td[class=wh-4 t-r]").select("a").text();
						game.setHomeName(st_homeName);

						st_guestRank = element.select("td[class=wh-6 t-l]").select("em").text();
						if (st_guestRank != null && !st_guestRank.isEmpty()) {

							st_guestRank = st_guestRank.split("]")[0].substring(1);

						}
						game.setGuestRank(st_guestRank);

						st_guestName = element.select("td[class=wh-6 t-l]").select("a").text();
						game.setGuestName(st_guestName);

						sps = new ArrayList<>();
						SP _sp1 = new SP();
						SP _sp2 = new SP();
						List<Integer> balls = new ArrayList<>();
						for (Element rq : element.select("td[class=wh-8 b-l]").select("em")) {
							if (rq.children().size() == 0) {
								balls.add(Integer.parseInt(rq.text()));
							}
						}
						
						if (balls.size() == 2) {
							_sp1.setBall(balls.get(0));
							_sp2.setBall(balls.get(1));
						}
						
						String[] _spsinfos=element.select("td[class=wh-8 b-l]").select("input[type=hidden]").attr("value").split("\\|");
						
						String _Score1=element.select("td[class=wh-5 bf]").text();
						String _Score2=element.select("td[class=wh-5 bf]").text();
						_sp1.setScore(_Score1);
						_sp2.setScore(_Score2);
						
						if(_spsinfos.length==2){
							
							String[] _spsvalues1=_spsinfos[0].split("\\s+");
							String[] _spsvalues2=_spsinfos[1].split("\\s+");
							//System.out.println("NetResults 142 spvalue[0]"+_spsvalues1[0].toString());
							//System.out.println("NetResults 143 spvalue[1]"+_spsvalues1[1].toString());
							for (int i = 0; i < _spsvalues1.length; i++) {
								try {

									System.out.println("CHECK："+(Integer.parseInt(_Score1.split(":")[0])-Integer.parseInt(_Score1.split(":")[1])));
									if (Double.parseDouble(_spsvalues1[i]) < 0) {
										
										switch (i) {
										case 0:
											_sp1.setResult(3);
											break;
										case 1:
											_sp1.setResult(1);
											break;
										case 2:
											_sp1.setResult(0);
											break;
										default:
											break;
										}
									}else{
									if((Integer.parseInt(_Score1.split(":")[0])-Integer.parseInt(_Score1.split(":")[1]))<0){
										_sp1.setResult(0);
									}else if((Integer.parseInt(_Score1.split(":")[0])-Integer.parseInt(_Score1.split(":")[1]))==0){
										_sp1.setResult(1);
									}else{
										_sp1.setResult(3);
									}
//									 for (String _string : _Score1.split(":")) {
//										Integer.parseInt(_string)
//									 }	
									}
									
								} catch (Exception e) {
									// TODO: handle exception
									
									break;
								}

							}
							
							for(int i=0;i<_spsvalues2.length;i++){
								//System.out.println(code_Flag+"148:"+Double.parseDouble(_spsvalues2[i]));
								if(Double.parseDouble(_spsvalues2[i])<0){
								
									switch (i) {
									case 0:
										_sp2.setResult(3);
										break;
									case 1:
										_sp2.setResult(1);
										break;
									case 2:
										_sp2.setResult(0);
										break;
									default:
										break;
									}
								}								
														
							}
							
							if(_spsvalues1.length==3){
								_sp1.setHomeSP(Math.abs(Double.parseDouble(_spsvalues1[0])));
								_sp1.setPingSP(Math.abs(Double.parseDouble(_spsvalues1[1])));
								_sp1.setGuestSP(Math.abs(Double.parseDouble(_spsvalues1[2])));
							}
							if(_spsvalues2.length==3){
								_sp2.setHomeSP(Math.abs(Double.parseDouble(_spsvalues2[0])));
								_sp2.setPingSP(Math.abs(Double.parseDouble(_spsvalues2[1])));
								_sp2.setGuestSP(Math.abs(Double.parseDouble(_spsvalues2[2])));
							}		
						}
						
						
						_sp1.setGameNumber(int_gameNumber);
						_sp2.setGameNumber(int_gameNumber);
						
						System.out.println("194_sp1.number:"+_sp1.getGameNumber()+"_sp1.result:"+_sp1.getResult());
						System.out.println("211_sp2.number:"+_sp2.getGameNumber()+"_sp1.result:"+_sp2.getResult());
						sps.add(_sp1);
						sps.add(_sp2);
						game.setSps(sps);
						
						//E
						int homePaim = 0;
						int guestPaim = 0;
						try {
						if (!st_homeRank.equals("")) {
							homePaim = Integer.parseInt(st_homeRank);
						}
						
						if (!st_guestRank.equals("")) {
							
								guestPaim = Integer.parseInt(st_guestRank);
							
							
						}
						} catch (Exception e) {
							// TODO: handle exception
						}
						homeE = (0.45 - 0.0053 * (homePaim - guestPaim)) *_sp1.getHomeSP();
						pingE = (0.45 - 0.0053 * (homePaim - guestPaim)) * _sp1.getPingSP();
						guestE = (0.45 - 0.0053 * (guestPaim - homePaim)) * _sp1.getGuestSP();
						game.setPingE(pingE);
						game.setHomeE(homeE);
						game.setGuestE(guestE);
						
						System.out.println(game.getGameNumber());
						games.add(game);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
			
//			for(Game game:games){
//				
//			//System.out.println(code_Flag+"187:"+game.toString());
//			}
		}
		return games;
	}

	/**
	 * 组装URL的集合
	 */
	void SMT_URL() {
		Elements elements = doc.getElementById("selectissue").children();
		if (elements != null) {

			urls = new ArrayList<String>();

			for (Element element : elements) {
				urls.add(URL + "&issue=" + element.attr("value"));
			}

		}
	}


	String convertGameNumber(Date _date, int int_gameNumber) throws ParseException {

		//Date _date = str_date);
		Calendar c = Calendar.getInstance();
		c.setTime(_date);
		String _strYear = String.valueOf(c.get(Calendar.YEAR)).substring(2);
		String _strMoth = String.format("%2d", c.get(Calendar.MONTH) + 1).replace(" ", "0");
		String _strDay = null;
		System.out.println(c.get(Calendar.HOUR_OF_DAY));
		if(c.get(Calendar.HOUR_OF_DAY)>12){
			_strDay=String.format("%2d", (c.get(Calendar.DAY_OF_MONTH)+1)).replace(" ", "0");
			
		}
		else{
			_strDay=String.format("%2d", c.get(Calendar.DAY_OF_MONTH)).replace(" ", "0");
			
		}
	
		String _strGameNumber = String.format("%3d", int_gameNumber).replace(" ", "0");
		return _strYear + _strMoth + _strDay + _strGameNumber;
	}
	
	
	
	@Override
	public List<Game> refreshResult() {

		// TODO Auto-generated method stub
		System.out.println("this is refreshResult");
		// 读取dome
		try {
			doc = Jsoup.connect(URL).userAgent("Mozilla/5.0 (Windows NT 6.1; rv:23.0) Gecko/20100101 Firefox/23.0")
					.ignoreContentType(true).timeout(TIMEOUT).maxBodySize(0).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ReadUrls();
	}

	@Override
	public com.li.sssp.factory.BeanEnum.NetResults getCode() {
		// TODO Auto-generated method stub
		return com.li.sssp.factory.BeanEnum.NetResults.彩果;
	}

}
