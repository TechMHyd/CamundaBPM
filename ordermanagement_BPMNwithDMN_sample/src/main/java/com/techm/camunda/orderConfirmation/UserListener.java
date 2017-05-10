package com.techm.camunda.orderConfirmation;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.ExecutionListener;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

public class UserListener implements ExecutionListener{
	

	@Autowired
	ProcessEngine processEngine;
	
	@Autowired
	TaskService taskServ;
	
	@Autowired
	TaskListGetter taskList;

	public void notify(DelegateExecution execution) throws Exception {
		
		execution.setVariable("approve", true);
		System.out.println("discount: "+execution.getVariable("discount")+" order amount: "+execution.getVariable("orderAmount")+" approved: "+execution.getVariable("approve"));
		for(Task task: taskList.getTaskListWithQuery("demo")){
			System.out.println(task.getAssignee());
			taskServ.complete(task.getId());			
			
		}		
		
		
		
	}

}
