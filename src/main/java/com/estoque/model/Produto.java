package com.estoque.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Produto {

    protected Produto() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private double preco;
    private int quantidade;

    public String getNome() { return this.nome; }
    public double getPreco() { return this.preco; }
    public int getQuantidade() { return this.quantidade; }

    public void setNome (String nome){
        this.nome = nome;
    }

    public void setPreco (double preco){
        this.preco = preco;
    }

    public void setQuantidade (int quantidade){
        this.quantidade = quantidade;
    }

    public Produto (String nome, double preco, int quantidade){
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }
}
