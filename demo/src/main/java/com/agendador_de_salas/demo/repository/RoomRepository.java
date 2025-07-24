package com.agendador_de_salas.demo.repository;

import com.agendador_de_salas.demo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository <Room, Long> {
    //Possível adicionar queries persolanzadas se necessário.
}
