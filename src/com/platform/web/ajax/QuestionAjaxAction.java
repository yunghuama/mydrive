package com.platform.web.ajax;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.platform.domain.Score;
import com.platform.domain.Section;
import com.platform.domain.Users;
import com.platform.service.QuestionService;
import com.platform.service.QuestionSubject3Service;
import com.platform.service.SystemService;
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
    
    private Score score;
    private String result;
    private int questionId;
    private String remarkQuestionId;
    private StatisticVO statisticVO;
    private List<ScoreVO> scoreVoList;
    private List<Section> sectionList;
    private int type;
    
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
				users.setRemindtimes(users.getReminddays()-1);
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
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		if(loginBean!=null){
			int flag = 	questionService.saveMarkQuestion(questionId, loginBean.getUser().getId(), loginBean.getUser().getCartype());
			if(flag==1)
				result = "success";
			else 
				result = "error";
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
				users.setRemindtimes(users.getReminddays()-1);
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
		sectionList = questionService.getSection(type+"");
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
	
}