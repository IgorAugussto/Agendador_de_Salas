package com.agendador_de_salas.demo.entity;

import com.agendador_de_salas.demo.entity.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Users implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private UserRole role;

    public Users(String email, String password, UserRole role) {
        this.email = email;
        this.password = password;
        this.role = role;

    }

    @Override
    public String getUsername() {
        return this.email; // Mapeia o login como username
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.MASTER) return List.of(new SimpleGrantedAuthority("ROLE_MASTER"),
                new SimpleGrantedAuthority("ROLE_ADMIN"));
        else return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
