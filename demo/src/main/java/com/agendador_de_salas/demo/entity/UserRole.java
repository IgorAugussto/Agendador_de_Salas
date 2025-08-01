package com.agendador_de_salas.demo.entity;

public enum UserRole {
    MASTER("master"),
    ADMIN("admin");

    private String role;

    UserRole(String role){
        this.role = role;
    }

    public String getRole(){
        return role;
    }
}
