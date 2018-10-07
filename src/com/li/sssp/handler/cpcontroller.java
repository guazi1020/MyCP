package com.li.sssp.handler;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.sssp.entites.PageGame;
import com.li.sssp.factory.BeanEnum.NetResults;
import com.li.sssp.factory.BeanEnum.UntilBean;
import com.li.sssp.factory.BeanFactory;
import com.li.sssp.server.GameServer;
import com.li.sssp.server.ResultServer;

@Controller
public class cpcontroller {

	@Autowired
	private GameServer gameServer;

	@Autowired
	private ResultServer resultServer;

	@RequestMapping(value = "index")
	public String index() {
		return "index";
	}

	@RequestMapping(value = "other")
	public String other() {
		return "other";
	}

	@RequestMapping(value="refreshResult",method=RequestMethod.GET)
	public void refreshResult(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		BeanFactory.getUntilBean(UntilBean.jsonOperation).renderData(response, resultServer.refreshResult().toString());
	}

	@RequestMapping(value = "term")
	public String term() throws JsonProcessingException {
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			mapper.writeValueAsString(gameServer.getAll()); // 输出json
//
//		} catch (JsonGenerationException e) {
//		}

		return "term";
	}

	@RequestMapping(value = "refreshSP", method = RequestMethod.GET)
	public void refreshSP(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {

		gameServer.getNetData(); // 先取当前数据

		BeanFactory.getUntilBean(UntilBean.jsonOperation).renderData(response, gameServer.refreshSP().toString()); // 以json的形式返回
	}

	@RequestMapping(value = "getNetData", method = RequestMethod.GET)
	public void ajaxForNetData(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException {

		String _sortColumn = null;

		Map map = request.getParameterMap();
		Set keSet = map.entrySet();
		for (Iterator itr = keSet.iterator(); itr.hasNext();) {
			Map.Entry me = (Map.Entry) itr.next();
			Object ok = me.getKey();
			Object ov = me.getValue();
			String[] value = new String[1];
			if (ov instanceof String[]) {
				value = (String[]) ov;
			} else {
				value[0] = ov.toString();
			}

			for (int k = 0; k < value.length; k++) {
				if (ok.toString().indexOf("sort") != -1) {
					_sortColumn = ok.toString().substring(5, ok.toString().length() - 1); // 列名
					System.out.println(value[k]);
				}
				System.out.println(ok + "=" + value[k]);
			}
		}

		String _searchPhrase = request.getParameter("searchPhrase");
		Boolean _isRefresh = false;
		if (request.getParameter("isRefresh").equals("true")) {
			_isRefresh = true;
		}
		System.out.println("controller 88 " + _isRefresh);
		System.out.println(!_isRefresh);
		//if(!_isRefresh){
		ObjectMapper mapper = new ObjectMapper();
		String str_netData = mapper.writeValueAsString(
				gameServer.getPgaeGame(new PageGame(1, 10, null, 0), _searchPhrase, _sortColumn, _isRefresh));

		BeanFactory.getUntilBean(UntilBean.jsonOperation).renderData(response, str_netData);
	//	}
	}

	@RequestMapping(value = "char")
	public String char_data() {
		//return "chardata";
		return "other";
	}

	@RequestMapping(value = "history")
	public String history() {
		return "history";
	}
	
	@RequestMapping(value="reacttest")
	public String reacttest(){
		return "react1213";
	}
	
	@RequestMapping(value = "getAjaxData", method = RequestMethod.GET)
	public void getAjaxData(HttpServletRequest request, HttpServletResponse response)
			throws JsonProcessingException, InterruptedException {
		Random rand = new Random();
		while (true){
			Thread.sleep(300);
			int i=rand.nextInt(100);
			if(i>20&&i<56){
				long responseTime=System.currentTimeMillis();
				String st="{'用时':}"+responseTime;
				BeanFactory.getUntilBean(UntilBean.jsonOperation).renderData(response, st);
				break;
			}else{
				Thread.sleep(1300);
			}
		}
	}
	
	
}
