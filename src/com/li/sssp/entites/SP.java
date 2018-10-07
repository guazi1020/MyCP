package com.li.sssp.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="SP_Info")
public class SP {

	private Integer id;
	private int gameNumber;
	private Integer ball;
	private double homeSP;
	private double pingSP;
	private double guestSP;
	private Integer result;
	private String score;
	
//	@Id
    @Id
    @GeneratedValue
    //@GeneratedValue(generator="system-uuid")  
//@GenericGenerator(name="system-uuid", strategy = "uuid") 
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
	/**
	 * 获取让球
	 * @return
	 */
	public Integer getBall() {
		return ball;
	}
	public void setBall(Integer ball) {
		this.ball = ball;
	}
	public double getHomeSP() {
		return homeSP;
	}
	public void setHomeSP(double homeSP) {
		this.homeSP = homeSP;
	}
	public double getPingSP() {
		return pingSP;
	}
	public void setPingSP(double pingSP) {
		this.pingSP = pingSP;
	}
	public double getGuestSP() {
		return guestSP;
	}
	public void setGuestSP(double guestSP) {
		this.guestSP = guestSP;
	}
	public SP() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getResult() {
		return result;
	}
	public void setResult(Integer result) {
		this.result = result;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	@Override
	public String toString() {
		return "SP [id=" + id + ", gameNumber=" + gameNumber + ", ball=" + ball + ", homeSP=" + homeSP + ", pingSP="
				+ pingSP + ", guestSP=" + guestSP + ", result=" + result + ", score=" + score + "]";
	}
	public SP(Integer id, int gameNumber, Integer ball, double homeSP, double pingSP, double guestSP, Integer result,
			String score) {
		super();
		this.id = id;
		this.gameNumber = gameNumber;
		this.ball = ball;
		this.homeSP = homeSP;
		this.pingSP = pingSP;
		this.guestSP = guestSP;
		this.result = result;
		this.score = score;
	}


	
	
}
