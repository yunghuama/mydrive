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
	
	public static final String QUESTION_CAR_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_car where category = ? limit ?,?";
	public static final String QUESTION_CAR_QUERY_ROWCOUNT = "select count(id) from questions_car where category = ?";
	public static final String QUESTION_BUS_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_bus where category = ? limit ?,?";
	public static final String QUESTION_BUS_QUERY_ROWCOUNT = "select count(id) from questions_bus where category = ?";
	public static final String QUESTION_TRUCK_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_truck where category = ? limit ?,?";
	public static final String QUESTION_TRUCK_QUERY_ROWCOUNT = "select count(id) from questions_truck where category = ?";
	public static final String QUESTION_MOTO_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_moto where category = ? limit ?,?";
	public static final String QUESTION_MOTO_QUERY_ROWCOUNT = "select count(id) from questions_moto where category = ?";

	public static final String QUESTION_CAR_QUERY_IDS = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_car where id in (?)";
	
	/**
	 * 学员
	 */
	public static final String STUDENT_LOGIN = "select id,name,remindtimes,begindate,identity,cartype from studentcard where number = ? and password = ?";
	public static final String STUDENT_UPDATEINFO = "update studentcard set name=?,identity=?,age=?,sex=?,cartype=?,phonenumber=? where id=?";
	public static final String STUDENT_COUNTDOWN = "update studentcard set remindtimes = remindtimes-1 where id = ?";
	/**
	 * 学校
	 */
	public static final String SCHOOL_LOGIN = "select id from schoolcard where number = ? and password = ?";


	/**
	 * 考试成绩
	 */
	public static final String EXAMSCORE_SAVE = "insert into examscore(id,studentId,score,time,cartype,createtime) values(?,?,?,?,?,?)";

	
	/**
	 * 错题
	 */
	public static final String ERRORQUESTION_SAVE = "insert question_wrong(id,questionId,studentId,questiontype,updateTime) values(?,?,?,?,?)";
	public static final String WRONG_QUESTION_QUERY_ROWCOUNT = "select count(id) from question_wrong where studentid = ? and questiontype = ?";
	
	public static final String WRONG_QUESTION_CAR_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_car where id in (select questionid from question_wrong where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	public static final String WRONG_QUESTION_BUS_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_bus where id in (select questionid from question_wrong where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	public static final String WRONG_QUESTION_TRUCK_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_truck where id in (select questionid from question_wrong where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	public static final String WRONG_QUESTION_MOTO_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_moto where id in (select questionid from question_wrong where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	
	
	/**
	 * 章节
	 */
	public static final String SECTION_QUERY_BY_TYPE = "select * from section where category = ?";
	
	
	/**
	 * 标记题
	 */
	public static final String MARK_QUESTION_SAVE = "insert into markquestion(id,questionid,studentid,questiontype,createtime) values(?,?,?,?,?)";
	public static final String MARK_QUESTION_DEL = "delete from markquestion where questionid = ? and studentid = ? and questiontype = ?";
	public static final String MARK_QUESTION_ISEXISTS = "select count(id) from markquestion where questionid = ? and studentid = ? and questiontype = ?";
	public static final String MARK_QUESTION_QUERY_ROWCOUNT = "select count(id) from markquestion where studentid = ? and questiontype = ?";
	
	
	public static final String MARK_QUESTION_CAR_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_car where id in (select questionid from markquestion where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	public static final String MARK_QUESTION_BUS_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_bus where id in (select questionid from markquestion where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	public static final String MARK_QUESTION_TRUCK_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_truck where id in (select questionid from markquestion where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	public static final String MARK_QUESTION_MOTO_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_moto where id in (select questionid from markquestion where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	
	
	
}