package dev.erik.transacao_api.business.services;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import dev.erik.transacao_api.controllers.dtos.TransacaoRequestDTO;
import dev.erik.transacao_api.infra.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

  private List<TransacaoRequestDTO> listaTransacoes = new ArrayList<>();

  public void adicionarTransacoes(TransacaoRequestDTO transacaoDto) {
    log.info("Iniciado o processo de adição de transação");

    if (transacaoDto.dataHora().isAfter(OffsetDateTime.now())) { // verificando se a data da transação é futura a atual
      log.error("Data da transação não pode ser futura a data atual");
      throw new UnprocessableEntity("Data da transação não pode ser futura a data atual");
    }

    if (transacaoDto.valor() <= 0) { // verificando se o valor da transação é menor ou igual a zero
      log.error("Valor da transação não pode ser menor ou igual a zero");
      throw new UnprocessableEntity("Valor da transação não pode ser menor ou igual a zero");
    }

    this.listaTransacoes.add(transacaoDto);
  }

  public void deletarTransacoes() {
    log.info("Iniciado o processo de remoção de transações");
    this.listaTransacoes.clear();
  }

  public List<TransacaoRequestDTO> buscarTransacoes(Integer invervaloBusca) {
    OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(invervaloBusca);
    log.info("Iniciado o processo de busca de transações");
    return this.listaTransacoes.stream()
        .filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo))
        .toList();
  }
}
