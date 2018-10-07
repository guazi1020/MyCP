package com.li.sssp.inter;

import java.util.Date;
import java.util.List;

import com.li.sssp.entites.Game;
import com.li.sssp.entites.PageGame;
import com.li.sssp.factory.BeanEnum.NetDataBean;

public interface INetData {

	/**
	 * 确定枚举选项
	 * @return
	 */
	NetDataBean getCode();
	/**
	 * 获取当前所有比赛数据
	 * @return
	 */
	List<Game> GetAllData();
	
	/**
	 * 获取当前比赛页面数据
	 * @param pageGame
	 * @return
	 */
	PageGame getAllPageGame(PageGame pageGame,String searchPhrase,String sortColumn,Boolean isrefresh);

	

}
