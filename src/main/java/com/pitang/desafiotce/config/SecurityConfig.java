package com.pitang.desafiotce.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.pitang.desafiotce.security.JWTAuthenticationFilter;
import com.pitang.desafiotce.security.JWTAuthorizationFilter;
import com.pitang.desafiotce.security.JWTUtil;

/**
 * @author Davi Pereira <pereraidavipe@gmail.com>
 * @since July/2020
 * 
 * Parameter configuration class for spring security
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private Environment env;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private JWTUtil jwtUtil;
	
	private static final String[] PUBLIC_MATCHERS_ALWAYS = {
			"/h2-console/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/users/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_POST_PUT = {
			"/users/**"
	};
	
	private static final String[] PUBLIC_MATCHERS_DELETE = {
			"/users/**"
	};
	
	/**
	 * Configuration the public matchers, filter among others.
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}
		
		http.cors().and().csrf().disable();
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
		.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST_PUT).permitAll()
		.antMatchers(HttpMethod.PUT, PUBLIC_MATCHERS_POST_PUT).permitAll()
		.antMatchers(HttpMethod.DELETE, PUBLIC_MATCHERS_DELETE).permitAll()
		.antMatchers(PUBLIC_MATCHERS_ALWAYS).permitAll().anyRequest().authenticated();

		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	/**
	 * Cors configuration for permission in a session scope. 
	 * It will be user to remove the CSRF controll
	 * 
	 * @return CorsConfigurationSource
	 */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("*/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}
	
	/**
	 * Password Encoder
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * verify if urisent is a public matchers
	 * 
	 * @param uriSent
	 * @return true if urisent is public, false if urisent is not a public matchers.
	 */
	public static boolean verifyPermissionURI(String uriSent) {
		List<String> listPublicURI = new ArrayList<String>();
		listPublicURI.addAll(Arrays.asList(PUBLIC_MATCHERS_ALWAYS));
		listPublicURI.addAll(Arrays.asList(PUBLIC_MATCHERS_GET));
		listPublicURI.addAll(Arrays.asList(PUBLIC_MATCHERS_POST_PUT));
		listPublicURI.addAll(Arrays.asList(PUBLIC_MATCHERS_DELETE));
		
		for (String uri : listPublicURI) {
			if (uri.contains(uriSent.subSequence(0, 5)))
				return true;
		}
		
		return false;
	}
	
}
