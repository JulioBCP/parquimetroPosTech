package br.com.fiap.parquimetro.entities;

import br.com.fiap.parquimetro.entities.pagamento.Cartao;
import br.com.fiap.parquimetro.entities.pagamento.FormaDePagamentoEnum;
import br.com.fiap.parquimetro.entities.pagamento.ModalidadeTempoEnum;
import br.com.fiap.parquimetro.entities.pagamento.Pix;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name="tb_estacionamento")
public class Estacionamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_carro")
    private Carro carro;

    private LocalDateTime horarioEntrada;

    private LocalDateTime horarioSaida;

    private ModalidadeTempoEnum modalidadeTempoEnum;

    private long tempoEmHoras;

    private double valorPagamento;

    private FormaDePagamentoEnum formaDePagamentoEnum;

    private String chavePix;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    public Estacionamento() {}

    public Estacionamento(Long id, Carro carro, LocalDateTime horarioEntrada, LocalDateTime horarioSaida,
                          ModalidadeTempoEnum modalidadeTempoEnum, long tempoEmHoras, double valorPagamento,
                          FormaDePagamentoEnum formaDePagamentoEnum, String chavePix, Cartao cartao) {
        this.id = id;
        this.carro = carro;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
        this.modalidadeTempoEnum = modalidadeTempoEnum;
        this.tempoEmHoras = tempoEmHoras;
        this.valorPagamento = valorPagamento;
        this.formaDePagamentoEnum = formaDePagamentoEnum;
        this.chavePix = chavePix;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public LocalDateTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalDateTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
    }

    public LocalDateTime getHorarioSaida() {
        return horarioSaida;
    }

    public void setHorarioSaida(LocalDateTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }

    public ModalidadeTempoEnum getModalidadeTempoEnum() {
        return modalidadeTempoEnum;
    }

    public void setModalidadeTempoEnum(ModalidadeTempoEnum modalidadeTempoEnum) {
        this.modalidadeTempoEnum = modalidadeTempoEnum;
    }

    public long getTempoEmHoras() {
        return tempoEmHoras;
    }

    public void setTempoEmHoras(long tempoEmHoras) {
        this.tempoEmHoras = tempoEmHoras;
    }

    public double getValorPagamento() {
        return valorPagamento;
    }

    public void setValorPagamento(double valorPagamento) {
        this.valorPagamento = valorPagamento;
    }

    public FormaDePagamentoEnum getFormaDePagamentoEnum() {
        return formaDePagamentoEnum;
    }

    public void setFormaDePagamentoEnum(FormaDePagamentoEnum formaDePagamentoEnum) {
        this.formaDePagamentoEnum = formaDePagamentoEnum;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    @Override
    public String toString() {
        return "Estacionamento{" +
                "id=" + id +
                ", carro=" + carro +
                ", horarioEntrada=" + horarioEntrada +
                ", horarioSaida=" + horarioSaida +
                ", modalidadeTempoEnum=" + modalidadeTempoEnum +
                ", tempoEmHoras=" + tempoEmHoras +
                ", valorPagamento=" + valorPagamento +
                ", formaDePagamentoEnum=" + formaDePagamentoEnum +
                ", chavePix='" + chavePix + '\'' +
                ", cartao=" + cartao +
                '}';
    }
}
