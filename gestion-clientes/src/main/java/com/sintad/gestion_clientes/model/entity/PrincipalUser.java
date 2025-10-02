package com.sintad.gestion_clientes.model.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class PrincipalUser implements UserDetails {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4040838263744368050L;
	private UserEntity user;
	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
		return List.of(new SimpleGrantedAuthority(user.getRole()));
	}

	@Override
	public String getPassword() {
		return this.user.getPassword();
	}

	@Override
	public String getUsername() {
		return this.user.getUsername();
	}

}
