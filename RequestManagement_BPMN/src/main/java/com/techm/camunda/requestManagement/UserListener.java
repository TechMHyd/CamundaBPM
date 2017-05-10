package com.techm.camunda.requestManagement;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

public class UserListener implements ExecutionListener{
	

	@Autowired
	ProcessEngine processEngine;
	
	@Autowired
	TaskService taskServ;
	
	@Autowired
	TaskListGetter taskList;

	public void notify(DelegateExecution execution) throws Exception {
		processEngine.getRuntimeService().setVariable(execution.getId(), "empName", execution.getVariable("empName"));
		System.out.println("<<< execution id: "+execution.getId());
		processEngine.getRuntimeService().setVariable(execution.getId(), "salaryAdv", execution.getVariable("salaryAdvance"));
		//execution.setVariable("empName", execution.getVariable("employeeName"));
		System.out.println("in listener empname>>> "+processEngine.getRuntimeService().getVariable(execution.getId(), "empName"));
		
		
		
	}

}
