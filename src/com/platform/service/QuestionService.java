package com.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.platform.dao.QuestionDAO;
import com.platform.domain.Question;
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
    
}