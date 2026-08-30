package com.KeyStone.FieldService2.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JvmFilter {

	@Autowired
	private JVMUtil jvmUtil;
	
	private final CustomUserDetailsService customUserDetailsService;
	public JvmFilter(CustomUserDetailsService customUserDetailsService) {
		this.customUserDetailsService= customUserDetailsService;
	}
	
	
	public JvmFilter(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws Exception {
	
		this.customUserDetailsService = null;
		String header =request.getHeader("Authorization");
		String token=null;
		
		if(StringUtils.hasText(header) && header.startsWith("Bearer")) {
			
			token= header.substring(7);
			
		}
		
		if(token !=null && jvmUtil.validateToken(token)) {
			
			String userEmail=jvmUtil.getUserEmail(token);
			UserDetails userDetail=customUserDetailsService.loadUserByUserEmail(userEmail);
			
			UsernamePasswordAuthenticationToken auth= new UsernamePasswordAuthenticationToken(userDetail,null,userDetail.getAuthorities());
	
			auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(auth);
			
		}
		filterChain.doFilter(request, response);
		
	}

		
		}
	

