package com.techm.camunda.camunda_rest_sample;


import javax.ws.rs.core.MediaType;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class ServiceDelegate implements JavaDelegate{
	
	@Autowired
	public TaskService taskServ;

	public void execute(DelegateExecution execution) throws Exception {
		Client client = Client.create();
        WebResource resource = client.resource("http://localhost:8080/rest-jersey1/webapi/restServ/");
        String color = resource.path("meth1/orange")
                .accept(MediaType.TEXT_PLAIN)
                    .get(String.class);
        
        System.out.println("newColor in serviceTask1 with restServ1: "+color);
        
        execution.setVariable("newColor", color); 
	}

}
