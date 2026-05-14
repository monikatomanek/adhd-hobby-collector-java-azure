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

    @GetMapping("/projects")
    public List<HobbyProject> projects() {
        return List.of(
                new HobbyProject(1, 1, "Knit Sweater", "Abandoned", "Turned into scarf, then abandoned.", "2025-01-10", 8),
                new HobbyProject(2, 2, "Clay Mug", "Finished", "Collapsed during shaping, became candle holder.", "2025-01-15", 7),
                new HobbyProject(3, 3, "To-Do List App", "Started", "Ironically never finished.", "2025-01-20", 9),
                new HobbyProject(5, 5, "Harvest Potatoes", "Finished", "Finally harvested something edible.", "2025-01-15", 5),
                new HobbyProject(6, 5, "Plant Tomatoes", "Started", "Fingers crossed.", "2025-01-22", 7),
                new HobbyProject(7, 6, "Lo-Fi Beat", "Started", "Vibey experiment in music production.", "2025-01-30", 9),
                new HobbyProject(8, 7, "Learn Wonderwall", "Abandoned", "Got bored quickly.", "2025-02-01", 4),
                new HobbyProject(9, 8, "Bake a Cake", "Finished", "Forgot sugar, had to cover with jam.", "2025-02-03", 6)
        );
    }
}