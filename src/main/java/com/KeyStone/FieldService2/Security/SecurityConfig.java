package com.KeyStone.FieldService2.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception {

        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JvmFilter jvmFilter(
            JVMUtil jvmUtil,
            CustomUserDetailsService customUserDetailsService) {

        return new JvmFilter(jvmUtil, customUserDetailsService);
    }

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            JvmFilter jvmFilter) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .sessionManagement(session ->
                session.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            )

            .authorizeHttpRequests(auth ->
                auth
                .requestMatchers(
                        "/api/site/all",
                	    "/",
                	    "/index.html",
                	    "/Login.html",   
                	    "/register.html",
                        "/dashboard.html",
                	    "/workorder.html",
                	    "/customer.html",       
                	    "/add-customer.html",
                        "/site.html",
                        "/technician.html",
                        "/edit-technician.html",
                	    "/Css/**",
                	    "/css/**",
                	    "/js/**",
                	    "/api/user_auth/**",
                	    "/api/email_log/**",
                	    "/api/workorders/**",
                	    "/api/customer/**",
                	    "/api/site/**",
                	    "/api/technician/**"
                	)
                	.permitAll()
                    .anyRequest()
                    .permitAll()
                    )

            .addFilterBefore(
                jvmFilter,
                UsernamePasswordAuthenticationFilter.class
            );

        return http.build();
    }
}