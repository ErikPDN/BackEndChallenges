package dev.erik.transacao_api.business.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.OffsetDateTime;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;

import dev.erik.transacao_api.controllers.dtos.TransacaoRequestDTO;
import dev.erik.transacao_api.infra.exceptions.UnprocessableEntity;
import dev.erik.transacao_api.controllers.dtos.EstatisticasResponseDTO;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {
  @InjectMocks
  TransacaoService transacaoService;

  TransacaoRequestDTO transacaoRequestDTO;
  EstatisticasResponseDTO estatisticasResponseDTO;

  @BeforeEach
  void setUp() {
    transacaoRequestDTO = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
    estatisticasResponseDTO = new EstatisticasResponseDTO(20.0, 20.0, 20.0, 20.0, 1L);
  }

  @Test
  void transacaoOcorreuComSucesso() {
    transacaoService.adicionarTransacoes(transacaoRequestDTO);
    List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(6000);
    assertTrue(transacoes.contains(transacaoRequestDTO));
  }

  @Test
  void deveLancarExcecaoSeValorNegativo() {
    UnprocessableEntity exception = assertThrows(UnprocessableEntity.class, () -> {
      transacaoService.adicionarTransacoes(new TransacaoRequestDTO(-20.0, OffsetDateTime.now()));
    });

    assertEquals("Valor da transação não pode ser menor ou igual a zero", exception.getMessage());
  }

  @Test
  void deveLancarExcecaoSeDataFutura() {
    UnprocessableEntity exception = assertThrows(UnprocessableEntity.class, () -> {
      transacaoService.adicionarTransacoes(new TransacaoRequestDTO(20.0, OffsetDateTime.now().plusDays(1)));
    });

    assertEquals("Data da transação não pode ser futura a data atual", exception.getMessage());
  }

  @Test
  void deveDeletarTransacoesComSucesso() {
    transacaoService.deletarTransacoes();
    List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(6000);
    assertTrue(transacoes.isEmpty());
  }

  @Test
  void deveBuscarTransacoesDentroDoIntervalo() {
    var transacaoTest = new TransacaoRequestDTO(30.0, OffsetDateTime.now().minusSeconds(120));

    transacaoService.adicionarTransacoes(transacaoRequestDTO);
    transacaoService.adicionarTransacoes(transacaoTest);

    List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(60);
    assertTrue(transacoes.contains(transacaoRequestDTO));
    assertFalse(transacoes.contains(transacaoTest));
  }
}
