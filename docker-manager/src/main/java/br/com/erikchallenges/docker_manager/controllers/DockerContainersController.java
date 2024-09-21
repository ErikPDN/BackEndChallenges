package br.com.erikchallenges.docker_manager.controllers;

import br.com.erikchallenges.docker_manager.services.DockerService;
import com.github.dockerjava.api.model.Container;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/containers")
public class DockerContainersController {
    private final DockerService dockerService;

    public DockerContainersController(DockerService dockerService) {
        this.dockerService = dockerService;
    }

    @GetMapping("")
    public List<Container> listContainers(@RequestParam(required = false, defaultValue = "true") boolean showAll) {
        return dockerService.listContainers(showAll);
    }

    @PostMapping("/{id}/start")
    public void startContainer(@PathVariable("id") String id) {
        dockerService.startContainer(id);
    }

    @PostMapping("/{id}/stop")
    public void stopContainer(@PathVariable("id") String id) {
        dockerService.stopContainer(id);
    }

    @DeleteMapping("/{id}")
    public void deleteContainer(@PathVariable("id") String id) {
        dockerService.deleteContainer(id);
    }

    @PostMapping()
    public void addContainer(@RequestParam String imageName) {
        dockerService.createContainer(imageName);
    }
}
