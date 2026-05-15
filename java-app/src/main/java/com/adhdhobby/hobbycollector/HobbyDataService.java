package com.adhdhobby.hobbycollector;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HobbyDataService {

    private final HobbyRepository hobbyRepository;
    private final SupplyRepository supplyRepository;
    private final ProjectRepository projectRepository;

    public HobbyDataService(
            HobbyRepository hobbyRepository,
            SupplyRepository supplyRepository,
            ProjectRepository projectRepository
    ) {
        this.hobbyRepository = hobbyRepository;
        this.supplyRepository = supplyRepository;
        this.projectRepository = projectRepository;
    }

    public List<Hobby> getHobbies() {
        return hobbyRepository.findAll();
    }

    public List<HobbyProject> getProjects() {
        return projectRepository.findAll();
    }

    public List<Supply> getSupplies() {
        return supplyRepository.findAll();
    }
}