package com.li.sssp.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.li.sssp.factory.BeanFactory;
import com.li.sssp.factory.BeanEnum.UntilBean;
import com.li.sssp.server.DataServer;

@Controller
public class historyController {

	@Autowired
	private DataServer dataServer;
	
	@RequestMapping(value="getAllData",method=RequestMethod.GET)
	public void getAllData(HttpServletRequest request, HttpServletResponse response) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		
		BeanFactory.getUntilBean(UntilBean.jsonOperation).renderData(response,mapper.writeValueAsString(dataServer.getAll()) );
	}

}
