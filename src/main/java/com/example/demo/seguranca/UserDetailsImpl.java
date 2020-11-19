package com.example.demo.seguranca;

import java.util.Collection;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.model.Usuario;

public class UserDetailsImpl implements UserDetails{
	private static final long serialVersionUID = 1L; //versão 

	private String userName;
	private String password;
	private List<GrantedAuthority> authorities; //COLECTION DA ROLE

	public UserDetailsImpl(Usuario user) { //CONSTRUTOR DE CLASSE
		this.userName = user.getUsuario();
		this.password = user.getSenha();		
	}

	public UserDetailsImpl() {}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true; //aqui costuma ser if else pra dar caminhos e diferentes situações
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
} 