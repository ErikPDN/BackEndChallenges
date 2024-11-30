package dev.erik.spring.magalu_ms.config;

import dev.erik.spring.magalu_ms.domain.Channel;
import dev.erik.spring.magalu_ms.domain.Status;
import dev.erik.spring.magalu_ms.repository.ChannelRepository;
import dev.erik.spring.magalu_ms.repository.NotificationRepository;
import dev.erik.spring.magalu_ms.repository.StatusRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class DataLoader implements CommandLineRunner {

    private final ChannelRepository channelRepository;
    private final StatusRepository statusRepository;

    public DataLoader(ChannelRepository channelRepository, StatusRepository statusRepository) {
        this.channelRepository = channelRepository;
        this.statusRepository = statusRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(Channel.Values.values())
                .map(Channel.Values::toChannel)
                .forEach(channelRepository::save);

        Arrays.stream(Status.Values.values())
                .map(Status.Values::toStatus)
                .forEach(statusRepository::save);
    }
}
