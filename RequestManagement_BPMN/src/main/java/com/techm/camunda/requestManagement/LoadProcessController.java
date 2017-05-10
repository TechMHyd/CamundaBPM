package com.techm.camunda.requestManagement;

import org.camunda.bpm.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoadProcessController {
	
	@Autowired
	public RepositoryService repositoryServ;
		
	@RequestMapping(value="/loadProcess" )
	public String loadProcess(){
		System.out.println("in load process");	
				
		repositoryServ.createDeployment()
        .addClasspathResource("salaryinadvance.bpmn")
        .deploy();
		
		return "login";
	}	
	
}
