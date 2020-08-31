package com.marriott.hms.service.impl;

import com.marriott.hms.dto.RoomDto;
import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;
import com.marriott.hms.exception.HotelManagementSystemException;
import com.marriott.hms.model.Hotel;
import com.marriott.hms.model.Room;
import com.marriott.hms.repository.RoomRepository;
import com.marriott.hms.service.IRoomService;
import com.marriott.hms.service.impl.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImpl implements IRoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Override
    public List<RoomDto> findRoomForHotelByRoomTypeAndRoomStatus(Hotel hotel, RoomType roomType, RoomStatus roomStatus) {
        return roomRepository.findByHotelAndRoomStatusAndRoomType(hotel, roomStatus, roomType)
                .stream()
                .map(room -> roomMapper.map(room))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> findRoomForHotelByRoomType(Hotel hotel, RoomType roomType) {
        return roomRepository.findByHotelAndRoomType(hotel, roomType)
                .stream()
                .map(room -> roomMapper.map(room))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> findRoomForHotelByRoomStatus(Hotel hotel, RoomStatus roomStatus) {
        return roomRepository.findByHotelAndRoomStatus(hotel, roomStatus)
                .stream()
                .map(room -> roomMapper.map(room))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDto findRoomForHotelAndRoomId(Hotel hotel, Integer roomId) {
        Room room = roomRepository.findByHotelAndId(hotel, roomId);
        Optional.ofNullable(room).orElseThrow(() -> new HotelManagementSystemException("room not found"));
        return roomMapper.map(room);
    }


}
