package com.techm.camunda.loanApproval;

import org.camunda.bpm.application.PostDeploy;
import org.camunda.bpm.application.ProcessApplication;
import org.camunda.bpm.application.impl.ServletProcessApplication;
import org.camunda.bpm.engine.CaseService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.variable.Variables;

@ProcessApplication("Loan Approval App bpmn")
public class LoanApprovalController extends ServletProcessApplication{
	
	@PostDeploy
	public void startInstance(ProcessEngine processEngine){
		CaseService caseServ= processEngine.getCaseService();
		caseServ.createCaseInstanceByKey("loan_application");
		Variables.createVariables().putValue("applicationSufficient", Variables.booleanValue(true))
		.putValue("rating", Variables.integerValue(4));
	}
	

}
