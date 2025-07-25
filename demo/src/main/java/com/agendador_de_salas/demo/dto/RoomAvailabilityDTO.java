package com.agendador_de_salas.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RoomAvailabilityDTO {

    private Long roomId;
    private String roomName;
    private List<DailyAvailability> availableSlots;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class DailyAvailability {
        private LocalDate date;
        private List<LocalTime> availableTimes;
    }

}
