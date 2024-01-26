package br.com.fiap.parquimetro.entities.pagamento;

import br.com.fiap.parquimetro.util.PagamentoNaoRealizadoException;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="tb_cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String numeroCartao;

    private String nomeNoCartao;

    private LocalDate dataValidade;

    private int ccv;

    public Cartao() {
    }

    public Cartao(Long id, String numeroCartao, String nomeNoCartao, LocalDate dataValidade, int ccv) {
        this.id = id;
        this.numeroCartao = numeroCartao;
        this.nomeNoCartao = nomeNoCartao;
        this.dataValidade = dataValidade;
        this.ccv = ccv;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getNomeNoCartao() {
        return nomeNoCartao;
    }

    public void setNomeNoCartao(String nomeNoCartao) {
        this.nomeNoCartao = nomeNoCartao;
    }

    public LocalDate getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(LocalDate dataValidade) {
        this.dataValidade = dataValidade;
    }

    public int getCcv() {
        return ccv;
    }

    public void setCcv(int ccv) {
        this.ccv = ccv;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cartao)) return false;
        Cartao cartao = (Cartao) o;
        return ccv == cartao.ccv && Objects.equals(id, cartao.id) && Objects.equals(numeroCartao, cartao.numeroCartao) && Objects.equals(nomeNoCartao, cartao.nomeNoCartao) && Objects.equals(dataValidade, cartao.dataValidade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numeroCartao, nomeNoCartao, dataValidade, ccv);
    }

    @Override
    public String toString() {
        return "Cartao{" +
                "id=" + id +
                ", numeroCartao='" + numeroCartao + '\'' +
                ", nomeNoCartao='" + nomeNoCartao + '\'' +
                ", dataValidade=" + dataValidade +
                ", ccv=" + ccv +
                '}';
    }

    public static boolean cartaoComCamposValidos(Cartao cartao) {
        return cartao.getId() != null
                && cartao.getNomeNoCartao() != null && !cartao.getNomeNoCartao().isBlank()
                && cartao.getNumeroCartao() != null && !cartao.getNumeroCartao().isBlank() && cartao.getNumeroCartao().length() == 16
                && !String.valueOf(cartao.getCcv()).isBlank() && String.valueOf(cartao.getCcv()).length() == 3
                && cartao.getDataValidade() != null && cartao.getDataValidade().isAfter(LocalDate.now());
    }

}
