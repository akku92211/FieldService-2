package com.KeyStone.FieldService2.Security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JvmFilter extends OncePerRequestFilter {

    private final JVMUtil jvmUtil;
    private final CustomUserDetailsService customUserDetailsService;

    public JvmFilter(
            JVMUtil jvmUtil,
            CustomUserDetailsService customUserDetailsService) {

        this.jvmUtil = jvmUtil;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        String token = null;

        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {
            token = header.substring(7);
        }

        if (token != null && jvmUtil.validateToken(token)) {

            String userEmail = jvmUtil.getUserEmail(token);

            UserDetails userDetails =
                    customUserDetailsService.loadUserByUsername(userEmail);

            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

            authentication.setDetails(
                    new WebAuthenticationDetailsSource()
                            .buildDetails(request)
            );

            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}