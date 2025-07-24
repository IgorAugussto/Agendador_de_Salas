package com.agendador_de_salas.demo.service;

import com.agendador_de_salas.demo.entity.Visitors;
import com.agendador_de_salas.demo.repository.VisitorsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitorsService {

    @Autowired
    private VisitorsRepository visitorsRepository;

    public Visitors createVisitor(Visitors visitors) {
        return visitorsRepository.save(visitors);
    }

    public List<Visitors> getAllVisitors () {
        return visitorsRepository.findAll();
    }

    public Optional<Visitors> getVisitorsById(Long id) {
        return visitorsRepository.findById(id);
    }

    public Visitors updateVisitors(Long id, Visitors updateVisitors) {
        Optional<Visitors> existingVisitor = visitorsRepository.findById(id);
        if (existingVisitor.isPresent()){
            Visitors visitors = existingVisitor.get();
            visitors.setName(updateVisitors.getName());
            visitors.setAge(updateVisitors.getAge());
            visitors.setPhone(updateVisitors.getPhone());
            return visitorsRepository.save(visitors);
        } else {
            return null;
        }
    }

    public void deleteVisitors(Long id) {
        visitorsRepository.deleteById(id);
    }
}
