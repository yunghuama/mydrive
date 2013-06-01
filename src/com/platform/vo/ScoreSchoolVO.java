package com.platform.vo;

public class ScoreSchoolVO {

	private int maxscore;
	private int minscore;
	private int scorecounts;
	private int avgscore;
	private int passcount;
	private String name;
	private int rate;
	private String stuId;

	
	
	public String getStuId() {
		return stuId;
	}
	public void setStuId(String stuId) {
		this.stuId = stuId;
	}
	public int getRate() {
		double d = passcount*100/scorecounts;
		System.out.println(d);
		return (int)d;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
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
	
}
