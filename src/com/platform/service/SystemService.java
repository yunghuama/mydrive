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
import com.platform.vo.ScoreVO;
import com.platform.vo.StatisticVO;

@Service
public class SystemService implements IService {

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

   
}