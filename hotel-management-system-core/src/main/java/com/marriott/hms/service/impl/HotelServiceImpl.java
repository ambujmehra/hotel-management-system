package com.marriott.hms.service.impl;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.dto.RoomDto;
import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;
import com.marriott.hms.exception.HotelManagementSystemException;
import com.marriott.hms.model.Hotel;
import com.marriott.hms.model.Room;
import com.marriott.hms.repository.HotelRepository;
import com.marriott.hms.service.IHotelService;
import com.marriott.hms.service.IRoomService;
import com.marriott.hms.service.impl.mapper.HotelMapper;
import com.marriott.hms.service.impl.mapper.RoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * The type Hotel service.
 */
@Service
public class HotelServiceImpl implements IHotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelMapper hotelMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private IRoomService roomService;

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        Hotel hotel = Hotel.builder()
                .rating(hotelDto.getRating())
                .pinCode(hotelDto.getPinCode())
                .hotelName(hotelDto.getHotelName())
                .city(hotelDto.getCity())
                .address(hotelDto.getAddress())
                .build();
        return hotelMapper.map(hotelRepository.save(hotel));
    }

    @Override
    public HotelDto findByHotelName(String hotelName) {
        Hotel hotel = hotelRepository.findByHotelName(hotelName);
        Optional.ofNullable(hotel).orElseThrow(() -> new HotelManagementSystemException("Hotel not found"));
        return hotelMapper.map(hotel);
    }

    @Override
    public HotelDto findById(Integer id) {
        Hotel hotel = hotelRepository.findById(id).orElse(null);
        Optional.ofNullable(hotel).orElseThrow(() -> new HotelManagementSystemException("Hotel not found"));
        return hotelMapper.map(hotel);
    }

    @Override
    public List<RoomDto> getRoomsByHotelId(Integer hotelId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        Optional.ofNullable(hotel).orElseThrow(() -> new HotelManagementSystemException("Hotel not found"));
        return hotel.getRooms().stream().map(room -> roomMapper.map(room)).collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> addRoomToAHotel(Integer hotelId, RoomDto roomDto) {
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        Optional.ofNullable(hotel).orElseThrow(() -> new HotelManagementSystemException("Hotel not found"));
        Room addRoom = Room.builder()
                .roomType(roomDto.getRoomType())
                .roomTariff(roomDto.getRoomTariff())
                .roomStatus(roomDto.getRoomStatus())
                .roomSize(roomDto.getRoomSize())
                .occupancy(roomDto.getOccupancy())
                .hotel(hotel)
                .build();
        hotel.getRooms().add(addRoom);
        hotelRepository.save(hotel);
        return hotel.getRooms().stream().map(room -> roomMapper.map(room)).collect(Collectors.toList());
    }

    @Override
    public List<RoomDto> getRoombyHotelIdAndRoomTypeAndStatus(Integer hotelId, RoomStatus roomStatus, RoomType roomType) {
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        Optional.ofNullable(hotel).orElseThrow(() -> new HotelManagementSystemException("Hotel not found"));
        List<RoomDto> roomDtos;
        if (roomStatus.equals(RoomStatus.ALL))
            roomDtos = roomService.findRoomForHotelByRoomType(hotel, roomType);
        else if(roomType.equals(RoomType.ALL))
            roomDtos = roomService.findRoomForHotelByRoomStatus(hotel, roomStatus);
        else
            roomDtos = roomService.findRoomForHotelByRoomTypeAndRoomStatus(hotel, roomType, roomStatus);

        return roomDtos;
    }

    @Override
    public RoomDto getRoomByHotelIdAndRoomId(Integer hotelId, Integer roomId) {
        Hotel hotel = hotelRepository.findById(hotelId).orElse(null);
        Optional.ofNullable(hotel).orElseThrow(() -> new HotelManagementSystemException("Hotel not found"));
        return roomService.findRoomForHotelAndRoomId(hotel, roomId);
    }


}
