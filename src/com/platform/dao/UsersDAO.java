package com.platform.dao;

import org.springframework.jdbc.core.JdbcTemplate;

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
	
}
