package com.platform.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.platform.service.SystemService;
import com.platform.util.LoginBean;


@Controller
@Scope("prototype")
public class SystemAction extends GenericAction {

	private static final long serialVersionUID = 1797119564459862667L;
	private String message;
	@Autowired
	private SystemService systemService;

	/**
	 * 获得学员的成绩1
	 * @return
	 */
	public String listScore1(){
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		try{
			page = systemService.listScore1(page, "4028813518f35feb0118f392eee50046");
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
			page = systemService.listScore3(page, "4028813518f35feb0118f392eee50046");
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
}