package com.marriott.hms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum Room type - specifies the type of room
 *
 * @author ambujmehra
 */
@AllArgsConstructor
@Getter
public enum RoomType {

    /**
     * Single occupancy room room type.
     */
    SINGLE_OCCUPANCY_ROOM,
    /**
     * Double occupancy room room type.
     */
    DOUBLE_OCCUPANCY_ROOM,
    /**
     * Triple occupancy room room type.
     */
    TRIPLE_OCCUPANCY_ROOM,
    /**
     * Suite room type.
     */
    SUITE;

}
