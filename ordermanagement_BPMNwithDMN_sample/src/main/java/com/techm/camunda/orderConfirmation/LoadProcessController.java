package com.techm.camunda.orderConfirmation;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoadProcessController {
	
	@Autowired
	public RepositoryService repositoryServ;

	@Autowired
	public RuntimeService runtimeServ;	
		
	@RequestMapping(value="/loadProcess" )
	public String loadProcess(){
		System.out.println("in load process");
		
		repositoryServ.createDeployment().addClasspathResource("discountRules.dmn").deploy();
		
		repositoryServ.createDeployment().addClasspathResource("orderItems.bpmn").deploy();
		
		VariableMap variable= Variables.createVariables().putValue("orderAmount", 600);		
		ProcessInstance instance= runtimeServ.startProcessInstanceByKey("orderProcess",variable);
		System.out.println(runtimeServ.getActivityInstance(instance.getProcessInstanceId()));		
		return "login";
	}	
	
}
