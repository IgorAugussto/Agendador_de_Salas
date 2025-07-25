package com.agendador_de_salas.demo.service;

import com.agendador_de_salas.demo.dto.RoomAvailabilityDTO;
import com.agendador_de_salas.demo.entity.Booking;
import com.agendador_de_salas.demo.entity.Room;
import com.agendador_de_salas.demo.repository.BookingRepository;
import com.agendador_de_salas.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomAvailabilityService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BookingRepository bookingRepository;

    public List<RoomAvailabilityDTO> getAvailabilityForNext30Days() {
        List<Room> rooms = roomRepository.findAll();
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusDays(30);
        List<RoomAvailabilityDTO> availabilityList = new ArrayList<>();

        for (Room room : rooms) {
            List<Booking> bookings = bookingRepository.findBookingsInRange(
                    room.getId(),
                    today.atStartOfDay(),
                    endDate.atTime(LocalTime.MAX)
            );

            Map<LocalDate, List<LocalTime>> dailyAvailability = new HashMap<>();

            for (int i = 0; i <= 30; i++) {
                LocalDate date = today.plusDays(i);
                DayOfWeek dayOfWeek = date.getDayOfWeek();

                if (room.getAvailableDays().contains(dayOfWeek)) {
                    List<LocalTime> times = new ArrayList<>();
                    LocalTime time = room.getOpenTime();

                    while (time.isBefore(room.getCloseTime())) {
                        LocalDateTime slotStart = date.atTime(time);

                        boolean isReserved = bookings.stream().anyMatch(b ->
                                b.getStartTime().equals(slotStart)
                        );

                        if (!isReserved) {
                            times.add(time);
                        }

                        time = time.plusHours(1); // Ajustável
                    }

                    if (!times.isEmpty()) {
                        dailyAvailability.put(date, times);
                    }
                }
            }

            List<RoomAvailabilityDTO.DailyAvailability> slotList = dailyAvailability.entrySet().stream()
                    .map(e -> new RoomAvailabilityDTO.DailyAvailability(e.getKey(), e.getValue()))
                    .toList();

            RoomAvailabilityDTO dto = new RoomAvailabilityDTO();
            dto.setRoomId(room.getId());
            dto.setRoomName(room.getName());
            dto.setAvailableSlots(slotList);

            availabilityList.add(dto);
        }

        return availabilityList;
    }

}
