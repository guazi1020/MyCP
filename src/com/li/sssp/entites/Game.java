package com.li.sssp.entites;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Game {

	private Integer id; // 主键
	private int gameNumber; // 比赛编号
	private Date gameData; // 比赛时间
	private String league; // 赛事名称
	private String homeName; // 主队名称
	private String homeRank; // 主队排名
	private String guestName; // 客队名称
	private String guestRank; // 客队排名
	private double homeE; // 主E
	private double pingE; // 平E
	private double guestE; // 客E
	private Integer homeIntegrate; // 主场六轮积分
	private Integer guestIntegrate; // 客场最近六轮积分
	private List<SP> sps;	//	spz值集合
	private int leagueNumber; 	// 赛事编号
	private String introduce;	//	简报
	private Date closeData;	//	关闭投注时间
	
	public Date getCloseData() {
		return closeData;
	}

	public void setCloseData(Date closeData) {
		this.closeData = closeData;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getLeagueNumber() {
		return leagueNumber;
	}

	public void setLeagueNumber(int leagueNumber) {
		this.leagueNumber = leagueNumber;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
  //  @Id @GeneratedValue(generator="system-uuid")  
 //   @GenericGenerator(name="system-uuid", strategy = "uuid") 
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getGameNumber() {
		return gameNumber;
	}

	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}

	public Date getGameData() {
		return gameData;
	}

	public void setGameData(Date gameData) {
		this.gameData = gameData;
	}

	public String getLeague() {
		return league;
	}

	public void setLeague(String league) {
		this.league = league;
	}

	public String getHomeName() {
		return homeName;
	}

	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}

	public String getHomeRank() {
		return homeRank;
	}

	public void setHomeRank(String homeRank) {
		this.homeRank = homeRank;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getGuestRank() {
		return guestRank;
	}

	public void setGuestRank(String guestRank) {
		this.guestRank = guestRank;
	}

	public double getHomeE() {
		return homeE;
	}

	public void setHomeE(double homeE) {
		this.homeE = homeE;
	}

	public double getPingE() {
		return pingE;
	}

	public void setPingE(double pingE) {
		this.pingE = pingE;
	}

	public double getGuestE() {
		return guestE;
	}

	public void setGuestE(double guestE) {
		this.guestE = guestE;
	}

	public Integer getHomeIntegrate() {
		return homeIntegrate;
	}

	public void setHomeIntegrate(Integer homeIntegrate) {
		this.homeIntegrate = homeIntegrate;
	}

	public Integer getGuestIntegrate() {
		return guestIntegrate;
	}

	public void setGuestIntegrate(Integer guestIntegrate) {
		this.guestIntegrate = guestIntegrate;
	}

	@OneToMany( cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name = "gameid")
	//@ManyToOne(fetch = FetchType.LAZY,cascade={CascadeType.REFRESH})
	public List<SP> getSps() {
		return sps;
	}

	public void setSps(List<SP> sps) {
		this.sps = sps;
	}

	public Game(int gameNumber, Date gameData, String league, String homeName, String homeRank, String guestName,
			String guestRank, double homeE, double pingE, double guestE, Integer homeIntegrate, Integer guestIntegrate,
			List<SP> sps,int leagueNumber,String introduce,Date closeData) {
		super();
		this.gameNumber = gameNumber;
		this.gameData = gameData;
		this.league = league;
		this.homeName = homeName;
		this.homeRank = homeRank;
		this.guestName = guestName;
		this.guestRank = guestRank;
		this.homeE = homeE;
		this.pingE = pingE;
		this.guestE = guestE;
		this.homeIntegrate = homeIntegrate;
		this.guestIntegrate = guestIntegrate;
		this.sps = sps;
		this.leagueNumber=leagueNumber;
		this.introduce=introduce;
		this.closeData=closeData;
	}

	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Game [id=" + id + ", gameNumber=" + gameNumber + ", gameData=" + gameData + ", league=" + league
				+ ", homeName=" + homeName + ", homeRank=" + homeRank + ", guestName=" + guestName + ", guestRank="
				+ guestRank + ", homeE=" + homeE + ", pingE=" + pingE + ", guestE=" + guestE + ", homeIntegrate="
				+ homeIntegrate + ", guestIntegrate=" + guestIntegrate + ", sps=" + sps + ", leagueNumber="
				+ leagueNumber + ", introduce=" + introduce + ", closeData=" + closeData + "]";
	}


	

}
