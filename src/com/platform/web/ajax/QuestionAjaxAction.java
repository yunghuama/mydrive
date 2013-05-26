package com.platform.web.ajax;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.platform.domain.Adver;
import com.platform.domain.Score;
import com.platform.domain.Section;
import com.platform.domain.Users;
import com.platform.service.AdverService;
import com.platform.service.QuestionService;
import com.platform.service.QuestionSubject3Service;
import com.platform.service.SystemService;
import com.platform.service.UsersService;
import com.platform.util.LoginBean;
import com.platform.vo.ScoreVO;
import com.platform.vo.StatisticVO;

@Controller
@Scope("prototype")
public class QuestionAjaxAction {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionSubject3Service questionSubject3Service;
    @Autowired
    private SystemService systemService;
    @Autowired
    private UsersService usersService;
    @Autowired
    private AdverService adverService;
    
    private Score score;
    private String result;
    private int questionId;
    private String remarkQuestionId;
    private StatisticVO statisticVO;
    private List<ScoreVO> scoreVoList;
    private List<Section> sectionList;
    private int type;
    private String oldPass,newPass;
    private Adver adver;
    private List<Adver> adverList;
    /**
     * 保存成绩
     * @return
     */
	public String saveScore(){
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		if(loginBean!=null){
			int r = questionService.saveExamScore(loginBean.getUser().getId(), score.getScore(), score.getTime(), loginBean.getUser().getCartype(),score.getErrorQuestion());
			if(r==1){
				result = "success";
				Users users = loginBean.getUser();
				users.setRemindtimes(users.getRemindtimes()-1);
			}
			else 
				result = "error";
		}else
			result = "error";
		return Action.SUCCESS;
	}
	
	/**
	 * 标记题目
	 * @return
	 */
	public String saveMarkQuestion(){
		try {
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			if(loginBean!=null){
				int flag = 	questionService.saveMarkQuestion(questionId, loginBean.getUser().getId(), loginBean.getUser().getCartype());
				if(flag==1)
					result = "success";
				else 
					result = "error";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  Action.SUCCESS;
	}
	
	/***************************科目三*******************************/
	
	  /**
     * 保存成绩
     * @return
     */
	public String saveScore3(){
		try{
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		if(loginBean!=null){
			int r = questionSubject3Service.saveExamScore(loginBean.getUser().getId(), score.getScore(), score.getTime(), score.getErrorQuestion());
			if(r==1){
				result = "success";
				Users users = loginBean.getUser();
				users.setRemindtimes(users.getRemindtimes()-1);
			}
			else 
				result = "error";
		}else
			result = "error";
		}catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 剩余次数-1
	 * @return
	 */
	public String countDownOne(){
		try{
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			int rs  = questionService.countDownOne(loginBean.getUser().getId());
			if(rs==1){
				result = "success";
				Users users = loginBean.getUser();
				users.setRemindtimes(users.getRemindtimes()-1);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 标记题目
	 * @return
	 */
	public String saveMarkQuestion3(){
		try{
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			if(loginBean!=null){
				int flag = 	questionSubject3Service.saveMarkQuestion(questionId, loginBean.getUser().getId());
				if(flag==1)
					result = "success";
				else 
					result = "error";
			}
		}catch(Exception e){
			e.printStackTrace();
			}
		
		return  Action.SUCCESS;
	}
	
	
	/**
	 * 获得科目一考试的统计信息
	 * @return
	 */
	public String statisticsSub1(){
		try{
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			statisticVO = questionService.getStatistic(loginBean.getUser().getId(), loginBean.getUser().getCartype());
		}catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 获得科目一的成绩
	 * @return
	 */
	public String scoreListSub1(){
		try{
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		scoreVoList = questionService.getScores(loginBean.getUser().getId(), loginBean.getUser().getCartype());
		}catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
	/**
	 * 获得科目三考试的统计信息
	 * @return
	 */
	public String statisticsSub3(){
		try{
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		statisticVO = questionSubject3Service.getStatistic(loginBean.getUser().getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}

	/**
	 * 获得科目三的成绩
	 * @return
	 */
	public String scoreListSub3(){
		try{
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		scoreVoList = questionSubject3Service.getScores(loginBean.getUser().getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 根据类型获得章节
	 * @return
	 */
	public String getCategory(){
		try {
			sectionList = questionService.getSection(type+"");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	
	/**
	 * 修改密码
	 * @return
	 */
	public String updatePass(){
		try {
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			int re = usersService.updatePass(loginBean.getUser().getId(), oldPass, newPass,loginBean.getUser().getRole());
			if(re==1){
				result = "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 续费
	 * @return
	 */
	public String updateTime(){
		try {
			LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
			int re = usersService.updateTime(loginBean.getUser().getId(), oldPass, newPass);
			if(re==1){
				result = "success";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return Action.SUCCESS;
	}
	
	/**
	 * 判断能否进行测试
	 * @return
	 */
	public String canExam(){
		try{
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		Users users = usersService.getUsersTime(loginBean.getUser().getId());
		if(users!=null){
			//计算剩余次数
			if(users.getRemindtimes()==0){
				result = "error";
				return Action.SUCCESS;
			}
			//计算剩余天数
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date stDate = sdf.parse(users.getBegindate());
			int days = (int)(new Date().getTime()-stDate.getTime())/(24*60*60*1000);
			if((users.getReminddays()-days)<0){
				result = "error";
				return Action.SUCCESS;
			}
			result = "success";
		}else{
			result = "error";
		}
		}catch(Exception e){
			e.printStackTrace();
			result = "error";
		}
		return Action.SUCCESS;
	}
	
	
	public String getAdvers(){
		try{
			adverList = adverService.listAdver(adver.getPage(), adver.getPosition());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return Action.SUCCESS;
	}
	
	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public Score getScore() {
		return score;
	}

	public void setScore(Score score) {
		this.score = score;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getRemarkQuestionId() {
		return remarkQuestionId;
	}

	public void setRemarkQuestionId(String remarkQuestionId) {
		this.remarkQuestionId = remarkQuestionId;
	}

	public StatisticVO getStatisticVO() {
		return statisticVO;
	}

	public void setStatisticVO(StatisticVO statisticVO) {
		this.statisticVO = statisticVO;
	}

	public List<ScoreVO> getScoreVoList() {
		return scoreVoList;
	}

	public void setScoreVoList(List<ScoreVO> scoreVoList) {
		this.scoreVoList = scoreVoList;
	}

	public List<Section> getSectionList() {
		return sectionList;
	}

	public void setSectionList(List<Section> sectionList) {
		this.sectionList = sectionList;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	

	public Adver getAdver() {
		return adver;
	}

	public void setAdver(Adver adver) {
		this.adver = adver;
	}

	public List<Adver> getAdverList() {
		return adverList;
	}

	public void setAdverList(List<Adver> adverList) {
		this.adverList = adverList;
	}
	
}