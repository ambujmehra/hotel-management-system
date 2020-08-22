package com.marriott.hms;

import com.marriott.hms.model.Hotel;
import com.marriott.hms.repository.HotelRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
public class HotelRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private HotelRepository hotelRepository;

    @Test
    public void whenFindByNameReturnHotel() {
        Hotel hotel = Hotel.builder()
                .city("Delhi")
                .address("street 10, park avenue")
                .hotelName("Courtyard Mariott")
                .pinCode("!22011")
                .rating(BigDecimal.valueOf(4.3))
                .build();
        entityManager.persist(hotel);
        entityManager.flush();

        Hotel expectedHotel = hotelRepository.findByHotelName(hotel.getHotelName());

        Assert.assertEquals(expectedHotel.getHotelName(), hotel.getHotelName());
    }
}
