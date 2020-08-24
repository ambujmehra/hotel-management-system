package com.marriott.hms.service;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.dto.RoomDto;
import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;
import com.marriott.hms.exception.HotelManagementSystemException;
import com.marriott.hms.model.Hotel;
import com.marriott.hms.model.Room;
import com.marriott.hms.repository.HotelRepository;
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

    @Before
    public void setUp() {
        Hotel hotel = Hotel.builder().city("Delhi")
                .address("street 10, park avenue")
                .hotelName("Courtyard Mariott")
                .pinCode("!22011")
                .rating(BigDecimal.valueOf(4.3))
                .build();
        hotel.setId(1);

        Room room  = Room.builder()
                .hotel(hotel)
                .occupancy(2)
                .roomSize(100)
                .roomStatus(RoomStatus.EMPTY)
                .roomTariff(BigDecimal.valueOf(1020))
                .roomType(RoomType.DOUBLE_OCCUPANCY_ROOM)
                .build();

        room.setId(1);

        List<Room> rooms = new ArrayList<>();
        rooms.add(room);
        hotel.setRooms(rooms);
        Mockito.when(hotelRepository.findByHotelName(hotel.getHotelName()))
                .thenReturn(hotel);
        Mockito.when(hotelRepository.findById(hotel.getId())).thenReturn(java.util.Optional.of(hotel));
        Mockito.when(hotelRepository.save(Mockito.any(Hotel.class)))
                .thenAnswer(i -> i.getArguments()[0]);
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
        Assert.assertEquals(hotelRooms.size(), 1);
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
                .roomSize(60)
                .roomStatus(RoomStatus.EMPTY)
                .roomTariff(BigDecimal.valueOf(510))
                .roomType(RoomType.SINGLE_OCCUPANCY_ROOM)
                .id(2)
                .build();

        List<RoomDto> roomDtos = hotelService.addRoomToAHotel(hotelId, addRoom);
        Assert.assertNotNull(roomDtos);
        Assert.assertEquals(roomDtos.size(), 2);
    }

}
