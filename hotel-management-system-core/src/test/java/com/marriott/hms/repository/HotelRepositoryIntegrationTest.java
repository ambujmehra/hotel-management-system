package com.marriott.hms.repository;

import com.marriott.hms.model.Hotel;
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

}
