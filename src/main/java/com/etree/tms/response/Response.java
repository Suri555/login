package com.etree.tms.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
@JsonInclude(Include.NON_DEFAULT)
public class Response {
	private Object data;
	private Object errors;
	private boolean isApiTimeout;
	private String message;
	private String status;
	public Object getData() {
		return data;
	}

	public Object getErrors() {
		return errors;
	}

	public boolean getIsApiTimeout() {
		return isApiTimeout;
	}

	public String getMessage() {
		return message;
	}

	public String getStatus() {
		return status;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setErrors(Object errors) {
		this.errors = errors;
	}

	public void setIsApiTimeout(boolean isApiTimeout) {
		this.isApiTimeout = isApiTimeout;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
