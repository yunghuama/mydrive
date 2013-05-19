package com.platform.domain;


public class Score extends BaseDomain {

    private static final long serialVersionUID = -7094766838338441306L;
    
	private int score;//分数
	private String time;//耗时
	private String errorQuestion;//错题
	
	public int  getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getErrorQuestion() {
		return errorQuestion;
	}
	public void setErrorQuestion(String errorQuestion) {
		this.errorQuestion = errorQuestion;
	}
}