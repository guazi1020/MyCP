package com.li.sssp.impl;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.li.sssp.entites.Game;
import com.li.sssp.factory.BeanEnum.BaseDataBean;
import com.li.sssp.inter.IBaseData;

@Component
public class BaseData implements IBaseData {

	@Override
	public List<Game> getAllGame() {
		//System.out.println("List<Game>");
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Game> getGamesByOneMonth(int month, Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseDataBean getCode() {
		// TODO Auto-generated method stub
		return BaseDataBean.数据库操作实现_One;
	}

	@Override
	public void Test() {
		// TODO Auto-generated method stub
		System.out.println("BaseData.Test()");
	}

}
