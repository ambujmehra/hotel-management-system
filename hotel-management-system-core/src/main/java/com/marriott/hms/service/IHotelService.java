package com.marriott.hms.service;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.dto.RoomDto;
import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;

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

    /**
     * Add room to a hotel list.
     *
     * @param hotelId the hotel id
     * @param roomDto the room dto
     * @return the list
     */
    List<RoomDto> addRoomToAHotel(Integer hotelId, RoomDto roomDto);

    /**
     * Gets roomby hotel id and room type and status.
     *
     * @param hotelId    the hotel id
     * @param roomStatus the room status
     * @param roomType   the room type
     * @return the roomby hotel id and room type and status
     */
    List<RoomDto> getRoombyHotelIdAndRoomTypeAndStatus(Integer hotelId, RoomStatus roomStatus, RoomType roomType);

    //List<RoomDto> getRoombyHotelIdAndRoomTypeAndStatusPaginated((Integer hotelId, RoomStatus roomStatus, RoomType roomType)
}
