package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "assinaturas")

public class Assinatura {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String tipo;
    private double preco;
    private String duracao;
    private String beneficios;

    public Assinatura(){}

    public Assinatura(String nome, String tipo, double preco, String duracao, String beneficios) {
        this.nome = nome;
        this.tipo = tipo;
        this.preco = preco;
        this.duracao = duracao;
        this.beneficios = beneficios;
    }

    public Long getId() {return id;}

    public String getNome() {return nome;}
    public void setNome(String nome) {this.nome = nome;}

    public String getTipo() {return tipo;}
    public void setTipo(String tipo) {this.tipo = tipo;}

    public String getPreco() {return preco;}
    public void setPreco(String preco) {this.preco = preco;}

    public String getDuracao() {return duracao;}
    public void setDuracao(String duracao) {this.duracao = duracao;}

    public String getBeneficios() {return beneficios;}
    public void setBeneficios(String beneficios) {this.beneficios = beneficios;}

    @Override
    public String toString() {
        return "Assinatura{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", tipo='" + tipo + '\'' +
               ", preco='" + preco + '\'' +
               ", duracao='" + duracao + '\'' +
               ", beneficios='" + beneficios + '\'' +
               '}';
    }
}
