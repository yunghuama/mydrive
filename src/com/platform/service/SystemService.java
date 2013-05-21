package com.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.platform.dao.SystemDAO;
import com.platform.domain.Announcement;
import com.platform.vo.Page;

@Service
public class SystemService implements IService {

    private SystemDAO systemDAO;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	systemDAO = SystemDAO.getInstance(jdbcTemplate);
    }

   public int save(Announcement ann){
	   return systemDAO.saveAnnouncement(ann.getSchoolcard(), ann.getTitle(), ann.getContent());
   }
   
   /**
    * 
    * @param page
    * @param schoolcard
    * @return
    */
   public Page list(Page page,String schoolcard){
	   return systemDAO.list(page, schoolcard);
   }
   
   
}