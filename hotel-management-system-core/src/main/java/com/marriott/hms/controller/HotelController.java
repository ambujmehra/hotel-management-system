package com.marriott.hms.controller;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.dto.ResponseDto;
import com.marriott.hms.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hms")
public class HotelController {

    @Autowired
    private IHotelService iHotelService;

    @RequestMapping(value = "/hotel/{hotel_id}", method = RequestMethod.GET)
    ResponseDto<HotelDto> getHotelById(@PathVariable(name = "hotel_id") Integer hotelId) {
        HotelDto hotelDto = iHotelService.findById(hotelId);
        return new ResponseDto<>(hotelDto);
    }

}
