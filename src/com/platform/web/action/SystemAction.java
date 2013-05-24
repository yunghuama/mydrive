package com.platform.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.platform.domain.Announcement;
import com.platform.domain.Users;
import com.platform.service.SystemService;
import com.platform.service.UsersService;
import com.platform.util.LoginBean;


@Controller
@Scope("prototype")
public class SystemAction extends GenericAction {

	private static final long serialVersionUID = 1797119564459862667L;
	private String message;
	@Autowired
	private SystemService systemService;
	@Autowired
	private UsersService usersService;
	
	private Announcement announcement;
	private String annId;
	private Users users;
	
	/**
	 * 保存公告
	 * @return
	 */
	public String saveAnnouncement(){
		try {
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			systemService.saveAnnouncement(loginBean.getUser().getId(),announcement.getTitle(),announcement.getContent());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 驾校查询公告
	 * @return
	 */
	public String listAnnouncement(){
		try {
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			page = systemService.listAnnouncement(page, loginBean.getUser().getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 学生查询驾校公告
	 * @return
	 */
	public String listAnnouncementStu(){
		try {
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			page = systemService.listAnnouncement(page, loginBean.getUser().getSchoolId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除驾校公告
	 */
	public String delAnnouncement(){
		try {
			systemService.delAnn(annId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 查看驾校公告
	 * 
	 */
	public String viewAnnouncement(){
		try {
			announcement = systemService.getAnnouncementById(annId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	
	
	/**
	 * 获得个人信息
	 * @return
	 */
	public String toUpdateUsers(){
		try {
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			users = usersService.getUsersById(loginBean.getUser().getId());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String updateUsers(){
		try{
		usersService.update(users);
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		loginBean.getUser().setCartype(users.getCartype());
		LoginBean loginBean1 = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		System.out.println(loginBean1.getUser().getCartype());
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


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public Users getUsers() {
		return users;
	}


	public void setUsers(Users users) {
		this.users = users;
	}
	
}