package com.marriott.hms.service;

import com.marriott.hms.dto.RoomDto;
import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;
import com.marriott.hms.model.Hotel;

import java.util.List;

/**
 * The interface Room service.
 *
 * @author ambujmehra
 */
public interface IRoomService {

    /**
     * Find room for hotel by room type and room status list.
     *
     * @param hotel      the hotel
     * @param roomType   the room type
     * @param roomStatus the room status
     * @return the list
     */
    List<RoomDto> findRoomForHotelByRoomTypeAndRoomStatus(Hotel hotel, RoomType roomType, RoomStatus roomStatus);

    /**
     * Find room for hotel by room type list.
     *
     * @param hotel    the hotel
     * @param roomType the room type
     * @return the list
     */
    List<RoomDto> findRoomForHotelByRoomType(Hotel hotel, RoomType roomType);

    /**
     * Find room for hotel by room status list.
     *
     * @param hotel      the hotel
     * @param roomStatus the room status
     * @return the list
     */
    List<RoomDto> findRoomForHotelByRoomStatus(Hotel hotel, RoomStatus roomStatus);


    /**
     * Find room for hotel and room id room dto.
     *
     * @param hotel  the hotel
     * @param roomId the room id
     * @return the room dto
     */
    RoomDto findRoomForHotelAndRoomId(Hotel hotel, Integer roomId);

}
