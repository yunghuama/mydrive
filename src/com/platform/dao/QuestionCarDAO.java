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
public class QuestionCarDAO extends GenericDAO{

	private static final long serialVersionUID = -2510323844187127893L;
	private static QuestionCarDAO instance;
	private JdbcTemplate jdbcTemplate;

	public static QuestionCarDAO getInstance(JdbcTemplate jdbcTemplate) {
        if(instance == null) {
        	instance =  new QuestionCarDAO(jdbcTemplate);
        }
        return instance;
    }
	
	public QuestionCarDAO(JdbcTemplate jdbcTemplate){
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
		page.setList(list);
		return page;
	}
	
	public int queryForInt(String sql,Object args){
		return jdbcTemplate.queryForInt(sql,args);
	}
	
	
	/**
	 * 查询已标记题目
	 */
	public Page<QuestionVO> listMarkQuestionCar(Page page,String studentId,int type){
		List<QuestionVO> list = jdbcTemplate.query(SQLConstant.MARK_QUESTION_CAR_QUERY_PAGE, new Object[]{studentId,type,(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()}, new RowMapper<QuestionVO>(){
			@Override
			public QuestionVO mapRow(ResultSet rs, int arg1) throws SQLException {
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
		
		int rowCount = queryForInt(SQLConstant.MARK_QUESTION_QUERY_ROWCOUNT,new Object[]{studentId,type});
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		page.setList(list);
		return page;
	}
	
	/**
	 * 查询错题
	 */
	public Page<QuestionVO> listWrongQuestionCar(Page page,String studentId,int type){
		List<QuestionVO> list = jdbcTemplate.query(SQLConstant.WRONG_QUESTION_CAR_QUERY_PAGE, new Object[]{studentId,type,(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()}, new RowMapper<QuestionVO>(){
			@Override
			public QuestionVO mapRow(ResultSet rs, int arg1) throws SQLException {
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
		
		int rowCount = queryForInt(SQLConstant.WRONG_QUESTION_QUERY_ROWCOUNT,new Object[]{studentId,type});
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		page.setList(list);
		return page;
	}
}