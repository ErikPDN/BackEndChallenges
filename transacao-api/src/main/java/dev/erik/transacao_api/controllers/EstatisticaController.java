package dev.erik.transacao_api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.erik.transacao_api.business.services.EstatisticasService;
import dev.erik.transacao_api.controllers.dtos.EstatisticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/estatistica")
@RequiredArgsConstructor
public class EstatisticaController {

  private final EstatisticasService estatisticasService;

  @GetMapping
  @Operation(description = "Busca as estatísticas das transações")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Transação removida com sucesso"),
      @ApiResponse(responseCode = "400", description = "Requisição inválida"),
      @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
  })
  public ResponseEntity<EstatisticasResponseDTO> buscarEstatisticas(
      @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {

    var response = this.estatisticasService.buscarEstatisticas(intervaloBusca);

    return ResponseEntity.ok(response);
  }
}
