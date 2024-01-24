package br.com.fiap.parquimetro.dto;

import java.time.LocalDateTime;

public record MailEstacionamentoDTO(
        String to,
        String subject,
        LocalDateTime horarioEntrada,
        LocalDateTime horarioSaida,
        double valorPagamento
) {
    
}
