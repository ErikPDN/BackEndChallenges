package dev.erik.spring.magalu_ms.repository;

import dev.erik.spring.magalu_ms.domain.Channel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
