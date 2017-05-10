package com.techm.camunda.model;

import java.util.HashMap;

public class User {
	
	private String username;
	private String password;
	private HashMap<String, String> userDesignationMap;
	private String salaryAdvance;
	private String designation;
	private String taskId;
	private String executionId;
	private String[] taskList;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String[] getTaskList() {
		return taskList;
	}
	public void setTaskList(String[] taskList) {
		this.taskList = taskList;
	}
	public HashMap<String, String> getUserDesignationMap() {
		return userDesignationMap;
	}
	public void setUserDesignationMap(HashMap<String, String> userDesignationMap) {
		this.userDesignationMap = userDesignationMap;
	}
	public String getSalaryAdvance() {
		return salaryAdvance;
	}
	public void setSalaryAdvance(String salaryAdvance) {
		this.salaryAdvance = salaryAdvance;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	
	
	

}
