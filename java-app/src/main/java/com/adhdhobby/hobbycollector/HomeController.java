package com.adhdhobby.hobbycollector;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class HomeController {

    private final HobbyDataService hobbyDataService;

    public HomeController(HobbyDataService hobbyDataService) {
        this.hobbyDataService = hobbyDataService;
    }

    @GetMapping("/")
    public String home() {
        return "ADHD Hobby Collector is running.";
    }

    @GetMapping("/summary")
    public Map<String, Object> summary() {
        return Map.of(
                "appName", "ADHD Hobby Collector",
                "hobbyCount", hobbies().size(),
                "projectCount", projects().size(),
                "supplyCount", supplies().size(),
                "availableEndpoints", List.of(
                        "/",
                        "/summary",
                        "/summary/projects-by-status",
                        "/summary/spending",
                        "/summary/spending-by-hobby",
                        "/hobbies",
                        "/projects",
                        "/supplies"
                )
        );
    }

    @GetMapping("/summary/projects-by-status")
    public Map<String, Long> projectsByStatus() {
        long startedCount = projects().stream()
                .filter(project -> project.getStatus().equals("Started"))
                .count();

        long finishedCount = projects().stream()
                .filter(project -> project.getStatus().equals("Finished"))
                .count();

        long abandonedCount = projects().stream()
                .filter(project -> project.getStatus().equals("Abandoned"))
                .count();

        return Map.of(
                "started", startedCount,
                "finished", finishedCount,
                "abandoned", abandonedCount
        );
    }

    @GetMapping("/summary/spending")
    public Map<String, Object> spendingSummary() {
        double totalSpent = supplies().stream()
                .mapToDouble(Supply::getCost)
                .sum();

        double essentialSpent = supplies().stream()
                .filter(Supply::isEssential)
                .mapToDouble(Supply::getCost)
                .sum();

        double nonEssentialSpent = supplies().stream()
                .filter(supply -> !supply.isEssential())
                .mapToDouble(Supply::getCost)
                .sum();

        double averageSupplyCost = supplies().stream()
                .mapToDouble(Supply::getCost)
                .average()
                .orElse(0);

        Supply mostExpensiveSupply = supplies().stream()
                .max(Comparator.comparingDouble(Supply::getCost))
                .orElse(null);

        return Map.of(
                "totalSpent", totalSpent,
                "essentialSpent", essentialSpent,
                "nonEssentialSpent", nonEssentialSpent,
                "averageSupplyCost", averageSupplyCost,
                "mostExpensiveSupply", mostExpensiveSupply
        );
    }

    @GetMapping("/summary/spending-by-hobby")
    public Map<String, Double> spendingByHobby() {
        Map<Integer, String> hobbyNamesById = hobbies().stream()
                .collect(Collectors.toMap(
                        Hobby::getId,
                        Hobby::getName
                ));

        return supplies().stream()
                .collect(Collectors.groupingBy(
                        supply -> hobbyNamesById.get(supply.getHobbyId()),
                        Collectors.summingDouble(Supply::getCost)
                ));
    }

    @GetMapping("/hobbies")
    public List<Hobby> hobbies() {
        return hobbyDataService.getHobbies();
    }

    @GetMapping("/projects")
    public List<HobbyProject> projects() {
        return hobbyDataService.getProjects();
    }

    @PostMapping("/projects")
    public Map<String, Object> addProject(@RequestBody ProjectRequest request) {
        int rowsAdded = hobbyDataService.addProject(request);

        return Map.of(
                "message", "Project added successfully",
                "rowsAdded", rowsAdded
        );
    }

    @PutMapping("/projects/{id}/status")
    public Map<String, Object> updateProjectStatus(
            @PathVariable int id,
            @RequestBody StatusUpdateRequest request
    ) {
        int rowsUpdated = hobbyDataService.updateProjectStatus(id, request);

        return Map.of(
                "message", "Project status updated successfully",
                "rowsUpdated", rowsUpdated
        );
    }

    @DeleteMapping("/projects/{id}")
    public Map<String, Object> archiveProject(@PathVariable int id) {
        int rowsDeleted = hobbyDataService.archiveProject(id);

        return Map.of(
                "message", rowsDeleted > 0 ? "Project archived successfully" : "Project not found",
                "rowsDeleted", rowsDeleted
        );
    }

    @GetMapping("/supplies")
    public List<Supply> supplies() {
        return hobbyDataService.getSupplies();
    }
}