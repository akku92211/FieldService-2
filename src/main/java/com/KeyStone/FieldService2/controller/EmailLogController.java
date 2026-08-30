package com.KeyStone.FieldService2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.KeyStone.FieldService2.DTO.EmailLogDTO;
import com.KeyStone.FieldService2.Security.EmailLogService;


@RestController
@RequestMapping("/api/email_log")

public class EmailLogController {

	@Autowired
	private EmailLogService emailService;
	
	@PostMapping("/resetPasswordEmail")
	public ResponseEntity<String>sendResetPasswordEmail(@RequestParam String to,@PathVariable String token){
		String result= emailService.sendResetPasswordEmail(to, token);
		return ResponseEntity.ok(result);
	}
	
	
	@PostMapping("/notify")
	public ResponseEntity<String>notification(@RequestBody EmailLogDTO){
		String result= emailService.notify(emailLog);
		return ResponseEntity.ok(result);
	}
	
}
