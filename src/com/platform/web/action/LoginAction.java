package com.platform.web.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.platform.constants.StringConstant;
import com.platform.domain.Users;
import com.platform.service.QuestionService;
import com.platform.service.SystemService;
import com.platform.service.UsersService;
import com.platform.util.LoginBean;


@Controller
@Scope("prototype")
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1797119564459862667L;
	private static final String PASSWORD_WRONG = "passwordWrong";
	private static final String STATE_FALSE = "stateFalse";
	private static final String FIRST_INIT = "firstInit";
	@Autowired
	private UsersService usersService;
	@Autowired
	private QuestionService questionService;
	@Autowired
	private SystemService systemService;

	private String accountName;
	private String password;
	private String errorMes;
	private String remember;
	private Users users;

	/**
	 * 登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String login() throws Exception {

		users= usersService.login(accountName, password);
		//如果登录失败
		if (users == null) {
			errorMes = "用户名或密码错误，请重新登录";
			return PASSWORD_WRONG;
		} 
		//计算剩余时间
		try{
			if(StringConstant.ROLE_STUDENT.equals(users.getRole())){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date stDate = sdf.parse(users.getBegindate());
			int days = (int)(new Date().getTime()-stDate.getTime())/(24*60*60*1000);
			users.setReminddays(users.getReminddays()-days<0?0:users.getReminddays()-days);
			}
		}catch(Exception e){
			System.out.println("计算剩余天数失败...");
			users.setReminddays(0);
			e.printStackTrace();
		}
		LoginBean loginBean = new LoginBean();
		loginBean.setUser(users);
		//保存登录日志
		systemService.saveLoginLogs(users.getId());
		
		ActionContext.getContext().getSession().put("LoginBean", loginBean);
		//判断学员需不需要改资料
		if(StringConstant.ROLE_STUDENT.equals(users.getRole())){
			if(users.getIdentity()==null||users.getName()==null){
				return FIRST_INIT;
			}
		}
		return SUCCESS;
	}

	
	/**
	 * 退出
	 * 
	 * @return
	 * @throws Exception
	 */
	public String logout() throws Exception {
		ActionContext.getContext().getSession().remove("LoginBean");
		return SUCCESS;
	}
	
	/**
	 * 
	 * @return
	 */
	public String firstInit(){
		try{
			usersService.update(users);
			usersService.updateActiveTime(users.getId());
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			loginBean.getUser().setCartype(users.getCartype());
			loginBean.getUser().setName(users.getName());
			loginBean.getUser().setIdentity(users.getIdentity());
		}catch(Exception e){
			e.printStackTrace();
			return "error";
		}
		
		return SUCCESS;
	}
	
	public String workspace(){
		LoginBean bean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		String role  = bean.getUser().getRole();
		if(StringConstant.ROLE_ADMIN.equals(role)){
			return "admin";
		}else if(StringConstant.ROLE_SCHOOL.equals(role)){
			return  "school";
		}else if(StringConstant.ROLE_STUDENT.equals(role)){
			return  "student";
		}
		return null;
	}

	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getErrorMes() {
		return errorMes;
	}
	public void setRemember(String remember) {
		this.remember = remember;
	}
	public String getRemember() {
		return remember;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}