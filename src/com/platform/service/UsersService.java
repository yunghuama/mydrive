package com.platform.service;

import com.platform.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.constants.StringConstant;
import com.platform.dao.UsersDAO;
import com.platform.domain.Users;
import com.platform.exception.CRUDException;
import com.platform.vo.SchoolVo;

import java.util.List;

@Service
public class UsersService implements IService {

    private UsersDAO usersDAO;

    @Override
	@Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        usersDAO = UsersDAO.getInstance(jdbcTemplate);
    }

    /**
     * 用户登录
     * 
     * @param accountName
     * @param password
     * @return
     * @throws CRUDException
     */
    public Users login(String accountName, String password) throws CRUDException {
    	
    	//先查询学员表
    	Users user = usersDAO.login_student(accountName.trim(), password.trim());
    	//再查询学校表
    	if(user==null)
    		user = usersDAO.login_school(accountName.trim(), password.trim());
    	//最后查询管理员表
    	if(user==null)
    		user = usersDAO.login_admin(accountName.trim(), password.trim());
    	
        return user;
    }

    public SchoolVo getSchoolById(String id){
    	return usersDAO.getSchoolById(id);
    }
    
   public int update(Users users){
	   return usersDAO.updateInfo(users);
   }
   
   public int updateActiveTime(String id){
	   return usersDAO.updateActiveTime(id);
   }
   
   public Users getUsersById(String id){
	   return usersDAO.getUsersById(id);
   }
   
   public int updatePass(String id,String oldPass,String newPass,String role){
	   if(StringConstant.ROLE_STUDENT.equals(role))
		   return usersDAO.updatePassStu(id, oldPass, newPass);
	   else if(StringConstant.ROLE_SCHOOL.equals(role))
		   	return usersDAO.updatePassSch(id, oldPass, newPass);
	   else if(StringConstant.ROLE_ADMIN.equals(role))
		   return usersDAO.updatePassAdmin(id, oldPass, newPass);
	    return 0;
   }
   
   
   @Transactional(rollbackFor={Exception.class,RuntimeException.class})
   public int updateTime(String id,String number,String password){
	   Users users = usersDAO.getTimes(number, password);
	   if(users!=null){
		  int r1 = usersDAO.updateTime(id, users.getRemindtimes(), users.getReminddays(), users.getBegindate());
		  int r2 = usersDAO.updateTo0(number, password);
		  if(r1==1&&r2==1)
			  return 1;
		  else 
		  {	  //创造异常
			  int arr[] = new int[0];
			  System.out.println(arr[5]);
		  }
	   }
	   return 0;
   }
   
   public Users getUsersTime(String id){
	   return usersDAO.getUsersTime(id);
   }

    /**
     * 根据卡号获得学生卡
     * @param number
     * @return
     */
    public StudentVo getStudentByNumber(String number){
         return usersDAO.getStudentCardByNumber(number);
    }

    /**
     * 获得驾校列表
     * @return
     */
    public List<SchoolVo> getSchoolVoList(){
        return usersDAO.getSchoolVoList();
    }

    /**
     * 更新学员卡
     * @param vo
     * @return
     */
    public int updateStudentCard(StudentVo vo){
        return usersDAO.updateStudentCard(vo);
    }

    /**
     * 更新学员卡
     * @return
     */
    public int updateStudentCards(int fnumber,int tnumber,String schoolId){
        return usersDAO.updateStudentCards(fnumber, tnumber, schoolId);
    }
}