package com.platform.web.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.platform.domain.Score;
import com.platform.domain.Users;
import com.platform.service.QuestionService;
import com.platform.service.QuestionSubject3Service;
import com.platform.util.LoginBean;

@Controller
@Scope("prototype")
public class QuestionAjaxAction {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionSubject3Service questionSubject3Service;
    
    private Score score;
    private String result;
    private int questionId;
    private String remarkQuestionId;
    
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
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		if(loginBean!=null){
			int r = questionSubject3Service.saveExamScore(loginBean.getUser().getId(), score.getScore(), score.getTime(), loginBean.getUser().getCartype());
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
	public String saveMarkQuestion3(){
		LoginBean loginBean = (LoginBean)ActionContext.getContext().getSession().get("LoginBean");
		if(loginBean!=null){
			int flag = 	questionSubject3Service.saveMarkQuestion(questionId, loginBean.getUser().getId());
			if(flag==1)
				result = "success";
			else 
				result = "error";
		}
		return  Action.SUCCESS;
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
	
}