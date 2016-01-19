package com.platform.service;

import java.util.List;

import com.platform.vo.SchoolVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.platform.constants.SQLConstant;
import com.platform.dao.SystemDAO;
import com.platform.domain.Announcement;
import com.platform.domain.Message;
import com.platform.domain.Users;
import com.platform.vo.Page;
import com.platform.vo.ScoreSchoolVO;
import com.platform.vo.ScoreVO;

@Service
public class SystemService implements IService {

    private SystemDAO systemDAO;
    @Override
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
   public Page listScore1(Page page,String schoolcard,String name) throws Exception {
	   return systemDAO.listScore1(page, schoolcard,name);
   }
   
   /**
    * 查询学员科目1成绩所有
    * @param schoolcard
    * @return
    */
   public List<ScoreSchoolVO> listScore1All(String schoolcard,String name){
	   return systemDAO.listScore1All(schoolcard,name);
   }
   
   /**
    * 查询学员科目1成绩所有
    * @param schoolcard
    * @return
    */
   public List<ScoreSchoolVO> listScore3All(String schoolcard,String name){
	   return systemDAO.listScore3All(schoolcard,name);
   }
   
   /**
    * 根据学员ID 查询统计结果
    */
   public ScoreSchoolVO getStasticScore1(String stuId){
	  return  systemDAO.getStasticScore1(stuId);
   }
   
   /**
    * 根据学员ID 查询详细分数
    */
   public List<ScoreVO> getScore1(String stuId){
	   return systemDAO.getScores1(stuId);
   }
   
   /**
    * 查询学员科目3成绩
    * @param page
    * @param schoolcard
    * @return
    */
   public Page listScore3(Page page,String schoolcard,String name) throws Exception {
	   return systemDAO.listScore3(page, schoolcard,name);
   }
   
   
   /**
    * 根据学员ID 查询统计结果
    */
   public ScoreSchoolVO getStasticScore3(String stuId){
	  return  systemDAO.getStasticScore3(stuId);
   }
   
   /**
    * 根据学员ID 查询详细分数
    */
   public List<ScoreVO> getScore3(String stuId){
	   return systemDAO.getScores3(stuId);
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
    * 保存登录日志
    */
   public int saveLoginLogs(String id){
	   return systemDAO.saveLoginLogs(id);
   }
   
   /**
    * 查询登录日志
    * 
    */
   public Page listLoginLogs(String sDate,String eDate,Page page) throws Exception{
	   return systemDAO.listLoginLogs(sDate, eDate, page);
   }
   
   /**
    * 查询激活量
    * 
    */
   public Page listActive(String sDate,String eDate,Page page) throws Exception{
	   return systemDAO.listActiveCard(sDate, eDate, page);
   }
   
   /**
    * 查询激活用户
    * @param sDate
    * @param eDate
    * @return
    * @throws Exception
    */
   public List<Users> listActiveUsers(String sDate,String eDate) throws Exception {
	   return systemDAO.listActiveUsers(sDate, eDate);
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
   
   public Page<Message> listMessageBySys(Page page){
	   return systemDAO.listMessageBySys(page);
   }
   
   public int deleteMessage(String id){
	   return systemDAO.deleteByProperty(SQLConstant.MESSAGE_DELETE, id);
   }
   
   public Message getMessage(String id){
	   return systemDAO.getMessage(id);
   }
   
   /**
    * 查询驾校logo
    */
   public String querySLogo(String id){
	   return systemDAO.querySLogo(id);
   }
   
   /**
    * 修改驾校logo
    */
   public int updateSLogo(String id,String logo){
	   return systemDAO.updateSLogo(id, logo);
   }

    /**
     * 查询驾校列表
      */
    public Page<SchoolVo> listSchool(Page page) throws Exception{
        return systemDAO.listSchool(page);
    }

    /**
     *
     * 保存驾校
     */
    public int saveSchool(SchoolVo school){
         return   systemDAO.saveSchool(school);
    }

    /**
     * 查询驾校信息
     */
    public SchoolVo getSchool(String id){
        return systemDAO.getSchoolById(id);
    }

    /**
     * 更新驾校信息
     */
    public int updateSchool(SchoolVo vo){
        return systemDAO.updateSchoolById(vo);
    }

    /**
     * 删除驾校
     */
    public int deleteSchool(String id){
         return systemDAO.delSchool(id);
    }
}