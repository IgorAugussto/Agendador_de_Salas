package com.agendador_de_salas.demo.service;

import com.agendador_de_salas.demo.entity.Room;
import com.agendador_de_salas.demo.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    // Criar nova sala
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    // Buscar todas as salas
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Buscar uma sala por ID
    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    // Atualizar uma sala
    public Room updateRoom(Long id, Room updatedRoom) {
        Optional<Room> existingRoom = roomRepository.findById(id);
        if (existingRoom.isPresent()) {
            Room room = existingRoom.get();
            room.setName(updatedRoom.getName());
            room.setCapacity(updatedRoom.getCapacity());
            return roomRepository.save(room);
        } else {
            return null;
        }
    }

    // Deletar uma sala
    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
