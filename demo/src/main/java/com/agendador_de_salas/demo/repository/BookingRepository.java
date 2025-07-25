package com.agendador_de_salas.demo.repository;

import com.agendador_de_salas.demo.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Long> {

    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND " +
            "(:startTime < b.endTime AND :endTime > b.startTime)")
    List<Booking> findConflictingBookings(Long roomId, LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT b FROM Booking b WHERE b.room.id = :roomId AND b.startTime BETWEEN :start AND :end")
    List<Booking> findBookingsInRange(@Param("roomId") Long roomId,
                                      @Param("start") LocalDateTime start,
                                      @Param("end") LocalDateTime end);

}
