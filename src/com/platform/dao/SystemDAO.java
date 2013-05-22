package com.platform.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.platform.constants.SQLConstant;
import com.platform.domain.Announcement;
import com.platform.util.PageHelper;
import com.platform.util.UUIDGenerator;
import com.platform.vo.Page;
import com.platform.vo.ScoreSchoolVO;

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
	

	/**
	 * 保存驾校通知
	 * @param studentId
	 * @param score
	 * @param time
	 * @param cartype
	 * @return
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
	public Page<ScoreSchoolVO> listScore1(Page<ScoreSchoolVO> page,String schoolcard){
		List<ScoreSchoolVO> list =  jdbcTemplate.query(SQLConstant.STATISTISC_BY_SCHOOL_SCORE,new Object[]{schoolcard,(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()},new RowMapper<ScoreSchoolVO>(){
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
		int rowCount = queryForInt(SQLConstant.STATISTISC_BY_SCHOOL_ROWCOUNT,schoolcard);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		page.setList(list);
		return page;
	}
	
	/**
	 * 根据驾校查询成绩3
	 */
	public Page<ScoreSchoolVO> listScore3(Page<ScoreSchoolVO> page,String schoolcard){
		List<ScoreSchoolVO> list =  jdbcTemplate.query(SQLConstant.STATISTISC_BY_SCHOOL_SCORE3,new Object[]{schoolcard,(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()},new RowMapper<ScoreSchoolVO>(){
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
		int rowCount = queryForInt(SQLConstant.STATISTISC_BY_SCHOOL_ROWCOUNT3,schoolcard);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		page.setList(list);
		return page;
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
}
