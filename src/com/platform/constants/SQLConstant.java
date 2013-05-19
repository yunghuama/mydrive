package com.platform.constants;


/**
 * 数据库常量
 * 
 * @author MarkerKing
 *
 */
public final class SQLConstant {
	private SQLConstant(){}
	
	/**
	 * 题库
	 */
	public static final String QUESTION_CAR_SAVE = "insert into questions_car(code,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,category,createtime) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String QUESTION_CAR_QUERY_RANDOM = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_car order by rand() limit ?";
	public static final String QUESTION_BUS_QUERY_RANDOM = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_bus order by rand() limit ?";
	public static final String QUESTION_TRUCK_QUERY_RANDOM = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_truck order by rand() limit ?";
	public static final String QUESTION_MOTO_QUERY_RANDOM = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_motorcycle order by rand() limit ?";

	
	/**
	 * 学员
	 */
	public static final String STUDENT_LOGIN = "select id,name,remindtimes,begindate,identity,cartype from studentcard where number = ? and password = ?";
	public static final String STUDENT_UPDATEINFO = "update studentcard set name=?,identity=?,age=?,sex=?,cartype=?,phonenumber=? where id=?";
	
	/**
	 * 学校
	 */
	public static final String SCHOOL_LOGIN = "select id from schoolcard where number = ? and password = ?";


}