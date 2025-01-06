package dev.erik.picpaychallenge.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.erik.picpaychallenge.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
