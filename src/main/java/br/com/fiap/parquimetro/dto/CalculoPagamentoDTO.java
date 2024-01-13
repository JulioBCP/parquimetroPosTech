package br.com.fiap.parquimetro.dto;

import br.com.fiap.parquimetro.entities.Carro;
import br.com.fiap.parquimetro.entities.pagamento.ModalidadeTempoEnum;

import java.time.LocalDateTime;

public record CalculoPagamentoDTO(
        Long id,
        LocalDateTime horarioEntrada,
        LocalDateTime horarioSaida,
        long tempoEmHoras,
        Carro carro,
        ModalidadeTempoEnum modalidadeTempoEnum
) {

    public CalculoPagamentoDTO withHorarioEntrada(LocalDateTime novoHorarioEntrada) {
        return new CalculoPagamentoDTO(this.id, novoHorarioEntrada, null, 0, this.carro, this.modalidadeTempoEnum);
    }

    public CalculoPagamentoDTO withHorarioSaida(LocalDateTime novoHorarioSaida) {
        return new CalculoPagamentoDTO(this.id, this.horarioEntrada, novoHorarioSaida, 0, this.carro, this.modalidadeTempoEnum);
    }
}
