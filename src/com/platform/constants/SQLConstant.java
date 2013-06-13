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
	public static final String QUESTION_CAR_SAVE = "insert into questions_car(code,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,category,tips,createtime) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String QUESTION_BUS_SAVE = "insert into questions_bus(code,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,category,tips,createtime) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String QUESTION_TRUCK_SAVE = "insert into questions_truck(code,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,category,tips,createtime) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String QUESTION_MOTO_SAVE = "insert into questions_motorcycle(code,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,category,tips,createtime) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String QUESTION_3_SAVE = "insert into questions3(code,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,category,tips,createtime) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	
	
	
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
	 * 题库 科目一 根据ID 查询
	 */
	public static final String 	QUESTION_CAR_QUERY_BY_ID = "select * from questions_car where id = ?";
	public static final String 	QUESTION_BUS_QUERY_BY_ID = "select * from questions_bus where id = ?";
	public static final String 	QUESTION_TRUCK_QUERY_BY_ID = "select * from questions_truck where id = ?";
	public static final String 	QUESTION_MOTO_QUERY_BY_ID = "select * from questions_motorcycle where id = ?";
	
	/**
	 * 更新 科目一
	 */
	public static final String QUESTION_CAR_UPDATE_BY_ID = "update questions_car set code = ?,question = ?,answer_a = ?,answer_b = ?,answer_c = ?,answer_d = ?,answer = ?,question_img = ?,question_video = ?,category = ?,tips = ? where id = ?";
	public static final String QUESTION_BUS_UPDATE_BY_ID = "update questions_bus set code = ?,question = ?,answer_a = ?,answer_b = ?,answer_c = ?,answer_d = ?,answer = ?,question_img = ?,question_video = ?,category = ?,tips = ? where id = ?";
	public static final String QUESTION_TRUCK_UPDATE_BY_ID = "update questions_truck set code = ?,question = ?,answer_a = ?,answer_b = ?,answer_c = ?,answer_d = ?,answer = ?,question_img = ?,question_video = ?,category = ?,tips = ? where id = ?";
	public static final String QUESTION_MOTO_UPDATE_BY_ID = "update questions_motorcycle set code = ?,question = ?,answer_a = ?,answer_b = ?,answer_c = ?,answer_d = ?,answer = ?,question_img = ?,question_video = ?,category = ?,tips = ? where id = ?";
	
	/**
	 * 删除 科目一
	 */
	public static final String QUESTION_CAR_DELETE_BY_ID = "delete from questions_car where id = ?";
	public static final String QUESTION_BUS_DELETE_BY_ID = "delete from questions_bus where id = ?";
	public static final String QUESTION_TRUCK_DELETE_BY_ID = "delete from questions_truck where id = ?";
	public static final String QUESTION_MOTO_DELETE_BY_ID = "delete from questions_motorcycle where id = ?";
	/**
	 * 题库 科目三
	 */
	public static final String QUESTION3_QUERY_RANDOM = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video from questions3 order by rand() limit ?";
	public static final String QUESTION3_QUERY_PAGE = "select id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips from questions3 where category = ? limit ?,?";
	public static final String QUESTION3_QUERY_ROWCOUNT = "select count(id) from questions3 where category = ?";
	public static final String QUESTION3_QUERY_PAGE_ALL = "select c.id,question,answer_a,answer_b,answer_c,answer_d,answer,question_img,question_video,tips,s.name as sname from questions3 c left join section s on c.category = s.id order by c.id limit ?,?";
	public static final String QUESTION3_QUERY_ROWCOUNT_ALL = "select count(id) from questions3";
	public static final String QUESTION3_QUERY_BY_ID = "select * from questions3 where id = ?";
	public static final String QUESTION3_UPDATE_BY_ID = "update questions3 set code = ?,question = ?,answer_a = ?,answer_b = ?,answer_c = ?,answer_d = ?,answer = ?,question_img = ?,question_video = ?,category = ?,tips = ? where id = ?";
	public static final String QUESTION3_DELETE_BY_ID = "delete from questions3 where id = ?";
	
	/**
	 * 学员
	 */
	public static final String STUDENT_LOGIN = "select id,name,remindtimes,reminddays,begindate,identity,cartype,schoolid from studentcard where number = ? and password = ?";
	public static final String STUDENT_UPDATEINFO = "update studentcard set name=?,identity=?,age=?,sex=?,cartype=?,phonenumber=? where id=?";
	public static final String STUDENT_COUNTDOWN = "update studentcard set remindtimes = remindtimes-1 where id = ?";
	public static final String STUDENT_GET_BY_ID = "select stu.id,stu.name,identity,age,sex,cartype,phonenumber,sch.name as sname from studentcard stu left join schoolcard sch on stu.schoolid = sch.id  where stu.id = ?";
	public static final String STUDENT_UPDATE_PASS = "update studentcard set password = ? where id = ? and password = ?";
	public static final String STUDENT_UPDATE_TIME = "update studentcard set remindtimes = ?,begindate = ?,reminddays = ? where id = ?";
	public static final String STUDENT_TIME_GET = "select remindtimes,begindate,reminddays from studentcard where number = ? and password = ?";
	public static final String STUDENT_TIME0 = "update studentcard set remindtimes = 0 where number = ? and password = ?";
	public static final String STUDENT_TIMES_GET = "select remindtimes,reminddays,begindate from studentcard where id = ?";
	public static final String STUDENT_ACTIVETIME_UPDATE = "update studentcard set activetime = ?";
	
	/**
	 * 学校
	 */
	public static final String SCHOOL_LOGIN = "select id,number,name from schoolcard where number = ? and password = ?";
	public static final String SCHOOL_UPDATE_PASS = "update schoolcard set password = ? where id = ? and password = ?";
	
	/**
	 * 管理员
	 */
	public static final String ADMIN_LOGIN = "select id,number from driveradmin where number = ? and password = ?";
	public static final String ADMIN_UPDATE_PASS = "update driveradmin set password = ? where id = ? and password = ?";

	/**
	 * 考试成绩
	 */
	public static final String EXAMSCORE_SAVE = "insert into examscore(id,studentId,score,time,cartype,createtime) values(?,?,?,?,?,?)";
	public static final String STATISTISC_SCORE = "select max(score) as maxscore, min(score) as minscore, count(id) as scorecounts ,avg(score) as avgscore,sum(score>=90) as passcount from examscore where studentid = ? and cartype = ?";
	public static final String EXAMSCORE_QUERY = "select score,time,createtime from examscore where studentid = ? and cartype = ? order by createtime desc limit 0,5";
	public static final String STATISTISC_BY_SCHOOL_SCORE = "select max(exam.score) as maxscore, min(exam.score) as minscore, count(exam.id) as scorecounts ,avg(exam.score) as avgscore,sum(exam.score>=90) as passcount,stu.name,stu.id as stuId  from examscore exam , studentcard stu where exam.studentid = stu.id and stu.schoolid = ? and stu.name like ? group by exam.studentid  limit ?,?";
	public static final String STATISTISC_BY_SCHOOL_ROWCOUNT = "select count(exam.id) from examscore exam , studentcard stu where exam.studentid = stu.id and stu.schoolid = ?";
	public static final String EXAMSCORE_QUERY_ALL = "select score,time,createtime from examscore where studentid = ?  order by createtime desc";
	public static final String STATISTISC_BY_SCHOOL_STU_SCORE = "select max(exam.score) as maxscore, min(exam.score) as minscore, count(exam.id) as scorecounts ,avg(exam.score) as avgscore,sum(exam.score>=90) as passcount,stu.name,stu.id as stuId  from examscore exam , studentcard stu where stu.id=?  and exam.studentid = stu.id";
	public static final String STATISTISC_BY_SCHOOL_SCORE_ALL = "select max(exam.score) as maxscore, min(exam.score) as minscore, count(exam.id) as scorecounts ,avg(exam.score) as avgscore,sum(exam.score>=90) as passcount,stu.name,stu.id as stuId  from examscore exam , studentcard stu where exam.studentid = stu.id and stu.schoolid = ? and stu.name like ? group by exam.studentid";
	
	/**
	 * 考试成绩 科目三
	 */
	public static final String EXAMSCORE3_SAVE = "insert into examscore3(id,studentId,score,time,createtime) values(?,?,?,?,?)";
	public static final String STATISTISC_SCORE3 = "select max(score) as maxscore, min(score) as minscore, count(id) as scorecounts ,avg(score) as avgscore,sum(score>=90) as passcount from examscore3 where studentid = ?";
	public static final String EXAMSCORE3_QUERY = "select score,time,createtime from examscore3 where studentid = ? order by createtime desc limit 0,5";
	public static final String STATISTISC_BY_SCHOOL_SCORE3 = "select max(exam.score) as maxscore, min(exam.score) as minscore, count(exam.id) as scorecounts ,avg(exam.score) as avgscore,sum(exam.score>=90) as passcount,stu.name,stu.id as stuId  from examscore3 exam , studentcard stu where exam.studentid = stu.id and stu.schoolid = ? and stu.name like ? group by exam.studentid limit ?,?";
	public static final String STATISTISC_BY_SCHOOL_ROWCOUNT3 = "select count(exam.id) from examscore3 exam , studentcard stu where exam.studentid = stu.id and stu.schoolid = ?";
	public static final String EXAMSCORE3_QUERY_ALL = "select score,time,createtime from examscore3 where studentid = ? order by createtime desc";
	public static final String STATISTISC3_BY_SCHOOL_STU_SCORE = "select max(exam.score) as maxscore, min(exam.score) as minscore, count(exam.id) as scorecounts ,avg(exam.score) as avgscore,sum(exam.score>=90) as passcount,stu.name,stu.id as stuId  from examscore3 exam , studentcard stu where stu.id=?  and exam.studentid = stu.id";
	public static final String STATISTISC_BY_SCHOOL_SCORE3_ALL = "select max(exam.score) as maxscore, min(exam.score) as minscore, count(exam.id) as scorecounts ,avg(exam.score) as avgscore,sum(exam.score>=90) as passcount,stu.name,stu.id as stuId  from examscore3 exam , studentcard stu where exam.studentid = stu.id and stu.schoolid = ? and stu.name like ? group by exam.studentid";
	
	/**
	 * 错题
	 */
	public static final String ERRORQUESTION_SAVE = "insert question_wrong(id,questionId,studentId,questiontype,updateTime) values(?,?,?,?,?)";
	public static final String WRONG_QUESTION_QUERY_ROWCOUNT = "select count(id) from question_wrong where studentid = ? and questiontype = ?";
	public static final String WRONG_QUESTION_DEL = "delete from question_wrong where questionid = ? and studentid = ? and questiontype = ?";

	
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
	public static final String WRONG_QUESTION3_DEL = "delete from question_wrong3 where questionid = ? and studentid = ?";

	
	
	/**
	 * 章节科目一
	 */
	public static final String SECTION_QUERY_BY_TYPE = "select * from section where cartype = ? order by sortindex";
	
	
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
	
	
	/**
	 * 广告
	 */
	public static final String ADVER_QUERY_PAGE = "select * from advertisement order by createtime desc limit ?,?";
	public static final String ADVER_SAVE = "insert into advertisement(id,name,url,page,position,stime,image,pathname,createtime) values(?,?,?,?,?,?,?,?,?)";
	public static final String ADVER_DEL = "delete from advertisement where id = ?";
	public static final String ADVER_GET = "select * from advertisement where page = ? and position = ?";
	public static final String ADVER_COUNT_ROW = "select count(id) from advertisement";
	
	
	/**
	 * 用户反馈
	 */
	public static final String MESSAGE_SAVE = "insert into message(id,title,content,schoolcard,studentcard,createtime) values(?,?,?,?,?,?)";
	public static final String MESSAGE_QUERY_BY_STU = "select id,title,createtime from message where studentcard = ? limit ?,?";
	public static final String MESSAGE_QUERY_BY_SCH = "select m.id,title,m.createtime,s.number as sname from message m left join studentcard s on m.studentcard = s.id where m.schoolcard = ? limit ?,?";
	public static final String MESSAGE_DELETE = "delete from message where id = ?";
	public static final String MESSAGE_GET = "select m.id,title,m.createtime,s.number,m.content,s.number as sname from message m left join studentcard s on m.studentcard = s.id where m.id  = ?";
	public static final String MESSAGE_QUERY_BY_STU_ROWCOUNT = "select count(id) from message where studentcard = ?";
	public static final String MESSAGE_QUERY_BY_SCH_ROWCOUNT = "select count(id) from message where schoolcard = ?";
	
	
	/**
	 * 登录日志
	 */
	public static final String LOGINLOGS_SAVE = "insert into loginlogs(id,userid,createtime,createdate) values(?,?,?,?)";
	public static final String LOGINLOGS_PAGE_QUERY = "select createdate,count(id) as lcount from loginlogs where createdate >= ? and createdate<=? group by createdate order by createdate desc limit ?,?";
	public static final String LOGINLOGS_ROWCOUNTS = "select count(select count(id) from loginlogs where createdate >= ? and createdate<=?) from loginlogs";
}