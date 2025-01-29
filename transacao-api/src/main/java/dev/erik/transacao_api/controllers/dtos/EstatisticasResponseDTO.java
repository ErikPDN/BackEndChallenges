package dev.erik.transacao_api.controllers.dtos;

public record EstatisticasResponseDTO(Double sum, Double avg, Double max, Double min, Long count) {
}
