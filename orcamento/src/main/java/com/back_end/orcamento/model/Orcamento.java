package com.back_end.orcamento.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

    @Column
  private String nomeCliente;

    @Column
  private String data;

  // get set


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
