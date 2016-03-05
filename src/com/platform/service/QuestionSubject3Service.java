package com.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.constants.StringConstant;
import com.platform.dao.QuestionSubject3DAO;
import com.platform.domain.Section;
import com.platform.vo.Page;
import com.platform.vo.QuestionVO;
import com.platform.vo.ScoreVO;
import com.platform.vo.StatisticVO;

@Service
public class QuestionSubject3Service implements IService {

    private QuestionSubject3DAO questionDAO;
    
    @Override
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
     * 随机获得科目三(摩托车)的题目
     * @return
     */
    public List<QuestionVO> listQuestionRandom_moto(){
    	return questionDAO.listQuestionRandom_moto();
    }
    
    
    /**
     * 保存考试成绩
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public int saveExamScore(String studentId,int score,String time,String carType,String errorQuestions){
    	if(errorQuestions!=null&&!errorQuestions.equals("")){
    		String array[] = errorQuestions.split("@");
    		for(int i=0;i<array.length;i++){
    			questionDAO.saveErrorQuestion(array[i], studentId , StringConstant.questionType.get(carType));
    		}
    	}
    	//剩余次数-1
    	questionDAO.countDownOne(studentId);
    	return questionDAO.saveExamScore(studentId, score, time,StringConstant.questionType.get(carType));
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
    public Page<QuestionVO> listQuestionOrder(Page<QuestionVO> page,String category) throws Exception{
    	return questionDAO.listQuestionOrder(page,category);
    }
    
    
    /**
     * 根据类型分页获取小汽车
     */
    public Page<QuestionVO> listQuestionOrder_moto(Page<QuestionVO> page,String category) throws Exception{
    	return questionDAO.listQuestionOrder_moto(page,category);
    }
    
    /**
     * 保存标记问题
     * @return
     */
    public int saveMarkQuestion(int questionId,String studentId , String carType){
    	//首先判断该记录是否存在
    	if(questionDAO.hasMarkQuestion(questionId, studentId,StringConstant.questionType.get(carType)))
    		return 1;
    	return questionDAO.saveMarkQuestion(questionId, studentId,StringConstant.questionType.get(carType));
    }
    
    /*****************************顺序练习开始结束*********************************/
    
    
    /*****************************已标记题开始*********************************/
    /**
     * 查询已标记题car
     */
    public Page<QuestionVO> listMarkQuestion(Page<QuestionVO> page,String studentId ,String cartype) throws Exception{
    	if(cartype==null||"".equals(cartype)) return null;
    	if(StringConstant.questionType_moto==StringConstant.questionType.get(cartype))
    		return questionDAO.listMarkQuestion_moto(page,studentId,StringConstant.questionType.get(cartype));
    	else
    		return questionDAO.listMarkQuestion(page,studentId,StringConstant.questionType.get(cartype));
    }
    
    
    /**
     * 删除已标记问题
     */
    public int delMarkQuestion(int questionId,String studentId, String carType){
    	if(carType==null||"".equals(carType)) return 0;
    	return questionDAO.delMarkQuestion(questionId,studentId,StringConstant.questionType.get(carType));
    }
    
    /*****************************已标记题结束*********************************/
    
    
    /*****************************错题练习开始*********************************/
    
    public Page<QuestionVO> listWrongQuestion(Page<QuestionVO> page,String studentId,String carType) throws Exception{
    	 return questionDAO.listWrongQuestion(page,studentId,StringConstant.questionType.get(carType));
    }
    
    /**
     * 删除错题问题
     */
    public int delWrongQuestion(int questionId,String studentId, String carType){
    	return questionDAO.delWrongQuestion(questionId,studentId,StringConstant.questionType.get(carType));
    }
    
    /*****************************错题练习结束*********************************/
    
    
    /******************************个人考试信息分析开始**************************/
    public StatisticVO getStatistic(String studentId,String cartype){
    	if(cartype==null||"".equals(cartype)) return null;
    	return questionDAO.statistic(studentId, StringConstant.questionType.get(cartype));
    }
   
    public List<ScoreVO> getScores(String studentId,String cartype){
    	return questionDAO.getScores(studentId, StringConstant.questionType.get(cartype));
    }
    
    /******************************个人考试信息分析结束**************************/
}