package backend.challenge.cryptography.repository;

import backend.challenge.cryptography.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
}
