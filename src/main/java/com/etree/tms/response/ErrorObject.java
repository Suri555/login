package com.etree.tms.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErrorObject {
	private String code;
	private String detail;
	private String source;
	private String title;
	
	
	
	
	public String getCode() {
		return code;
	}
	public String getDetail() {
		return detail;
	}
	public String getSource() {
		return source;
	}
	public String getTitle() {
		return title;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	

}
