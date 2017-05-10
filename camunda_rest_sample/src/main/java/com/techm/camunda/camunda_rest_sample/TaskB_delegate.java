package com.techm.camunda.camunda_rest_sample;

import javax.ws.rs.core.MediaType;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;


public class TaskB_delegate implements JavaDelegate{

	public void execute(DelegateExecution execution) throws Exception {
		
		Client client = Client.create();
        WebResource resource = client.resource("http://localhost:8080/rest-jersey1/webapi/restServ/");
        
        // Get response  
        
        String color = resource.path("meth2/"+execution.getVariable("newColor"))
                .accept(MediaType.TEXT_PLAIN)
                    .get(String.class);
        
        System.out.println("newColor in serviceTask2 with restServ2: "+color);
        
        execution.setVariable("newColor", color); 
		
	}
}
