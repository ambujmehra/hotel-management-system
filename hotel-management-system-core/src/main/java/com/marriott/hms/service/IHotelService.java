package com.marriott.hms.service;

import com.marriott.hms.dto.HotelDto;

/**
 * The interface Hotel service.
 *
 * @author ambujmehra
 */
public interface IHotelService {
    /**
     * Create hotel hotel dto.
     *
     * @param hotelDto the hotel dto
     * @return the hotel dto
     */
    HotelDto createHotel(HotelDto hotelDto);

    HotelDto findByHotelName(String hotelName);

    HotelDto findById(Integer id);
}
