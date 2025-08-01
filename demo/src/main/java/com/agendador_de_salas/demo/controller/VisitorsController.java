package com.agendador_de_salas.demo.controller;

import com.agendador_de_salas.demo.entity.Visitors;
import com.agendador_de_salas.demo.service.VisitorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/visitors")
public class VisitorsController {

    @Autowired
    private VisitorsService visitorsService;

    @PostMapping
    public ResponseEntity<Visitors> createVisitor(@RequestBody Visitors visitors) {
        Visitors savedVisitor = visitorsService.createVisitor(visitors);
        return ResponseEntity.ok(savedVisitor);
    }

    @GetMapping
    public ResponseEntity<List<Visitors>> getAllVisitors () {
        return ResponseEntity.ok(visitorsService.getAllVisitors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Visitors> getVisitorsById (@PathVariable Long id) {
        return visitorsService.getVisitorsById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Visitors> updateVisitors (@PathVariable Long id, @RequestBody Visitors updateVisitors) {
        Visitors visitors = visitorsService.updateVisitors(id, updateVisitors);
        if (visitors != null){
            return ResponseEntity.ok(visitors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Visitors> deleteVisitors (@PathVariable Long id) {
        visitorsService.deleteVisitors(id);
        return ResponseEntity.noContent().build();
    }
}
