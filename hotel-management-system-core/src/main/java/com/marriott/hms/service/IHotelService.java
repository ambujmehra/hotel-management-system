package com.marriott.hms.service;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.dto.RoomDto;

import java.util.List;

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

    /**
     * Find by hotel name hotel dto.
     *
     * @param hotelName the hotel name
     * @return the hotel dto
     */
    HotelDto findByHotelName(String hotelName);

    /**
     * Find by id hotel dto.
     *
     * @param id the id
     * @return the hotel dto
     */
    HotelDto findById(Integer id);

    /**
     * Gets rooms by hotel id.
     *
     * @param hotelId the hotel id
     * @return the rooms by hotel id
     */
    List<RoomDto> getRoomsByHotelId(Integer hotelId);
}
