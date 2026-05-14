package com.adhdhobby.hobbycollector;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "ADHD Hobby Collector is running.";
    }

    @GetMapping("/hobbies")
    public List<Hobby> hobbies() {
        return List.of(
                new Hobby(1, "Knitting", "Craft", "Beginner"),
                new Hobby(2, "Pottery", "Art", "Beginner"),
                new Hobby(3, "Coding", "Tech", "Intermediate"),
                new Hobby(4, "Skateboarding", "Sport", "Beginner"),
                new Hobby(5, "Gardening", "Outdoors", "Intermediate"),
                new Hobby(6, "Music Production", "Music", "Intermediate"),
                new Hobby(7, "Guitar", "Music", "Beginner"),
                new Hobby(8, "Cooking", "Lifestyle", "Beginner")
        );
    }
}