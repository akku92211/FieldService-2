package com.KeyStone.FieldService2.Service;

import java.util.Date;
import java.util.UUID;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.KeyStone.FieldService2.DTO.AuthResponseDTO;
import com.KeyStone.FieldService2.DTO.LoginRequestDTO;
import com.KeyStone.FieldService2.DTO.RegisterRequestDTO;
import com.KeyStone.FieldService2.Entity.UserEntity;
import com.KeyStone.FieldService2.Repository.UserRepository;
import com.KeyStone.FieldService2.Security.EmailLogService;
import com.KeyStone.FieldService2.Security.JVMUtil;
import com.KeyStone.FieldService2.Security.TokenBlockService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserAuthService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JVMUtil jvmUtil;
	
	@Autowired
	private PasswordEncoder passswordEncode;
	
	@Autowired
	private EmailLogService emailLogService;
	
	@Autowired
	private TokenBlockService tokenBlockService;

	private PasswordEncoder passwordEncode;
	
	public String register(RegisterRequestDTO register) {
		
		java.util.Optional<UserRepository> existingUser= userRepository.findByUserEmail(register.userEmail);
		
		if(existingUser.isPresent()) {
			throw new RuntimeException("User already exist");
		}
		UserRepository user=  new UserRepository();
		
		User.withUsername(register.userName);
		user.setUsername(register.userEmail);
		user.setPassword(passswordEncode.encode(register.password));
		user.setRole(register.role);
		
		user.save(user);
		
		return "User Register Successfully";
		
	}
	
	public AuthResponseDTO login(LoginRequestDTO login) {
		UserRepository user= userRepository.findByUserEmail(login.userEamil)
		                         .orElseThrow(()-> new RuntimeException("User not found"));
		
		if(!passswordEncode.matches(login.password,user.getPassword())) {
			
			throw new RuntimeException("Invalid credentials");
		}
		String token= jvmUtil.generateToken(user);
		return new AuthResponseDTO(token,"Login successfully");
	}
	
	public void forgotPassword(String userEmail) {
		
		UserRepository user= userRepository.findByUserEmail(userEmail)
		                               .orElseThrow(()-> new RuntimeException("user not found"));
		
		String token = UUID.randomUUID().toString();
		user.setResetTokenExpiry(token);
		user.setResetTokenExpiry(new Date(System.currentTimeMillis()+10*60*1000));
		user.save(user);
		
		emailLogService.sendResetPasswordEmail(userEmail, token);
		}
	
	public void resetPassword(String token,String newPassword) {
		
		UserRepository user = UserRepository.findByResetToken(token)
				.orElseThrow(()-> new RuntimeException("invalid token"));
		
		if(user.getResetTokenExpiry().before(new Date())) {
			throw new RuntimeException("token expired");
		}
		
		user.setPassword(passwordEncode.encode(newPassword));
		user.setResetTokenExpiry(null);
		user.setResetTokenExpiry(null);
		
		user.save(user);
	}
	
	
	public String logout(HttpServletRequest request) {
		
		String header = request.getHeader("Authorization");
		String token = jvmUtil.extractToken(header);
		
		if(token !=null) {
			tokenBlockService.blockListToken(token);
		}
		 return "Logged out successfully";
		
	
	}
}
