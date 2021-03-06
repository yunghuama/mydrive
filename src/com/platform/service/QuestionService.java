package com.platform.service;

import java.util.List;

import com.platform.domain.QuestionTable;
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
import com.platform.dao.QuestionSubject3DAO;
import com.platform.dao.QuestionTruckDAO;
import com.platform.domain.Question;
import com.platform.domain.Section;
import com.platform.vo.Page;
import com.platform.vo.QuestionVO;
import com.platform.vo.ScoreVO;
import com.platform.vo.StatisticVO;

@Service
public class QuestionService implements IService {

    private QuestionDAO questionDAO;
    private QuestionBusDAO questionBusDAO;
    private QuestionCarDAO questionCarDAO;
    private QuestionTruckDAO questionTruckDAO;
    private QuestionMotoDAO questionMotoDAO;
    private QuestionSubject3DAO questionSubject3DAO;
    
    @Override
	@Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	questionDAO = QuestionDAO.getInstance(jdbcTemplate);
    	questionBusDAO = QuestionBusDAO.getInstance(jdbcTemplate);
    	questionCarDAO = QuestionCarDAO.getInstance(jdbcTemplate);
    	questionTruckDAO = QuestionTruckDAO.getInstance(jdbcTemplate);
    	questionMotoDAO = QuestionMotoDAO.getInstance(jdbcTemplate);
    	questionSubject3DAO = QuestionSubject3DAO.getInstance(jdbcTemplate);
    }

    /**
     * 保存小车类型题库
     * @param question
     * @return
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public int saveQuestion_car(Question question,String code){
    	return questionCarDAO.saveQuestion_Car(question,code);
    }
    
    /**
     * 保存客车类型题库
     * @param question
     * @return
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public int saveQuestion_bus(Question question,String code){
    	return questionBusDAO.saveQuestion_Bus(question,code);
    }
    
    /**
     * 保存卡车类型题库
     * @param question
     * @return
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public int saveQuestion_truck(Question question,String code){
    	return questionTruckDAO.saveQuestion_Truck(question,code);
    }
    
    /**
     * 保存摩托车类型题库
     * @param question
     * @return
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public int saveQuestion_moto(Question question,String code){
    	return questionMotoDAO.saveQuestion_Moto(question,code);
    }
    
    /**
     * 保存摩托车类型题库
     * @param question
     * @return
     */
    @Transactional(rollbackFor={Exception.class,RuntimeException.class})
    public int saveQuestion3(Question question,String code){
    	return questionSubject3DAO.saveQuestion3(question,code);
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
    	return questionDAO.saveExamScore(studentId, score, time, StringConstant.questionType.get(cartype));
    }
    
    /**
     * 剩余次数-1
     */
    public int countDownOne(String id){
    	return questionDAO.countDownOne(id);
    }
    
    
    /*****************************顺序练习开始*********************************/
    /**
     * 获得章节
     */
    public List<Section> getSection(String type,String code){
    	return questionDAO.getSection(type,code);
    }
 
    /**
     * 根据类型分页获取小汽车
     */
    public Page<QuestionVO> listQuestionOrder_car(Page<QuestionVO> page,String category) throws Exception{
    	return questionCarDAO.listQuestionOrder_car(page,category);
    }
    
    /**
     * 根据类型分页获取客车
     */
    public Page<QuestionVO> listQuestionOrder_bus(Page<QuestionVO> page,String category) throws Exception {
    	return questionBusDAO.listQuestionOrder_bus(page,category);
    }
    
    /**
     * 根据类型分页获取卡车
     */
    public Page<QuestionVO> listQuestionOrder_truck(Page<QuestionVO> page,String category) throws Exception{
    	return questionTruckDAO.listQuestionOrder_truck(page,category);
    }
    
    /**
     * 根据类型分页获取摩托车
     */
    public Page<QuestionVO> listQuestionOrder_moto(Page<QuestionVO> page,String category) throws Exception{
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
    
    
    /*****************************已标记题开始*********************************/
    /**
     * 查询已标记题car
     */
    public Page<QuestionVO> listMarkQuestion(Page<QuestionVO> page,String studentId,String cartype) throws Exception{
    	if(cartype==null||"".equals(cartype)) return null;
    	if(StringConstant.questionType_car==StringConstant.questionType.get(cartype))
    		return questionCarDAO.listMarkQuestionCar(page,studentId,StringConstant.questionType.get(cartype));
    	else if(StringConstant.questionType_bus==StringConstant.questionType.get(cartype))
    		return questionBusDAO.listMarkQuestionBus(page,studentId,StringConstant.questionType.get(cartype));
    		else if(StringConstant.questionType_truck==StringConstant.questionType.get(cartype))
    			return questionTruckDAO.listMarkQuestionTruck(page,studentId,StringConstant.questionType.get(cartype));
    			else if(StringConstant.questionType_moto==StringConstant.questionType.get(cartype))
    				return questionMotoDAO.listMarkQuestionMoto(page,studentId,StringConstant.questionType.get(cartype));
    	return page;
    }
    
    
    /**
     * 删除已标记问题
     */
    public int delMarkQuestion(int questionId,String studentId,String cartype){
    	return questionDAO.delMarkQuestion(questionId,studentId,StringConstant.questionType.get(cartype));
    }
    
    
    /**
     * 删除错题问题
     */
    public int delWrongQuestion(int questionId,String studentId,String cartype){
    	return questionDAO.delWrongQuestion(questionId,studentId,StringConstant.questionType.get(cartype));
    }
    
    
    /*****************************错题练习开始*********************************/
    
    public Page<QuestionVO> listWrongQuestion(Page<QuestionVO> page,String studentId,String cartype) throws Exception{
    	if(cartype==null) return null;
    	if(StringConstant.questionType_car==StringConstant.questionType.get(cartype))
    		return questionCarDAO.listWrongQuestionCar(page,studentId,StringConstant.questionType.get(cartype));
    	else if(StringConstant.questionType_bus==StringConstant.questionType.get(cartype))
    		return questionBusDAO.listWrongQuestionBus(page,studentId,StringConstant.questionType.get(cartype));
    		else if(StringConstant.questionType_truck==StringConstant.questionType.get(cartype))
    			return questionTruckDAO.listWrongQuestionTruck(page,studentId,StringConstant.questionType.get(cartype));
    			else if(StringConstant.questionType_moto==StringConstant.questionType.get(cartype))
    				return questionMotoDAO.listWrongQuestionMoto(page,studentId,StringConstant.questionType.get(cartype));
    	return page;
    }
    
    
    /******************************个人考试信息分析开始**************************/
    
    public StatisticVO getStatistic(String studentId,String cartype){
    	if(cartype==null||"".equals(cartype)) return null;
    	return questionDAO.statistic(studentId, StringConstant.questionType.get(cartype));
    }
    
    public List<ScoreVO> getScores(String studentId,String cartype){
    	if(cartype==null||"".equals(cartype)) return null;
     	return questionDAO.getScores(studentId,StringConstant.questionType.get(cartype));
    }
    
    /*******************************题库管理开始***********************************/
    public Page<QuestionVO> listQuestion(Page<QuestionVO> page,int cartype,String type) throws Exception{
    	if(StringConstant.questionType_car== cartype)
    		return questionCarDAO.listQuestionOrderAll_car(page,type);
    	else if(StringConstant.questionType_bus== cartype)
    		return questionBusDAO.listQuestionOrderAll_bus(page,type);
    		else if(StringConstant.questionType_truck== cartype)
    			return questionTruckDAO.listQuestionOrderAll_truck(page,type);
    			else if(StringConstant.questionType_moto== cartype)
    				return questionMotoDAO.listQuestionOrderAll_moto(page,type);
    				else if(StringConstant.questionType_3 == cartype)
    					return questionSubject3DAO.listQuestionAll(page,type);
    	return page;
    }

    /**
     * 获得命名规则
     * @return
     */
    public List<QuestionTable> questionTableList(){
           return questionDAO.questionTableList();
    }

    /**
     * 
     * @param question
     * @param cartype
     * @return
     */
    public int saveQuestion(Question question,int cartype,String code){
    	if(StringConstant.questionType_car== cartype)
    		return questionCarDAO.saveQuestion_Car(question,code);
    	else if(StringConstant.questionType_bus== cartype)
    		return questionBusDAO.saveQuestion_Bus(question,code);
    		else if(StringConstant.questionType_truck== cartype)
    			return questionTruckDAO.saveQuestion_Truck(question,code);
    			else if(StringConstant.questionType_moto== cartype)
    				return questionMotoDAO.saveQuestion_Moto(question,code);
    				else if(StringConstant.questionType_3 == cartype)
    					return questionSubject3DAO.saveQuestion3(question,code);
    	return 0;
    }
    
    /**
     * 根据ID 查询问题类型
     * @param id
     * @param cartype
     * @return
     */
    public Question findQuestionById(int id,int cartype,String code){
    	if(StringConstant.questionType_car== cartype)
    		return questionCarDAO.findQuestionById(id,code);
    	else if(StringConstant.questionType_bus== cartype)
    		return questionBusDAO.findQuestionById(id,code);
    		else if(StringConstant.questionType_truck== cartype)
    			return questionTruckDAO.findQuestionById(id,code);
    			else if(StringConstant.questionType_moto== cartype)
    				return questionMotoDAO.findQuestionById(id,code);
    				else if(StringConstant.questionType_3 == cartype)
    					return questionSubject3DAO.findQuestionById(id,code);
    	return null;
    }
    
    /**
     * 根据ID 更新问题
     * @param cartype
     * @return
     */
    public int updateQuestion(Question question,int cartype,String code){
    	if(StringConstant.questionType_car== cartype)
    		return questionCarDAO.updateQuestion(question,code);
    	else if(StringConstant.questionType_bus== cartype)
    		return questionBusDAO.updateQuestion(question,code);
    		else if(StringConstant.questionType_truck== cartype)
    			return questionTruckDAO.updateQuestion(question,code);
    			else if(StringConstant.questionType_moto== cartype)
    				return questionMotoDAO.updateQuestion(question,code);
    				else if(StringConstant.questionType_3 == cartype)
    					return questionSubject3DAO.updateQuestion(question,code);
    	return 0;
    }
    
    /**
     * 根据ID 更新问题
     * @param id
     * @param cartype
     * @return
     */
    public int deleteQuestion(String id,int cartype){
    	if(StringConstant.questionType_car== cartype)
    		return questionCarDAO.deleteByProperty(SQLConstant.QUESTION_CAR_DELETE_BY_ID, id);
    	else if(StringConstant.questionType_bus== cartype)
    		return questionBusDAO.deleteByProperty(SQLConstant.QUESTION_BUS_DELETE_BY_ID, id);
    		else if(StringConstant.questionType_truck== cartype)
    			return questionTruckDAO.deleteByProperty(SQLConstant.QUESTION_TRUCK_DELETE_BY_ID, id);
    			else if(StringConstant.questionType_moto== cartype)
    				return questionMotoDAO.deleteByProperty(SQLConstant.QUESTION_MOTO_DELETE_BY_ID, id);
    				else if(StringConstant.questionType_3 == cartype)
    					return questionSubject3DAO.deleteByProperty(SQLConstant.QUESTION3_DELETE_BY_ID, id);
    	return 0;
    }
}