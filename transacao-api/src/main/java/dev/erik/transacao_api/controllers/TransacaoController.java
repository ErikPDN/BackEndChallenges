package dev.erik.transacao_api.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.erik.transacao_api.business.services.TransacaoService;
import dev.erik.transacao_api.controllers.dtos.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/transacao")
public class TransacaoController {
  private final TransacaoService transacaoService;

  public TransacaoController(TransacaoService transacaoService) {
    this.transacaoService = transacaoService;
  }

  @PostMapping
  @Operation(description = "Adiciona uma transação")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Transação adicionada com sucesso"),
      @ApiResponse(responseCode = "400", description = "Requisição inválida"),
      @ApiResponse(responseCode = "422", description = "Entidade não processável"),
      @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
  })
  public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO transacaoRequestDTO) {

    this.transacaoService.adicionarTransacoes(transacaoRequestDTO);

    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @DeleteMapping
  @Operation(description = "Deleta todas as transações")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "Transações deletadas com sucesso"),
      @ApiResponse(responseCode = "400", description = "Requisição inválida"),
      @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
  })
  public ResponseEntity<Void> deletarTransacoes() {
    this.transacaoService.deletarTransacoes();

    return ResponseEntity.noContent().build();
  }

  @GetMapping
  @Operation(description = "Busca as transações")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
      @ApiResponse(responseCode = "400", description = "Requisição inválida"),
      @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
  })
  public ResponseEntity<List<TransacaoRequestDTO>> buscarTransacoes(
      @RequestParam(value = "intervaloBusca", required = false, defaultValue = "60") Integer intervaloBusca) {
    var response = this.transacaoService.buscarTransacoes(intervaloBusca);
    return ResponseEntity.ok(response);
  }
}
