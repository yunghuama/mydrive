package com.platform.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.platform.util.LoginBean;
import com.platform.vo.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.platform.constants.SQLConstant;
import com.platform.constants.StringConstant;
import com.platform.domain.Announcement;
import com.platform.domain.Message;
import com.platform.domain.Users;
import com.platform.util.PageHelper;
import com.platform.util.UUIDGenerator;

/**
 * <p>程序名称：       UsersDAO.java</p>
 * <p>程序说明：       用户DAO</p>
 * <p>时间：          2012-12-12 16:36 </p>	
 * 
 * @author：          cheney.mydream
 * @version：         Ver 0.2
 */
public class SystemDAO extends GenericDAO{

	private static final long serialVersionUID = -2510323844187127893L;
	private static SystemDAO instance;
	private JdbcTemplate jdbcTemplate;

	public static SystemDAO getInstance(JdbcTemplate jdbcTemplate) {
        if(instance == null) {
        	instance =  new SystemDAO(jdbcTemplate);
        }
        return instance;
    }
	
	public SystemDAO(JdbcTemplate jdbcTemplate){
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
	 * 保存驾校通知
	 */
	public int saveAnnouncement(String schoolCard,String title,String content){
		return jdbcTemplate.update(SQLConstant.ANNOUNCEMENT_SAVE, new Object[]{
				UUIDGenerator.generate(),
				title,
				content,
				schoolCard,
				new Date()
		});
	}
	
	/**
	 * 查询公告
	 * @param page
	 * @param schoolcard
	 * @return
	 */
	public Page<Announcement> listAnnouncement(Page<Announcement> page,String schoolcard){
		List<Announcement> list =  jdbcTemplate.query(SQLConstant.ANNOUNCEMENT_QUERY,new Object[]{schoolcard,(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()},new RowMapper<Announcement>(){
			@Override
			public Announcement mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Announcement vo = new Announcement();
				vo.setId(rs.getString("id"));
				vo.setContent(rs.getString("content"));
				vo.setTitle(rs.getString("title"));
				vo.setCreateTime(rs.getDate("createtime") +" "+rs.getTime("createtime"));
				return vo;
			}
		});
		int rowCount = queryForInt(SQLConstant.ANNOUNCEMENT_ROWCOUNT,schoolcard);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		page.setList(list);
		return page;
	}
	
	
	
	/**
	 * 根据驾校查询成绩1
	 */
	public Page<ScoreSchoolVO> listScore1(Page<ScoreSchoolVO> page,String schoolcard,String name) throws Exception{

        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.STATISTISC_BY_SCHOOL_SCORE,"examscore",type);
        List list = new ArrayList();
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,schoolcard);
        ps.setString(2,"%"+name+"%");
        ps.setInt(3, (page.getCurrPage()-1)*page.getPageSize());
        ps.setInt(4, page.getPageSize());
        ResultSet rs1 = ps.executeQuery();
        while(rs1.next()){
            ScoreSchoolVO vo = new ScoreSchoolVO();
            vo.setMaxscore(rs1.getInt("maxscore"));
            vo.setMinscore(rs1.getInt("minscore"));
            vo.setPasscount(rs1.getInt("passcount"));
            vo.setAvgscore(rs1.getInt("avgscore"));
            vo.setScorecounts(rs1.getInt("scorecounts"));
            vo.setStuId(rs1.getString("stuId"));
            vo.setName(rs1.getString("name"));
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
	 * 根据驾校查询成绩1所有
	 */
	public List<ScoreSchoolVO> listScore1All(String schoolcard,String name){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.STATISTISC_BY_SCHOOL_SCORE_ALL,"examscore",type);
		return  jdbcTemplate.query(sql,new Object[]{schoolcard,"%"+name+"%"},new RowMapper<ScoreSchoolVO>(){
			@Override
			public ScoreSchoolVO mapRow(ResultSet rs, int arg1)
					throws SQLException {
				ScoreSchoolVO vo = new ScoreSchoolVO();
				vo.setMaxscore(rs.getInt("maxscore"));
				vo.setMinscore(rs.getInt("minscore"));
				vo.setPasscount(rs.getInt("passcount"));
				vo.setAvgscore(rs.getInt("avgscore"));
				vo.setScorecounts(rs.getInt("scorecounts"));
				vo.setStuId(rs.getString("stuId"));
				vo.setName(rs.getString("name"));
				return vo;
			}
		});
	}
	
	/**
	 * 根据驾校查询成绩3所有
	 */
	public List<ScoreSchoolVO> listScore3All(String schoolcard,String name){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.STATISTISC_BY_SCHOOL_SCORE3_ALL,"examscore3",type);
		return  jdbcTemplate.query(sql,new Object[]{schoolcard,"%"+name+"%"},new RowMapper<ScoreSchoolVO>(){
			@Override
			public ScoreSchoolVO mapRow(ResultSet rs, int arg1)
					throws SQLException {
				ScoreSchoolVO vo = new ScoreSchoolVO();
				vo.setMaxscore(rs.getInt("maxscore"));
				vo.setMinscore(rs.getInt("minscore"));
				vo.setPasscount(rs.getInt("passcount"));
				vo.setAvgscore(rs.getInt("avgscore"));
				vo.setScorecounts(rs.getInt("scorecounts"));
				vo.setStuId(rs.getString("stuId"));
				vo.setName(rs.getString("name"));
				return vo;
			}
		});
	}
	
	/**
	 * 根学员统计查询
	 */
	public ScoreSchoolVO getStasticScore1(String stuId){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.STATISTISC_BY_SCHOOL_STU_SCORE,"examscore",type);
		List<ScoreSchoolVO> list =  jdbcTemplate.query(sql,new Object[]{stuId},new RowMapper<ScoreSchoolVO>(){
			@Override
			public ScoreSchoolVO mapRow(ResultSet rs, int arg1)
					throws SQLException {
				ScoreSchoolVO vo = new ScoreSchoolVO();
				vo.setMaxscore(rs.getInt("maxscore"));
				vo.setMinscore(rs.getInt("minscore"));
				vo.setPasscount(rs.getInt("passcount"));
				vo.setAvgscore(rs.getInt("avgscore"));
				vo.setScorecounts(rs.getInt("scorecounts"));
				vo.setName(rs.getString("name"));
				return vo;
			}
		});
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}
	
	public List<ScoreVO> getScores1(String studentId){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.EXAMSCORE_QUERY_ALL,"examscore",type);
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
	
	
	/**
	 * 根据驾校查询成绩3
	 */
	public Page<ScoreSchoolVO> listScore3(Page<ScoreSchoolVO> page,String schoolcard,String name) throws Exception{

        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.STATISTISC_BY_SCHOOL_SCORE3,"examscore3",type);
        List list = new ArrayList();
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,schoolcard);
        ps.setString(2,"%"+name+"%");
        ps.setInt(3, (page.getCurrPage()-1)*page.getPageSize());
        ps.setInt(4, page.getPageSize());
        ResultSet rs1 = ps.executeQuery();
        while(rs1.next()){
            ScoreSchoolVO vo = new ScoreSchoolVO();
            vo.setMaxscore(rs1.getInt("maxscore"));
            vo.setMinscore(rs1.getInt("minscore"));
            vo.setPasscount(rs1.getInt("passcount"));
            vo.setAvgscore(rs1.getInt("avgscore"));
            vo.setScorecounts(rs1.getInt("scorecounts"));
            vo.setStuId(rs1.getString("stuId"));
            vo.setName(rs1.getString("name"));
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
	 * 根学员统计查询
	 */
	public ScoreSchoolVO getStasticScore3(String stuId){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.STATISTISC3_BY_SCHOOL_STU_SCORE,"examscore3",type);
		List<ScoreSchoolVO> list =  jdbcTemplate.query(sql,new Object[]{stuId},new RowMapper<ScoreSchoolVO>(){
			@Override
			public ScoreSchoolVO mapRow(ResultSet rs, int arg1)
					throws SQLException {
				ScoreSchoolVO vo = new ScoreSchoolVO();
				vo.setMaxscore(rs.getInt("maxscore"));
				vo.setMinscore(rs.getInt("minscore"));
				vo.setPasscount(rs.getInt("passcount"));
				vo.setAvgscore(rs.getInt("avgscore"));
				vo.setScorecounts(rs.getInt("scorecounts"));
				vo.setName(rs.getString("name"));
				return vo;
			}
		});
		if(list!=null&&list.size()>0)
			return list.get(0);
		return null;
	}
	
	public List<ScoreVO> getScores3(String studentId){
        Users users  = LoginBean.getLoginBean().getUser();
        String type = users.getQuestionType();
        String sql = formatSQL(SQLConstant.EXAMSCORE3_QUERY_ALL,"examscore3",type);
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
	
	
	public Announcement getAnnouncementById(String id){
		List<Announcement> list = jdbcTemplate.query(SQLConstant.ANNOUNCEMENT_GET,new Object[]{id},new RowMapper<Announcement>(){
			@Override
			public Announcement mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Announcement vo = new Announcement();
				vo.setId(rs.getString("id"));
				vo.setContent(rs.getString("content"));
				vo.setTitle(rs.getString("title"));
				vo.setCreateTime(rs.getDate("createtime") +" "+rs.getTime("createtime"));
				return vo;
			}
		});
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/***
	 * 意见反馈
	 */
	public int saveMessage(Message message){
		return jdbcTemplate.update(SQLConstant.MESSAGE_SAVE, new Object[]{
				UUIDGenerator.generate(),
				message.getTitle(),
				message.getContent(),
				message.getSchoolcard(),
				message.getStudentCard(),
				new Date(),
				message.getType()
		});
	}
	
	/**
	 * 根据学员查询
	 * @param page
	 * @param studentId
	 * @return
	 */
	public Page<Message> listMessageByStu(Page page,String studentId){
		List<Message> list =  jdbcTemplate.query(SQLConstant.MESSAGE_QUERY_BY_STU,new Object[]{studentId,(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()},new RowMapper<Message>(){
			@Override
			public Message mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Message vo = new Message();
				vo.setId(rs.getString("id"));
				vo.setTitle(rs.getString("title"));
				vo.setCreateTime(rs.getDate("createtime") +" "+rs.getTime("createtime"));
				return vo;
			}
		});
		int rowCount = queryForInt(SQLConstant.MESSAGE_QUERY_BY_STU_ROWCOUNT,studentId);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		page.setList(list);
		return page;
	}
	
	
	/**
	 * 根据驾校查询
	 * @param page
	 * @param schoolId
	 * @return
	 */
	public Page<Message> listMessageBySch(Page page,String schoolId){
		List<Message> list =  jdbcTemplate.query(SQLConstant.MESSAGE_QUERY_BY_SCH,new Object[]{schoolId,(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()},new RowMapper<Message>(){
			@Override
			public Message mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Message vo = new Message();
				vo.setId(rs.getString("id"));
				vo.setTitle(rs.getString("title"));
				vo.setStudentCard(rs.getString("sname"));
				vo.setCreateTime(rs.getDate("createtime") +" "+rs.getTime("createtime"));
				return vo;
			}
		});
		int rowCount = queryForInt(SQLConstant.MESSAGE_QUERY_BY_SCH_ROWCOUNT,schoolId);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		page.setList(list);
		return page;
	}
	
	/**
	 * 根据驾校查询
	 * @param page
	 * @return
	 */
	public Page<Message> listMessageBySys(Page page){
		List<Message> list =  jdbcTemplate.query(SQLConstant.MESSAGE_QUERY_BY_SYS,new Object[]{"1",(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()},new RowMapper<Message>(){
			@Override
			public Message mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Message vo = new Message();
				vo.setId(rs.getString("id"));
				vo.setTitle(rs.getString("title"));
				vo.setStudentCard(rs.getString("sname"));
				vo.setCreateTime(rs.getDate("createtime") +" "+rs.getTime("createtime"));
				return vo;
			}
		});
		int rowCount = queryForInt(SQLConstant.MESSAGE_QUERY_BY_SCH_ROWCOUNT,"1");
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		page.setList(list);
		return page;
	}
	
	
	/**
	 * 保存登录日志
	 * @param id
	 * @return
	 */
	public int saveLoginLogs(String id){
		return jdbcTemplate.update(SQLConstant.LOGINLOGS_SAVE, new Object[]{
			UUIDGenerator.generate(),
			id,
			new Date(),
			new Date()
		});
	}
	
	/**
	 * 查询登录日志
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page listLoginLogs(String sDate, String eDate, Page page) throws Exception{
		List list = new ArrayList();
		Connection conn = jdbcTemplate.getDataSource().getConnection();
		PreparedStatement ps = conn.prepareStatement(SQLConstant.LOGINLOGS_PAGE_QUERY);
		ps.setString(1, sDate);
		ps.setString(2, eDate);
		ps.setInt(3, (page.getCurrPage()-1)*page.getPageSize());
		ps.setInt(4, page.getPageSize());
		ResultSet rs1 = ps.executeQuery();
		while(rs1.next()){
			LoginLogs ll = new LoginLogs();
			ll.setCounts(rs1.getInt("lcount"));
			ll.setDate(rs1.getDate("createdate")+"");
			list.add(ll);
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
	 * 查询激活用户
	 * @param id
	 * @return
	 */
	public Page listActiveCard(String sDate, String eDate, Page page)throws Exception{
		List list = new ArrayList();
		Connection conn = jdbcTemplate.getDataSource().getConnection();
		PreparedStatement ps = conn.prepareStatement(SQLConstant.USERSACTIVELOGS);
		ps.setString(1, sDate);
		ps.setString(2, eDate);
		ps.setInt(3, (page.getCurrPage()-1)*page.getPageSize());
		ps.setInt(4, page.getPageSize());
		ResultSet rs1 = ps.executeQuery();
		while(rs1.next()){
			LoginLogs ll = new LoginLogs();
			ll.setCounts(rs1.getInt("lcount"));
			ll.setDate(rs1.getDate("activedate")+"");
			list.add(ll);
		}
		ResultSet rs = ps.executeQuery(SQLConstant.SELECT_ROWCOUNTS);
		while(rs.next()){
			page.setRowCount(rs.getInt("counts"));
		}
		rs.close();
		rs1.close();
		ps.close();
		conn.close();
		//计算两个日期之间的天数
		page.setMaxPage(PageHelper.getMaxPage(page.getRowCount(), page.getPageSize()));
		page.setList(list);
		return page;
	}
	
	/**
	 * 查询激活
	 * @param id
	 * @return
	 */
	
	public List<Users> listActiveUsers(String sDate, String eDate)throws Exception{
		return jdbcTemplate.query(SQLConstant.USERSACTIVEEXPORT, new Object[]{sDate,eDate},new RowMapper<Users>(){
			@Override
			public Users mapRow(ResultSet rs, int arg1) throws SQLException {
				Users user = new Users();
				user.setActiveTime(rs.getDate("activetime")+" "+rs.getTime("activetime"));
				user.setNumber(rs.getString("number"));
				user.setName(rs.getString("name"));
				user.setSchoolId(rs.getString("schoolname"));
				return user;
			}
		});
	}
	
	public Message getMessage(String id){
		List<Message> list = jdbcTemplate.query(SQLConstant.MESSAGE_GET,new Object[]{id},new RowMapper<Message>(){
			@Override
			public Message mapRow(ResultSet rs, int arg1)
					throws SQLException {
				Message vo = new Message();
				vo.setId(rs.getString("id"));
				vo.setContent(rs.getString("content"));
				vo.setTitle(rs.getString("title"));
				vo.setStudentCard(rs.getString("sname"));
				vo.setCreateTime(rs.getDate("createtime") +" "+rs.getTime("createtime"));
				return vo;
			}
		});
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询驾校图片
	 */
	public String querySLogo(String id){
		return jdbcTemplate.queryForObject(SQLConstant.SCHOOL_LOGO_GET, String.class, id);
	}
	
	/**
	 * 更新驾校图片
	 */
	public int updateSLogo(String id,String logo){
		return jdbcTemplate.update(SQLConstant.SCHOOL_LOGO_UPDATE, new Object[]{
				logo,
				id
		});
	}

    /**
     * 查询所有驾校
     */
    public Page<SchoolVo> listSchool(Page page) throws Exception{
        List list = new ArrayList();
        Connection conn = jdbcTemplate.getDataSource().getConnection();
        PreparedStatement ps = conn.prepareStatement(SQLConstant.SCHOOL_QUERY);
        ps.setInt(1, (page.getCurrPage()-1)*page.getPageSize());
        ps.setInt(2, page.getPageSize());
        ResultSet rs1 = ps.executeQuery();
        while(rs1.next()){
            SchoolVo vo = new SchoolVo();
            vo.setNumber(rs1.getString("number"));
            vo.setName(rs1.getString("name"));
            vo.setAddress(rs1.getString("address"));
            vo.setTel(rs1.getString("tel"));
            vo.setQuestionType(rs1.getString("questiontype"));
            vo.setId(rs1.getString("id"));
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
        //计算两个日期之间的天数
        page.setMaxPage(PageHelper.getMaxPage(page.getRowCount(), page.getPageSize()));
        page.setList(list);
        return page;
    }

    public SchoolVo getSchoolById(String id){
         List<SchoolVo> list = jdbcTemplate.query(SQLConstant.SCHOOL_IDENTITY_GET,new Object[]{id},new RowMapper<SchoolVo>() {
             @Override
             public SchoolVo mapRow(ResultSet rs, int i) throws SQLException {
                 SchoolVo vo = new SchoolVo();
                 vo.setNumber(rs.getString("number"));
                 vo.setName(rs.getString("name"));
                 vo.setAddress(rs.getString("address"));
                 vo.setTel(rs.getString("tel"));
                 vo.setQuestionType(rs.getString("questiontype"));
                 vo.setId(rs.getString("id"));
                 return vo;
             }
         }
         ) ;
        if(list!=null&&list.size()>0){
           return list.get(0);
        }
        return null;
    }

    public int updateSchoolById(SchoolVo vo){
          return jdbcTemplate.update(SQLConstant.SCHOOL_UPDATE,new Object[]{
             vo.getName(),
             vo.getAddress(),
             vo.getTel(),
             vo.getQuestionType(),
             vo.getId()
          });
    }
}
