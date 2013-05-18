package com.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
}