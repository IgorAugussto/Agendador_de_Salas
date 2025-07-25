package com.agendador_de_salas.demo.controller;

import com.agendador_de_salas.demo.dto.RoomAvailabilityDTO;
import com.agendador_de_salas.demo.service.RoomAvailabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomAvailabilityController {

    @Autowired
    private RoomAvailabilityService availabilityService;

    @GetMapping("/available")
    public ResponseEntity<List<RoomAvailabilityDTO>> getAvailableRooms() {
        List<RoomAvailabilityDTO> availability = availabilityService.getAvailabilityForNext30Days();
        return ResponseEntity.ok(availability);
    }

}
