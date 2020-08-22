package com.marriott.hms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Test controller.
 *
 * @author ambujmehra
 */
@RestController
@RequestMapping("/hms")
public class TestController {

    /**
     * Ping controller for Load balancer readiness and liveliness probing
     *
     * @return the string
     */
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String pingController() {
        return HttpStatus.OK.getReasonPhrase();
    }
}
