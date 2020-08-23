package com.marriott.hms.service;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.dto.RoomDto;
import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;
import com.marriott.hms.exception.HotelManagementSystemException;
import com.marriott.hms.model.Hotel;
import com.marriott.hms.model.Room;
import com.marriott.hms.repository.HotelRepository;
import com.marriott.hms.service.impl.HotelServiceImpl;
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
    }
    @Autowired
    private HotelServiceImpl hotelService;

    @MockBean
    private HotelRepository hotelRepository;

    @Before
    public void setUp() {
        Hotel hotel = Hotel.builder()
                .city("Delhi")
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

}
