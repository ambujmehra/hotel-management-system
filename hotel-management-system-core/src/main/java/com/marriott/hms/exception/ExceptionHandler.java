package com.marriott.hms.exception;

import com.marriott.hms.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The type Exception handler.
 */
@ControllerAdvice
public class ExceptionHandler {

    /**
     * Handle exception response entity.
     *
     * @param hotelException the hotel exception
     * @return the response entity
     */
    @org.springframework.web.bind.annotation.ExceptionHandler({HotelManagementSystemException.class})
    public ResponseEntity<Object> handleException(
            HotelManagementSystemException hotelException) {
        List<String> exception = new ArrayList<>(Collections.singletonList(hotelException.getMessage()));
        ResponseDto<Object> responseDto = new ResponseDto<>(exception);
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
    }
}
