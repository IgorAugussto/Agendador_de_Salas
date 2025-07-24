package com.agendador_de_salas.demo.service;

import com.agendador_de_salas.demo.entity.Booking;
import com.agendador_de_salas.demo.entity.Room;
import com.agendador_de_salas.demo.repository.BookingRepository;
import com.agendador_de_salas.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private RoomRepository roomRepository;

    public Booking createBooking(Booking booking) {

        LocalTime start = booking.getStartTime().toLocalTime();
        LocalTime end = booking.getEndTime().toLocalTime();

        // Busca a sala do banco para garantir que ela existe e está gerenciada pelo JPA
        Room room = roomRepository.findById(booking.getRoom().getId())
                .orElseThrow(() -> new RuntimeException("Sala não encontrada"));

        booking.setRoom(room);

        List<Booking> conflicts = bookingRepository.findConflictingBookings(
                booking.getRoom().getId(),
                booking.getStartTime(),
                booking.getEndTime()
        );

        if (!conflicts.isEmpty()) {
            throw new RuntimeException("Conflito de horário: a sala já está reservada nesse período.");
        }

        DayOfWeek bookingDay = booking.getStartTime().getDayOfWeek();
        if (!room.getAvailableDays().contains(bookingDay)) {
            throw new RuntimeException("Sala indisponível neste dia da semana.");
        }

        if (start.isBefore(room.getOpenTime()) || end.isAfter(room.getCloseTime())) {
            throw new RuntimeException("Horário fora do funcionamento da sala.");
        }


        return bookingRepository.save(booking);
    }


    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Função auxiliar para validar sobreposição de horários
    private boolean timesOverlap(LocalDateTime start1, LocalDateTime end1, LocalDateTime start2, LocalDateTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }
}
