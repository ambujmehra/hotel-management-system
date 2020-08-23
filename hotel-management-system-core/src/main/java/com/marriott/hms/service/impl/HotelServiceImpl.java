package com.marriott.hms.service.impl;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.dto.RoomDto;
import com.marriott.hms.exception.HotelManagementSystemException;
import com.marriott.hms.model.Hotel;
import com.marriott.hms.repository.HotelRepository;
import com.marriott.hms.service.IHotelService;
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

}
