package com.platform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public String formatSQL(String sql,String str,String prefix){
        if(sql!=null&&!"".equals(str)){
            sql = sql.replace(str,prefix+"_"+str);
        }
        return sql;
    }

	/**
	 * 保存小车
	 * @param question
	 * @return
	 */
	public int saveQuestion3(Question question){
		return jdbcTemplate.update(SQLConstant.QUESTION_3_SAVE, new Object[]{
				question.getCode(),
				question.getQuestion(),
				question.getAnswer_a(),
				question.getAnswer_b(),
				question.getAnswer_c(),
				question.getAnswer_d(),
				question.getAnswer(),
				question.getImage(),
				question.getVideo(),
				question.getCategory(),
				question.getTips(),
				new Date()
		});
	}
	
	/**
	 * 从小车题库获得指定数量的题目
	 * @return
	 */
	public List<QuestionVO> listQuestionRandom(){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.QUESTION3_QUERY_RANDOM,"questions3",type);
		return jdbcTemplate.query(sql,new Object[]{50},new RowMapper<QuestionVO>(){
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
	public Page<QuestionVO> listQuestionOrder(Page<QuestionVO> page,String category) throws Exception{
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.QUESTION3_QUERY_PAGE,"questions3",type);
        List list = new ArrayList();
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,category);
        ps.setInt(2, (page.getCurrPage()-1)*page.getPageSize());
        ps.setInt(3, page.getPageSize());
        ResultSet rs1 = ps.executeQuery();
        while(rs1.next()){
            QuestionVO vo = new QuestionVO();
            vo.setId(rs1.getInt("id"));
            vo.setAnswer(rs1.getString("answer"));
            vo.setAnswer_a(rs1.getString("answer_a"));
            vo.setAnswer_b(rs1.getString("answer_b"));
            vo.setAnswer_c(rs1.getString("answer_c"));
            vo.setAnswer_d(rs1.getString("answer_d"));
            vo.setQuestion(rs1.getString("question"));
            vo.setImage(rs1.getString("question_img"));
            vo.setVideo(rs1.getString("question_video"));
            vo.setTips(rs1.getString("tips"));
            list.add(vo);
        }
        ResultSet rs = ps.executeQuery(SQLConstant.SELECT_ROWCOUNTS);
        while(rs.next()){
            page.setRowCount(rs.getInt("counts"));
        }
        rs.close();
        rs1.close();
        ps.close();
        conn.close();
        page.setMaxPage(PageHelper.getMaxPage(page.getRowCount(), page.getPageSize()));
        page.setList(list);

		return page;
	}
	
	/**
	 * 从科目三题库根据类型分页获取题目
	 * @return
	 */
	public Page<QuestionVO> listQuestionAll(Page<QuestionVO> page,String type) throws Exception{

        String sql = formatSQL(SQLConstant.QUESTION3_QUERY_PAGE_ALL,"questions3",type);
        sql = formatSQL(sql,"section",type);
        List list = new ArrayList();
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, (page.getCurrPage()-1)*page.getPageSize());
        ps.setInt(2, page.getPageSize());
        ResultSet rs1 = ps.executeQuery();
        while(rs1.next()){
            QuestionVO vo = new QuestionVO();
            vo.setId(rs1.getInt("id"));
            vo.setAnswer(rs1.getString("answer"));
            vo.setAnswer_a(rs1.getString("answer_a"));
            vo.setAnswer_b(rs1.getString("answer_b"));
            vo.setAnswer_c(rs1.getString("answer_c"));
            vo.setAnswer_d(rs1.getString("answer_d"));
            vo.setQuestion(rs1.getString("question"));
            vo.setImage(rs1.getString("question_img"));
            vo.setVideo(rs1.getString("question_video"));
            vo.setTips(rs1.getString("tips"));
            vo.setCategory(rs1.getString("sname"));
            list.add(vo);
        }
        ResultSet rs = ps.executeQuery(SQLConstant.SELECT_ROWCOUNTS);
        while(rs.next()){
            page.setRowCount(rs.getInt("counts"));
        }
        rs.close();
        rs1.close();
        ps.close();
        conn.close();
        page.setMaxPage(PageHelper.getMaxPage(page.getRowCount(), page.getPageSize()));
        page.setList(list);
        return page;
	}
	
	public int queryForInt(String sql,Object args){
		return jdbcTemplate.queryForInt(sql,args);
	}
	
	/**
	 * 根据ID 查询问题
	 * @return
	 */
	public Question findQuestionById(int id){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.QUESTION3_QUERY_BY_ID,"questions3",type);
		List<Question> list =  jdbcTemplate.query(sql,new Object[]{id},new RowMapper<Question>(){
			@Override
			public Question mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Question vo = new Question();
				vo.setId(rs.getInt("id"));
				vo.setCode(rs.getString("code"));
				vo.setCode(rs.getString("code"));
				vo.setAnswer(rs.getString("answer"));
				vo.setAnswer_a(rs.getString("answer_a"));
				vo.setAnswer_b(rs.getString("answer_b"));
				vo.setAnswer_c(rs.getString("answer_c"));
				vo.setAnswer_d(rs.getString("answer_d"));
				vo.setQuestion(rs.getString("question"));
				vo.setImage(rs.getString("question_img"));
				vo.setVideo(rs.getString("question_video"));
				vo.setTips(rs.getString("tips"));
				vo.setCategory(rs.getString("category"));
				return vo;
			}
		});
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 根据ID 更新问题
	 * @return
	 */
	public int updateQuestion(Question question){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.QUESTION_BUS_UPDATE_BY_ID,"questions3",type);
		return jdbcTemplate.update(sql, new Object[]{
				question.getCode(),
				question.getQuestion(),
				question.getAnswer_a(),
				question.getAnswer_b(),
				question.getAnswer_c(),
				question.getAnswer_d(),
				question.getAnswer(),
				question.getImage(),
				question.getVideo(),
				question.getCategory(),
				question.getTips(),
				question.getId()
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
	public int saveExamScore(String studentId,int score,String time){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.EXAMSCORE3_SAVE,"examscore3",type);
		return jdbcTemplate.update(sql, new Object[]{
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
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.ERRORQUESTION3_SAVE,"question_wrong3",type);
		return jdbcTemplate.update(sql, new Object[]{
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
	public int saveMarkQuestion(int questionId,String studentId){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.MARK3_QUESTION_SAVE,"markquestion3",type);
		return jdbcTemplate.update(sql, new Object[]{
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
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.MARK3_QUESTION_ISEXISTS,"markquestion3",type);
		return jdbcTemplate.queryForInt(sql, new Object[]{
			questionId,
			studentId
		}) == 0 ? false : true;
	}
	
	/**
	 * 删除已标记题
	 */
	public int delMarkQuestion(int questionId,String studentId){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.MARK3_QUESTION_DEL,"markquestion3",type);
		return jdbcTemplate.update(sql, questionId,studentId);
	}
	
	/**
	 * 删除错题
	 */
	public int delWrongQuestion(int questionId,String studentId){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.WRONG_QUESTION3_DEL,"question_wrong3",type);
		return jdbcTemplate.update(sql, questionId,studentId);
	}
	/**
	 * 查询已标记题目
	 */
	public Page<QuestionVO> listMarkQuestion(Page page,String studentId) throws Exception{

        Users users  = LoginBean.getLoginBean().getUser();
        String questionType = users.getQuestionType();
        String sql = formatSQL(SQLConstant.MARK3_QUESTION_QUERY_PAGE,"questions3",questionType);
        sql =  formatSQL(sql,"markquestion3",questionType);
        List list = new ArrayList();
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentId);
        ps.setInt(2, (page.getCurrPage()-1)*page.getPageSize());
        ps.setInt(3, page.getPageSize());
        ResultSet rs1 = ps.executeQuery();
        while(rs1.next()){
            QuestionVO vo = new QuestionVO();
            vo.setId(rs1.getInt("id"));
            vo.setAnswer(rs1.getString("answer"));
            vo.setAnswer_a(rs1.getString("answer_a"));
            vo.setAnswer_b(rs1.getString("answer_b"));
            vo.setAnswer_c(rs1.getString("answer_c"));
            vo.setAnswer_d(rs1.getString("answer_d"));
            vo.setQuestion(rs1.getString("question"));
            vo.setImage(rs1.getString("question_img"));
            vo.setVideo(rs1.getString("question_video"));
            vo.setTips(rs1.getString("tips"));
            list.add(vo);
        }
        ResultSet rs = ps.executeQuery(SQLConstant.SELECT_ROWCOUNTS);
        while(rs.next()){
            page.setRowCount(rs.getInt("counts"));
        }
        rs.close();
        rs1.close();
        ps.close();
        conn.close();
        page.setMaxPage(PageHelper.getMaxPage(page.getRowCount(), page.getPageSize()));
        page.setList(list);

		return page;
	}
	
	/**
	 * 查询错题
	 */
	public Page<QuestionVO> listWrongQuestion(Page page,String studentId) throws Exception{

        Users users  = LoginBean.getLoginBean().getUser();
        String questionType = users.getQuestionType();
        String sql = formatSQL(SQLConstant.WRONG_QUESTION3_QUERY_PAGE,"questions3",questionType);
        sql =  formatSQL(sql,"question_wrong3",questionType);
        List list = new ArrayList();
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentId);
        ps.setInt(2, (page.getCurrPage()-1)*page.getPageSize());
        ps.setInt(3, page.getPageSize());
        ResultSet rs1 = ps.executeQuery();
        while(rs1.next()){
            QuestionVO vo = new QuestionVO();
            vo.setId(rs1.getInt("id"));
            vo.setAnswer(rs1.getString("answer"));
            vo.setAnswer_a(rs1.getString("answer_a"));
            vo.setAnswer_b(rs1.getString("answer_b"));
            vo.setAnswer_c(rs1.getString("answer_c"));
            vo.setAnswer_d(rs1.getString("answer_d"));
            vo.setQuestion(rs1.getString("question"));
            vo.setImage(rs1.getString("question_img"));
            vo.setVideo(rs1.getString("question_video"));
            vo.setTips(rs1.getString("tips"));
            list.add(vo);
        }
        ResultSet rs = ps.executeQuery(SQLConstant.SELECT_ROWCOUNTS);
        while(rs.next()){
            page.setRowCount(rs.getInt("counts"));
        }
        rs.close();
        rs1.close();
        ps.close();
        conn.close();
        page.setMaxPage(PageHelper.getMaxPage(page.getRowCount(), page.getPageSize()));
        page.setList(list);

		return page;
	}
	
	/**
	 * 查询分数汇总
	 * @param studentId
	 * @return
	 */
	public StatisticVO statistic(String studentId){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.STATISTISC_SCORE3,"examscore3",type);
		List<StatisticVO> list = jdbcTemplate.query(sql,new Object[]{studentId},new RowMapper<StatisticVO>(){
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
	public List<ScoreVO> getScores(String studentId){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.EXAMSCORE3_QUERY,"examscore3",type);
		return jdbcTemplate.query(sql,new Object[]{studentId},new RowMapper<ScoreVO>(){
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
