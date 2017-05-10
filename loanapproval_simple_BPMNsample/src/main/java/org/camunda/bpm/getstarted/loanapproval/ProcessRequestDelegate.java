package org.camunda.bpm.getstarted.loanapproval;

import java.util.logging.Logger;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class ProcessRequestDelegate implements JavaDelegate {

	  private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");

	  public void execute(DelegateExecution execution) throws Exception {
		  boolean isApprovalAmt=validateForm(execution.getVariable("customerId").toString(), Double.valueOf(execution.getVariable("amount").toString()));
	    LOGGER.info("Processing request by '"+execution.getVariable("customerId")+"'...");
	    execution.setVariable("isApprovableAmount", isApprovalAmt);
	    LOGGER.info("loan approvable'"+isApprovalAmt+"'...");
	  }
	  
	  public boolean validateForm(String customerId, double amount){
		  boolean isApprovableAmount=Boolean.FALSE;
		  if(customerId.equalsIgnoreCase("Jm00493106")&& amount<300000){
			  isApprovableAmount=true;
		  }
		  return isApprovableAmount;
	  }

	}
