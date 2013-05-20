package com.platform.web.action;

import java.io.FileInputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.platform.constants.StringConstant;
import com.platform.domain.Question;
import com.platform.domain.Section;
import com.platform.domain.Users;
import com.platform.service.QuestionSubject3Service;
import com.platform.service.UsersService;
import com.platform.util.LoginBean;
import com.platform.vo.QuestionVO;


@Controller
@Scope("prototype")
public class QuestionSubject3Action extends GenericAction {

	private static final long serialVersionUID = 1797119564459862667L;
	private String message;
	@Autowired
	private UsersService usersService;
	@Autowired
	private QuestionSubject3Service questionService;
	private List<QuestionVO> list;
	private List<Section> sectionList;
	private String categoryId;
	private int questionId;
	/**
	 * 初始化练习模式试题
	 * @return
	 */
	public String initExerciseQuestion(){
		try{
			list = questionService.listQuestionRandom();
		if(list==null||list.size()==0){
			message = "暂无该类型的题库";
			return "noquestion";
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化模拟考试
	 * @return
	 */
	public String initSimulationQuestion(){
		try{
			list = questionService.listQuestionRandom();
		if(list==null||list.size()==0){
			message = "暂无该类型的题库";
			return "noquestion";
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 获得章节科目一
	 */
	public String section(){
		sectionList = questionService.getSection(StringConstant.SECTION_TYPE_3);
		return SUCCESS;
	}
	
	
	public String orderQuestion(){
		try{
			page = questionService.listQuestionOrder(page,categoryId);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 获得已标记题
	 * @return
	 */
	public String markQuestion(){
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		page = questionService.listMarkQuestion(page, loginBean.getUser().getId());
		return SUCCESS;
	}
	
	
	/*
	 * 
	 * 删除标记题目
	 */
	public String delMarkQuestion(){
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		questionService.delMarkQuestion(questionId,loginBean.getUser().getId());
		return  Action.SUCCESS;
	}
	
	/**
	 * 获得错题
	 */
	public String listWrongQuestion(){
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		page = questionService.listWrongQuestion(page, loginBean.getUser().getId());
		return Action.SUCCESS;
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
//				questionService.saveQuestion_car(q);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public List<QuestionVO> getList() {
		return list;
	}

	public void setList(List<QuestionVO> list) {
		this.list = list;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}


}