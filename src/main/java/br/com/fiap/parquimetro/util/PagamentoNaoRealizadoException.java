package br.com.fiap.parquimetro.util;

public class PagamentoNaoRealizadoException extends RuntimeException{

    public PagamentoNaoRealizadoException(String mensagem) {
        super(mensagem);
    }
}
