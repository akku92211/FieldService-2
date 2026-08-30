package com.KeyStone.FieldService2.Security;

import java.security.Permissions;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.KeyStone.FieldService2.Entity.UserEntity;
import com.KeyStone.FieldService2.Repository.UserRepository;

@Service
public abstract class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepo;
	public CustomUserDetailsService(UserRepository userRepo) {
		this.userRepo=userRepo;
	
	}
	
	public UserDetails loadUserByUserEmail(String userEmail) throws Exception{
		
		UserRepository user=userRepo.findByUserEmail(userEmail).orElseThrow(()-> new RuntimeException("User not found"));
		
		Set<Permissions>perms= RoleBasedPermissions.getRoleBasedPermission().get(user.getRole().name());
		
		return new org.springframework.security.core.userdetails.User(user.getUserEmail(), user.getPassword(), null);
	}
	
}
	



