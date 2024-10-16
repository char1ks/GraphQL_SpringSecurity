package com.example.GraphQL_with_SpringSecurity.Query;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.GraphQL_with_SpringSecurity.Security.heroDetails;
import com.example.GraphQL_with_SpringSecurity.Service.heroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.example.GraphQL_with_SpringSecurity.Model.hero;

import java.util.List;

@Component
public class heroQuery implements GraphQLQueryResolver {
    @Autowired
    private heroService operations;

    @Autowired
    private AuthenticationManager authenticationManager; // Используем AuthenticationManager

    public List<hero> allHero(Integer count) {
        return operations.getAllHeroes(count);
    }

    @PreAuthorize("isAuthenticated()")
    public hero concreteHero(int id) {
        return operations.getConcreteHero(id);
    }

    public hero loginHero(String name, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(name, password);
        try {
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication); // Сохраняем аутентификацию
            heroDetails userDetails = (heroDetails) authentication.getPrincipal();
            return userDetails.getHero();
        } catch (AuthenticationException e) {
            throw new RuntimeException("Authentication failed: " + e.getMessage(), e);
        }
    }
}