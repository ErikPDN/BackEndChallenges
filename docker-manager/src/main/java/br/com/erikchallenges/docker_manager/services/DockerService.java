package br.com.erikchallenges.docker_manager.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
import org.springframework.stereotype.Service;
import com.github.dockerjava.api.model.Container;

import java.util.List;

@Service
public class DockerService {
    private final DockerClient dockerClient;

    public DockerService(DockerClient dockerClient) {
        this.dockerClient = dockerClient;
    }

    public List<Container> listContainers(boolean all) {
        return dockerClient.listContainersCmd().withShowAll(all).exec();
    }

    public List<Image> listImages() {
        return dockerClient.listImagesCmd().exec();
    }

    public List<Image> filterImages(String filterName) {
        return dockerClient.listImagesCmd().withImageNameFilter(filterName).exec();
    }

    public void startContainer(String containerId) {
        dockerClient.startContainerCmd(containerId).exec();
    }

    public void stopContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
    }

    public void deleteContainer(String containerId) {
        dockerClient.removeContainerCmd(containerId).exec();
    }

    public void createContainer(String imageName) {
        dockerClient.createContainerCmd(imageName).exec();
    }
}
