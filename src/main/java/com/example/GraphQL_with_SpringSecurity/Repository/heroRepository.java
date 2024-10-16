package com.example.GraphQL_with_SpringSecurity.Repository;

import com.example.GraphQL_with_SpringSecurity.Model.hero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface heroRepository extends JpaRepository<hero,Integer> {
    hero findByUsername(String username);
}
