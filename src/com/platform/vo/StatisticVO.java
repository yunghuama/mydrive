package com.platform.vo;

public class StatisticVO {

	private int maxscore;
	private int minscore;
	private int scorecounts;
	private int avgscore;
	private int passcount;
	private String time;
	private String createtime;
	
	
	public int getMaxscore() {
		return maxscore;
	}
	public void setMaxscore(int maxscore) {
		this.maxscore = maxscore;
	}
	public int getMinscore() {
		return minscore;
	}
	public void setMinscore(int minscore) {
		this.minscore = minscore;
	}
	public int getScorecounts() {
		return scorecounts;
	}
	public void setScorecounts(int scorecounts) {
		this.scorecounts = scorecounts;
	}
	public int getAvgscore() {
		return avgscore;
	}
	public void setAvgscore(int avgscore) {
		this.avgscore = avgscore;
	}
	public int getPasscount() {
		return passcount;
	}
	public void setPasscount(int passcount) {
		this.passcount = passcount;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
}
