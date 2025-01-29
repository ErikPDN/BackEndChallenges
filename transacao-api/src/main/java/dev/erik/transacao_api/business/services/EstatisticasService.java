package dev.erik.transacao_api.business.services;

import java.util.DoubleSummaryStatistics;

import org.springframework.stereotype.Service;
import dev.erik.transacao_api.controllers.dtos.EstatisticasResponseDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

  private final TransacaoService transacaoService;

  public EstatisticasResponseDTO buscarEstatisticas(Integer invervaloBusca) {
    log.info("Iniciado o processo de busca de estatísticas");
    var transacoes = transacaoService.buscarTransacoes(invervaloBusca);

    if (transacoes.isEmpty()) {
      log.info("Nenhuma transação encontrada");
      return new EstatisticasResponseDTO(0.0, 0.0, 0.0, 0.0, 0L);
    }

    DoubleSummaryStatistics estatisticas = transacoes.stream()
        .mapToDouble(transacao -> transacao.valor())
        .summaryStatistics(); // calculando estatísticas de transações
    //
    log.info("Estatísticas calculadas com sucesso");
    return new EstatisticasResponseDTO(estatisticas.getSum(), estatisticas.getAverage(), estatisticas.getMax(),
        estatisticas.getMin(), estatisticas.getCount());
  }
}
