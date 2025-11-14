package entities;

import jakarta.persistence.*;

@Entity //diz que a classe representa uma tabela no db
@Table(name = "usuarios") //nome da tabela no db
public class Usuario {

    @Id //primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private Long id; //tipo

    private String nome; 

    @Column(unique = true, nullable = false)
    private String email;

    private String senha;

    public Usuario() {} //JPA obriga esse construtor sem argumentos

    public Usuario(String nome, String email, String senha) { //construtor com argumentos
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    @Override
    public String toString() {
        return "Usuario{" +
               "id=" + id +
               ", nome='" + nome + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}
