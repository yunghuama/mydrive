package com.platform.service;

import java.util.Date;
import java.util.List;

import org.eclipse.jdt.internal.compiler.impl.StringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.opensymphony.xwork2.ActionContext;
import com.platform.constants.SQLConstant;
import com.platform.dao.UsersDAO;
import com.platform.domain.Users;
import com.platform.exception.CRUDException;
import com.platform.util.FileHelper;
import com.platform.util.ImageHelper;
import com.platform.util.LoginBean;
import com.platform.util.SearchUtil;
import com.platform.util.Validate;

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
    public Users saveLogin(String accountName, String password) throws CRUDException {
        return null;
    }

   
}