package com.adhdhobby.hobbycollector;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyDataService {

    public List<Hobby> getHobbies() {
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

    public List<HobbyProject> getProjects() {
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

    public List<Supply> getSupplies() {
        return List.of(
                new Supply(1, 1, "Yarn", 12.50, true, "Material"),
                new Supply(2, 1, "Knitting Needles", 8.00, true, "Tool"),
                new Supply(3, 2, "Clay", 20.00, true, "Material"),
                new Supply(4, 2, "Glaze Paints", 15.00, false, "Consumable"),
                new Supply(5, 3, "Laptop", 800.00, true, "Tool"),
                new Supply(6, 3, "Energy Drinks", 5.00, false, "Consumable"),
                new Supply(7, 4, "Skateboard", 120.00, true, "Tool"),
                new Supply(8, 4, "Helmet", 35.00, true, "Tool"),
                new Supply(9, 5, "Garden Gloves", 10.00, true, "Tool"),
                new Supply(10, 5, "Shovel", 25.00, true, "Tool"),
                new Supply(11, 6, "MIDI Keyboard", 150.00, true, "Tool"),
                new Supply(12, 6, "Headphones", 90.00, true, "Tool"),
                new Supply(13, 7, "Acoustic Guitar", 200.00, true, "Tool"),
                new Supply(14, 7, "Guitar Picks", 5.00, false, "Consumable"),
                new Supply(15, 8, "Flour", 2.50, true, "Material"),
                new Supply(16, 8, "Mixing Bowl", 12.00, true, "Tool")
        );
    }
}