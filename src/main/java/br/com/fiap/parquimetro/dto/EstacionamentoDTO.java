package br.com.fiap.parquimetro.dto;

import br.com.fiap.parquimetro.entities.Carro;
import br.com.fiap.parquimetro.entities.pagamento.Cartao;
import br.com.fiap.parquimetro.entities.pagamento.FormaDePagamentoEnum;
import br.com.fiap.parquimetro.entities.pagamento.ModalidadeTempoEnum;

import java.time.LocalDateTime;

public record EstacionamentoDTO(
        Long id,
        Carro carro,
        LocalDateTime horarioEntrada,
        LocalDateTime horarioSaida,
        ModalidadeTempoEnum modalidadeTempoEnum,
        long tempoEmHoras,
        double valorPagamento,
        FormaDePagamentoEnum formaDePagamentoEnum,
        String chavePix,
        Cartao cartao
) {

    public EstacionamentoDTO comHorarioEntrada(LocalDateTime dataHoraAtual) {
        return new EstacionamentoDTO(this.id, this.carro, dataHoraAtual, null, this.modalidadeTempoEnum,
                this.tempoEmHoras, 0.0, this.formaDePagamentoEnum, this.chavePix, this.cartao);
    }

    public EstacionamentoDTO comHorarioSaida(LocalDateTime dataHoraAtual) {
        return new EstacionamentoDTO(this.id, this.carro, this.horarioEntrada, dataHoraAtual, this.modalidadeTempoEnum,
                0, 0.0, this.formaDePagamentoEnum, this.chavePix, this.cartao);
    }
}
