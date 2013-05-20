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
public class QuestionSubject3DAO extends GenericDAO{

	private static final long serialVersionUID = -2510323844187127893L;
	private static QuestionSubject3DAO instance;
	private JdbcTemplate jdbcTemplate;

	public static QuestionSubject3DAO getInstance(JdbcTemplate jdbcTemplate) {
        if(instance == null) {
        	instance =  new QuestionSubject3DAO(jdbcTemplate);
        }
        return instance;
    }
	
	public QuestionSubject3DAO(JdbcTemplate jdbcTemplate){
		super(jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	/**
	 * 从小车题库获得指定数量的题目
	 * @return
	 */
	public List<QuestionVO> listQuestionRandom(){
		return jdbcTemplate.query(SQLConstant.QUESTION3_QUERY_RANDOM,new Object[]{50},new RowMapper<QuestionVO>(){
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
	public Page<QuestionVO> listQuestionOrder(Page<QuestionVO> page,String category){
		List<QuestionVO> list =  jdbcTemplate.query(SQLConstant.QUESTION3_QUERY_PAGE,new Object[]{category,(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()},new RowMapper<QuestionVO>(){
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
		int rowCount = queryForInt(SQLConstant.QUESTION3_QUERY_ROWCOUNT,category);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		page.setList(list);
		return page;
	}
	
	public int queryForInt(String sql,Object args){
		return jdbcTemplate.queryForInt(sql,args);
	}
	
	/**
	 * 保存考试成绩
	 * @param studentId
	 * @param score
	 * @param time
	 * @param cartype
	 * @return
	 */
	public int saveExamScore(String studentId,int score,String time){
		return jdbcTemplate.update(SQLConstant.EXAMSCORE3_SAVE, new Object[]{
				UUIDGenerator.generate(),
				studentId,
				score,
				time,
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
	public int saveErrorQuestion(String questionId,String studentId){
		return jdbcTemplate.update(SQLConstant.ERRORQUESTION3_SAVE, new Object[]{
				UUIDGenerator.generate(),
				questionId,
				studentId,
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
	
	/**
	 * 保存标记的题目
	 */
	public int saveMarkQuestion(int questionId,String studentId){
		return jdbcTemplate.update(SQLConstant.MARK3_QUESTION_SAVE, new Object[]{
			UUIDGenerator.generate(),
			questionId,
			studentId,
			new Date()
		});
	}
	
	/**
	 * 保存标记的题目
	 */
	public boolean hasMarkQuestion(int questionId,String studentId){
		return jdbcTemplate.queryForInt(SQLConstant.MARK3_QUESTION_ISEXISTS, new Object[]{
			questionId,
			studentId
		}) == 0 ? false : true;
	}
	
	/**
	 * 删除已标记题
	 */
	public int delMarkQuestion(int questionId,String studentId){
		return jdbcTemplate.update(SQLConstant.MARK3_QUESTION_DEL, questionId,studentId);
	}
	
	
	/**
	 * 查询已标记题目
	 */
	public Page<QuestionVO> listMarkQuestion(Page page,String studentId){
		List<QuestionVO> list = jdbcTemplate.query(SQLConstant.MARK3_QUESTION_QUERY_PAGE, new Object[]{studentId,(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()}, new RowMapper<QuestionVO>(){
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
		
		int rowCount = queryForInt(SQLConstant.MARK3_QUESTION_QUERY_ROWCOUNT,new Object[]{studentId});
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		page.setList(list);
		return page;
	}
	
	/**
	 * 查询错题
	 */
	public Page<QuestionVO> listWrongQuestion(Page page,String studentId){
		List<QuestionVO> list = jdbcTemplate.query(SQLConstant.WRONG_QUESTION3_QUERY_PAGE, new Object[]{studentId,(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()}, new RowMapper<QuestionVO>(){
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
		
		int rowCount = queryForInt(SQLConstant.WRONG_QUESTION3_QUERY_ROWCOUNT,new Object[]{studentId});
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		page.setList(list);
		return page;
	}
}
