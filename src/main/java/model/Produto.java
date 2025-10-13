package model;

import java.time.LocalDate;

public class Produto {
    private int id;
    private String nome;
    private double preco;
    private int quantidade;
    private LocalDate fabricacao;
    private LocalDate validade;

    public Produto() {}

    public Produto(String nome, double preco, int quantidade, LocalDate fabricacao, LocalDate validade) {
        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
        this.fabricacao = fabricacao;
        this.validade = validade;
    }

    // Getters e setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public LocalDate getFabricacao() { return fabricacao; }
    public void setFabricacao(LocalDate fabricacao) { this.fabricacao = fabricacao; }
    public LocalDate getValidade() { return validade; }
    public void setValidade(LocalDate validade) { this.validade = validade; }
}
