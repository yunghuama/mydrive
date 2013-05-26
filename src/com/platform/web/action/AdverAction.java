package com.platform.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.platform.constants.StringConstant;
import com.platform.domain.Adver;
import com.platform.domain.AttachedFile;
import com.platform.service.AdverService;
import com.platform.util.ConfigureUtil;
import com.platform.util.FileHelper;
import com.platform.util.UploadHelper;


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
			if(upload!=null&&upload.size()>0){
				ConfigureUtil configure = new ConfigureUtil();
				String path = configure.get(StringConstant.PATH_IMAGE_ADVER);
				UploadHelper helper = new UploadHelper(upload, uploadFileName, uploadTitle, uploadContentType, path, UploadHelper.NAME_UNIX_TIME);
				List<AttachedFile> list = helper.getAttachedFiles();
				if(list!=null&&list.size()>0){
					AttachedFile af = list.get(0);
					adver.setImage(af.getFileName());
					adver.setPathName(af.getExtendName());
				}
			}
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

	public Adver getAdver() {
		return adver;
	}

	public void setAdver(Adver adver) {
		this.adver = adver;
	}
}