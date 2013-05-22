package com.platform.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.platform.domain.Announcement;
import com.platform.service.SystemService;
import com.platform.util.LoginBean;


@Controller
@Scope("prototype")
public class SystemAction extends GenericAction {

	private static final long serialVersionUID = 1797119564459862667L;
	private String message;
	@Autowired
	private SystemService systemService;
	private Announcement announcement;
	private String annId;
	
	/**
	 * 保存公告
	 * @return
	 */
	public String saveAnnouncement(){
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		systemService.saveAnnouncement(loginBean.getUser().getId(),announcement.getTitle(),announcement.getContent());
		return SUCCESS;
	}
	
	
	/**
	 * 驾校查询公告
	 * @return
	 */
	public String listAnnouncement(){
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		page = systemService.listAnnouncement(page, loginBean.getUser().getId());
		return SUCCESS;
	}
	
	/**
	 * 学生查询驾校公告
	 * @return
	 */
	public String listAnnouncementStu(){
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		page = systemService.listAnnouncement(page, loginBean.getUser().getSchoolId());
		return SUCCESS;
	}
	
	/**
	 * 删除驾校公告
	 */
	public String delAnnouncement(){
		systemService.delAnn(annId);
		return SUCCESS;
	}
	
	/**
	 * 查看驾校公告
	 * 
	 */
	public String viewAnnouncement(){
		announcement = systemService.getAnnouncementById(annId);
		return SUCCESS;
	}
	
	/**
	 * 获得学员的成绩1
	 * @return
	 */
	public String listScore1(){
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		try{
			page = systemService.listScore1(page, loginBean.getUser().getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	/**
	 * 获得学员的成绩3
	 * @return
	 */
	public String listScore3(){
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		try{
			page = systemService.listScore3(page, loginBean.getUser().getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}


	public Announcement getAnnouncement() {
		return announcement;
	}


	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}


	public String getAnnId() {
		return annId;
	}


	public void setAnnId(String annId) {
		this.annId = annId;
	}
	
}