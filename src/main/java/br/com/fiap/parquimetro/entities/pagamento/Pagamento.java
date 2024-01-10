package br.com.fiap.parquimetro.entities.pagamento;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="tb-pagamento")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private FormaDePagamentoEnum formaDePagamentoEnum;

    @ManyToOne
    @JoinColumn(name = "pix_id")
    private Pix pix;

    @ManyToOne
    @JoinColumn(name = "cartao_id")
    private Cartao cartao;

    public Pagamento() {
    }

    public Pagamento(Long id, FormaDePagamentoEnum formaDePagamentoEnum, Pix pix, Cartao cartao) {
        this.id = id;
        this.formaDePagamentoEnum = formaDePagamentoEnum;
        this.pix = pix;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FormaDePagamentoEnum getFormaDePagamentoEnum() {
        return formaDePagamentoEnum;
    }

    public void setFormaDePagamentoEnum(FormaDePagamentoEnum formaDePagamentoEnum) {
        this.formaDePagamentoEnum = formaDePagamentoEnum;
    }

    public Pix getPix() {
        return pix;
    }

    public void setPix(Pix pix) {
        this.pix = pix;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pagamento)) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id) && formaDePagamentoEnum == pagamento.formaDePagamentoEnum && Objects.equals(pix, pagamento.pix) && Objects.equals(cartao, pagamento.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, formaDePagamentoEnum, pix, cartao);
    }

    @Override
    public String toString() {
        String dadosPagamentos = "Pagamento{" +
                "id=" + id +
                ", formaDePagamentoEnum=" + formaDePagamentoEnum;
                if (formaDePagamentoEnum.getId() == FormaDePagamentoEnum.PIX.getId()) {
                    dadosPagamentos += ", pix=" + pix;
                }
                if (formaDePagamentoEnum.getId() == FormaDePagamentoEnum.CREDITO.getId()) {
                    dadosPagamentos += ", cartao de credito=" + cartao;
                }
                if (formaDePagamentoEnum.getId() == FormaDePagamentoEnum.DEBITO.getId()) {
                    dadosPagamentos += ", cartao de debito=" + cartao;
                }
            dadosPagamentos += '}';

                return dadosPagamentos;
    }
}
