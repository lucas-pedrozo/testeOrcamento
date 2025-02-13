package com.back_end.orcamento.dto;

public class ProdutoOrcamento_ResquestDTO {

    private String nome;
    private String valor;
    private Integer orcamento_id;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Integer getOrcamento_id() {
        return orcamento_id;
    }

    public void setOrcamento_id(Integer orcamento_id) {
        this.orcamento_id = orcamento_id;
    }
}
