package com.marriott.hms.service;

import com.marriott.hms.dto.RoomDto;
import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;
import com.marriott.hms.exception.HotelManagementSystemException;
import com.marriott.hms.model.Hotel;
import com.marriott.hms.model.Room;
import com.marriott.hms.repository.RoomRepository;
import com.marriott.hms.service.impl.RoomServiceImpl;
import com.marriott.hms.service.impl.mapper.RoomMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class RoomServiceTest {

    @TestConfiguration
    static class RoomServiceImplTestContextConfiguration {
        @Bean
        public IRoomService roomService() {
            return new RoomServiceImpl();
        }

        @Bean
        public RoomMapper roomMapper() {
            return new RoomMapper();
        }
    }

    @MockBean
    private RoomRepository roomRepository;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private IRoomService roomService;

    private Hotel hotel;

    @Before
    public void setup() {
        hotel = Hotel.builder().city("Delhi").address("street 10, park avenue").hotelName("Courtyard Mariott").pinCode("!22011").rating(BigDecimal.valueOf(4.3)).build();
        hotel.setId(1);

        Room room  = Room.builder().hotel(hotel).occupancy(2).roomSize(100).roomStatus(RoomStatus.EMPTY).roomTariff(BigDecimal.valueOf(1020)).roomType(RoomType.DOUBLE_OCCUPANCY_ROOM).build();
        room.setId(1);

        Room otherRoom  = Room.builder().hotel(hotel).occupancy(1).roomSize(100).roomStatus(RoomStatus.OCCUPIED).roomTariff(BigDecimal.valueOf(1020)).roomType(RoomType.SINGLE_OCCUPANCY_ROOM).build();
        otherRoom.setId(2);

        Room thirdRoom  = Room.builder().hotel(hotel).occupancy(1).roomSize(100).roomStatus(RoomStatus.OCCUPIED).roomTariff(BigDecimal.valueOf(1020)).roomType(RoomType.SUITE).build();
        otherRoom.setId(3);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        rooms.add(otherRoom);
        rooms.add(thirdRoom);
        hotel.setRooms(rooms);

        List<Room> occupiedAll = new ArrayList<>();
        occupiedAll.add(thirdRoom);
        occupiedAll.add(otherRoom);

        List<Room> allSuite = new ArrayList<>();
        allSuite.add(thirdRoom);
        Mockito.when(roomRepository.findByHotelAndRoomStatus(hotel, RoomStatus.OCCUPIED)).thenReturn(occupiedAll);
        Mockito.when(roomRepository.findByHotelAndRoomType(hotel, RoomType.SUITE)).thenReturn(allSuite);
        Mockito.when(roomRepository.findByHotelAndRoomStatusAndRoomType(hotel, RoomStatus.OCCUPIED, RoomType.SUITE)).thenReturn(allSuite);
        Mockito.when(roomRepository.findByHotelAndId(hotel, 2)).thenReturn(otherRoom);


    }


    @Test
    public void getRoomByHotelIdAndOccupied() {
        List<RoomDto> roomDtos = roomService.findRoomForHotelByRoomStatus(hotel, RoomStatus.OCCUPIED);
        Assert.assertNotNull(roomDtos);
        Assert.assertEquals(roomDtos.size(), 2);
    }

    @Test
    public void getRoomByHotelIdAndSuite() {
        List<RoomDto> roomDtos = roomService.findRoomForHotelByRoomType(hotel,  RoomType.SUITE);
        Assert.assertNotNull(roomDtos);
        Assert.assertEquals(roomDtos.size(), 1);
    }

    @Test
    public void getRoomByHotelIdAndEmptyAndSuite() {
        List<RoomDto> roomDtos = roomService.findRoomForHotelByRoomTypeAndRoomStatus(hotel,  RoomType.SUITE, RoomStatus.OCCUPIED);
        Assert.assertNotNull(roomDtos);
        Assert.assertEquals(roomDtos.size(), 1);
    }

    @Test
    public void testFindByHotelAndRoomId() {
        RoomDto roomDto = roomService.findRoomForHotelAndRoomId(hotel, 2);
        Assert.assertNotNull(roomDto);
        Assert.assertEquals(roomDto.getRoomTariff(), BigDecimal.valueOf(1020));

    }

    @Test
    public void testFindByHotelAndRoomIdRoomNotFound() {
        RoomDto roomDto = null;
        try {
            roomDto = roomService.findRoomForHotelAndRoomId(hotel, 20);
        } catch (HotelManagementSystemException e) {
            Assert.assertEquals(e.getMessage(), "room not found");
            return;
        }
        Assert.assertNotNull(roomDto);
    }
}
