package com.example.helloworldapi;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String sayHello(){
        return "Hello, World!";

    }
    @GetMapping("/")
    public String home(){
        return "Welcome! My first Spring Boot API";
    }
}
