package com.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.constants.SQLConstant;
import com.platform.constants.StringConstant;
import com.platform.dao.QuestionBusDAO;
import com.platform.dao.QuestionCarDAO;
import com.platform.dao.QuestionDAO;
import com.platform.dao.QuestionMotoDAO;
import com.platform.dao.QuestionTruckDAO;
import com.platform.domain.Question;
import com.platform.domain.Section;
import com.platform.vo.Page;
import com.platform.vo.QuestionVO;

@Service
public class QuestionService implements IService {

    private QuestionDAO questionDAO;
    private QuestionBusDAO questionBusDAO;
    private QuestionCarDAO questionCarDAO;
    private QuestionTruckDAO questionTruckDAO;
    private QuestionMotoDAO questionMotoDAO;
    
    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	questionDAO = QuestionDAO.getInstance(jdbcTemplate);
    	questionBusDAO = QuestionBusDAO.getInstance(jdbcTemplate);
    	questionCarDAO = QuestionCarDAO.getInstance(jdbcTemplate);
    	questionTruckDAO = QuestionTruckDAO.getInstance(jdbcTemplate);
    	questionMotoDAO = QuestionMotoDAO.getInstance(jdbcTemplate);
    }

    /**
     * 保存小车类型题库
     * @param question
     * @return
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public int saveQuestion_car(Question question){
    	return questionCarDAO.saveQuestion_Car(question);
    }
   
    
    /*****************************练习模式|模拟考试开始*********************************/
    
    /**
     * 随机获得小车的题目
     * @return
     */
    public List<QuestionVO> listQuestionRandom_car(){
    	return questionCarDAO.listQuestionRandom_Car();
    }
    
    /**
     * 随机获得客车题目
     * 
     */
    public List<QuestionVO> listQuestionRandom_bus(){
    	return questionBusDAO.listQuestionRandom_bus();
    }
    
    /**
     * 随机获得货车题目
     * 
     */
    public List<QuestionVO> listQuestionRandom_truck(){
    	return questionTruckDAO.listQuestionRandom_truck();
    }
    
    /**
     * 随机获得摩托车题目
     * 
     */
    public List<QuestionVO> listQuestionRandom_moto(){
    	return questionMotoDAO.listQuestionRandom_moto();
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
    public Page<QuestionVO> listQuestionOrder_car(Page<QuestionVO> page,String category){
    	return questionCarDAO.listQuestionOrder_car(page,category);
    }
    
    /**
     * 根据类型分页获取客车
     */
    public Page<QuestionVO> listQuestionOrder_bus(Page<QuestionVO> page,String category){
    	return questionBusDAO.listQuestionOrder_bus(page,category);
    }
    
    /**
     * 根据类型分页获取卡车
     */
    public Page<QuestionVO> listQuestionOrder_truck(Page<QuestionVO> page,String category){
    	return questionTruckDAO.listQuestionOrder_truck(page,category);
    }
    
    /**
     * 根据类型分页获取摩托车
     */
    public Page<QuestionVO> listQuestionOrder_moto(Page<QuestionVO> page,String category){
    	return questionMotoDAO.listQuestionOrder_moto(page,category);
    }
    
    /**
     * 保存标记问题
     * @return
     */
    public int saveMarkQuestion(int questionId,String studentId,String cartype){
    	//首先判断该记录是否存在
    	if(questionDAO.hasMarkQuestion(questionId, studentId, StringConstant.questionType.get(cartype)))
    		return 1;
    	return questionDAO.saveMarkQuestion(questionId, studentId, StringConstant.questionType.get(cartype));
    }
    
    /*****************************顺序练习开始结束*********************************/
    
    
    /*****************************已标记题开始*********************************/
    /**
     * 查询已标记题car
     */
    public Page<QuestionVO> listMarkQuestion(Page<QuestionVO> page,String studentId,String cartype){
    	if(StringConstant.questionType_car==StringConstant.questionType.get(cartype))
    		return questionCarDAO.listMarkQuestionCar(page,studentId,StringConstant.questionType.get(cartype));
    	else if(StringConstant.questionType_bus==StringConstant.questionType.get(cartype))
    		return questionBusDAO.listMarkQuestionBus(page,studentId,StringConstant.questionType.get(cartype));
    		else if(StringConstant.questionType_bus==StringConstant.questionType.get(cartype))
    			return questionTruckDAO.listMarkQuestionTruck(page,studentId,StringConstant.questionType.get(cartype));
    			else if(StringConstant.questionType_bus==StringConstant.questionType.get(cartype))
    				return questionMotoDAO.listMarkQuestionMoto(page,studentId,StringConstant.questionType.get(cartype));
    	return page;
    }
    
    
    /**
     * 删除已标记问题
     */
    public int delMarkQuestion(int questionId,String studentId,String cartype){
    	return questionDAO.delMarkQuestion(questionId,studentId,StringConstant.questionType.get(cartype));
    }
    
    /*****************************已标记题结束*********************************/
    
    
    /*****************************错题练习开始*********************************/
    
    public Page<QuestionVO> listWrongQuestion(Page<QuestionVO> page,String studentId,String cartype){
    	if(StringConstant.questionType_car==StringConstant.questionType.get(cartype))
    		return questionCarDAO.listWrongQuestionCar(page,studentId,StringConstant.questionType.get(cartype));
    	else if(StringConstant.questionType_bus==StringConstant.questionType.get(cartype))
    		return questionBusDAO.listWrongQuestionBus(page,studentId,StringConstant.questionType.get(cartype));
    		else if(StringConstant.questionType_bus==StringConstant.questionType.get(cartype))
    			return questionTruckDAO.listWrongQuestionTruck(page,studentId,StringConstant.questionType.get(cartype));
    			else if(StringConstant.questionType_bus==StringConstant.questionType.get(cartype))
    				return questionMotoDAO.listWrongQuestionMoto(page,studentId,StringConstant.questionType.get(cartype));
    	return page;
    }
    
    /*****************************错题练习结束*********************************/
    
}