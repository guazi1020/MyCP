package com.li.sssp.until;

import java.util.Comparator;

import org.springframework.stereotype.Component;

import com.li.sssp.entites.Game;
import com.li.sssp.factory.BeanEnum.UntilBean;

@Component
public class ComparatorGames implements Comparator<Game> {

	@Override
	public int compare(Game o1, Game o2) {
		return o1.getLeague().compareTo(o2.getLeague());
		
		// TODO Auto-generated method stub
		//return 0;
	}



}
