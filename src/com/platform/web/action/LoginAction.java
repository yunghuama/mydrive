package com.platform.web.action;

import java.io.FileInputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.platform.constants.StringConstant;
import com.platform.domain.BaseRole;
import com.platform.domain.Question;
import com.platform.domain.Users;
import com.platform.service.QuestionService;
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

	private String accountName;
	private String password;
	private String errorMes;
	private String remember;

	/**
	 * 登录
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String login() throws Exception {

		Users users= usersService.login(accountName, password);
		//如果登录失败
		if (users == null) {
			errorMes = "用户名或密码错误，请重新登录";
			return PASSWORD_WRONG;
		} 
		
		LoginBean loginBean = new LoginBean();
		loginBean.setUser(users);
		
		ActionContext.getContext().getSession().put("LoginBean", loginBean);

		//判断学员需不需要改资料
		if(StringConstant.ROLE_SCHOOL.equals(users.getRole())){
			if(users.getIdentity()==null||users.getName()==null){
				return FIRST_INIT;
			}
		}
		
		return SUCCESS;
	}

	public void importXls(){
		System.out.println("导入");
		try{

		FileInputStream f = new FileInputStream("/home/cheney/question_car.xls");
		HSSFWorkbook wb = new HSSFWorkbook(f);
		HSSFSheet sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();
		//如果没有错误,则进行导入
			for(int i=1;i<=rowNum;i++){
				HSSFRow row = sheet.getRow(i);
				HSSFCell code = row.getCell((short)0);
				HSSFCell question = row.getCell((short)1);
				HSSFCell a = row.getCell((short)2);
				HSSFCell b = row.getCell((short)3);
				HSSFCell c = row.getCell((short)4);
				HSSFCell d = row.getCell((short)5);
				HSSFCell answer = row.getCell((short)6);
				HSSFCell image = row.getCell((short)7);
				HSSFCell category = row.getCell((short)8);
				
				Question q = new Question();
				q.setCode(code.getStringCellValue());
				q.setQuestion(question.getStringCellValue());
				q.setAnswer_a(a.getStringCellValue());
				q.setAnswer_b(b.getStringCellValue());
				q.setAnswer_c(c==null? "" : c.getStringCellValue());
				q.setAnswer_d(d==null? "" :d.getStringCellValue());
				q.setAnswer(answer.getStringCellValue());
				q.setCategory(category==null? "":category.getStringCellValue());
				q.setQuestion_img(image==null?"":image.getStringCellValue());
				questionService.saveQuestion_car(q);
			}
			
		}catch(Exception e){
			e.printStackTrace();
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

}