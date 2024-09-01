package backend.challenge.cryptography.controller.dto;

import backend.challenge.cryptography.entity.TransactionEntity;

public record TransactionResponse(Long id,
                                  String userDocument,
                                  String creditCardToken,
                                  Long value) {

    public static TransactionResponse fromEntity(TransactionEntity entity) {
        return new TransactionResponse(
                entity.getTransactionId(),
                entity.getRawUserDocument(),
                entity.getRawCreditCardToken(),
                entity.getTransactionValue()
        );
    }
}
