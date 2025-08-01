package com.agendador_de_salas.demo.dto;

import com.agendador_de_salas.demo.entity.UserRole;

public record RegisterDTO(String email, String password, UserRole role) {
}
