package com.marriott.hms.controller;

import com.marriott.hms.dto.HotelDto;
import com.marriott.hms.dto.ResponseDto;
import com.marriott.hms.dto.RoomDto;
import com.marriott.hms.service.IHotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Hotel controller.
 */
@RestController
@RequestMapping("/hms")
public class HotelController {

    @Autowired
    private IHotelService iHotelService;

    /**
     * Gets hotel by id.
     *
     * @param hotelId the hotel id
     * @return the hotel by id
     */
    @RequestMapping(value = "/hotel/{hotel_id}", method = RequestMethod.GET)
    ResponseDto<HotelDto> getHotelById(@PathVariable(name = "hotel_id") Integer hotelId) {
        HotelDto hotelDto = iHotelService.findById(hotelId);
        return new ResponseDto<>(hotelDto);
    }

    /**
     * Find room by hotel id and room id response dto.
     *
     * @param hotelId the hotel id
     * @param roomId  the room id
     * @return the response dto
     */
    @RequestMapping(value =  "/hotel/{hotel_id}/room/{room_id}", method = RequestMethod.GET)
    public ResponseDto<RoomDto> findRoomByHotelIdAndRoomId(@PathVariable(name = "hotel_id") Integer hotelId,
                                                           @PathVariable(name = "room_id") Integer roomId) {
        RoomDto roomDto = iHotelService.getRoomByHotelIdAndRoomId(hotelId, roomId);
        return new ResponseDto<>(roomDto);
    }

}
