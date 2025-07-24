package com.agendador_de_salas.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "room_available_days", joinColumns = @JoinColumn(name = "room_id"))
    @Enumerated(EnumType.STRING)
    private List<DayOfWeek> availableDays;


    private String name;
    private Integer capacity;
    private LocalTime openTime;
    private LocalTime closeTime;
}
