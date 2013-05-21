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
    * 查询驾校公告
    * @param page
    * @param schoolcard
    * @return
    */
   public Page listAnnouncement(Page page,String schoolcard){
	   return systemDAO.listAnnouncement(page, schoolcard);
   }
   
   
   /**
    * 查询学员科目1成绩
    * @param page
    * @param schoolcard
    * @return
    */
   public Page listScore1(Page page,String schoolcard){
	   return systemDAO.listScore1(page, schoolcard);
   }
   
   /**
    * 查询学员科目3成绩
    * @param page
    * @param schoolcard
    * @return
    */
   public Page listScore3(Page page,String schoolcard){
	   return systemDAO.listScore3(page, schoolcard);
   }
}