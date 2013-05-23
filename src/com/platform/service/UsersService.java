package com.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.dao.UsersDAO;
import com.platform.domain.Users;
import com.platform.exception.CRUDException;

@Service
public class UsersService implements IService {

    private UsersDAO usersDAO;

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
    	Users user = usersDAO.login_student(accountName.toString(), password.toString());
    	//再查询学校表
    	if(user==null)
    		user = usersDAO.login_student(accountName.toString(), password.toString());
    	//最后查询管理员表
    	if(user==null)
    		user = usersDAO.login_admin(accountName.toString(), password.toString());
    	
        return user;
    }

   public int update(Users users){
	   return usersDAO.updateInfo(users);
   }
   
   public Users getUsersById(String id){
	   return usersDAO.getUsersById(id);
   }
   
   public int updatePass(String id,String oldPass,String newPass){
	   return usersDAO.updatePass(id, oldPass, newPass);
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
}