package com.li.sssp.inter;

import javax.servlet.http.HttpServletResponse;

import com.li.sssp.factory.BeanEnum.UntilBean;

public interface IJson {


		/**
		 * 定义json的输出
		 * @param response
		 * @param data
		 */
		public void renderData(HttpServletResponse response, String data);
		
		/**
		 * 确定枚举选项
		 * @return
		 */
		UntilBean getCode();
		
}
