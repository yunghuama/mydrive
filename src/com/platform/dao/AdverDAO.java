package com.platform.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.platform.constants.SQLConstant;
import com.platform.domain.Adver;
import com.platform.util.PageHelper;
import com.platform.util.UUIDGenerator;
import com.platform.vo.Page;

/**
 * <p>程序名称：       UsersDAO.java</p>
 * <p>程序说明：       用户DAO</p>
 * <p>时间：          2012-12-12 16:36 </p>	
 * 
 * @author：          cheney.mydream
 * @version：         Ver 0.2
 */
public class AdverDAO extends GenericDAO{

	private static final long serialVersionUID = -2510323844187127893L;
	private static AdverDAO instance;
	private JdbcTemplate jdbcTemplate;

	public static AdverDAO getInstance(JdbcTemplate jdbcTemplate) {
        if(instance == null) {
        	instance =  new AdverDAO(jdbcTemplate);
        }
        return instance;
    }
	
	public AdverDAO(JdbcTemplate jdbcTemplate){
		super(jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 学员登录
	 * @param number
	 * @param password
	 * @return
	 */
	public Page<Adver> pageAdver(Page page){
		List<Adver> list = jdbcTemplate.query(SQLConstant.ADVER_QUERY_PAGE, new Object[]{(page.getCurrPage()-1)*page.getPageSize(),page.getPageSize()},new RowMapper<Adver>(){
			@Override
			public Adver mapRow(ResultSet rs, int arg1) throws SQLException {
				Adver adver = new Adver();
				adver.setId(rs.getString("id"));
				adver.setPage(rs.getInt("page"));
				adver.setPosition(rs.getInt("position"));
				adver.setTime(rs.getInt("time"));
				adver.setUrl(rs.getString("url"));
				adver.setCreateTime(rs.getDate("createtime")+" "+rs.getTime("createtime"));
				return adver;
			}
		});
		int rowCount = queryForInt(SQLConstant.ADVER_COUNT_ROW);
		page.setRowCount(rowCount);
		page.setMaxPage(PageHelper.getMaxPage(rowCount, page.getPageSize()));
		page.setList(list);
		return page;
	}
	
	public int saveAdver(Adver adver){
		return jdbcTemplate.update(SQLConstant.ADVER_SAVE,new Object[]{
				UUIDGenerator.generate(),
				adver.getUrl(),
				adver.getPage(),
				adver.getPosition(),
				adver.getTime(),
				adver.getImage(),
				adver.getPathName(),
				new Date()
		});
	}
	
	public List<Adver>  listAdver(int page,int position){
		return jdbcTemplate.query(SQLConstant.ADVER_GET, new Object[]{page,position},new RowMapper<Adver>(){
			@Override
			public Adver mapRow(ResultSet rs, int arg1) throws SQLException {
				Adver adver = new Adver();
				adver.setId(rs.getString("id"));
				adver.setPage(rs.getInt("page"));
				adver.setPosition(rs.getInt("position"));
				adver.setTime(rs.getInt("time"));
				adver.setUrl(rs.getString("url"));
				adver.setCreateTime(rs.getDate("createtime")+" "+rs.getTime("createtime"));
				return adver;
			}
		});
	}
}
