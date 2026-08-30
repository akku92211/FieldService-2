package com.KeyStone.FieldService2.Security;

import org.hibernate.pretty.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.KeyStone.FieldService2.DTO.EmailLogDTO;
import com.KeyStone.FieldService2.Entity.EmailLogin;
import com.KeyStone.FieldService2.Repository.EmailLogRepository;


@Service
public class EmailLogService<MimeMessage> {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EmailLogin  emailLogin;
	
	public String sendResetPasswordEmail(String to,String token) {
		String resetPasswordLink= "http://localhost:6868/auth/reset-password?token="+token;
		
		SimpleMailMessage=new SimpleMessage();
		message.setTo(to);
		message.setSubject("Reset your password");
		message.setText("Click the link to reset password:\n"+resetPsswordLink);
		
		
		javaMailSender.send(message);
		return "Sent resetPassword link to youremail";
		
	}
	
	public String notify(EmailLogDTO emailLog) {
		
		boolean sentStatus= false;
		
		try {
			MimeMessage message= javaMailSender.createMimeMessage();
			MessageHelper helper= new MessageHelper(message,true);
			helper.setTo(emailLog.receptientEmail);
			helper.setSubject(emailLog.subject);
			helper.setText(emailLog.body,true);
			
			javaMailSender.send(message);
			sentStatus=true;
		}catch(Exception e) {
			sentStatu= false;
		}
		
		EmailLogin logs= new EmailLogin(emailLog.receptientEmail,emailLog.subject,emailLog.body);
		emailLogin.save(logs);
		
		return sentStatus ?"Email sent Successfully":"Email sending failed";
	}
	
}
