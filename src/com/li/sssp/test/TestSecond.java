//package com.li.sssp.test;
//
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//
//import org.junit.Test;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.li.sssp.entites.Game;
//import com.li.sssp.factory.BeanEnum.BaseDataBean;
//import com.li.sssp.factory.BeanEnum.NetDataBean;
//import com.li.sssp.factory.BeanEnum.NetResults;
//import com.li.sssp.factory.BeanFactory;
//import com.li.sssp.server.DataServer;
//import com.li.sssp.server.GameServer;
//import com.li.sssp.server.ResultServer;
//
//public class TestSecond {
//
//	ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//
//	@Test
//	public void testBase() {
//		DataServer dss = (DataServer) ctx.getBean(DataServer.class);
//		//System.out.println(dss.getAll());
//		dss.InsertOne();
//	}
//
//	public void baseTest() {
//		BeanFactory.getBaseDataBean(BaseDataBean.数据库操作实现_One).getAllGame();
//	}
//
//	public void datetimetest() throws ParseException {
//		// System.out.println("bgein");
//		//
//		// Date _date= sdf.parse("2017-06-15 23:40");
//		// Calendar c1=Calendar.getInstance();
//		// c1.setTime(_date);
//		// int _month= c1.get(Calendar.MONTH)+1;
//		// int _day=c1.get(Calendar.DAY_OF_MONTH);
//		// int _year=c1.get(Calendar.YEAR);
//		//
//		// String str=String.format("%2d",_month).replace(" ", "0");
//		//
//		// System.out.println(str);
//		// System.out.println(String.valueOf(_year).substring(1)+String.valueOf(_month)+String.valueOf(_day));
//
//		System.out.println(convertGameNumber("2017-06-15 08:40", "1"));
//
//	}
//
//	String convertGameNumber(String str_date, String str_gameNumber) throws ParseException {
//
//		Date _date = sdf.parse(str_date);
//		Calendar c = Calendar.getInstance();
//		c.setTime(_date);
//		String _strYear = String.valueOf(c.get(Calendar.YEAR)).substring(2);
//		String _strMoth = String.format("%2d", c.get(Calendar.MONTH) + 1).replace(" ", "0");
//		String _strDay = null;
//		System.out.println(c.get(Calendar.HOUR_OF_DAY));
//		if (c.get(Calendar.HOUR_OF_DAY) > 12) {
//			_strDay = String.format("%2d", (c.get(Calendar.DAY_OF_MONTH) + 1)).replace(" ", "0");
//
//		} else {
//			_strDay = String.format("%2d", c.get(Calendar.DAY_OF_MONTH)).replace(" ", "0");
//
//		}
//
//		String _strGameNumber = String.format("%3d", Integer.parseInt(str_gameNumber)).replace(" ", "0");
//		return _strYear + _strMoth + _strDay + _strGameNumber;
//	}
//
//	public void resutl() {
//		// BeanFactory.getNetResultBean(NetResults.彩果).refreshResult();
//		ResultServer resultServer = (ResultServer) ctx.getBean(ResultServer.class);
//		resultServer.refreshResult();
//	}
//
//	public void netdataforServer() throws JsonProcessingException {
//		GameServer gameServer = (GameServer) ctx.getBean(GameServer.class);
//		// for (Game game : gameServer.getNetData()) {
//		//
//		// // gameServer.del(game.getGameNumber());
//		// // gameServer.delByGame(game);
//		// // System.out.println();
//		// gameServer.delByGame(gameServer.findBygameNumber(game.getGameNumber()));
//		// gameServer.save(game);
//		// }
//		gameServer.getNetData();
//		gameServer.refreshSP();
//	}
//
//	public void netdata() {
//		BeanFactory.getNetDataBean(NetDataBean.网络数据操作实现_One).GetAllData();
//		// System.out.println(BeanFactory.getNetDataBean(NetDataBean.网络数据操作实现_Two).GetAllData());
//	}
//
//	public void test() {
//
//		// BeanFactory.getGameInfo(GameBean.第一个).getTeamInfo(10);
//		// System.out.println( BeanFactory.class);
//		// GameFactory.getLeague("leagueInfo").getLeagueInfo();;
//		// System.out.println("hope");
//		// fail("Not yet implemented");
//	}
//
//}
