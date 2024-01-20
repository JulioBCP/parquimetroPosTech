package br.com.fiap.parquimetro.entities.pagamento;

import br.com.fiap.parquimetro.entities.Carro;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_calculo_pagamento")
public class CalculoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime horarioEntrada;

    private LocalDateTime horarioSaida;

    private long tempoEmHoras;

    private int flagAlerta;

    @OneToOne
    @JoinColumn(name = "id_carro")
    private Carro carro;

    private ModalidadeTempoEnum modalidadeTempoEnum;

    public CalculoPagamento() {
    }

    public CalculoPagamento(Long id, LocalDateTime horarioEntrada, LocalDateTime horarioSaida, Carro carro, ModalidadeTempoEnum modalidadeTempoEnum) {
        this.id = id;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
        this.carro = carro;
        this.modalidadeTempoEnum = modalidadeTempoEnum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getTempoEmHoras() {
        return tempoEmHoras;
    }

    public void setTempoEmHoras(long tempoEmHoras) {
        this.tempoEmHoras = tempoEmHoras;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public ModalidadeTempoEnum getModalidadeTempoEnum() {
        return modalidadeTempoEnum;
    }

    public void setModalidadeTempoEnum(ModalidadeTempoEnum modalidadeTempoEnum) {
        this.modalidadeTempoEnum = modalidadeTempoEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CalculoPagamento)) return false;
        CalculoPagamento that = (CalculoPagamento) o;
        return Objects.equals(id, that.id) && Objects.equals(horarioEntrada, that.horarioEntrada) && Objects.equals(horarioSaida, that.horarioSaida) && Objects.equals(carro, that.carro) && modalidadeTempoEnum == that.modalidadeTempoEnum;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, horarioEntrada, horarioSaida, carro, modalidadeTempoEnum);
    }

    @Override
    public String toString() {
        return "CalculoPagamento{" +
                "id=" + id +
                ", horarioEntrada=" + horarioEntrada +
                ", horarioSaida=" + horarioSaida +
                ", carro=" + carro +
                ", modalidadeTempoEnum=" + modalidadeTempoEnum +
                '}';
    }

    public int getFlagAlerta() {
        return flagAlerta;
    }

    public void setFlagAlerta(int flagAlerta) {
        this.flagAlerta = flagAlerta;
    }

}
