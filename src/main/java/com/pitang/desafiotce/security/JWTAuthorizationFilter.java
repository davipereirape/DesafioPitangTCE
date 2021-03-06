package com.pitang.desafiotce.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.pitang.desafiotce.config.SecurityConfig;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * Filter to verify authorization of user by token.
 */
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;

	private UserDetailsService userDetailsService;
	
	/**
	 * Constructor 
	 * 
	 * @param authenticationManager
	 * @param jwtUtil
	 * @param userDetailsService
	 */
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, 
			UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}

	/**
	 * Verify the authorization of user by token
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain chain) throws IOException, ServletException {
		
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer "))	{
			UsernamePasswordAuthenticationToken auth = this.getAuthentication(header.substring(7));

			if (auth != null) {
				SecurityContextHolder.getContext().setAuthentication(auth);
			} else if (!SecurityConfig.verifyPermissionURI(request.getRequestURI())){
				onUnsuccessfulAuthentication(request, response, new SessionAuthenticationException("Unauthorized - invalid session"));
				return;
			}
		}  else if (!SecurityConfig.verifyPermissionURI(request.getRequestURI())) {
			onUnsuccessfulAuthentication(request, response, new SessionAuthenticationException("Unauthorized"));
			return;
		}

		chain.doFilter(request, response);
	}
	
	/**
	 * Set informations of response in a case of unsuccessful. 
	 */
	protected void onUnsuccessfulAuthentication(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException failed) throws IOException {
		
		response.setStatus(401);
        response.setContentType("application/json"); 
        response.getWriter().append(json(failed));
	}


	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (jwtUtil.isTokenValido(token)) {
			String userName = jwtUtil.getUserName(token);
			UserDetails user = userDetailsService.loadUserByUsername(userName);

			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}
	
	private String json(AuthenticationException failed) {
        return "{\"message\": \"" + failed.getMessage() + "\", "
            + "\"errorCode\": \"1\"}";
    } 
}
