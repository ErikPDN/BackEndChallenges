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
import static org.mockito.Mockito.times;

import dev.erik.transacao_api.controllers.dtos.TransacaoRequestDTO;
import dev.erik.transacao_api.controllers.dtos.EstatisticasResponseDTO;

@ExtendWith(MockitoExtension.class)
public class EstatisticasServiceTest {
  @InjectMocks
  EstatisticasService estatisticasService;

  @Mock
  TransacaoService transacaoService;

  TransacaoRequestDTO transacaoRequestDTO;
  EstatisticasResponseDTO estatisticasResponseDTO;

  @BeforeEach
  void setUp() {
    transacaoRequestDTO = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
    estatisticasResponseDTO = new EstatisticasResponseDTO(20.0, 20.0, 20.0, 20.0, 1L);
  }

  @Test
  void calcularEstatisticasComSucesso() {
    when(transacaoService.buscarTransacoes(60)).thenReturn(List.of(transacaoRequestDTO));

    EstatisticasResponseDTO estatisticas = estatisticasService.buscarEstatisticas(60);

    verify(transacaoService, times(1)).buscarTransacoes(60);
    assertEquals(estatisticasResponseDTO, estatisticas);
  }

  @Test
  void calcularEstatisticasComListaVazia() {
    EstatisticasResponseDTO estatisticaVazia = new EstatisticasResponseDTO(0.0, 0.0, 0.0, 0.0, 0L);
    when(transacaoService.buscarTransacoes(60)).thenReturn(List.of());

    EstatisticasResponseDTO estatisticas = estatisticasService.buscarEstatisticas(60);

    verify(transacaoService, times(1)).buscarTransacoes(60);
    assertEquals(estatisticaVazia, estatisticas);
  }
}
