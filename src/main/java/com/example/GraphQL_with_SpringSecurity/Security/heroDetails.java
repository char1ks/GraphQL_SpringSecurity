package com.example.GraphQL_with_SpringSecurity.Security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import com.example.GraphQL_with_SpringSecurity.Model.hero;


public class heroDetails implements UserDetails {

    private hero hero;

    public heroDetails(com.example.GraphQL_with_SpringSecurity.Model.hero hero) {
        this.hero = hero;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return hero.getPassword();
    }

    @Override
    public String getUsername() {
        return hero.getUsername();
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

    public com.example.GraphQL_with_SpringSecurity.Model.hero getHero() {
        return hero;
    }
}
