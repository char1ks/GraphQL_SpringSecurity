package com.example.GraphQL_with_SpringSecurity.Service;

import com.example.GraphQL_with_SpringSecurity.Model.hero;
import com.example.GraphQL_with_SpringSecurity.Security.heroDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.GraphQL_with_SpringSecurity.Repository.heroRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class heroService implements UserDetailsService {
    @Autowired
    private heroRepository operations;

    public List<hero> getAllHeroes(Integer count) {
        if (count != null && count > 0) {
            return operations.findAll().stream()
                    .limit(count)
                    .collect(Collectors.toList());
        }
        return operations.findAll();
    }
    public hero getConcreteHero(int id){
        return operations.findById(id).orElse(null);
    }
    public hero saveHero(String name,String age,String password){
        hero hero=new hero(name,age,password);
        return operations.save(hero);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<hero> heroFind= Optional.ofNullable(operations.findByUsername(username));
        if (heroFind.isEmpty()) {
            throw new UsernameNotFoundException("Клиент не найден");
        }
        return new heroDetails(heroFind.get());
    }
}
