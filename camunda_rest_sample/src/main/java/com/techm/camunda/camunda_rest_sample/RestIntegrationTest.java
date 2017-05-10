package com.techm.camunda.camunda_rest_sample;

import java.util.HashMap;
import java.util.Map;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:com/techm/camunda/camunda_rest_sample/applicationContext.xml")
public class RestIntegrationTest {

	@Autowired
	public RepositoryService repositoryServ;
	
	@Autowired
	public RuntimeService runtimeServ;
	
	@Autowired
	public TaskService taskServ;
	
	@Autowired
	@Rule
	public ProcessEngineRule processEngineRule;
	
	@Test
	@Deployment
	public void testDelegate(){
		
		  Map<String, Object> variables = new HashMap<String, Object>();
		    variables.put("user", "demo"); 
		repositoryServ.createDeployment()
        .addClasspathResource("restIntegration.bpmn") 
        .deploy();
		
		ProcessInstance instance= runtimeServ.startProcessInstanceByKey("restIntegration",variables);
		System.out.println(runtimeServ.getActivityInstance(instance.getProcessInstanceId()));	
		 
		
		for(Task task: taskServ.createTaskQuery().taskAssignee("demo").list()){
			System.out.println("new color: "+taskServ.getVariable(task.getId(), "newColor"));
		}
		
	}
}
