package com.attin.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @RequestMapping("/hi")
    String hello() {
        return "Hello World, Spring Boot!";
    }
}