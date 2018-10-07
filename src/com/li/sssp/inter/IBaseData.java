package com.li.sssp.inter;

import java.awt.print.Pageable;
import java.util.List;

import com.li.sssp.entites.Game;

import com.li.sssp.factory.BeanEnum.BaseDataBean;

public interface IBaseData {

	BaseDataBean getCode();
	/**
	 * 获取所有的历史数据,慎用
	 * @return
	 */
	List<Game> getAllGame();
	
	/**
	 * 获取某一个月的全部数据-倒叙
	 * @param month 月份
	 * @param page 
	 * @return
	 */
	List<Game> getGamesByOneMonth(int month, Pageable page);
	
	
	void Test();
}
