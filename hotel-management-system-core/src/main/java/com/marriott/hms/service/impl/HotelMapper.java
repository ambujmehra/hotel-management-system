package com.marriott.hms.service.impl;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.model.Hotel;
import com.marriott.hms.service.IMapper;
import org.springframework.stereotype.Component;

/**
 * The type Hotel mapper.
 *
 * @author ambujmehra
 */
@Component
public class HotelMapper implements IMapper<Hotel, HotelDto> {

    @Override
    public HotelDto map(Hotel hotel) {
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
