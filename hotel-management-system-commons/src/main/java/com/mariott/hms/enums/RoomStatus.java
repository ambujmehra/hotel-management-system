package com.mariott.hms.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum Room status - specifies if a room is empty or full
 *
 * @author ambujmehra
 */
@Getter
@AllArgsConstructor
public enum RoomStatus {

    /**
     * Empty room status.
     */
    EMPTY,

    /**
     * Occupied room status.
     */
    OCCUPIED;
}
