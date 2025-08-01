package com.agendador_de_salas.demo.repository;

import com.agendador_de_salas.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
    UserDetails findByEmail(String email);
}
