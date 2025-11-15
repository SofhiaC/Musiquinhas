package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "musica")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    private String artista;

    private Integer duracao; // em segundos

    public Musica() {}

    public Musica(String titulo, String artista, Integer duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
    }

    // Getters e Setters
    public Long getId() { return id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }

    public Integer getDuracao() { return duracao; }
    public void setDuracao(Integer duracao) { this.duracao = duracao; }

    @Override
    public String toString() {
        return "Musica{" +
               "id=" + id +
               ", titulo='" + titulo + '\'' +
               ", artista='" + artista + '\'' +
               ", duracao=" + duracao +
               '}';
    }
}
