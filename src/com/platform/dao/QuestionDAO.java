package com.platform.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.platform.domain.QuestionTable;
import com.platform.domain.Users;
import com.platform.util.LoginBean;
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

    public String formatSQL(String sql,String str,String prefix){
        if(sql!=null&&!"".equals(str)){
            sql = sql.replace(str,prefix+"_"+str);
        }
        return sql;
    }

	/**
	 * 保存考试成绩
	 * @param studentId
	 * @param score
	 * @param time
	 * @param cartype
	 * @return
	 */
	public int saveExamScore(String studentId,int score,String time,int cartype){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.EXAMSCORE_SAVE,"examscore",type);
		return jdbcTemplate.update(sql, new Object[]{
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
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.ERRORQUESTION_SAVE,"question_wrong",type);
		return jdbcTemplate.update(sql, new Object[]{
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
        Users users  = LoginBean.getLoginBean().getUser();
        String questionType = users.getQuestionType();
        String sql = formatSQL(SQLConstant.SECTION_QUERY_BY_TYPE,"section",questionType);

		return jdbcTemplate.query(sql,new Object[]{type}, new RowMapper<Section>(){
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
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.MARK_QUESTION_SAVE,"markquestion",type);
		return jdbcTemplate.update(sql, new Object[]{
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
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.MARK_QUESTION_ISEXISTS,"markquestion",type);
		return jdbcTemplate.queryForInt(sql, new Object[]{
			questionId,
			studentId,
			questionType
		}) == 0 ? false : true;
	}
	
	/**
	 * 删除已标记题
	 */
	public int delMarkQuestion(int questionId,String studentId,int questionType){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.MARK_QUESTION_DEL,"markquestion",type);
		return jdbcTemplate.update(sql, questionId,studentId,questionType);
	}
	
	/**
	 * 删除错题
	 */
	public int delWrongQuestion(int questionId,String studentId,int questionType){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.WRONG_QUESTION_DEL,"question_wrong",type);
		return jdbcTemplate.update(sql, questionId,studentId,questionType);
	}
	
	/**
	 * 查询分数汇总
	 * @param studentId
	 * @return
	 */
	public StatisticVO statistic(String studentId,int carType){

        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.STATISTISC_SCORE,"examscore",type);

		List<StatisticVO> list = jdbcTemplate.query(sql,new Object[]{studentId,carType},new RowMapper<StatisticVO>(){
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
	public List<ScoreVO> getScores(String studentId,int cartype){

        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.EXAMSCORE_QUERY,"examscore",type);

		return jdbcTemplate.query(sql,new Object[]{studentId,cartype},new RowMapper<ScoreVO>(){
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

    public List<QuestionTable> questionTableList(){
        return jdbcTemplate.query(SQLConstant.QUESTIONTABLE_QUERY,new RowMapper<QuestionTable>() {
            @Override
            public QuestionTable mapRow(ResultSet rs, int i) throws SQLException {
                QuestionTable qt = new QuestionTable();
                qt.setName(rs.getString("tablename"));
                qt.setTitle(rs.getString("tabletitle"));
                return qt;
            }
        });
    }
}
