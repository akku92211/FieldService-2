package com.KeyStone.FieldService2.Service;

import java.util.Date;
import java.util.UUID;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.KeyStone.FieldService2.Enum.Role;

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
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailLogService emailLogService;

    @Autowired
    private TokenBlockService tokenBlockService;

    public String register(RegisterRequestDTO register) {

        java.util.Optional<UserEntity> existingUser =
                userRepository.findByUserEmail(register.userEmail);

        if (existingUser.isPresent()) {
            throw new RuntimeException("User already exists");
        }

        UserEntity user = new UserEntity();

        user.setUserName(register.userName);
        user.setUserEmail(register.userEmail);
        user.setPhone(register.phone);
        user.setPassword(passwordEncoder.encode(register.password));
        user.setRole(register.role);

        userRepository.save(user);

        return "User Register Successfully";
    }

    public AuthResponseDTO login(LoginRequestDTO login) {
    	
        String email = login.userEmail.trim();


        UserEntity user = userRepository.findByUserEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found" + email));

        if (!passwordEncoder.matches(
                login.password,
                user.getPassword())) {

            throw new RuntimeException("Invalid credentials");
        }

        String token = jvmUtil.generateToken(user);

        return new AuthResponseDTO(token, "Login successfully");
    }

    public void forgotPassword(String userEmail) {

        UserEntity user = userRepository.findByUserEmail(userEmail)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();

        user.setResetToken(token);

        user.setResetTokenExpiry(
                new Date(System.currentTimeMillis()
                        + 10 * 60 * 1000));

        userRepository.save(user);

        emailLogService.sendResetPasswordEmail(
                userEmail, token);
    }

    public void resetPassword(
            String token,
            String newPassword) {

        UserEntity user = userRepository
                .findByResetToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Invalid token"));

        if (user.getResetTokenExpiry().before(new Date())) {
            throw new RuntimeException("Token expired");
        }

        user.setPassword(
                passwordEncoder.encode(newPassword));

        user.setResetToken(null);
        user.setResetTokenExpiry(null);

        userRepository.save(user);
    }

    public String logout(HttpServletRequest request) {

        String header =
                request.getHeader("Authorization");

        String token =
                jvmUtil.extractToken(header);

        if (token != null) {
            tokenBlockService.blockListToken(token);
        }

        return "Logged out successfully";
    }
    
    public List<UserEntity> getTechnicians() {
        return userRepository.findByRole(Role.TECHNICIAN);
    }
    
    public UserEntity updateTechnician(Long id, UserEntity updatedTechnician) {

        UserEntity technician = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        technician.setUserName(updatedTechnician.getUserName());
        technician.setUserEmail(updatedTechnician.getUserEmail());
        technician.setPhone(updatedTechnician.getPhone());

        return userRepository.save(technician);
    }
    
    public void deleteTechnician(Long id) {

        UserEntity technician = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        userRepository.delete(technician);
    }
}