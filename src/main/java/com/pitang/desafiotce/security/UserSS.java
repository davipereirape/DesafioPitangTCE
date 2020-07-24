package com.pitang.desafiotce.security;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

/**
 * @author Davi Pereira <pereiradavipe@gmail.com>
 * @since July of 2020
 * 
 * UserDatails necessary in framework spring boot authentication.
 */
public class UserSS implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	@Getter
	private Integer id;
	
	@Getter
	private String login;
	
	@Getter
	private String password;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public UserSS() {
	}

	public UserSS(Integer id, String login, String password) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.authorities = new ArrayList<GrantedAuthority>();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public String getUsername() {
		return login;
	}
	
}
