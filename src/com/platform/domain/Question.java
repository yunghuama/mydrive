package com.platform.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class Question extends BaseDomain {

    private static final long serialVersionUID = -7094766838338441306L;
    
    private String code;//章节编码
	private String question;
	private String answer_a;
	private String answer_b ;
	private String answer_c ;
	private String answer_d ;
	private String answer;
	private String question_img;
	private String question_video;
	private String category ;
	private String tips;
	
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer_a() {
		return answer_a;
	}
	public void setAnswer_a(String answer_a) {
		this.answer_a = answer_a;
	}
	public String getAnswer_b() {
		return answer_b;
	}
	public void setAnswer_b(String answer_b) {
		this.answer_b = answer_b;
	}
	public String getAnswer_c() {
		return answer_c;
	}
	public void setAnswer_c(String answer_c) {
		this.answer_c = answer_c;
	}
	public String getAnswer_d() {
		return answer_d;
	}
	public void setAnswer_d(String answer_d) {
		this.answer_d = answer_d;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getQuestion_img() {
		return question_img;
	}
	public void setQuestion_img(String question_img) {
		this.question_img = question_img;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getQuestion_video() {
		return question_video;
	}
	public void setQuestion_video(String question_video) {
		this.question_video = question_video;
	}
}