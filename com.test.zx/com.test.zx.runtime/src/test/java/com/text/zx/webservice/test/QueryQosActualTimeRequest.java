package com.text.zx.webservice.test;


/**
 * @author 李法新
 * @date 2016年6月15日 下午12:24:47
 */
public class QueryQosActualTimeRequest {
	private String taskcode;
	private String taskname;
	private String password;
	private QueryQosActualTimeParam request;

	public String getTaskcode() {
		return taskcode;
	}

	public void setTaskcode(String taskcode) {
		this.taskcode = taskcode;
	}

	public String getTaskname() {
		return taskname;
	}

	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public QueryQosActualTimeParam getRequest() {
		return request;
	}

	public void setRequest(QueryQosActualTimeParam request) {
		this.request = request;
	}
	
}
