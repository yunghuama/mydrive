package com.platform.web.ajax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.platform.domain.Score;
import com.platform.domain.Users;
import com.platform.service.QuestionService;
import com.platform.util.LoginBean;

@Controller
@Scope("prototype")
public class QuestionAjaxAction {

    @Autowired
    private QuestionService questionService;
    
    private Score score;
    private String result;

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
	
}