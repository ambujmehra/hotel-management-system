package com.marriott.hms.repository;

import com.marriott.hms.enums.RoomStatus;
import com.marriott.hms.enums.RoomType;
import com.marriott.hms.model.Hotel;
import com.marriott.hms.model.Room;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HotelRepositoryIntegrationTest {

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void injectedComponentsAreNotNull() {
        Assert.assertNotNull(hotelRepository);
    }

    @Test
    public void testInsertHotelEntity() {
        Hotel hotel = Hotel.builder()
                .city("Delhi")
                .address("street 10, park avenue")
                .hotelName("Courtyard Mariott")
                .pinCode("!22011")
                .rating(BigDecimal.valueOf(4.3))
                .build();
        hotelRepository.save(hotel);
        Hotel expectedHotel = hotelRepository.findByHotelName(hotel.getHotelName());
        Assert.assertEquals(expectedHotel.getHotelName(), hotel.getHotelName());
    }


    @Test
    public void testFindByHotelName() {
        Hotel expectedHotel = hotelRepository.findByHotelName("Marriott rosymary");
        Assert.assertEquals(expectedHotel.getHotelName(), "Marriott rosymary");
    }

    @Test
    public void testNoOfRoomsAvailableInAHotel() {
        Hotel expectedHotel = hotelRepository.findByHotelName("Marriott rosymary");
        Assert.assertEquals(expectedHotel.getRooms().size(), 2);
    }


    @Test
    public void testNullHotelFindByName() {
        hotelRepository.findByHotelName("Marriott rosymary1");
    }

    @Test
    public void testInsertRoomoAHotel() {
        Hotel expectedHotel = hotelRepository.findByHotelName("Marriott rosymary");
        Assert.assertEquals(expectedHotel.getHotelName(), "Marriott rosymary");
        Room addRoom = Room.builder()
                .roomType(RoomType.DOUBLE_OCCUPANCY_ROOM)
                .roomTariff(BigDecimal.TEN)
                .roomStatus(RoomStatus.EMPTY)
                .roomSize(109)
                .occupancy(2)
                .hotel(expectedHotel)
                .build();

        expectedHotel.getRooms().add(addRoom);
        Hotel updatedHotel = hotelRepository.save(expectedHotel);
        Assert.assertEquals(updatedHotel.getRooms().size(), 4);
    }
}
