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
	 * 题库 科目一
	 */
	public static final String QUESTION_CAR_SAVE = "insert into questions_car(code,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,category,createtime) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String QUESTION_BUS_SAVE = "insert into questions_bus(code,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,category,createtime) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String QUESTION_TRUCK_SAVE = "insert into questions_truck(code,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,category,createtime) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String QUESTION_MOTO_SAVE = "insert into questions_motorcycle(code,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,category,createtime) values(?,?,?,?,?,?,?,?,?,?,?)";
	public static final String QUESTION_3_SAVE = "insert into questions3(code,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,category,createtime) values(?,?,?,?,?,?,?,?,?,?,?)";
	
	
	
	public static final String QUESTION_CAR_QUERY_RANDOM = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_car order by rand() limit ?";
	public static final String QUESTION_BUS_QUERY_RANDOM = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_bus order by rand() limit ?";
	public static final String QUESTION_TRUCK_QUERY_RANDOM = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_truck order by rand() limit ?";
	public static final String QUESTION_MOTO_QUERY_RANDOM = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions_motorcycle order by rand() limit ?";
	
	public static final String QUESTION_CAR_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions_car where category = ? limit ?,?";
	public static final String QUESTION_CAR_QUERY_ROWCOUNT = "select count(id) from questions_car where category = ?";
	public static final String QUESTION_BUS_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions_bus where category = ? limit ?,?";
	public static final String QUESTION_BUS_QUERY_ROWCOUNT = "select count(id) from questions_bus where category = ?";
	public static final String QUESTION_TRUCK_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions_truck where category = ? limit ?,?";
	public static final String QUESTION_TRUCK_QUERY_ROWCOUNT = "select count(id) from questions_truck where category = ?";
	public static final String QUESTION_MOTO_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions_motorcycle where category = ? limit ?,?";
	public static final String QUESTION_MOTO_QUERY_ROWCOUNT = "select count(id) from questions_motorcycle where category = ?";
	/**
	 * 题库 科目一 分页获取
	 */
	public static final String QUESTION_CAR_QUERY_PAGE_ALL = "select c.id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips,s.name as sname from questions_car c left join section s on c.category = s.id order by c.id limit ?,?";
	public static final String QUESTION_CAR_QUERY_ROWCOUNT_ALL = "select count(id) from questions_car";
	public static final String QUESTION_BUS_QUERY_PAGE_ALL= "select c.id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips,s.name as sname from questions_bus c left join section s on c.category = s.id order by c.id limit ?,?";
	public static final String QUESTION_BUS_QUERY_ROWCOUNT_ALL = "select count(id) from questions_bus";
	public static final String QUESTION_TRUCK_QUERY_PAGE_ALL = "select c.id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips,s.name as sname from questions_truck c left join section s on c.category = s.id order by c.id limit ?,?";
	public static final String QUESTION_TRUCK_QUERY_ROWCOUNT_ALL = "select count(id) from questions_truck";
	public static final String QUESTION_MOTO_QUERY_PAGE_ALL = "select c.id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips,s.name as sname from questions_motorcycle c left join section s on c.category = s.id order by c.id limit ?,?";
	public static final String QUESTION_MOTO_QUERY_ROWCOUNT_ALL = "select count(id) from questions_motorcycle";
	
	/**
	 * 题库 科目三
	 */
	public static final String QUESTION3_QUERY_RANDOM = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions3 order by rand() limit ?";
	public static final String QUESTION3_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions3 where category = ? limit ?,?";
	public static final String QUESTION3_QUERY_ROWCOUNT = "select count(id) from questions3 where category = ?";
	public static final String QUESTION3_QUERY_PAGE_ALL = "select c.id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips,s.name as sname from questions3 c left join section s on c.category = s.id order by c.id limit ?,?";
	public static final String QUESTION3_QUERY_ROWCOUNT_ALL = "select count(id) from questions3";

	
	
	/**
	 * 学员
	 */
	public static final String STUDENT_LOGIN = "select id,name,remindtimes,begindate,identity,cartype,schoolid from studentcard where number = ? and password = ?";
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
	public static final String STATISTISC_SCORE = "select max(score) as maxscore, min(score) as minscore, count(id) as scorecounts ,avg(score) as avgscore,sum(score>=90) as passcount from examscore where studentid = ? and cartype = ?";
	public static final String EXAMSCORE_QUERY = "select score,time,createtime from examscore where studentid = ? and cartype = ? order by createtime desc limit 0,5";
	public static final String STATISTISC_BY_SCHOOL_SCORE = "select max(exam.score) as maxscore, min(exam.score) as minscore, count(exam.id) as scorecounts ,avg(exam.score) as avgscore,sum(exam.score>=90) as passcount,stu.name  from examscore exam , studentcard stu where exam.studentid = stu.id and stu.schoolid = ?  limit ?,?";
	public static final String STATISTISC_BY_SCHOOL_ROWCOUNT = "select count(exam.id) from examscore exam , studentcard stu where exam.studentid = stu.id and stu.schoolid = ?";
	/**
	 * 考试成绩 科目三
	 */
	public static final String EXAMSCORE3_SAVE = "insert into examscore3(id,studentId,score,time,createtime) values(?,?,?,?,?)";
	public static final String STATISTISC_SCORE3 = "select max(score) as maxscore, min(score) as minscore, count(id) as scorecounts ,avg(score) as avgscore,sum(score>=90) as passcount from examscore3 where studentid = ?";
	public static final String EXAMSCORE3_QUERY = "select score,time,createtime from examscore3 where studentid = ? order by createtime desc limit 0,5";
	public static final String STATISTISC_BY_SCHOOL_SCORE3 = "select max(exam.score) as maxscore, min(exam.score) as minscore, count(exam.id) as scorecounts ,avg(exam.score) as avgscore,sum(exam.score>=90) as passcount,stu.name  from examscore3 exam , studentcard stu where exam.studentid = stu.id and stu.schoolid = ? limit ?,?";
	public static final String STATISTISC_BY_SCHOOL_ROWCOUNT3 = "select count(exam.id) from examscore3 exam , studentcard stu where exam.studentid = stu.id and stu.schoolid = ?";
	
	/**
	 * 错题
	 */
	public static final String ERRORQUESTION_SAVE = "insert question_wrong(id,questionId,studentId,questiontype,updateTime) values(?,?,?,?,?)";
	public static final String WRONG_QUESTION_QUERY_ROWCOUNT = "select count(id) from question_wrong where studentid = ? and questiontype = ?";
	
	public static final String WRONG_QUESTION_CAR_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions_car where id in (select questionid from question_wrong where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	public static final String WRONG_QUESTION_BUS_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions_bus where id in (select questionid from question_wrong where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	public static final String WRONG_QUESTION_TRUCK_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions_truck where id in (select questionid from question_wrong where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	public static final String WRONG_QUESTION_MOTO_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions_motorcycle where id in (select questionid from question_wrong where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	
	/**
	 * 错题 科目三
	 */
	public static final String ERRORQUESTION3_SAVE = "insert question_wrong3(id,questionId,studentId,updateTime) values(?,?,?,?)";
	public static final String WRONG_QUESTION3_QUERY_ROWCOUNT = "select count(id) from question_wrong3 where studentid = ?";
	public static final String WRONG_QUESTION3_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions3 where id in (select questionid from question_wrong3 where studentid = ?  order by createtime desc ) limit ?,?";	

	
	
	/**
	 * 章节科目一
	 */
	public static final String SECTION_QUERY_BY_TYPE = "select * from section where category = ?";
	
	
	/**
	 * 标记题
	 */
	public static final String MARK_QUESTION_SAVE = "insert into markquestion(id,questionid,studentid,questiontype,createtime) values(?,?,?,?,?)";
	public static final String MARK_QUESTION_DEL = "delete from markquestion where questionid = ? and studentid = ? and questiontype = ?";
	public static final String MARK_QUESTION_ISEXISTS = "select count(id) from markquestion where questionid = ? and studentid = ? and questiontype = ?";
	public static final String MARK_QUESTION_QUERY_ROWCOUNT = "select count(id) from markquestion where studentid = ? and questiontype = ?";

	
	public static final String MARK_QUESTION_CAR_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions_car where id in (select questionid from markquestion where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	public static final String MARK_QUESTION_BUS_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions_bus where id in (select questionid from markquestion where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	public static final String MARK_QUESTION_TRUCK_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions_truck where id in (select questionid from markquestion where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	public static final String MARK_QUESTION_MOTO_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions_motorcycle where id in (select questionid from markquestion where studentid = ? and questiontype = ? order by createtime desc ) limit ?,?";	
	
	/**
	 * 标记题科目三
	 */
	public static final String MARK3_QUESTION_SAVE = "insert into markquestion3(id,questionid,studentid,createtime) values(?,?,?,?)";
	public static final String MARK3_QUESTION_DEL = "delete from markquestion3 where questionid = ? and studentid = ?";
	public static final String MARK3_QUESTION_ISEXISTS = "select count(id) from markquestion3 where questionid = ? and studentid = ?";
	public static final String MARK3_QUESTION_QUERY_ROWCOUNT = "select count(id) from markquestion3 where studentid = ?";

	public static final String MARK3_QUESTION_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions3 where id in (select questionid from markquestion3 where studentid = ?  order by createtime desc ) limit ?,?";
	
	
	/**
	 * 驾校公告
	 */
	public static final String ANNOUNCEMENT_SAVE = "insert into announcement(id,title,content,schoolcard,createtime) values(?,?,?,?,?)";
	public static final String ANNOUNCEMENT_DEL =  "delete from announcement where id = ?";
	public static final String ANNOUNCEMENT_QUERY = "select * from announcement where schoolcard = ? order by createtime desc limit ?,?";
	public static final String ANNOUNCEMENT_ROWCOUNT = "select count(id) from announcement where schoolcard = ?";
	public static final String ANNOUNCEMENT_GET = "select * from announcement where id = ?";
}