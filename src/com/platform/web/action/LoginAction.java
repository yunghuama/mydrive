package com.platform.web.action;

import javax.servlet.http.Cookie;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.platform.constants.StringConstant;
import com.platform.domain.Users;
import com.platform.service.UsersService;
import com.platform.util.LoginBean;


@Controller
@Scope("prototype")
public class LoginAction extends ActionSupport {

	private static final long serialVersionUID = 1797119564459862667L;
	private static final String PASSWORD_WRONG = "passwordWrong";
	private static final String STATE_FALSE = "stateFalse";

	@Autowired
	private UsersService usersService;


	private String accountName;
	private String password;
	private String errorMes;
	private String remember;

	private String shorcutHtml;

	/**
	 * 登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String login() throws Exception {

		Users user = usersService.saveLogin(accountName, password);
		if (user == null) {
			errorMes = "用户名或密码错误，请重新登录";
			return PASSWORD_WRONG;
		} else if(!StringConstant.TRUE.equals(user.getState())) {
			errorMes = "用户已被禁用，无法登录";
			return STATE_FALSE;
		} else {
//			Hibernate.initialize(user.getDepartment());
			LoginBean loginBean = new LoginBean();
			loginBean.setUser(user);
			ActionContext.getContext().getSession().put("LoginBean", loginBean);
			
			return SUCCESS;
		}
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
	 * 显示桌面
	 * @return
	 * @throws Exception
	 */
	public String showDeskTop() throws Exception {
	   
		return SUCCESS;
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
	public String getShorcutHtml() {
		return shorcutHtml;
	}
	public void setShorcutHtml(String shorcutHtml) {
		this.shorcutHtml = shorcutHtml;
	}
    

}