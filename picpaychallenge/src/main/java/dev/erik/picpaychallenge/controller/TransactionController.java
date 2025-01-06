package dev.erik.picpaychallenge.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.erik.picpaychallenge.controller.dto.CreateTransactionRequestDTO;
import dev.erik.picpaychallenge.controller.dto.CreateTransactionResponseDTO;
import dev.erik.picpaychallenge.service.TransactionService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
  private final TransactionService transactionService;

  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping
  public ResponseEntity<CreateTransactionResponseDTO> createTransaction(
      @RequestBody @Valid CreateTransactionRequestDTO transactionDTO) {
    var createTransaction = this.transactionService.createTransaction(transactionDTO.value(), transactionDTO.payerId(),
        transactionDTO.payeeId());

    var response = new CreateTransactionResponseDTO(createTransaction.id(), createTransaction.value(),
        createTransaction.payerId(), createTransaction.payeeId());
    return ResponseEntity.ok(response);
  }
}
