package com.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.constants.StringConstant;
import com.platform.dao.QuestionDAO;
import com.platform.domain.Question;
import com.platform.domain.Section;
import com.platform.vo.Page;
import com.platform.vo.QuestionVO;

@Service
public class QuestionService implements IService {

    private QuestionDAO questionDAO;
    
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	questionDAO = QuestionDAO.getInstance(jdbcTemplate);
    }

    /**
     * 保存小车类型题库
     * @param question
     * @return
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public int saveQuestion_car(Question question){
    	return questionDAO.saveQuestion_Car(question);
    }
   
    /**
     * 随机获得小车的题目
     * @return
     */
    public List<QuestionVO> listQuestionRandom_car(){
    	return questionDAO.listQuestionRandom_Car();
    }
    
    /**
     * 随机获得客车题目
     * 
     */
    public List<QuestionVO> listQuestionRandom_bus(){
    	return questionDAO.listQuestionRandom_bus();
    }
    
    /**
     * 随机获得货车题目
     * 
     */
    public List<QuestionVO> listQuestionRandom_truck(){
    	return questionDAO.listQuestionRandom_truck();
    }
    
    /**
     * 随机获得摩托车题目
     * 
     */
    public List<QuestionVO> listQuestionRandom_moto(){
    	return questionDAO.listQuestionRandom_moto();
    }
    
    /**
     * 保存考试成绩
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public int saveExamScore(String studentId,int score,String time,String cartype,String errorQuestions){
    	if(errorQuestions!=null&&!errorQuestions.equals("")){
    		String array[] = errorQuestions.split("@");
    		for(int i=0;i<array.length;i++){
    			questionDAO.saveErrorQuestion(array[i], studentId, StringConstant.questionType.get(cartype));
    		}
    	}
    	//剩余次数-1
    	questionDAO.countDownOne(studentId);
    	return questionDAO.saveExamScore(studentId, score, time, cartype);
    }
    
    
    /**
     * 获得章节
     */
    public List<Section> getSection(String type){
    	return questionDAO.getSection(type);
    }
    
    /**
     * 根据类型分页获取小汽车
     */
    public Page<QuestionVO> listQuestionOrder_car(Page<QuestionVO> page,String category){
    	return questionDAO.listQuestionOrder_car(page,category);
    }
    
    /**
     * 根据类型分页获取小汽车
     */
    public Page<QuestionVO> listQuestionOrder_bus(Page<QuestionVO> page,String category){
    	return questionDAO.listQuestionOrder_car(page,category);
    }
    
    /**
     * 根据类型分页获取小汽车
     */
    public Page<QuestionVO> listQuestionOrder_truck(Page<QuestionVO> page,String category){
    	return questionDAO.listQuestionOrder_car(page,category);
    }
    
    /**
     * 根据类型分页获取小汽车
     */
    public Page<QuestionVO> listQuestionOrder_moto(Page<QuestionVO> page,String category){
    	return questionDAO.listQuestionOrder_car(page,category);
    }
}