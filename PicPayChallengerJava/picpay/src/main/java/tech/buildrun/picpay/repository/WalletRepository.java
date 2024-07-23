package tech.buildrun.picpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.buildrun.picpay.entity.Wallet;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
