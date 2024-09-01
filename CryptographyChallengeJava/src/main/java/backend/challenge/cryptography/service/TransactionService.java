package backend.challenge.cryptography.service;

import backend.challenge.cryptography.controller.dto.CreateTransactionRequest;
import backend.challenge.cryptography.controller.dto.TransactionResponse;
import backend.challenge.cryptography.controller.dto.UpdateTransactionRequest;
import backend.challenge.cryptography.entity.TransactionEntity;
import backend.challenge.cryptography.repository.TransactionRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void create(CreateTransactionRequest request) {
        var entity = new TransactionEntity();
        entity.setRawCreditCardToken(request.creditCardToken());
        entity.setRawUserDocument(request.userDocument());
        entity.setTransactionValue(request.value());

        transactionRepository.save(entity);
    }

    public Page<TransactionResponse> listAll(int page, int pageSize) {
        var content = transactionRepository.findAll(PageRequest.of(page, pageSize));

        return content.map(TransactionResponse::fromEntity);
    }

    public TransactionResponse findById(Long id) {
        var entity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return TransactionResponse.fromEntity(entity);
    }

    public void update(Long id, UpdateTransactionRequest request) {
        var entity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        entity.setTransactionValue(request.value());
        transactionRepository.save(entity);
    }


    public void deleteById(Long id) {
        var entity = transactionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        transactionRepository.deleteById(entity.getTransactionId());
    }
}
