package com.marriott.hms.exception;


import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * The type Hotel management system exception.
 *
 * @author ambujmehra
 */
@Getter
public class HotelManagementSystemException extends RuntimeException {
    private Integer code;
    private String type;
    private HttpStatus httpStatus;

    /**
     * Instantiates a new Hotel management system exception.
     */
    public HotelManagementSystemException() {
        super();
    }

    /**
     * Instantiates a new Hotel management system exception.
     *
     * @param message the message
     */
    public HotelManagementSystemException(String message) {
        super(message);
    }

    /**
     * Instantiates a new Hotel management system exception.
     *
     * @param message   the message
     * @param exception the exception
     */
    public HotelManagementSystemException(String message, Exception exception) {
        super(message, exception);
    }
}
