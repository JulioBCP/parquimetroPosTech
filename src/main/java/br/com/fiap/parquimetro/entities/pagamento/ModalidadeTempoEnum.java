package br.com.fiap.parquimetro.entities.pagamento;

public enum ModalidadeTempoEnum {

    TEMPO_FIXO(1, "Contabilizacao de tempo pre-fixado pelo cliente"),
    TEMPO_VARIAVEL(2, "Contabilizacao de tempo por hora");

    private int id;
    private String descricao;

    ModalidadeTempoEnum(int id, String descricao) {
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
