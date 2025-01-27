package com.FMeneguzzi.demo_park.api.jwt;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtUserDetails extends User {

    private com.FMeneguzzi.demo_park.api.entities.User usuario;

    public JwtUserDetails(com.FMeneguzzi.demo_park.api.entities.User usuario) {
        super(usuario.getUsername(), usuario.getPassword(), AuthorityUtils.createAuthorityList(usuario.getRole().name()));
        this.usuario = usuario;
    }

    public Long getId(){
        return this.usuario.getId();
    }

    public String getRole(){
        return this.usuario.getRole().name();
    }
}
