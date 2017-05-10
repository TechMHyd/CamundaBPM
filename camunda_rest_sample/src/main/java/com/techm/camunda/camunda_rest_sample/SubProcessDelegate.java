package com.techm.camunda.camunda_rest_sample;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SubProcessDelegate implements JavaDelegate {

	public void execute(DelegateExecution execution) throws Exception {
		execution.setVariable("name", "janaki");
		
	}
	
	

}
