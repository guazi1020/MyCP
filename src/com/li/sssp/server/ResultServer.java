package com.li.sssp.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.li.sssp.entites.Game;
import com.li.sssp.factory.BeanEnum.NetResults;
import com.li.sssp.factory.BeanFactory;
import com.li.sssp.repository.GameRepository;

@Service
public class ResultServer {

	private List<Game> games;

	@Autowired
	private GameRepository gameRepository;

	// --------------------------私有函数begin------------------------------------------

	/**
	 * getResult
	 */
	void getResult() {
		games = BeanFactory.getNetResultBean(NetResults.彩果).refreshResult();
	}

	/**
	 * 根据gameNumber找出数据库中相关数据
	 * 
	 * @param gameNumber
	 * @return
	 */
	public Game findBygameNumber(int gameNumber) {
		return gameRepository.findBygameNumber(gameNumber);
	};

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Transactional
	Boolean delResultCurrent(Game game) {
		if (game != null) {
			gameRepository.delete(game);
		}
		return true;
	}

	/**
	 * 保存
	 * 
	 * @param game
	 */
	public void save(Game game) {
		//if (game.getSps().get(0).getResult() != null) {
			
			gameRepository.saveAndFlush(game);
		//}
	}

	// ----------------------------私有函数end---------------------------------------------------------
	public Boolean refreshResult() {
		// 获取彩果
		getResult();
		// System.out.println("ResutlServer 71:"+games.toString());

		// 根据彩果删除数据库中的数据
		for (Game game : games) {
			if (delResultCurrent(findBygameNumber(game.getGameNumber()))) {
				System.out.println("ResultServer 76:" + game.toString());
		
				save(game);
				}
		}
		// 保存

		return true;
	}

}
