package com.marriott.hms.service.impl.mapper;

import com.marriott.hms.dto.RoomDto;
import com.marriott.hms.model.Room;
import com.marriott.hms.service.IMapper;
import org.springframework.stereotype.Component;

/**
 * The type Room mapper.
 *
 * @author ambujmehra
 */
@Component
public class RoomMapper implements IMapper<Room, RoomDto> {

    @Override
    public RoomDto map(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .occupancy(room.getOccupancy())
                .roomSize(room.getRoomSize())
                .roomStatus(room.getRoomStatus())
                .roomTariff(room.getRoomTariff())
                .roomType(room.getRoomType())
                .build();
    }
}
