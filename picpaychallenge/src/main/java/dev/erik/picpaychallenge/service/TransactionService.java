package dev.erik.picpaychallenge.service;

import java.math.BigDecimal;

import dev.erik.picpaychallenge.service.dto.TransactionDTO;

public interface TransactionService {
  TransactionDTO createTransaction(BigDecimal value, Long payerId, Long payeeId);
}
