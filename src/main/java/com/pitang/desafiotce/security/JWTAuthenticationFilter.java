package com.pitang.desafiotce.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pitang.desafiotce.dto.CredentialsDTO;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * Filter to configure spring boot authentication.
 *
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter (AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	
	/**
	 * Used for spring boot to verify the credentials.
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) 
			throws AuthenticationException {
		
		try {
			CredentialsDTO creds = new ObjectMapper().readValue(req.getInputStream(), CredentialsDTO.class);
			UsernamePasswordAuthenticationToken authToken 
				= new UsernamePasswordAuthenticationToken(creds.getLogin(), creds.getPassword(), new ArrayList<>());
			
			Authentication auth = authenticationManager.authenticate(authToken);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Used to configure information(token, etc) in case of success authentication. 
	 */
	@Override
	public void successfulAuthentication(HttpServletRequest req, HttpServletResponse res,
			FilterChain chain, Authentication auth) throws IOException, ServletException {
		
		String userName = ((UserSS) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generationToken(userName);
		res.addHeader("Authorization", "Bearer" + token);
	}

}
