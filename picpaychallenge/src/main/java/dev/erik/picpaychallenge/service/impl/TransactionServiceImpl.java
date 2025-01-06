package dev.erik.picpaychallenge.service.impl;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Service;

import dev.erik.picpaychallenge.entity.Transaction;
import dev.erik.picpaychallenge.entity.User;
import dev.erik.picpaychallenge.entity.UserType;
import dev.erik.picpaychallenge.repository.TransactionRepository;
import dev.erik.picpaychallenge.repository.UserRepository;
import dev.erik.picpaychallenge.service.AuthorizationService;
import dev.erik.picpaychallenge.service.NotificationService;
import dev.erik.picpaychallenge.service.TransactionService;
import dev.erik.picpaychallenge.service.dto.TransactionDTO;
import dev.erik.picpaychallenge.service.exceptions.InsufficientBalanceException;
import dev.erik.picpaychallenge.service.exceptions.MerchantPaymentNotAllowedException;
import dev.erik.picpaychallenge.service.exceptions.TransactionNotAuthorizedException;
import dev.erik.picpaychallenge.service.exceptions.UserNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

  private final TransactionRepository transactionRepository;
  private final NotificationService notificationService;
  private final AuthorizationService authorizationService;
  private final UserRepository userRepository;

  public TransactionServiceImpl(TransactionRepository transactionRepository, NotificationService notificationService,
      AuthorizationService authorizationService, UserRepository userRepository) {
    this.transactionRepository = transactionRepository;
    this.notificationService = notificationService;
    this.authorizationService = authorizationService;
    this.userRepository = userRepository;
  }

  @Override
  @Transactional
  public TransactionDTO createTransaction(BigDecimal value, Long payerId, Long payeeId) {
    var payer = this.userRepository.findById(payerId)
        .orElseThrow(() -> new UserNotFoundException(payerId));

    var payee = this.userRepository.findById(payeeId)
        .orElseThrow(() -> new UserNotFoundException(payeeId));

    this.validatePayer(payer, value);

    payer.debit(value);
    payee.credit(value);

    this.userRepository.save(payer);
    this.userRepository.save(payee);

    var transaction = new Transaction(payer, payee, value);
    this.transactionRepository.save(transaction);

    var transactionDTO = new TransactionDTO(transaction.getId(), value, payerId, payeeId);

    // Cria uma thread para enviar a notificação assíncronamente
    CompletableFuture.runAsync(() -> this.notificationService.sendNotification(transactionDTO));

    return new TransactionDTO(transaction.getId(), value, payerId, payeeId);
  }

  private void validatePayer(User payer, BigDecimal value) {
    if (payer.getUserType().equals(UserType.MERCHANT)) {
      throw new MerchantPaymentNotAllowedException(payer.getId());
    }
    if (payer.getBalance().compareTo(value) < 0) {
      throw new InsufficientBalanceException(payer.getId());
    }
    if (!this.authorizationService.isAuthorize()) {
      throw new TransactionNotAuthorizedException();
    }
  }
}
