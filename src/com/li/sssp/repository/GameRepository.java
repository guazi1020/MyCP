package com.li.sssp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.li.sssp.entites.Game;


public interface GameRepository extends JpaRepository<Game, Integer> {

	
	/**
	 * 根据actor进行删除
	 * @param actor
	 * @return
	 */
	@Modifying
	@Query("delete from Game g where g.gameNumber=?1")
	Integer DelBygameNumber(int gameNumber);
	
	Game findBygameNumber(int gameNumber);
}
