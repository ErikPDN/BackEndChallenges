package dev.erik.spring.magalu_ms.repository;

import dev.erik.spring.magalu_ms.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
