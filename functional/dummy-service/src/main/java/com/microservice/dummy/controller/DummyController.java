package com.microservice.dummy.controller;

/*
 * Created by dendy-prtha on 03/09/2019.
 * TODO: Add a class header comment!
 */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyController {

    @GetMapping("/test")
    public String test() {
        return "Test Dummy service";
    }

}