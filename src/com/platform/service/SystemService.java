package com.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.platform.constants.SQLConstant;
import com.platform.dao.SystemDAO;
import com.platform.domain.Announcement;
import com.platform.domain.Message;
import com.platform.vo.Page;

@Service
public class SystemService implements IService {

    private SystemDAO systemDAO;
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	systemDAO = SystemDAO.getInstance(jdbcTemplate);
    }

   public int saveAnnouncement(String schoolcard,String title,String content){
	   return systemDAO.saveAnnouncement(schoolcard, title, content);
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
   public Page listScore1(Page page,String schoolcard,String name){
	   return systemDAO.listScore1(page, schoolcard,name);
   }
   
   /**
    * 查询学员科目3成绩
    * @param page
    * @param schoolcard
    * @return
    */
   public Page listScore3(Page page,String schoolcard,String name){
	   return systemDAO.listScore3(page, schoolcard,name);
   }
   
   
   /**
    * 删除公告
    * 
    */
   public int delAnn(String id){
	  return systemDAO.deleteByProperty(SQLConstant.ANNOUNCEMENT_DEL, id);
   }
   
   /**
    * 查看公告
    */
   public Announcement getAnnouncementById(String id){
	   return systemDAO.getAnnouncementById(id);
   }
   
   
   /**
    * 保存意见
    */
   public int saveMessage(Message message){
	   return systemDAO.saveMessage(message);
   }
   
   /**
    * 
    */
   public Page<Message> listMessageByStu(Page page,String studentId){
	   return systemDAO.listMessageByStu(page, studentId);
   }
   
   public Page<Message> listMessageBySch(Page page,String schoolId){
	   return systemDAO.listMessageBySch(page, schoolId);
   }
   
   public int deleteMessage(String id){
	   return systemDAO.deleteByProperty(SQLConstant.MESSAGE_DELETE, id);
   }
   
   public Message getMessage(String id){
	   return systemDAO.getMessage(id);
   }
}