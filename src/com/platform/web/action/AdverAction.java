package com.platform.web.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.domain.Adver;
import com.platform.service.AdverService;


@Controller
@Scope("prototype")
public class AdverAction extends GenericAction {

	private static final long serialVersionUID = 1797119564459862667L;
	@Autowired
	private AdverService adverService;
	private Adver adver;

	/**
	 * 分页查询
	 * @return
	 */
	public String list(){
		try{
			page = adverService.pageAdver(page);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return SUCCESS;
	}

	/**
	 * 保存
	 * @return
	 */
	public String save(){
		try{
			adverService.saveAdver(adver);
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 删除
	 * @return
	 */
	public String delete(){
		try{
			adverService.delAdver(adver.getId());
		}catch(Exception e){
			e.printStackTrace();
		}
		return SUCCESS;
	}
}