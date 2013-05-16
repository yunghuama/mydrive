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

import com.opensymphony.xwork2.ActionSupport;
import com.platform.domain.Question;
import com.platform.service.QuestionService;
import com.platform.service.UsersService;
import com.platform.vo.QuestionVO;


@Controller
@Scope("prototype")
public class QuestionAction extends ActionSupport {

	private static final long serialVersionUID = 1797119564459862667L;

	@Autowired
	private UsersService usersService;
	@Autowired
	private QuestionService questionService;
	private List<QuestionVO> list;
	
	/**
	 * 初始化练习模式试题
	 * @return
	 */
	public String initExerciseQuestion(){
		long t = System.currentTimeMillis();
		list = questionService.listQuestionRandom_car();
		System.out.println(System.currentTimeMillis()-t);
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

	public List<QuestionVO> getList() {
		return list;
	}

	public void setList(List<QuestionVO> list) {
		this.list = list;
	}

}