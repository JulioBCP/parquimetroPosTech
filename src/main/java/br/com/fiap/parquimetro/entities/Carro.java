package br.com.fiap.parquimetro.entities;

import jakarta.persistence.*;

@Entity
@Table(name="tb-carros")
public class Carro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String placa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_Pessoa", nullable = false)
    private Pessoa pessoa;

    public Carro(){

    }

    public Carro(Long id, String placa, Pessoa pessoa) {
        this.id = id;
        this.placa = placa;
        this.pessoa = pessoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "id=" + id +
                ", placa='" + placa + '\'' +
                '}';
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }
    public Pessoa getPessoa() {
        return pessoa;
    }
    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

}
