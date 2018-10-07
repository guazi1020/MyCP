package com.li.sssp.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.li.sssp.entites.Game;
import com.li.sssp.entites.PageGame;
import com.li.sssp.factory.BeanEnum.NetDataBean;
import com.li.sssp.factory.BeanFactory;
import com.li.sssp.repository.GameRepository;

@Service
public class GameServer {

	@Autowired
	private GameRepository gameRepository;


	private List<Game> games;

	private PageGame pageGame;


	/**
	 * 刷新存储未来比赛
	 * @return
	 */
	public Boolean refreshSP() {
		for (Game game : games) {
			delByGame(findBygameNumber(game.getGameNumber()));
			save(game);
		}

		return true;
	}

	/**
	 * 根据gameNumber查找相应的game
	 * 
	 * @param gameNumber
	 * @return
	 */
	public Game findBygameNumber(int gameNumber) {
		return gameRepository.findBygameNumber(gameNumber);
	};

	/**
	 * 保存
	 * 
	 * @param game
	 */
	public void save(Game game) {

		gameRepository.saveAndFlush(game);
	}

	/**
	 * 根据game删除
	 * 
	 * @param game
	 */
	@Transactional
	public void delByGame(Game game) {
		if (game != null) {
			gameRepository.delete(game);
		}
	}

	public List<Game> getAll() {
		return gameRepository.findAll();
	}

	/**
	 * 触发网络爬虫，输出JSON,用于显示
	 * 
	 * @return
	 * @throws JsonProcessingException
	 */
	public List<Game> getNetData() throws JsonProcessingException {

		games = BeanFactory.getNetDataBean(NetDataBean.网络数据操作实现_One).GetAllData();
		//
		return games;
	}

	/**
	 * 获取PageGame数据
	 * 
	 * @param pageGame
	 * @return
	 */
	public PageGame getPgaeGame(PageGame _pageGame, String _searchPhrase, String sortColumn, Boolean isRefresh) {

		// System.out.println("server 55:" + _searchPhrase);
		// System.out.println("server 56:" + sortColumn);
		// System.out.print("server 57" + isRefresh);

		pageGame = BeanFactory.getNetDataBean(NetDataBean.网络数据操作实现_One).getAllPageGame(_pageGame, _searchPhrase,
				sortColumn, isRefresh);

		return pageGame;
	}

}
