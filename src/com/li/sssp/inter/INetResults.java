package com.li.sssp.inter;

import java.util.List;

import com.li.sssp.entites.Game;
import com.li.sssp.factory.BeanEnum.NetResults;

public interface INetResults {

	NetResults getCode();
	List<Game> refreshResult();
}
