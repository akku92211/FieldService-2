package com.KeyStone.FieldService2.Security;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.KeyStone.FieldService2.DTO.EmailLogDTO;
import com.KeyStone.FieldService2.Entity.EmailLogin;
import com.KeyStone.FieldService2.Repository.EmailLogRepository;

import jakarta.mail.internet.MimeMessage;


@Service
public class EmailLogService {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmailLogRepository emailLogRepository;

	SimpleMailMessage message = new SimpleMailMessage();
	
	public String sendResetPasswordEmail(String to,String token) {
		String resetPasswordLink= "http://localhost:6868/auth/reset-password?token="+token;
		
		
		message.setTo(to);
		message.setSubject("Reset your password");
		message.setText("Click the link to reset password:\n"+resetPasswordLink);
        javaMailSender.send(message);
		return "Sent resetPassword link to youremail";
		
	}
	
	public String notify(EmailLogDTO emailLog) {
		
		boolean sentStatus= false;
		
		try {
			MimeMessage message= javaMailSender.createMimeMessage();
			MimeMessageHelper helper= new MimeMessageHelper(message,true);
			helper.setTo(emailLog.receptientEmail);
			helper.setSubject(emailLog.subject);
			helper.setText(emailLog.body,true);
			
			javaMailSender.send(message);
			sentStatus=true;
		}catch(Exception e) {
			sentStatus= false;
		}
		
		EmailLogin logs= new EmailLogin(emailLog.receptientEmail,emailLog.subject,emailLog.body);
		emailLogRepository.save(logs);
		
		return sentStatus ?"Email sent Successfully":"Email sending failed";
	}
	
}
