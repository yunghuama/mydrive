package com.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.dao.QuestionSubject3DAO;
import com.platform.domain.Section;
import com.platform.vo.Page;
import com.platform.vo.QuestionVO;

@Service
public class QuestionSubject3Service implements IService {

    private QuestionSubject3DAO questionDAO;
    
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	questionDAO = QuestionSubject3DAO.getInstance(jdbcTemplate);
    }
    
    /*****************************练习模式|模拟考试开始*********************************/
    
    /**
     * 随机获得科目三的题目
     * @return
     */
    public List<QuestionVO> listQuestionRandom(){
    	return questionDAO.listQuestionRandom();
    }
    
    
    /**
     * 保存考试成绩
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public int saveExamScore(String studentId,int score,String time,String errorQuestions){
    	if(errorQuestions!=null&&!errorQuestions.equals("")){
    		String array[] = errorQuestions.split("@");
    		for(int i=0;i<array.length;i++){
    			questionDAO.saveErrorQuestion(array[i], studentId);
    		}
    	}
    	//剩余次数-1
    	questionDAO.countDownOne(studentId);
    	return questionDAO.saveExamScore(studentId, score, time);
    }
    
    /*****************************练习模式|模拟考试结束*********************************/
    
    
    /*****************************顺序练习开始*********************************/
    /**
     * 获得章节
     */
    public List<Section> getSection(String type){
    	return questionDAO.getSection(type);
    }
 
    /**
     * 根据类型分页获取小汽车
     */
    public Page<QuestionVO> listQuestionOrder(Page<QuestionVO> page,String category){
    	return questionDAO.listQuestionOrder(page,category);
    }
    
    
    /**
     * 保存标记问题
     * @return
     */
    public int saveMarkQuestion(int questionId,String studentId){
    	//首先判断该记录是否存在
    	if(questionDAO.hasMarkQuestion(questionId, studentId))
    		return 1;
    	return questionDAO.saveMarkQuestion(questionId, studentId);
    }
    
    /*****************************顺序练习开始结束*********************************/
    
    
    /*****************************已标记题开始*********************************/
    /**
     * 查询已标记题car
     */
    public Page<QuestionVO> listMarkQuestion(Page<QuestionVO> page,String studentId){
    	return questionDAO.listMarkQuestion(page,studentId);
    }
    
    
    /**
     * 删除已标记问题
     */
    public int delMarkQuestion(int questionId,String studentId){
    	return questionDAO.delMarkQuestion(questionId,studentId);
    }
    
    /*****************************已标记题结束*********************************/
    
    
    /*****************************错题练习开始*********************************/
    
    public Page<QuestionVO> listWrongQuestion(Page<QuestionVO> page,String studentId){
    	 return questionDAO.listWrongQuestion(page,studentId);
    }
    
    /*****************************错题练习结束*********************************/
    
}