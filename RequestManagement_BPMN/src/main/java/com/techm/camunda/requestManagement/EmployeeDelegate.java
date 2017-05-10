package com.techm.camunda.requestManagement;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class EmployeeDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		boolean isManager=Boolean.FALSE;
		if(execution.getVariable("designation").toString().equalsIgnoreCase("manager")){
			isManager=Boolean.TRUE;
		}
		execution.setVariable("isManager", isManager);
	}
}
