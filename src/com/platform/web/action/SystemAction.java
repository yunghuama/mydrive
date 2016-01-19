package com.platform.web.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.platform.domain.*;
import com.platform.service.QuestionService;
import com.platform.vo.SchoolVo;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.platform.constants.StringConstant;
import com.platform.service.SystemService;
import com.platform.service.UsersService;
import com.platform.util.ConfigureUtil;
import com.platform.util.LoginBean;
import com.platform.util.UploadHelper;
import com.platform.vo.ScoreSchoolVO;
import com.platform.vo.ScoreVO;


@Controller
@Scope("prototype")
public class SystemAction extends GenericAction {

	private static final long serialVersionUID = 1797119564459862667L;
	private String message;
	@Autowired
	private SystemService systemService;
	@Autowired
	private UsersService usersService;
    @Autowired
    private QuestionService questionService;
	
	private Announcement announcement;
	private String annId,stDate,enDate;
	private Users users;
	private Message msg;
	private String name = "";
	private InputStream inputStream;
	private String fileName;
	private String logo;
    private SchoolVo schoolVo;
    private List<QuestionTable> questionTableList;

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
			page = systemService.listScore1(page, loginBean.getUser().getId(),name.trim());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 导出学员成绩统计1
	 */
	public String exportStaticSocre1(){
		try{
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			List<ScoreSchoolVO> list = systemService.listScore1All(loginBean.getUser().getId(), name);
			
			fileName = "xycjtj_km1.xls";
			
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("驾考成绩");  
			//设置居中的样式
			HSSFCellStyle style = wb.createCellStyle();  
	        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中  
	        style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
	        //创建第一行
	        HSSFRow row = sheet.createRow((short) 0); 
	        row.setHeight((short)500);  
	        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 6)); 
	        HSSFCell ce = row.createCell(0); 
	        ce.setCellValue("学员驾考成绩统计");
	        ce.setCellStyle(style);
	        //创建第二行
	        HSSFRow row2 = sheet.createRow((short) 1);
	        HSSFCell ce21 = row2.createCell(0); 
	        ce21.setCellValue("学员名称");
	        HSSFCell ce22 = row2.createCell(1); 
	        ce22.setCellValue("最高分");
	        HSSFCell ce23 = row2.createCell(2); 
	        ce23.setCellValue("最低分");
	        HSSFCell ce24 = row2.createCell(3); 
	        ce24.setCellValue("考试次数");
	        HSSFCell ce25 = row2.createCell(4); 
	        ce25.setCellValue("平均成绩");
	        HSSFCell ce26 = row2.createCell(5); 
	        ce26.setCellValue("通过次数");
	        HSSFCell ce27 = row2.createCell(6); 
	        ce27.setCellValue("通过率");
	        //创建统计结果
	        for(int i=0;i<list.size();i++){
	        HSSFRow tr = sheet.createRow((short) i+2);
	        ScoreSchoolVO vo1 = list.get(i);
	        HSSFCell ce31 = tr.createCell(0); 
	        ce31.setCellValue(vo1.getName());
	        HSSFCell ce32 = tr.createCell(1); 
	        ce32.setCellValue(vo1.getMaxscore());
	        HSSFCell ce33 = tr.createCell(2); 
	        ce33.setCellValue(vo1.getMinscore());
	        HSSFCell ce34 = tr.createCell(3); 
	        ce34.setCellValue(vo1.getScorecounts());
	        HSSFCell ce35 = tr.createCell(4); 
	        ce35.setCellValue(vo1.getAvgscore());
	        HSSFCell ce36 = tr.createCell(5); 
	        ce36.setCellValue(vo1.getPasscount());
	        HSSFCell ce37 = tr.createCell(6); 
	        ce37.setCellValue(vo1.getRate()+"%");
	        }
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        wb.write(out);
	        inputStream = new ByteArrayInputStream(out.toByteArray());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 导出学员成绩1
	 */
	public String exportSocre1(){
		try{
			ScoreSchoolVO vo1 = systemService.getStasticScore1(users.getId());
			List<ScoreVO> list1 = systemService.getScore1(users.getId());
			
			fileName = "xycj_km1.xls";
			
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("驾考成绩");  
			//设置居中的样式
			HSSFCellStyle style = wb.createCellStyle();  
	        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中  
	        style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
	        //创建第一行
	        HSSFRow row = sheet.createRow((short) 0); 
	        row.setHeight((short)500);  
	        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 6)); 
	        HSSFCell ce = row.createCell(0); 
	        ce.setCellValue("学员驾考成绩统计");
	        ce.setCellStyle(style);
	        //创建第二行
	        HSSFRow row2 = sheet.createRow((short) 1);
	        HSSFCell ce21 = row2.createCell(0); 
	        ce21.setCellValue("学员名称");
	        HSSFCell ce22 = row2.createCell(1); 
	        ce22.setCellValue("最高分");
	        HSSFCell ce23 = row2.createCell(2); 
	        ce23.setCellValue("最低分");
	        HSSFCell ce24 = row2.createCell(3); 
	        ce24.setCellValue("考试次数");
	        HSSFCell ce25 = row2.createCell(4); 
	        ce25.setCellValue("平均成绩");
	        HSSFCell ce26 = row2.createCell(5); 
	        ce26.setCellValue("通过次数");
	        HSSFCell ce27 = row2.createCell(6); 
	        ce27.setCellValue("通过率");
	        //创建第三行
	        HSSFRow row3 = sheet.createRow((short) 2);
	        HSSFCell ce31 = row3.createCell(0); 
	        ce31.setCellValue(vo1.getName());
	        HSSFCell ce32 = row3.createCell(1); 
	        ce32.setCellValue(vo1.getMaxscore());
	        HSSFCell ce33 = row3.createCell(2); 
	        ce33.setCellValue(vo1.getMinscore());
	        HSSFCell ce34 = row3.createCell(3); 
	        ce34.setCellValue(vo1.getScorecounts());
	        HSSFCell ce35 = row3.createCell(4); 
	        ce35.setCellValue(vo1.getAvgscore());
	        HSSFCell ce36 = row3.createCell(5); 
	        ce36.setCellValue(vo1.getPasscount());
	        HSSFCell ce37 = row3.createCell(6); 
	        ce37.setCellValue(vo1.getRate()+"%");
	        //创建第4行
	        HSSFRow row4 = sheet.createRow((short) 3); 
	        row4.setHeight((short)500);  
	        sheet.addMergedRegion(new CellRangeAddress(3, (short) 3, 0, (short) 6)); 
	        HSSFCell ce41 = row4.createCell(0); 
	        ce41.setCellValue("学员驾考成绩明细");
	        ce41.setCellStyle(style);
	        //创建第5行
	        HSSFRow row5 = sheet.createRow((short) 4);
	        HSSFCell ce51 = row5.createCell(0); 
	        ce51.setCellValue("分数");
	        HSSFCell ce52 = row5.createCell(1); 
	        ce52.setCellValue("考试时长");
	        HSSFCell ce53 = row5.createCell(2); 
	        ce53.setCellValue("考试时间");
	        //开始循环创建考试成绩
	        for(int i=0;i<list1.size();i++){
	        	ScoreVO v = list1.get(i);
	        	HSSFRow scoreRow = sheet.createRow((short) (5+i));
	 	        HSSFCell scoreRow1 = scoreRow.createCell(0); 
	 	        scoreRow1.setCellValue(v.getScore());
	 	        HSSFCell scoreRow2 = scoreRow.createCell(1); 
	 	        scoreRow2.setCellValue(v.getTime());
	 	        HSSFCell scoreRow3 = scoreRow.createCell(2); 
	 	        scoreRow3.setCellValue(v.getCreatetime());
	        }
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        wb.write(out);
	        inputStream = new ByteArrayInputStream(out.toByteArray());
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
			page = systemService.listScore3(page, loginBean.getUser().getId(),name);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 导出学员成绩统计3所有
	 */
	public String exportStaticSocre3(){
		try{
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			List<ScoreSchoolVO> list = systemService.listScore3All(loginBean.getUser().getId(), name);
			
			fileName = "xycjtj_km3.xls";
			
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("驾考成绩");  
			//设置居中的样式
			HSSFCellStyle style = wb.createCellStyle();  
	        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中  
	        style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
	        //创建第一行
	        HSSFRow row = sheet.createRow((short) 0); 
	        row.setHeight((short)500);  
	        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 6)); 
	        HSSFCell ce = row.createCell(0); 
	        ce.setCellValue("学员驾考成绩统计");
	        ce.setCellStyle(style);
	        //创建第二行
	        HSSFRow row2 = sheet.createRow((short) 1);
	        HSSFCell ce21 = row2.createCell(0); 
	        ce21.setCellValue("学员名称");
	        HSSFCell ce22 = row2.createCell(1); 
	        ce22.setCellValue("最高分");
	        HSSFCell ce23 = row2.createCell(2); 
	        ce23.setCellValue("最低分");
	        HSSFCell ce24 = row2.createCell(3); 
	        ce24.setCellValue("考试次数");
	        HSSFCell ce25 = row2.createCell(4); 
	        ce25.setCellValue("平均成绩");
	        HSSFCell ce26 = row2.createCell(5); 
	        ce26.setCellValue("通过次数");
	        HSSFCell ce27 = row2.createCell(6); 
	        ce27.setCellValue("通过率");
	        //创建统计结果
	        for(int i=0;i<list.size();i++){
	        HSSFRow tr = sheet.createRow((short) i+2);
	        ScoreSchoolVO vo1 = list.get(i);
	        HSSFCell ce31 = tr.createCell(0); 
	        ce31.setCellValue(vo1.getName());
	        HSSFCell ce32 = tr.createCell(1); 
	        ce32.setCellValue(vo1.getMaxscore());
	        HSSFCell ce33 = tr.createCell(2); 
	        ce33.setCellValue(vo1.getMinscore());
	        HSSFCell ce34 = tr.createCell(3); 
	        ce34.setCellValue(vo1.getScorecounts());
	        HSSFCell ce35 = tr.createCell(4); 
	        ce35.setCellValue(vo1.getAvgscore());
	        HSSFCell ce36 = tr.createCell(5); 
	        ce36.setCellValue(vo1.getPasscount());
	        HSSFCell ce37 = tr.createCell(6); 
	        ce37.setCellValue(vo1.getRate()+"%");
	        }
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        wb.write(out);
	        inputStream = new ByteArrayInputStream(out.toByteArray());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 导出学员成绩3
	 */
	public String exportSocre3(){
		try{
			ScoreSchoolVO vo1 = systemService.getStasticScore3(users.getId());
			List<ScoreVO> list1 = systemService.getScore3(users.getId());
			
			fileName = "xycj_km3.xls";
			
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("驾考成绩");  
			//设置居中的样式
			HSSFCellStyle style = wb.createCellStyle();  
	        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中  
	        style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
	        //创建第一行
	        HSSFRow row = sheet.createRow((short) 0); 
	        row.setHeight((short)500);  
	        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 6)); 
	        HSSFCell ce = row.createCell(0); 
	        ce.setCellValue("学员驾考成绩统计");
	        ce.setCellStyle(style);
	        //创建第二行
	        HSSFRow row2 = sheet.createRow((short) 1);
	        HSSFCell ce21 = row2.createCell(0); 
	        ce21.setCellValue("学员名称");
	        HSSFCell ce22 = row2.createCell(1); 
	        ce22.setCellValue("最高分");
	        HSSFCell ce23 = row2.createCell(2); 
	        ce23.setCellValue("最低分");
	        HSSFCell ce24 = row2.createCell(3); 
	        ce24.setCellValue("考试次数");
	        HSSFCell ce25 = row2.createCell(4); 
	        ce25.setCellValue("平均成绩");
	        HSSFCell ce26 = row2.createCell(5); 
	        ce26.setCellValue("通过次数");
	        HSSFCell ce27 = row2.createCell(6); 
	        ce27.setCellValue("通过率");
	        //创建第三行
	        HSSFRow row3 = sheet.createRow((short) 2);
	        HSSFCell ce31 = row3.createCell(0); 
	        ce31.setCellValue(vo1.getName());
	        HSSFCell ce32 = row3.createCell(1); 
	        ce32.setCellValue(vo1.getMaxscore());
	        HSSFCell ce33 = row3.createCell(2); 
	        ce33.setCellValue(vo1.getMinscore());
	        HSSFCell ce34 = row3.createCell(3); 
	        ce34.setCellValue(vo1.getScorecounts());
	        HSSFCell ce35 = row3.createCell(4); 
	        ce35.setCellValue(vo1.getAvgscore());
	        HSSFCell ce36 = row3.createCell(5); 
	        ce36.setCellValue(vo1.getPasscount());
	        HSSFCell ce37 = row3.createCell(6); 
	        ce37.setCellValue(vo1.getRate()+"%");
	        //创建第4行
	        HSSFRow row4 = sheet.createRow((short) 3); 
	        row4.setHeight((short)500);  
	        sheet.addMergedRegion(new CellRangeAddress(3, (short) 3, 0, (short) 6)); 
	        HSSFCell ce41 = row4.createCell(0); 
	        ce41.setCellValue("学员驾考成绩明细");
	        ce41.setCellStyle(style);
	        //创建第5行
	        HSSFRow row5 = sheet.createRow((short) 4);
	        HSSFCell ce51 = row5.createCell(0); 
	        ce51.setCellValue("分数");
	        HSSFCell ce52 = row5.createCell(1); 
	        ce52.setCellValue("考试时长");
	        HSSFCell ce53 = row5.createCell(2); 
	        ce53.setCellValue("考试时间");
	        //开始循环创建考试成绩
	        for(int i=0;i<list1.size();i++){
	        	ScoreVO v = list1.get(i);
	        	HSSFRow scoreRow = sheet.createRow((short) (5+i));
	 	        HSSFCell scoreRow1 = scoreRow.createCell(0); 
	 	        scoreRow1.setCellValue(v.getScore());
	 	        HSSFCell scoreRow2 = scoreRow.createCell(1); 
	 	        scoreRow2.setCellValue(v.getTime());
	 	        HSSFCell scoreRow3 = scoreRow.createCell(2); 
	 	        scoreRow3.setCellValue(v.getCreatetime());
	        }
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        wb.write(out);
	        inputStream = new ByteArrayInputStream(out.toByteArray());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	/**
	 * 导出学员成绩3
	 */
	public String exportActive(){
		try{
			if(stDate==null||"".equals(stDate)||enDate==null||"".equals(enDate)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				stDate = enDate = sdf.format(new Date());
			}
			List<Users> list = systemService.listActiveUsers(stDate,enDate);
			
			fileName = "jhktj.xls";
			
			HSSFWorkbook wb = new HSSFWorkbook(); 
			HSSFSheet sheet = wb.createSheet("激活卡统计");  
			//设置居中的样式
			HSSFCellStyle style = wb.createCellStyle();  
	        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);//垂直居中  
	        style.setAlignment(CellStyle.ALIGN_CENTER);//水平居中  
	        //创建第一行
	        HSSFRow row = sheet.createRow((short) 0); 
	        row.setHeight((short)500);  
	        sheet.addMergedRegion(new CellRangeAddress(0, (short) 0, 0, (short) 6)); 
	        HSSFCell ce = row.createCell(0); 
	        ce.setCellValue("学员卡激活统计");
	        ce.setCellStyle(style);
	        //创建第二行
	        HSSFRow row2 = sheet.createRow((short) 1);
	        HSSFCell ce21 = row2.createCell(0); 
	        ce21.setCellValue("卡号");
	        HSSFCell ce22 = row2.createCell(1); 
	        ce22.setCellValue("姓名");
	        HSSFCell ce23 = row2.createCell(2); 
	        ce23.setCellValue("所属驾校");
	        HSSFCell ce24 = row2.createCell(3); 
	        ce24.setCellValue("激活时间");
	        //开始循环创建考试成绩
	        for(int i=0;i<list.size();i++){
	        	Users v = list.get(i);
	        	HSSFRow scoreRow = sheet.createRow((short) (2+i));
	 	        HSSFCell scoreRow1 = scoreRow.createCell(0); 
	 	        scoreRow1.setCellValue(v.getNumber());
	 	        HSSFCell scoreRow2 = scoreRow.createCell(1); 
	 	        scoreRow2.setCellValue(v.getName());
	 	        HSSFCell scoreRow3 = scoreRow.createCell(2); 
	 	        scoreRow3.setCellValue(v.getSchoolId());
	 	        HSSFCell scoreRow4 = scoreRow.createCell(3); 
	 	        scoreRow4.setCellValue(v.getActiveTime());
	        }
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        wb.write(out);
	        inputStream = new ByteArrayInputStream(out.toByteArray());
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
	
	/**
	 * 查询登录日志
	 * @return
	 */
	public String listLoginLogs(){
		try{
		if(stDate==null||"".equals(stDate)||enDate==null||"".equals(enDate)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			stDate = enDate = sdf.format(new Date());
		}
		page = systemService.listLoginLogs(stDate, enDate, page);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	/**
	 * 查询激活用户
	 * @return
	 */
	public String listActiveCard(){
		try{
		if(stDate==null||"".equals(stDate)||enDate==null||"".equals(enDate)){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			stDate = enDate = sdf.format(new Date());
		}
		page = systemService.listActive(stDate, enDate, page);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String updateUsers(){
		try{
		usersService.update(users);
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		loginBean.getUser().setCartype(users.getCartype());
		loginBean.getUser().setIdentity(users.getIdentity());
		loginBean.getUser().setName(users.getName());
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

	
	public String saveMessage(){
		try{
		
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		msg.setStudentCard(loginBean.getUser().getId());
		msg.setSchoolcard(loginBean.getUser().getSchoolId());
		systemService.saveMessage(msg);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String listMessageStu(){
		try{
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			page = systemService.listMessageByStu(page, loginBean.getUser().getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String listMessageSch(){
		try{
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			page = systemService.listMessageBySch(page, loginBean.getUser().getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String listMessageSys(){
		try{
			page = systemService.listMessageBySys(page);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getMessageById(){
		try{
			msg = systemService.getMessage(msg.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String delMessageStu(){
		try{
			systemService.deleteMessage(msg.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	public String delMessageSch(){
		try{
			systemService.deleteMessage(msg.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}
	
	
	
	
	public String getFileName() {
		try{
			//ServletActionContext.getResponse().setHeader("charset","ISO8859-1");
			return new String(fileName.getBytes(),"iso8859-1");
		}catch(Exception e){
			e.printStackTrace();
		}
		return "error.xls";
	}
	
	
	/**
	 * 预修改驾校logo
	 * @return
	 */
	public String toUpdateSLogo(){
		try{
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			logo = systemService.querySLogo(loginBean.getUser().getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 修改
	 * @return
	 */
	public String updateSLogo(){
		try{
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			if(upload!=null&&upload.size()>0){
				ConfigureUtil configure = new ConfigureUtil();
				String path = configure.get(StringConstant.PATH_IMAGE_SLOGO);
				UploadHelper helper = new UploadHelper(upload, uploadFileName, uploadTitle, uploadContentType, path, UploadHelper.UID);
				List<AttachedFile> list = helper.getAttachedFiles();
				if(list!=null&&list.size()>0){
					AttachedFile af = list.get(0);
					systemService.updateSLogo(loginBean.getUser().getId(), af.getExtendName());
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}

    /**
     * 预保存驾校
     * @return
     */
    public String toSaveSchool(){
        questionTableList = questionService.questionTableList();
        return SUCCESS;
    }

    /**
     * 保存驾校
     * @return
     */
    public String saveSchool(){
        systemService.saveSchool(schoolVo);
        return SUCCESS;
    }

    /**
     * 查询驾校列表
     * @return
     */
	public String listSchool(){
         try{
             page = systemService.listSchool(page);
         }catch(Exception e){
             e.printStackTrace();
         }
        return SUCCESS;
    }

    /**
     * 查询驾校
     * @return
     */
    public String toUpdateSchool(){
        try{
            questionTableList = questionService.questionTableList();
            schoolVo = systemService.getSchool(schoolVo.getId());
        }catch(Exception e){
            e.printStackTrace();
        }
       return SUCCESS;
    }

    /**
     * 更新驾校
     * @return
     */
    public String updateSchool(){
        try{
            systemService.updateSchool(schoolVo);
        }catch (Exception e){
            e.printStackTrace();
        }
        return SUCCESS;
    }

    /**
     * 删除驾校
     * @return
     */
    public String deleteSchool(){
       try{
           systemService.deleteSchool(schoolVo.getId());
       }catch(Exception e){
           e.printStackTrace();
       }
      return SUCCESS;
    }

    public SchoolVo getSchoolVo() {
        return schoolVo;
    }

    public void setSchoolVo(SchoolVo schoolVo) {
        this.schoolVo = schoolVo;
    }

    public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public InputStream getInputStream() {
		System.out.println(inputStream);
		return inputStream;
	}

    public List<QuestionTable> getQuestionTableList() {
        return questionTableList;
    }

    public void setQuestionTableList(List<QuestionTable> questionTableList) {
        this.questionTableList = questionTableList;
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


	public Message getMsg() {
		return msg;
	}


	public void setMsg(Message msg) {
		this.msg = msg;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getStDate() {
		return stDate;
	}


	public void setStDate(String stDate) {
		this.stDate = stDate;
	}


	public String getEnDate() {
		return enDate;
	}


	public void setEnDate(String enDate) {
		this.enDate = enDate;
	}

}