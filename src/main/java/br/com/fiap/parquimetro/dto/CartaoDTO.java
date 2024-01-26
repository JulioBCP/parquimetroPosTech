package br.com.fiap.parquimetro.dto;

import java.time.LocalDate;

public record CartaoDTO(
        Long id,
        String numeroCartao,
        String nomeNoCartao,
        LocalDate dataValidade,
        int ccv
) {}
