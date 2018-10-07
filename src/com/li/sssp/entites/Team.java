package com.li.sssp.entites;

public class Team {

	private Integer t_number;	//	队伍序号
	private String t_name;	//	队名
	private String t_paiming;	//	现在排名
	public Integer getT_number() {
		return t_number;
	}
	public void setT_number(Integer t_number) {
		this.t_number = t_number;
	}
	public String getT_name() {
		return t_name;
	}
	public void setT_name(String t_name) {
		this.t_name = t_name;
	}
	public String getT_paiming() {
		return t_paiming;
	}
	public void setT_paiming(String t_paiming) {
		this.t_paiming = t_paiming;
	}
	public Team(Integer t_number, String t_name, String t_paiming) {
		super();
		this.t_number = t_number;
		this.t_name = t_name;
		this.t_paiming = t_paiming;
	}
	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Team [t_number=" + t_number + ", t_name=" + t_name + ", t_paiming=" + t_paiming + "]";
	}
	
	
	
	
}
