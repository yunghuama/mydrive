package com.platform.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.platform.vo.StudentVo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.platform.constants.SQLConstant;
import com.platform.constants.StringConstant;
import com.platform.domain.Users;
import com.platform.vo.SchoolVo;

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
				user.setReminddays(rs.getInt("reminddays"));
                user.setQuestionType(rs.getString("questiontype"));
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
				user.setName(rs.getString("name"));
				return user;
			}
		});
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 管理员
	 * @param number
	 * @param password
	 * @return
	 */
	public Users login_admin(String number,String password){
		List<Users> list = jdbcTemplate.query(SQLConstant.ADMIN_LOGIN, new Object[]{number,password},new RowMapper<Users>(){
			@Override
			public Users mapRow(ResultSet rs, int arg1) throws SQLException {
				Users user = new Users();
				user.setId(rs.getString("id"));
				user.SetRole(StringConstant.ROLE_ADMIN);
				user.setName(rs.getString("number"));
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
	
	/**
	 * 更新激活时间
	 */
	public int updateActiveTime(String id){
		return jdbcTemplate.update(SQLConstant.STUDENT_ACTIVETIME_UPDATE, new Date(),new Date(),id);
	}
	
	/**
	 * 更新密码学员
	 */
	public int updatePassStu(String id,String oldPass,String newPass){
		return jdbcTemplate.update(SQLConstant.STUDENT_UPDATE_PASS,new Object[]{
			newPass,
			id,
			oldPass
		});
	}
	
	/**
	 * 更新密码驾校
	 */
	public int updatePassSch(String id,String oldPass,String newPass){
		return jdbcTemplate.update(SQLConstant.SCHOOL_UPDATE_PASS,new Object[]{
			newPass,
			id,
			oldPass
		});
	}
	
	/**
	 * 更新密码管理员
	 */
	public int updatePassAdmin(String id,String oldPass,String newPass){
		return jdbcTemplate.update(SQLConstant.ADMIN_UPDATE_PASS,new Object[]{
			newPass,
			id,
			oldPass
		});
	}
	
	
	/**
	 * 续费
	 */
	public int updateTime(String id,int remindtimes,int reminddays,String begindate){
		return jdbcTemplate.update(SQLConstant.STUDENT_UPDATE_TIME,new Object[]{
				remindtimes,
				begindate,
				reminddays,
				id
		});
	}
	
	public Users getTimes(String number,String password){
		List<Users> list = jdbcTemplate.query(SQLConstant.STUDENT_TIME_GET, new Object[]{number,password},new RowMapper<Users>(){
			@Override
			public Users mapRow(ResultSet rs, int arg1) throws SQLException {
				Users user = new Users();
				user.setReminddays(rs.getInt("reminddays"));
				user.setRemindtimes(rs.getInt("remindtimes"));
				user.setBegindate(rs.getDate("begindate")+" "+rs.getTime("begindate"));
				return user;
			}
		});
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 销毁学生卡
	 */
	public int updateTo0(String number,String password){
		return jdbcTemplate.update(SQLConstant.STUDENT_TIME0, new Object[]{
				number,
				password
		});
	}
	
	/**
	 * 获得个人信息
	 * @param id
	 * @return
	 */
	public Users getUsersById(String id){
		List<Users> list =  jdbcTemplate.query(SQLConstant.STUDENT_GET_BY_ID,new Object[]{id}, new RowMapper<Users>(){
			@Override
			public Users mapRow(ResultSet rs, int arg1) throws SQLException {
				Users user = new Users();
				user.setId(rs.getString("id"));
				user.setAge(rs.getInt("age"));
				user.setSex(rs.getString("sex"));
				user.setName(rs.getString("name"));
				user.setCartype(rs.getString("cartype"));
				user.setIdentity(rs.getString("identity"));
				user.setPhonenumber(rs.getString("phonenumber"));
				user.setSchoolId(rs.getString("sname"));
				return user;
			}
		});
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	/**
	 * 查询SCHOOL 信息
	 * @param id
	 * @return
	 */
	public SchoolVo getSchoolById(String id){
		List<SchoolVo> list =  jdbcTemplate.query(SQLConstant.SCHOOL_IDENTITY_GET,new Object[]{id}, new RowMapper<SchoolVo>(){
			@Override
			public SchoolVo mapRow(ResultSet rs, int arg1) throws SQLException {
				SchoolVo vo = new SchoolVo();
				vo.setAddress(rs.getString("address"));
				vo.setLogo(rs.getString("logo"));
				vo.setName(rs.getString("name"));
				vo.setTel(rs.getString("tel"));
				return vo;
			}
		});
		
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	public Users getUsersTime(String id){
		List<Users> list =  jdbcTemplate.query(SQLConstant.STUDENT_TIMES_GET,new Object[]{id}, new RowMapper<Users>(){
			@Override
			public Users mapRow(ResultSet rs, int arg1) throws SQLException {
				Users user = new Users();
				user.setBegindate(rs.getDate("begindate")+"");
				user.setRemindtimes(rs.getInt("remindtimes"));
				user.setReminddays(rs.getInt("reminddays"));
				return user;
			}
		});
		if(list!=null&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

    /**
     *
     * @return
     */
    public StudentVo getStudentCardByNumber(String number){
          List<StudentVo> list = jdbcTemplate.query(SQLConstant.STUDENT_GET_BY_NUMBER,new Object[]{number},new RowMapper<StudentVo>() {
              @Override
              public StudentVo mapRow(ResultSet rs, int i) throws SQLException {
                  StudentVo vo = new StudentVo();
                  vo.setNumber(rs.getString("number"));
                  vo.setRemindDays(rs.getInt("reminddays"));
                  vo.setRemindTimes(rs.getInt("remindtimes"));
                  vo.setSchoolId(rs.getString("schoolId"));
                  vo.setBeginDate(rs.getDate("begindate")+"");
                  return vo;
              }
          });
        if(list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     * 获得驾校列表
     * @return
     */
    public List<SchoolVo> getSchoolVoList(){
         return  jdbcTemplate.query(SQLConstant.SCHOOLCARD_GET_ALL,new RowMapper<SchoolVo>() {
             @Override
             public SchoolVo mapRow(ResultSet rs, int i) throws SQLException {
                 SchoolVo vo = new SchoolVo();
                 vo.setId(rs.getString("id"));
                 vo.setName(rs.getString("name"));
                 return vo;
             }
         });

    }

    /**
     * 更新学员卡信息
     */
    public int updateStudentCard(StudentVo vo){
         return jdbcTemplate.update(SQLConstant.STUDENT_UPDATE_AJAX,new Object[]{
             vo.getRemindTimes(),
             vo.getRemindDays(),
             vo.getSchoolId(),
             new Date(),
             vo.getNumber()
         });
    }

    /**
     * 更新学员卡信息
     * @param fnumber
     * @param tnumber
     * @param schoolId
     * @return
     */
    public int updateStudentCards(int fnumber,int tnumber,String schoolId){
         return jdbcTemplate.update(SQLConstant.STUDENT_UPDATE_BY_NUMBERS,new Object[]{
                 schoolId,
                 fnumber+"",
                 tnumber+""
         });
    }
}
