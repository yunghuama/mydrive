package com.platform.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.platform.constants.SQLConstant;
import com.platform.dao.AdverDAO;
import com.platform.domain.Adver;
import com.platform.vo.Page;

@Service
public class AdverService implements IService {

    private AdverDAO adverDAO;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    	adverDAO = AdverDAO.getInstance(jdbcTemplate);
    }

  
    /**
     * 分页查询
     * @param page
     * @return
     */
    public Page<Adver> pageAdver(Page page){
    	return adverDAO.pageAdver(page);
    }
    
    /**
     * 保存广告
     * @param adver
     * @return
     */
    public int saveAdver(Adver adver){
    	return adverDAO.saveAdver(adver);
    }
    
    public List<Adver> listAdver(int page,int position){
    	return adverDAO.listAdver(page, position);
    }
    
    
    public int delAdver(String id){
    	return adverDAO.deleteByProperty(SQLConstant.ADVER_DEL, id);
    }
}