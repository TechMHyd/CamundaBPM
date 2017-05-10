package com.techm.camunda.orderConfirmation;

import java.util.List;

import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

public class TaskListGetter {
	
	@Autowired
	public TaskService taskServ;
	
	public List<Task> getTaskList(){		
		 List<Task> taskList=taskServ.createTaskQuery().list();		
		 return taskList;
	}
	public List<Task> getTaskListWithQuery(String asginee){		
		 List<Task> taskList=taskServ.createTaskQuery().taskAssignee(asginee).list();		
		 return taskList;
	}

}
