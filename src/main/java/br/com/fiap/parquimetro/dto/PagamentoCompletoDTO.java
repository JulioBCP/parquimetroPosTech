package br.com.fiap.parquimetro.dto;

import br.com.fiap.parquimetro.entities.pagamento.CalculoPagamento;
import br.com.fiap.parquimetro.entities.pagamento.Pagamento;

public record PagamentoCompletoDTO(
        Long id,
        Pagamento pagamento,
        CalculoPagamento calculoPagamento
) {}
