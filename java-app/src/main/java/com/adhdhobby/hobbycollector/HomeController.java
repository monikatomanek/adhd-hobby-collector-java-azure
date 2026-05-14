package com.adhdhobby.hobbycollector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "ADHD Hobby Collector is running.";
    }

    @GetMapping("/hobbies")
    public String hobbies() {
        return """
                Hobbies:
                - Knitting
                - Pottery
                - Coding
                - Skateboarding
                - Gardening
                - Music Production
                - Guitar
                - Cooking
                """;
    }
}