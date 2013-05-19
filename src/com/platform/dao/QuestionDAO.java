package com.platform.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.platform.constants.SQLConstant;
import com.platform.domain.Question;
import com.platform.domain.Section;
import com.platform.util.PageHelper;
import com.platform.util.UUIDGenerator;
import com.platform.vo.Page;
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
	 * 从小车题库根据类型分页获取题目
	 * @return
	 */
	public Page<QuestionVO> listQuestionOrder_car(Page<QuestionVO> page,String category){
		List<QuestionVO> list =  jdbcTemplate.query(SQLConstant.QUESTION_CAR_QUERY_PAGE,new Object[]{category,(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()},new RowMapper<QuestionVO>(){
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
		int rowCount = queryForInt(SQLConstant.QUESTION_CAR_QUERY_ROWCOUNT,category);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		
		return page;
	}
	
	public int queryForInt(String sql,Object args){
		return jdbcTemplate.queryForInt(sql,args);
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
	
	/**
	 * 保存错误的题目
	 * @param questionId
	 * @param studentId
	 * @param questiontype
	 * @return
	 */
	public int saveErrorQuestion(String questionId,String studentId,int questiontype){
		return jdbcTemplate.update(SQLConstant.ERRORQUESTION_SAVE, new Object[]{
				UUIDGenerator.generate(),
				questionId,
				studentId,
				questiontype,
				new Date()
		});
	}
	
	/**
	 * 使用次数减一
	 * @param id
	 * @return
	 */
	public int countDownOne(String id){
		return jdbcTemplate.update(SQLConstant.STUDENT_COUNTDOWN,id);
	}
	
	/**
	 * 获得章节
	 * @param type
	 * @return
	 */
	public List<Section> getSection(String type){
		return jdbcTemplate.query(SQLConstant.SECTION_QUERY_BY_TYPE,new Object[]{type}, new RowMapper<Section>(){
			@Override
			public Section mapRow(ResultSet rs, int arg1) throws SQLException {
				Section section = new Section();
				section.setId(rs.getString("id"));
				section.setName(rs.getString("name"));
				return section;
			}
		});
	}
}
