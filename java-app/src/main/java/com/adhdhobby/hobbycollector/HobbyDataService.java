package com.adhdhobby.hobbycollector;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyDataService {

    private final HobbyRepository hobbyRepository;
    private final SupplyRepository SupplyRepository;

    public HobbyDataService(HobbyRepository hobbyRepository, SupplyRepository supplyRepository) {
        this.hobbyRepository = hobbyRepository;
        this.SupplyRepository = SupplyRepository;
    }

    public List<Hobby> getHobbies() {
        return hobbyRepository.findAll();
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
        return SupplyRepository.findAll();
    }
}