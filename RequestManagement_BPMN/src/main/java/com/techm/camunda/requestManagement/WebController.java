package com.techm.camunda.requestManagement;

import java.util.ArrayList;
import java.util.HashMap;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.techm.camunda.model.User;

@Controller
public class WebController {
	
	@Autowired
	public RuntimeService runtimeServ;
	
	@Autowired
	public TaskService taskServ;
	
	@Autowired
	ProcessEngine processEngine;
	
	@Autowired
	public TaskListGetter taskListGetter;
	

	@RequestMapping(value="/logout" )
	public String logout(){
		return "login";
	}
	
	@RequestMapping(value="/loginCheck" , method=RequestMethod.POST)
	public String loginCheck(@ModelAttribute("user") User user, Model model){		
		
		String resultJsp="login";		
		
		ProcessInstance instance= runtimeServ.startProcessInstanceByKey("requestMgmt");
		System.out.println(runtimeServ.getActivityInstance(instance.getProcessInstanceId()));
		model.addAttribute("empName", user.getUsername());
		System.out.println(user.getUsername());
		if((user.getUsername().equalsIgnoreCase("Sonam") || user.getUsername().equalsIgnoreCase("rohit")) && (user.getPassword().equals("password"))){		
			Task task= taskListGetter.getTaskByName("Employee");
			task.delegate(user.getUsername());
			taskServ.saveTask(task);
			resultJsp="showSalaryAdvJsp";						
	
		}else if(user.getUsername().equals("Arvind") && (user.getPassword().equals("password"))){
				
				ArrayList<User> userList= new ArrayList<User>();
				for(Task task: taskListGetter.getTaskListWithQuery("Arvind")){
					User userTask= new User();
					System.out.println("task executionId in manager: "+task.getExecutionId());
					System.out.println("emp name in arvind task: "+runtimeServ.getVariable(task.getExecutionId(), "loggedEmp"));
					userTask.setUsername(runtimeServ.getVariable(task.getExecutionId(), "loggedEmp").toString());
					userTask.setSalaryAdvance(runtimeServ.getVariable(task.getExecutionId(), "salaryAdvance").toString());
					userTask.setTaskId(task.getId());
					userTask.setExecutionId(task.getExecutionId());
					userList.add(userTask);					
				}
				model.addAttribute("userList", userList);
				resultJsp="taskView";
			
		}
		return resultJsp;
	}
	
	@RequestMapping(value="/applySalaryAdv" , method=RequestMethod.POST)
	public String applySalaryAdvance(@ModelAttribute("userData") User user, Model model) throws InterruptedException{
		System.out.println("apply salary");
		if(user.getDesignation().equalsIgnoreCase("manager")){
		for(Task task: taskListGetter.getTaskListWithQuery(user.getUsername())){
			boolean isManager=false;
			runtimeServ.setVariable(task.getExecutionId(), "designation", user.getDesignation());			
			isManager=true;
			System.out.println("task execution id: "+task.getExecutionId());	
			
			System.out.println("user name: "+user.getUsername());
			runtimeServ.setVariable(task.getExecutionId(), "loggedEmp", user.getUsername());
			runtimeServ.setVariable(task.getExecutionId(), "isManager", isManager);
			runtimeServ.setVariable(task.getExecutionId(), "salaryAdvance", user.getSalaryAdvance());
			System.out.println("emp name in employee task: "+runtimeServ.getVariable(task.getExecutionId(), "loggedEmp"));		
			completeTask(task.getId());
			taskServ.deleteTask(task.getId());
			
		}
		model.addAttribute("designation", user.getDesignation());
		model.addAttribute("salaryAdvance", user.getSalaryAdvance());
		return "success";
		}else{
			model.addAttribute("msg", "salary advance auto approved and process completed");
			return "assistant";
		}
	}
	
	@RequestMapping(value="/completeTask" , method= RequestMethod.POST)
	public String completeTask(@ModelAttribute("user") User user, Model model){
		System.out.println(user.getTaskList());
		runtimeServ.setVariable(user.getExecutionId(), "approve", true);
		completeTask(user.getTaskId());	
		taskServ.deleteTask(user.getTaskId());
		return "ApprovalResult";
	}

	public void completeTask(String taskId){
		taskServ.complete(taskId);
		
	}
}
