package com.platform.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.platform.constants.SQLConstant;
import com.platform.domain.Question;
import com.platform.util.UUIDGenerator;
import com.platform.vo.QuestionVO;

/**
 * <p>程序名称：       UsersDAO.java</p>
 * <p>程序说明：       用户DAO</p>
 * <p>时间：          2012-12-12 16:36 </p>	
 * 
 * @author：          cheney.mydream
 * @version：         Ver 0.2
 */
public class QuestionDAO extends GenericDAO{

	private static final long serialVersionUID = -2510323844187127893L;
	private static QuestionDAO instance;
	private JdbcTemplate jdbcTemplate;

	public static QuestionDAO getInstance(JdbcTemplate jdbcTemplate) {
        if(instance == null) {
        	instance =  new QuestionDAO(jdbcTemplate);
        }
        return instance;
    }
	
	public QuestionDAO(JdbcTemplate jdbcTemplate){
		super(jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 保存小车
	 * @param question
	 * @return
	 */
	public int saveQuestion_Car(Question question){
		return jdbcTemplate.update(SQLConstant.QUESTION_CAR_SAVE, new Object[]{
				question.getCode(),
				question.getQuestion(),
				question.getAnswer_a(),
				question.getAnswer_b(),
				question.getAnswer_c(),
				question.getAnswer_d(),
				question.getAnswer(),
				question.getQuestion_img(),
				question.getQuestion_video(),
				question.getCategory(),
				new Date()
		});
	}
	
	/**
	 * 从小车题库获得指定数量的题目
	 * @return
	 */
	public List<QuestionVO> listQuestionRandom_Car(){
		return jdbcTemplate.query(SQLConstant.QUESTION_CAR_QUERY_RANDOM,new Object[]{100},new RowMapper<QuestionVO>(){
			@Override
			public QuestionVO mapRow(ResultSet rs, int arg1)
					throws SQLException {
				QuestionVO vo = new QuestionVO();
				vo.setId(rs.getInt("id"));
				vo.setAnswer(rs.getString("answer"));
				vo.setAnswer_a(rs.getString("answer_a"));
				vo.setAnswer_b(rs.getString("answer_b"));
				vo.setAnswer_c(rs.getString("answer_c"));
				vo.setAnswer_d(rs.getString("answer_d"));
				vo.setQuestion(rs.getString("question"));
				vo.setImage(rs.getString("question_img"));
				vo.setVideo(rs.getString("question_video"));
				return vo;
			}
		});
	}
	
	/**
	 * 从客车题库获得指定数量的题目
	 * @return
	 */
	public List<QuestionVO> listQuestionRandom_bus(){
		return jdbcTemplate.query(SQLConstant.QUESTION_BUS_QUERY_RANDOM,new Object[]{100},new RowMapper<QuestionVO>(){
			@Override
			public QuestionVO mapRow(ResultSet rs, int arg1)
					throws SQLException {
				QuestionVO vo = new QuestionVO();
				vo.setId(rs.getInt("id"));
				vo.setAnswer(rs.getString("answer"));
				vo.setAnswer_a(rs.getString("answer_a"));
				vo.setAnswer_b(rs.getString("answer_b"));
				vo.setAnswer_c(rs.getString("answer_c"));
				vo.setAnswer_d(rs.getString("answer_d"));
				vo.setQuestion(rs.getString("question"));
				vo.setImage(rs.getString("question_img"));
				vo.setVideo(rs.getString("question_video"));
				return vo;
			}
		});
	}
	
	/**
	 * 从大货车题库获得指定数量的题目
	 * @return
	 */
	public List<QuestionVO> listQuestionRandom_truck(){
		return jdbcTemplate.query(SQLConstant.QUESTION_TRUCK_QUERY_RANDOM,new Object[]{100},new RowMapper<QuestionVO>(){
			@Override
			public QuestionVO mapRow(ResultSet rs, int arg1)
					throws SQLException {
				QuestionVO vo = new QuestionVO();
				vo.setId(rs.getInt("id"));
				vo.setAnswer(rs.getString("answer"));
				vo.setAnswer_a(rs.getString("answer_a"));
				vo.setAnswer_b(rs.getString("answer_b"));
				vo.setAnswer_c(rs.getString("answer_c"));
				vo.setAnswer_d(rs.getString("answer_d"));
				vo.setQuestion(rs.getString("question"));
				vo.setImage(rs.getString("question_img"));
				vo.setVideo(rs.getString("question_video"));
				return vo;
			}
		});
	}
	
	/**
	 * 从大货车题库获得指定数量的题目
	 * @return
	 */
	public List<QuestionVO> listQuestionRandom_moto(){
		return jdbcTemplate.query(SQLConstant.QUESTION_MOTO_QUERY_RANDOM,new Object[]{100},new RowMapper<QuestionVO>(){
			@Override
			public QuestionVO mapRow(ResultSet rs, int arg1)
					throws SQLException {
				QuestionVO vo = new QuestionVO();
				vo.setId(rs.getInt("id"));
				vo.setAnswer(rs.getString("answer"));
				vo.setAnswer_a(rs.getString("answer_a"));
				vo.setAnswer_b(rs.getString("answer_b"));
				vo.setAnswer_c(rs.getString("answer_c"));
				vo.setAnswer_d(rs.getString("answer_d"));
				vo.setQuestion(rs.getString("question"));
				vo.setImage(rs.getString("question_img"));
				vo.setVideo(rs.getString("question_video"));
				return vo;
			}
		});
	}
	
	/**
	 * 保存考试成绩
	 * @param studentId
	 * @param score
	 * @param time
	 * @param cartype
	 * @return
	 */
	public int saveExamScore(String studentId,int score,String time,String cartype){
		return jdbcTemplate.update(SQLConstant.EXAMSCORE_SAVE, new Object[]{
				UUIDGenerator.generate(),
				studentId,
				score,
				time,
				cartype,
				new Date()
		});
	}
}
