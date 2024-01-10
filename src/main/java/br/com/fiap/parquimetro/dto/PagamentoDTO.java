package br.com.fiap.parquimetro.dto;

import br.com.fiap.parquimetro.entities.pagamento.Cartao;
import br.com.fiap.parquimetro.entities.pagamento.FormaDePagamentoEnum;
import br.com.fiap.parquimetro.entities.pagamento.Pix;

public record PagamentoDTO(
        Long id,
        FormaDePagamentoEnum formaPagmentoEnum,
        Pix pix,
        Cartao cartao
){}
