package com.li.sssp.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.li.sssp.entites.Game;
import com.li.sssp.entites.PageGame;
import com.li.sssp.factory.BeanEnum.NetDataBean;
import com.li.sssp.inter.INetData;

@Component
public class NetData_Two implements INetData {

	@Override
	public NetDataBean getCode() {
		// TODO Auto-generated method stub
		return NetDataBean.网络数据操作实现_Two;
	}

	@Override
	public List<Game> GetAllData() {
		// TODO Auto-generated method stub
		System.out.println("this is NetData_Two’s GetAllData 方法");
		return null;
	}

	@Override
	public PageGame getAllPageGame(PageGame pageGame, String searchPhrase, String sortColumn, Boolean isrefresh) {
		// TODO Auto-generated method stub
		return null;
	}






}
