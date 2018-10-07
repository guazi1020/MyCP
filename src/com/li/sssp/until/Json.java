package com.li.sssp.until;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.li.sssp.factory.BeanEnum.UntilBean;
import com.li.sssp.inter.IJson;

@Component
public class Json implements IJson {

	@Override
	public void renderData(HttpServletResponse response, String data) {
		response.setContentType("text/html");
		response.setHeader("Cache-Control", "no-cache");
		response.setCharacterEncoding("UTF-8");

		// TODO Auto-generated method stub
		PrintWriter printWriter = null;
		try {
			printWriter = response.getWriter();
			printWriter.print(data);
		} catch (IOException ex) {

		} finally {
			if (null != printWriter) {
				printWriter.flush();
				printWriter.close();
			}
		}
	}
	
	public UntilBean getCode(){

		return UntilBean.jsonOperation;
	};

}
