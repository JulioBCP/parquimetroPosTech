package br.com.fiap.parquimetro.entities.pagamento;

import jakarta.persistence.*;

@Entity
@Table(name="tb-pagamento-completo")
public class PagamentoCompleto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToOne
    private Pagamento pagamento;
    @OneToOne
    private CalculoPagamento calculoPagamento;

    public PagamentoCompleto() {
    }

    public PagamentoCompleto(Long id, Pagamento pagamento, CalculoPagamento calculoPagamento) {
        this.id = id;
        this.pagamento = pagamento;
        this.calculoPagamento = calculoPagamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public CalculoPagamento getCalculoPagamento() {
        return calculoPagamento;
    }

    public void setCalculoPagamento(CalculoPagamento calculoPagamento) {
        this.calculoPagamento = calculoPagamento;
    }
}
