package com.techm.camunda.camunda_rest_sample;

import static org.junit.Assert.assertEquals;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class OLDServiceTaskRestTest {

	
	@Rule
	  public ProcessEngineRule processEngineRule = new ProcessEngineRule();

	  @Test
	  @Deployment
	  public void shouldPackForHoliday() {	    
	    
	    RepositoryService repositoryServ= processEngineRule.getRepositoryService();
	    
	    repositoryServ.createDeployment()
        .addClasspathResource("restSample.bpmn")
        .deploy();

	    RuntimeService runtimeService = processEngineRule.getRuntimeService();
	    TaskService taskService = processEngineRule.getTaskService();

	    runtimeService.startProcessInstanceByKey("restSample");

	    Task task = taskService.createTaskQuery().singleResult();
	    Assert.assertNotNull(task);
	    assertEquals("Pack for holiday", task.getName());

	    boolean isHoliday = Boolean.parseBoolean(taskService.getVariable(task.getId(), "isHoliday").toString());
	    Assert.assertTrue(isHoliday);
	  }

	 /* @Test
	  @Deployment(resources={"restSample.bpmn"})
	  public void shouldPackForWork() {
	    Map<String, Object> variables = new HashMap<String, Object>();
	    variables.put("date", "2014-01-02");

	    RuntimeService runtimeService = processEngineRule.getRuntimeService();
	    TaskService taskService = processEngineRule.getTaskService();

	    runtimeService.startProcessInstanceByKey("restSample", variables);

	    Task task = taskService.createTaskQuery().singleResult();
	    Assert.assertNotNull(task);
	    assertEquals("Pack for work", task.getName());

	    boolean isHoliday = Boolean.parseBoolean(taskService.getVariable(task.getId(), "isHoliday").toString());
	    Assert.assertFalse(isHoliday);
	  }*/

	
}
