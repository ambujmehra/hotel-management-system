package com.marriott.hms.repository;

import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;
import com.marriott.hms.model.Hotel;
import com.marriott.hms.model.Room;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Room repository.
 */
@Repository
public interface RoomRepository extends CrudRepository<Room, Integer> {

    /**
     * Find by hotel and room type list.
     *
     * @param hotel    the hotel
     * @param roomType the room type
     * @return the list
     */
    List<Room> findByHotelAndRoomType(Hotel hotel, RoomType roomType);

    /**
     * Find by hotel and room status list.
     *
     * @param hotel      the hotel
     * @param roomStatus the room status
     * @return the list
     */
    List<Room> findByHotelAndRoomStatus(Hotel hotel, RoomStatus roomStatus);

    /**
     * Find by hotel and room status and room type list.
     *
     * @param hotel      the hotel
     * @param roomStatus the room status
     * @param roomType   the room type
     * @return the list
     */
    List<Room> findByHotelAndRoomStatusAndRoomType(Hotel hotel, RoomStatus roomStatus, RoomType roomType);
}
