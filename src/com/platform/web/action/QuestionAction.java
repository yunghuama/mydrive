package com.platform.web.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.platform.constants.StringConstant;
import com.platform.domain.AttachedFile;
import com.platform.domain.Question;
import com.platform.domain.Section;
import com.platform.domain.Users;
import com.platform.service.QuestionService;
import com.platform.service.UsersService;
import com.platform.util.FileHelper;
import com.platform.util.LoginBean;
import com.platform.util.UploadHelper;
import com.platform.vo.QuestionVO;


@Controller
@Scope("prototype")
public class QuestionAction extends GenericAction {

	private static final long serialVersionUID = 1797119564459862667L;
	private String message;
	@Autowired
	private UsersService usersService;
	@Autowired
	private QuestionService questionService;
	private List<QuestionVO> list;
	private List<Section> sectionList;
	private String categoryId;
	private int questionId;
	private int type;//题库类型
	private Question question;
	private int fileType;//上传文件类型
	private QuestionVO questionVO;
	private int isDelImage;//是否删除图片
	/**
	 * 初始化练习模式试题
	 * @return
	 */
	public String initExerciseQuestion(){
		try{
		long t = System.currentTimeMillis();
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		Users users = loginBean.getUser();
		type = StringConstant.questionType.get(users.getCartype());
		if(StringConstant.questionType_car == type)
			list = questionService.listQuestionRandom_car();
		if(StringConstant.questionType_bus == type)
			list = questionService.listQuestionRandom_bus();
		if(StringConstant.questionType_truck == type)
			list = questionService.listQuestionRandom_truck();
		if(StringConstant.questionType_moto == type)
			list = questionService.listQuestionRandom_moto();
		System.out.println(System.currentTimeMillis()-t);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 初始化练习模式试题
	 * @return
	 */
	public String initSimulationQuestion(){
		try{
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		Users users = loginBean.getUser();
		type = StringConstant.questionType.get(users.getCartype());
		if(StringConstant.questionType_car == type)
			list = questionService.listQuestionRandom_car();
		if(StringConstant.questionType_bus == type)
			list = questionService.listQuestionRandom_bus();
		if(StringConstant.questionType_truck == type)
			list = questionService.listQuestionRandom_truck();
		if(StringConstant.questionType_moto == type)
			list = questionService.listQuestionRandom_moto();
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 
	 * 获得章节科目一
	 */
	public String section1(){
		try {
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			Users users = loginBean.getUser();
			type = StringConstant.questionType.get(users.getCartype());
			sectionList = questionService.getSection(type+"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 
	 * 获得章节科目一
	 */
	public String section3(){
		try {
			sectionList = questionService.getSection(StringConstant.SECTION_TYPE_3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String orderQuestion1(){
		try{
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		Users users = loginBean.getUser();
		type = StringConstant.questionType.get(users.getCartype());
		if(StringConstant.questionType_car == type)
			page = questionService.listQuestionOrder_car(page,categoryId);
		if(StringConstant.questionType_bus == type)
			page = questionService.listQuestionOrder_bus(page,categoryId);
		if(StringConstant.questionType_truck == type)
			page = questionService.listQuestionOrder_truck(page,categoryId);
		if(StringConstant.questionType_moto == type)
			page = questionService.listQuestionOrder_moto(page,categoryId);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**fileType
	 * 获得已标记题
	 * @return
	 */
	public String markQuestion1(){
		try{
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			type = StringConstant.questionType.get(loginBean.getUser().getCartype());
			page = questionService.listMarkQuestion(page, loginBean.getUser().getId(), loginBean.getUser().getCartype());
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/*
	 * 
	 * 删除标记题
	 */
	public String delMarkQuestion(){
		
		try {
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			questionService.delMarkQuestion(questionId,loginBean.getUser().getId(),loginBean.getUser().getCartype());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  Action.SUCCESS;
	}
	
	/*
	 * 
	 * 删除错题
	 */
	public String delWrongQuestion(){
		try {
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			questionService.delWrongQuestion(questionId,loginBean.getUser().getId(),loginBean.getUser().getCartype());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  Action.SUCCESS;
	}
	
	/**
	 * 获得错题
	 */
	public String listWrongQuestion(){
		try {
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			type = StringConstant.questionType.get(loginBean.getUser().getCartype());
			page = questionService.listWrongQuestion(page, loginBean.getUser().getId(), loginBean.getUser().getCartype());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 问题列表查询
	 * @return
	 */
	public String listQuestion(){
		try {
			page = questionService.listQuestion(page, type);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
	/**
	 * 添加新问题
	 * @return
	 * @throws IOException
	 */
	public String saveQuestion() throws IOException{
		//如果有附件
		try{
		
		if(upload!=null&&upload.size()>0){
			String path = FileHelper.getPath(type);
			UploadHelper helper = new UploadHelper(upload, uploadFileName, uploadTitle, uploadContentType, path, UploadHelper.NAME);
			List<AttachedFile> list = helper.getAttachedFiles();
			if(list!=null&&list.size()>0){
				AttachedFile af = list.get(0);
				if(fileType==0)
				question.setImage(af.getFileName());
				else if(fileType==1)
				question.setVideo(af.getFileName());
			}
		}
		//保存
		questionService.saveQuestion(question, type);
		}catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 预更新问题
	 * @return
	 */
	public String toUpdateQuestion(){
		try{
			question = questionService.findQuestionById(questionId, type);
			int t = type==4?3:1;
			sectionList = questionService.getSection(t+"");
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	public String  updateQuestion() throws IOException{
		//如果有附件
		try{
		if(upload!=null&&upload.size()>0){
			String path = FileHelper.getPath(type);
			UploadHelper helper = new UploadHelper(upload, uploadFileName, uploadTitle, uploadContentType, path, UploadHelper.NAME);
			List<AttachedFile> list = helper.getAttachedFiles();
			if(list!=null&&list.size()>0){
				AttachedFile af = list.get(0);
				if(fileType==0){
				question.setImage(af.getFileName());
				question.setVideo("");
				}
				else if(fileType==1){
				question.setVideo(af.getFileName());
				question.setImage("");
				}
			}
		}
		//保存
		questionService.updateQuestion(question, type);
		}catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	public String deleteQuestion(){
		questionService.deleteQuestion(questionId+"", type);
		return Action.SUCCESS;
	}
	
	public void importExcel(){
		try{

			System.out.println("导入");
			String path = "/home/cheney/driver/db1/q3.xlsx";
			importXlsx(path);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void importXls(String path){
		try{
			//如果是xls
			FileInputStream f = new FileInputStream(path);
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
					q.setImage(image==null?"":image.getStringCellValue());
					
					questionService.saveQuestion_moto(q);
					
					System.out.println(q.getCode());
				}
				System.out.println("导入结束");
			}catch(Exception e){
				e.printStackTrace();
			}
	}
	
	public void importXlsx(String path){
		try{
			File f1 = new File(path);
			System.out.print(f1.getAbsolutePath());
			FileInputStream f = new FileInputStream(path);
			XSSFWorkbook hssfWorkbook = new XSSFWorkbook(f);
			XSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
			int rowNum = hssfSheet.getLastRowNum();
			for(int i=1;i<=rowNum;i++){
				XSSFRow row = hssfSheet.getRow(i);
				XSSFCell code = row.getCell(0);
				XSSFCell question = row.getCell((short)1);
				XSSFCell a = row.getCell((short)2);
				XSSFCell b = row.getCell((short)3);
				XSSFCell c = row.getCell((short)4);
				XSSFCell d = row.getCell((short)5);
				XSSFCell answer = row.getCell((short)6);
				XSSFCell image = row.getCell((short)7);
				XSSFCell category = row.getCell((short)8);
				
				Question q = new Question();
				q.setCode(code.getStringCellValue().trim());
				q.setQuestion(question.getStringCellValue().trim());
				q.setAnswer_a(a.getStringCellValue().trim());
				q.setAnswer_b(b.getStringCellValue().trim());
				q.setAnswer_c(c==null? "" : c.getStringCellValue().trim());
				q.setAnswer_d(d==null? "" :d.getStringCellValue().trim());
				q.setAnswer(answer.getStringCellValue().trim());
				q.setCategory(category==null? "":category.getStringCellValue().trim());
				if(image!=null){
					String img = image.getStringCellValue();
					if(img.endsWith("flv")){
						q.setVideo(img);
					}else{
						q.setImage(img);
					}
				}
//				q.setImage(image==null?"":image.getStringCellValue().trim());
				//导入
				questionService.saveQuestion3(q);
				System.out.println(q.getCode());
			}
			
			System.out.println("导入结束");
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}

	public QuestionVO getQuestionVO() {
		return questionVO;
	}

	public void setQuestionVO(QuestionVO questionVO) {
		this.questionVO = questionVO;
	}

	public int getIsDelImage() {
		return isDelImage;
	}

	public void setIsDelImage(int isDelImage) {
		this.isDelImage = isDelImage;
	}

}