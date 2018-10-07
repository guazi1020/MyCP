package com.li.sssp.entites;

import java.util.List;

public class PageGame {

	private int current;	//	当前页数
	private int rowCound;	//	每页的行数
	private List<Game> rows;
	private int total;	//	总行数
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getRowCound() {
		return rowCound;
	}
	public void setRowCound(int rowCound) {
		this.rowCound = rowCound;
	}
	public List<Game> getRows() {
		return rows;
	}
	public void setRows(List<Game> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * 
	 * @param current 	当前页数
	 * @param rowCound 	每页行数
	 * @param rows	内容
	 * @param total 总共页数
	 */
	public PageGame(int current, int rowCound, List<Game> rows, int total) {
		super();
		this.current = current;
		this.rowCound = rowCound;
		this.rows = rows;
		this.total = total;
	}
	@Override
	public String toString() {
		return "PageGame [current=" + current + ", rowCound=" + rowCound + ", rows=" + rows + ", total=" + total + "]";
	}
	
	

	

	
}
