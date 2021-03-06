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
import com.platform.util.PageHelper;
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
public class QuestionMotoDAO extends GenericDAO{

	private static final long serialVersionUID = -2510323844187127893L;
	private static QuestionMotoDAO instance;
	private JdbcTemplate jdbcTemplate;

	public static QuestionMotoDAO getInstance(JdbcTemplate jdbcTemplate) {
        if(instance == null) {
        	instance =  new QuestionMotoDAO(jdbcTemplate);
        }
        return instance;
    }
	
	public QuestionMotoDAO(JdbcTemplate jdbcTemplate){
		super(jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public int queryForInt(String sql,Object args){
		return jdbcTemplate.queryForInt(sql,args);
	}

    /**
     * 根据动态前缀组合表名
     * @param sql
     * @param str
     * @param prefix
     * @return
     */
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
	public int saveQuestion_Moto(Question question,String code){
        String sql = formatSQL(SQLConstant.QUESTION_MOTO_SAVE,"questions_motorcycle",code);
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
				new Date()
		});
	}
	
	/**
	 * 摩托车题库获得指定数量的题目
	 * @return
	 */
	public List<QuestionVO> listQuestionRandom_moto(){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.QUESTION_MOTO_QUERY_RANDOM,"questions_motorcycle",type);
		return jdbcTemplate.query(sql,new Object[]{100},new RowMapper<QuestionVO>(){
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
	 * 从摩托题库根据类型分页获取题目
	 * @return
	 */
	public Page<QuestionVO> listQuestionOrder_moto(Page<QuestionVO> page,String category) throws Exception{

        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.QUESTION_MOTO_QUERY_PAGE,"questions_motorcycle",type);
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
	 * 从摩托题库根据类型分页获取题目
	 * @return
	 */
	public Page<QuestionVO> listQuestionOrderAll_moto(Page<QuestionVO> page,String type) throws  Exception{

        String sql = formatSQL(SQLConstant.QUESTION_MOTO_QUERY_PAGE_ALL,"questions_motorcycle",type);
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
	
	/**
	 * 根据ID 查询问题
	 * @return
	 */
	public Question findQuestionById(int id,String type){
        String sql = formatSQL(SQLConstant.QUESTION_MOTO_QUERY_BY_ID,"questions_motorcycle",type);
		List<Question> list =  jdbcTemplate.query(sql,new Object[]{id},new RowMapper<Question>(){
			@Override
			public Question mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Question vo = new Question();
				vo.setId(rs.getInt("id"));
				vo.setAnswer(rs.getString("answer"));
				vo.setCode(rs.getString("code"));
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
	public int updateQuestion(Question question,String type){
        String sql = formatSQL(SQLConstant.QUESTION_MOTO_UPDATE_BY_ID,"questions_motorcycle",type);
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
	 * 查询已标记题目
	 */
	public Page<QuestionVO> listMarkQuestionMoto(Page page,String studentId,int type) throws Exception{

        Users users  = LoginBean.getLoginBean().getUser();
        String questionType = users.getQuestionType();
        String sql = formatSQL(SQLConstant.MARK_QUESTION_MOTO_QUERY_PAGE,"questions_motorcycle",questionType);
        sql =  formatSQL(sql,"markquestion",questionType);
        List list = new ArrayList();
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentId);
        ps.setInt(2,type);
        ps.setInt(3, (page.getCurrPage()-1)*page.getPageSize());
        ps.setInt(4, page.getPageSize());
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
	public Page<QuestionVO> listWrongQuestionMoto(Page page,String studentId,int type) throws Exception{

        Users users  = LoginBean.getLoginBean().getUser();
        String questionType = users.getQuestionType();
        String sql = formatSQL(SQLConstant.WRONG_QUESTION_MOTO_QUERY_PAGE,"questions_motorcycle",questionType);
        sql =  formatSQL(sql,"question_wrong",questionType);
        List list = new ArrayList();
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,studentId);
        ps.setInt(2,type);
        ps.setInt(3, (page.getCurrPage()-1)*page.getPageSize());
        ps.setInt(4, page.getPageSize());
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
}
