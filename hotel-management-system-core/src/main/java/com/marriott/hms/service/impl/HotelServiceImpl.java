package com.marriott.hms.service.impl;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.exception.HotelManagementSystemException;
import com.marriott.hms.model.Hotel;
import com.marriott.hms.repository.HotelRepository;
import com.marriott.hms.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HotelServiceImpl implements IHotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Override
    public HotelDto createHotel(HotelDto hotelDto) {
        return null;
    }

    @Override
    public HotelDto findByHotelName(String hotelName) {
        Hotel hotel =  hotelRepository.findByHotelName(hotelName);
        Optional.ofNullable(hotel).orElseThrow(() -> new HotelManagementSystemException("Hotel not found"));
        return HotelDto.builder()
                .id(hotel.getId())
                .address(hotel.getAddress())
                .city(hotel.getCity())
                .hotelName(hotel.getHotelName())
                .pinCode(hotel.getPinCode())
                .rating(hotel.getRating())
                .build();
    }

    @Override
    public HotelDto findById(Integer id) {
        Hotel hotel = hotelRepository.findById(id).get();
        Optional.ofNullable(hotel).orElseThrow(() -> new HotelManagementSystemException("Hotel not found"));
        return HotelDto.builder()
                .id(hotel.getId())
                .address(hotel.getAddress())
                .city(hotel.getCity())
                .hotelName(hotel.getHotelName())
                .pinCode(hotel.getPinCode())
                .rating(hotel.getRating())
                .build();
    }
}
