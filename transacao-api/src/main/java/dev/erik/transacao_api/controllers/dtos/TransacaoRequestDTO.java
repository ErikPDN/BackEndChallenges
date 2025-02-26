package dev.erik.transacao_api.controllers.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.OffsetDateTime;

@Schema(description = "DTO de requisição para criar uma transação")
public record TransacaoRequestDTO(
    @Schema(description = "Valor da transação", example = "100.00") Double valor,

    @Schema(description = "Data e hora da transação", example = "2025-02-25T14:30:00Z") @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX") OffsetDateTime dataHora) {
}
