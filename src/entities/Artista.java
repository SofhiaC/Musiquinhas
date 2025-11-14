package entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "artista")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nome;

    @OneToMany(mappedBy = "artista")
    private List<Album> albuns = new ArrayList<>();

    public Artista() {}

    public Artista(String nome) {
        this.nome = nome;
    }

    public Long getId() { return id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    @Override
    public String toString() {
        return "Artista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                '}';
    }

    public void inicializarArtistas() {
        try {
            criarArtista("Lady Gaga");
            criarArtista("Bruno & Marrone");
        } catch (Exception e) {
            System.out.println("Erro ao criar artistas: " + e.getMessage());
        }
    }
}
