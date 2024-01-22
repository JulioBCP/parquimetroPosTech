package br.com.fiap.parquimetro.entities.pagamento;

public enum FormaDePagamentoEnum {

    CREDITO(1, "Cartao de Credito"),
    DEBITO(2, "Cartao de Debito"),
    PIX(3, "Pix");

    private int id;
    private String descricao;

    FormaDePagamentoEnum(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

}
