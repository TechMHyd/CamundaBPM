package com.techm.camunda.camunda_rest_sample;

import java.util.logging.Logger;

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
public class TestRestSampleBpmn {
	
Logger logger= Logger.getLogger(this.getClass().getCanonicalName());
	
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
		
	    
		repositoryServ.createDeployment()
        .addClasspathResource("restSample.bpmn")
        .deploy();
		
		ProcessInstance instance= runtimeServ.startProcessInstanceByKey("restSample");
		System.out.println(runtimeServ.getActivityInstance(instance.getProcessInstanceId()));
		
		 //Task task = taskServ.createTaskQuery().singleResult();
		//System.out.println(taskServ.getVariable(task.getId(), "isItemAvailable"));
		
		 for(Task demoTask: taskServ.createTaskQuery().taskAssignee("demo").list()){
				System.out.println("assignee of the user task: "+demoTask.getAssignee());
			}
		 for(Task johnTask: taskServ.createTaskQuery().taskAssignee("john").list()){
			 System.out.println("assignee of the user task: "+johnTask.getAssignee());
			}
		
	}

}
