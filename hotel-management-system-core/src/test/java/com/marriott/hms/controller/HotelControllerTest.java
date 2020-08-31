package com.marriott.hms.controller;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.service.IHotelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(HotelController.class)
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IHotelService iHotelService;

    @Before
    public void setup() {

        HotelDto hotel = HotelDto.builder().city("Delhi").address("street 10, park avenue").hotelName("Courtyard Mariott").pinCode("!22011").rating(BigDecimal.valueOf(4.3)).build();
        hotel.setId(1);

        Mockito.when(iHotelService.findById(1)).thenReturn(hotel);
    }


    @Test
    public void testGetHotelById() throws Exception {
        mockMvc.perform(get("/hms/hotel/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
        .andExpect(jsonPath("$.data").exists())
        .andExpect(jsonPath("$.data.hotelName").value("Courtyard Mariott"));
    }

    @Test
    public void testindRoomByHotelIdAndRoomId() throws Exception {
        mockMvc.perform(get("/hms/hotel/1/room/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").exists())
                .andExpect(jsonPath("$.data.roomType").value("DOUBLE_OCCUPANCY_ROOM"));
    }



}
