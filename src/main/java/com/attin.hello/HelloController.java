package com.attin.hello;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @Value("${service.token.username}")
    private String username;

    @Value("${service.token.password}")
    private String password;

    @RequestMapping("/hi")
    String hello() {
        return "Hello World, Spring Boot!";
    }

    @RequestMapping("/usrpsw")
    String usrpsw() {
        return  "UserName :".concat(username)
                .concat(" , password : ").concat(password);
    }
}