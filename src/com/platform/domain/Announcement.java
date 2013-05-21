package com.platform.domain;


public class Announcement  {

    private static final long serialVersionUID = -7094766838338441306L;
    
    private String id;
	private String title;
	private String content;
	private String schoolcard;
	private String createTime;
	

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSchoolcard() {
		return schoolcard;
	}
	public void setSchoolcard(String schoolcard) {
		this.schoolcard = schoolcard;
	}

}