package com.abc.empapp.security.jwt;


import com.abc.empapp.domain.entity.AppUsers;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

@AllArgsConstructor
public class MyUserDetails implements UserDetails {
	private AppUsers user;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		//String authority = user.getRole();
		SimpleGrantedAuthority a = new SimpleGrantedAuthority(user.getRole());
		System.out.println("--->> Inside MyUserDetails class :- "+a.getAuthority());
		return Arrays.asList(a);
      
	}
	@Override
	public String getPassword() {
		String password = user.getPassword();
		return password;
	}
	@Override
	public String getUsername() {
		String username = user.getUsername();
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {		return true;	}

	@Override
	public boolean isAccountNonLocked() {	return true; }

	@Override
	public boolean isCredentialsNonExpired() {		return true;}

	@Override
	public boolean isEnabled() {	return true;	}

	
}
