package br.com.fiap.parquimetro.dto;

public record MailEstacionamentoDTO(
        String to,
        String subject,
        String horarioEntrada,
        String horarioSaida,
        double valorPagamento
) {
    
}
