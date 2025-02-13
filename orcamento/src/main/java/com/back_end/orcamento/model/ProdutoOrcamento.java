package com.back_end.orcamento.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table
public class ProdutoOrcamento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

        @Column
    private String nome;

        @Column
    private String valor;

    @ManyToOne
    @JoinColumn(name = "orcamento_id")
    @JsonIgnoreProperties({"orçamentos"})
    private Orcamento orcamento;

    // get set

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Orcamento getOrcamento() {
        return orcamento;
    }

    public void setOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
    }
}
