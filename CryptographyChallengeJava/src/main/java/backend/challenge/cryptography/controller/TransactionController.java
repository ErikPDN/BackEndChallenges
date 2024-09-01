package backend.challenge.cryptography.controller;

import backend.challenge.cryptography.controller.dto.CreateTransactionRequest;
import backend.challenge.cryptography.controller.dto.TransactionResponse;
import backend.challenge.cryptography.controller.dto.UpdateTransactionRequest;
import backend.challenge.cryptography.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateTransactionRequest request) {
        transactionService.create(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable(value = "id") Long id,
                                       @RequestBody UpdateTransactionRequest request) {
        transactionService.update(id, request);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<TransactionResponse>> listAll(@RequestParam(name = "page", defaultValue = "0") Integer page,
                                                             @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        var body = transactionService.listAll(page, pageSize);
        return ResponseEntity.ok(body);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> findById(@PathVariable(value = "id") Long id) {
        var body = transactionService.findById(id);
        return ResponseEntity.ok(body);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "id") Long id) {
        transactionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
