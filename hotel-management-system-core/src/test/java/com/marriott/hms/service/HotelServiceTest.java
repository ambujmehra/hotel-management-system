package com.marriott.hms.service;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.model.Hotel;
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

        Mockito.when(hotelRepository.findByHotelName(hotel.getHotelName()))
                .thenReturn(hotel);
    }

    @Test
    public void testFindByHotelName() {
        HotelDto expectedHotelDto = hotelService.findByHotelName("Courtyard Mariott");
        Assert.assertNotNull(expectedHotelDto);
        Assert.assertEquals(expectedHotelDto.getHotelName(), "Courtyard Mariott");
    }

}
