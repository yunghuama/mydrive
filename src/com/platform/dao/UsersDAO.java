package com.platform.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.platform.constants.SQLConstant;
import com.platform.constants.StringConstant;
import com.platform.domain.Users;

/**
 * <p>程序名称：       UsersDAO.java</p>
 * <p>程序说明：       用户DAO</p>
 * <p>时间：          2012-12-12 16:36 </p>	
 * 
 * @author：          cheney.mydream
 * @version：         Ver 0.2
 */
public class UsersDAO extends GenericDAO{

	private static final long serialVersionUID = -2510323844187127893L;
	private static UsersDAO instance;
	private JdbcTemplate jdbcTemplate;

	public static UsersDAO getInstance(JdbcTemplate jdbcTemplate) {
        if(instance == null) {
        	instance =  new UsersDAO(jdbcTemplate);
        }
        return instance;
    }
	
	public UsersDAO(JdbcTemplate jdbcTemplate){
		super(jdbcTemplate);
		this.jdbcTemplate = jdbcTemplate;
	}
	
	/**
	 * 学员登录
	 * @param number
	 * @param password
	 * @return
	 */
	public Users login_student(String number,String password){
		List<Users> list = jdbcTemplate.query(SQLConstant.STUDENT_LOGIN, new Object[]{number,password},new RowMapper<Users>(){
			@Override
			public Users mapRow(ResultSet rs, int arg1) throws SQLException {
				Users user = new Users();
				user.setId(rs.getString("id"));
				user.setBegindate(rs.getDate("begindate")+"");
				user.setIdentity(rs.getString("identity"));
				user.setName(rs.getString("name"));
				user.setRemindtimes(rs.getInt("remindtimes"));
				user.setCartype(rs.getString("cartype"));
				user.SetRole(StringConstant.ROLE_STUDENT);
				user.setSchoolId(rs.getString("schoolid"));
				return user;
			}
		});
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 学校登录
	 * @param number
	 * @param password
	 * @return
	 */
	public Users login_school(String number,String password){
		List<Users> list = jdbcTemplate.query(SQLConstant.SCHOOL_LOGIN, new Object[]{number,password},new RowMapper<Users>(){
			@Override
			public Users mapRow(ResultSet rs, int arg1) throws SQLException {
				Users user = new Users();
				user.setId(rs.getString("id"));
				user.SetRole(StringConstant.ROLE_SCHOOL);
				return user;
			}
		});
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 学校登录
	 * @param number
	 * @param password
	 * @return
	 */
	public Users login_admin(String number,String password){
		List<Users> list = jdbcTemplate.query(SQLConstant.SCHOOL_LOGIN, new Object[]{number,password},new RowMapper<Users>(){
			@Override
			public Users mapRow(ResultSet rs, int arg1) throws SQLException {
				Users user = new Users();
				user.setId(rs.getString("id"));
				user.SetRole(StringConstant.ROLE_ADMIN);
				return user;
			}
		});
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 信息完善
	 * @param users
	 * @return
	 */
	public int updateInfo(Users users){
		return jdbcTemplate.update(SQLConstant.STUDENT_UPDATEINFO, new Object[]{
			users.getName(),
			users.getIdentity(),
			users.getAge(),
			users.getSex(),
			users.getCartype(),
			users.getPhonenumber(),
			users.getId()
		});
	}
}
