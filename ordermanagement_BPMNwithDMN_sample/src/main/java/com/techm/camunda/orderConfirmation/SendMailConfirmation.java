package com.techm.camunda.orderConfirmation;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class SendMailConfirmation implements JavaDelegate{

	private MailSender mailSender;

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("sending mail....");
		/*SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom("janakimesineni@gmail.com");
		message.setTo("janakimesineni@gmail.com");
		message.setSubject("your order confirmed");
		message.setText("your order placed with amount "+execution.getVariable("orderAmount") +" and reach you back with order status.");
		mailSender.send(message);*/
		
	}

}
