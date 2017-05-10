package com.techm.camunda.camunda_rest_sample;

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
public class TestSubProcess {
	
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
			    
		//Subprocess test
		repositoryServ.createDeployment().addClasspathResource("subProcess.bpmn").deploy();	
		ProcessInstance instance= runtimeServ.startProcessInstanceByKey("subProcessEx");
		System.out.println(runtimeServ.getActivityInstance(instance.getProcessInstanceId()));	
		 
		
		for(Task task: taskServ.createTaskQuery().taskAssignee("demo").list()){
			System.out.println("items in user task1: "+taskServ.getVariable(task.getId(), "name"));
			taskServ.setVariable(task.getId(), "location", "hyderabd");
			taskServ.setVariable(task.getId(), "approved", false);
			taskServ.complete(task.getId());
		}
		for(Task task: taskServ.createTaskQuery().taskAssignee("john").list()){
			System.out.println("items in user task2: "+taskServ.getVariable(task.getId(), "location"));			
		}
		
		for(Task task: taskServ.createTaskQuery().taskAssignee("peter").list()){
			System.out.println("items in user task3: "+taskServ.getVariable(task.getId(), "location"));			
		}
		
		/*//receive task
		
		repositoryServ.createDeployment().addClasspathResource("receiveTask.bpmn").deploy();	
		ProcessInstance pi= runtimeServ.startProcessInstanceByKey("receiveTask");
		Execution execution = runtimeServ.createExecutionQuery()
				  .processInstanceId(pi.getId()).activityId("waitState").singleResult();
		runtimeServ.signal(execution.getId());
		System.out.println(pi);*/
		
	}

}
