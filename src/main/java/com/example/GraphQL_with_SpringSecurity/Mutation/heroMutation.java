package com.example.GraphQL_with_SpringSecurity.Mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.example.GraphQL_with_SpringSecurity.Model.hero;
import com.example.GraphQL_with_SpringSecurity.Service.heroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class heroMutation implements GraphQLMutationResolver {
    @Autowired
    private heroService operations;

    public hero createHero(String name,String age,String password){
        return operations.saveHero(name,age,password);
    }
}
