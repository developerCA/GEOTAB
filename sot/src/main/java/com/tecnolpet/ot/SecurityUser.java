package com.tecnolpet.ot;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tecnolpet.ot.seguridad.UsuarioAuthenticate;

	public class SecurityUser extends UsuarioAuthenticate implements UserDetails {

	private static final long serialVersionUID = 1L;

	
	
	public SecurityUser(UsuarioAuthenticate user) {
		if (user != null) {
			
			this.id=user.getId();
			this.setUsername(user.getUsername());
			this.setPassword(user.getPassword());
			this.setNombresCompletos(user.getNombresCompletos());
			this.setPermisos(user.getPermisos());
			this.setSucursal(user.getSucursal());
			this.setPerfil_empresa(user.getPerfil_empresa());
			this.setRuta(user.getRuta());
			this.setEmail(user.getEmail());
					
		}
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();

		return authorities;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	


}
