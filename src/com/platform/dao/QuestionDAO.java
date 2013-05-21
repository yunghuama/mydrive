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
import com.platform.vo.ScoreVO;
import com.platform.vo.StatisticVO;

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
	
	/**
	 * 保存标记的题目
	 */
	public int saveMarkQuestion(int questionId,String studentId,int questionType){
		return jdbcTemplate.update(SQLConstant.MARK_QUESTION_SAVE, new Object[]{
			UUIDGenerator.generate(),
			questionId,
			studentId,
			questionType,
			new Date()
		});
	}
	
	/**
	 * 保存标记的题目
	 */
	public boolean hasMarkQuestion(int questionId,String studentId,int questionType){
		return jdbcTemplate.queryForInt(SQLConstant.MARK_QUESTION_ISEXISTS, new Object[]{
			questionId,
			studentId,
			questionType
		}) == 0 ? false : true;
	}
	
	/**
	 * 删除已标记题
	 */
	public int delMarkQuestion(int questionId,String studentId,int questionType){
		return jdbcTemplate.update(SQLConstant.MARK_QUESTION_DEL, questionId,studentId,questionType);
	}
	
	
	/**
	 * 查询分数汇总
	 * @param studentId
	 * @return
	 */
	public StatisticVO statistic(String studentId,String carType){
		List<StatisticVO> list = jdbcTemplate.query(SQLConstant.STATISTISC_SCORE,new Object[]{studentId,carType},new RowMapper<StatisticVO>(){
			@Override
			public StatisticVO mapRow(ResultSet rs, int arg1) throws SQLException {
				StatisticVO vo  = new StatisticVO();
				vo.setMaxscore(rs.getInt("maxscore"));
				vo.setMinscore(rs.getInt("minscore"));
				vo.setPasscount(rs.getInt("passcount"));
				vo.setAvgscore(rs.getInt("avgscore"));
				vo.setScorecounts(rs.getInt("scorecounts"));
				return vo;
			}
		});
		
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}
	
	/**
	 * 查询分数
	 * @param studentId
	 * @return
	 */
	public List<ScoreVO> getScores(String studentId,String cartype){
		return jdbcTemplate.query(SQLConstant.EXAMSCORE_QUERY,new Object[]{studentId,cartype},new RowMapper<ScoreVO>(){
			@Override
			public ScoreVO mapRow(ResultSet rs, int arg1) throws SQLException {
				ScoreVO vo  = new ScoreVO();
				vo.setScore(rs.getInt("score"));
				vo.setTime(rs.getString("time"));
				vo.setCreatetime(rs.getDate("createtime")+ " "+rs.getTime("createtime"));
				return vo;
			}
		});
	}
}
