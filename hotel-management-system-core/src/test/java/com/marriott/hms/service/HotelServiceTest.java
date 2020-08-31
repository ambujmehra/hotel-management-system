package com.marriott.hms.service;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.dto.RoomDto;
import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;
import com.marriott.hms.exception.HotelManagementSystemException;
import com.marriott.hms.model.Hotel;
import com.marriott.hms.model.Room;
import com.marriott.hms.repository.HotelRepository;
import com.marriott.hms.service.impl.RoomServiceImpl;
import com.marriott.hms.service.impl.mapper.HotelMapper;
import com.marriott.hms.service.impl.HotelServiceImpl;
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
public class HotelServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {
        @Bean
        public IHotelService hotelService() {
            return new HotelServiceImpl();
        }

        @Bean
        public HotelMapper hotelMapper() {
            return new HotelMapper();
        }

        @Bean
        public RoomMapper roomMapper() {
            return new RoomMapper();
        }

    }
    @Autowired
    private HotelServiceImpl hotelService;

    @MockBean
    private HotelRepository hotelRepository;

    @MockBean
    private IRoomService roomService;

    @Autowired
    private RoomMapper roomMapper;

    @Before
    public void setUp() {
        Hotel hotel = Hotel.builder().city("Delhi").address("street 10, park avenue").hotelName("Courtyard Mariott").pinCode("!22011").rating(BigDecimal.valueOf(4.3)).build();
        hotel.setId(1);

        Room room  = Room.builder().hotel(hotel).occupancy(2).roomSize(100).roomStatus(RoomStatus.EMPTY).roomTariff(BigDecimal.valueOf(1020)).roomType(RoomType.DOUBLE_OCCUPANCY_ROOM).build();
        room.setId(1);

        Room otherRoom  = Room.builder().hotel(hotel).occupancy(1).roomSize(100).roomStatus(RoomStatus.OCCUPIED).roomTariff(BigDecimal.valueOf(1020)).roomType(RoomType.SINGLE_OCCUPANCY_ROOM).build();
        otherRoom.setId(2);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        rooms.add(otherRoom);
        hotel.setRooms(rooms);
        Mockito.when(hotelRepository.findByHotelName(hotel.getHotelName()))
                .thenReturn(hotel);
        Mockito.when(hotelRepository.findById(hotel.getId())).thenReturn(java.util.Optional.of(hotel));
        Mockito.when(hotelRepository.save(Mockito.any(Hotel.class)))
                .thenAnswer(i -> i.getArguments()[0]);

        List<RoomDto> roomDto1 = new ArrayList<>();
        roomDto1.add(RoomDto.builder().build());
        Mockito.when(roomService.findRoomForHotelByRoomStatus(hotel , RoomStatus.OCCUPIED)).thenReturn(roomDto1);

        Mockito.when(roomService.findRoomForHotelByRoomType(hotel , RoomType.SUITE)).thenReturn(roomDto1);

        Mockito.when(roomService.findRoomForHotelByRoomTypeAndRoomStatus(hotel , RoomType.SUITE, RoomStatus.EMPTY)).thenReturn(roomDto1);

        Mockito.when(roomService.findRoomForHotelAndRoomId(hotel, 2)).thenReturn(roomMapper.map(otherRoom));

    }

    @Test
    public void testFindByHotelName() {
        HotelDto expectedHotelDto = hotelService.findByHotelName("Courtyard Mariott");
        Assert.assertNotNull(expectedHotelDto);
        Assert.assertEquals(expectedHotelDto.getHotelName(), "Courtyard Mariott");
    }

    @Test
    public void testNullHotelException() {
        try {
            hotelService.findByHotelName("Courtyard Mariott1");
        } catch (HotelManagementSystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Hotel not found");
        }
    }

    @Test
    public void testFindHotelById() {
        Integer id =1;
        HotelDto hotelDto = hotelService.findById(id);
        Assert.assertNotNull(hotelDto);
        Assert.assertEquals(hotelDto.getHotelName(), "Courtyard Mariott");
    }

    @Test
    public void getRoomsForAHotelTest() {
        Integer id = 1;
        List<RoomDto> hotelRooms = hotelService.getRoomsByHotelId(id);
        Assert.assertNotNull(hotelRooms);
        Assert.assertEquals(hotelRooms.size(), 2);
    }

    @Test
    public void testHotelNotExistInGetRooms() {
        try {
            hotelService.getRoomsByHotelId(1);
        } catch (HotelManagementSystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Hotel not found");
        }
    }

    @Test
    public void testHotelCreate() {
        HotelDto hotelDto = HotelDto.builder()
                .rating(BigDecimal.valueOf(4.1))
                .pinCode("122011")
                .hotelName("JW marriott")
                .address("Park avenue")
                .city("Bangalore")
                .build();
        HotelDto expectedHotelDto = hotelService.createHotel(hotelDto);
        Assert.assertNotNull(expectedHotelDto);
        Assert.assertEquals(expectedHotelDto.getHotelName(), hotelDto.getHotelName());

    }

    @Test
    public void addRoomToAHotelTest() {
        Integer hotelId = 1;
        RoomDto addRoom  = RoomDto.builder()
                .occupancy(1)
                .roomSize(600)
                .roomStatus(RoomStatus.OCCUPIED)
                .roomTariff(BigDecimal.valueOf(5100))
                .roomType(RoomType.SUITE)
                .id(3)
                .build();

        List<RoomDto> roomDtos = hotelService.addRoomToAHotel(hotelId, addRoom);
        Assert.assertNotNull(roomDtos);
        Assert.assertEquals(roomDtos.size(), 3);
    }

    @Test
    public void getRoomByHotelIdAndOccupiedAndAllType() {
        List<RoomDto> roomDtos = hotelService.getRoombyHotelIdAndRoomTypeAndStatus(1, RoomStatus.OCCUPIED, RoomType.ALL);
        Assert.assertNotNull(roomDtos);
        Assert.assertEquals(roomDtos.size(), 1);
    }

    @Test
    public void getRoomByHotelIdAndOccupiedAndSuite() {
        List<RoomDto> roomDtos = hotelService.getRoombyHotelIdAndRoomTypeAndStatus(1, RoomStatus.ALL, RoomType.SUITE);
        Assert.assertNotNull(roomDtos);
        Assert.assertEquals(roomDtos.size(), 1);
    }

    @Test
    public void getRoomByHotelIdAndEmptyAndSuite() {
        List<RoomDto> roomDtos = hotelService.getRoombyHotelIdAndRoomTypeAndStatus(1, RoomStatus.EMPTY, RoomType.SUITE);
        Assert.assertNotNull(roomDtos);
        Assert.assertEquals(roomDtos.size(), 1);
    }


    @Test
    public void testGetRoomByHotelIdAndRoomId() {
        RoomDto roomDto = hotelService.getRoomByHotelIdAndRoomId(1,2);
        Assert.assertNotNull(roomDto);
        Assert.assertEquals(roomDto.getRoomTariff(), BigDecimal.valueOf(1020));
    }

    @Test
    public void testGetRoomByHotelIdAndRoomIdHotelNotFound() {
        RoomDto roomDto;
        try {
            roomDto = hotelService.getRoomByHotelIdAndRoomId(10,2);
        } catch (HotelManagementSystemException exception) {
            Assert.assertEquals(exception.getMessage(), "Hotel not found");
            return;
        }
        Assert.assertNotNull(roomDto);
    }


    @Test
    public void testGetRoomByHotelIdAndRoomIdRoomNotFound() {
        RoomDto roomDto = null;
        try {
            roomDto = hotelService.getRoomByHotelIdAndRoomId(1,20);
        } catch (HotelManagementSystemException e) {
            Assert.assertEquals(e.getMessage(), "room not found");
        }
        //Assert.assertNotNull(roomDto);
    }

}
