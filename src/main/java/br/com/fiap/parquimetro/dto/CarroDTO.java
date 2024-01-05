package br.com.fiap.parquimetro.dto;

import br.com.fiap.parquimetro.entities.Pessoa;

public record CarroDTO(
        Long id,
        String placa,
        Pessoa pessoa
) {

}
