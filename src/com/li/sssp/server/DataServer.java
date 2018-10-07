package com.li.sssp.server;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.li.sssp.entites.GameNow;
import com.li.sssp.entites.Game;
import com.li.sssp.entites.PageGame;
import com.li.sssp.repository.EmployeeRespository;
import com.li.sssp.repository.GameRepository;

@Service
public class DataServer {

	@Autowired
	private GameRepository gameRepository;


	
	private List<Game> games;
	private PageGame pgames;
	
	@Autowired
	private EmployeeRespository employeeRepository;
	private GameNow employee;
	/**
	 * 返回所有数据库中的game，用于测试
	 * @return
	 */
	public PageGame getAll() {
		games=gameRepository.findAll();
		pgames=new PageGame(1,10,games,games.size());
		return pgames;
	}
	
	public void InsertOne(){
		employee=new GameNow("li");
		
		List<GameNow> employees=new ArrayList<>();
		employees.add(employee);
		employees.add(new GameNow("w"));
		employeeRepository.save(employees);
		employeeRepository.flush();
		//employeeRepository.saveAndFlush(employee);
	}

}
