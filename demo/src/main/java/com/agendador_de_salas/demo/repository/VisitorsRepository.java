package com.agendador_de_salas.demo.repository;

import com.agendador_de_salas.demo.entity.Visitors;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorsRepository extends JpaRepository<Visitors, Long> {
}
