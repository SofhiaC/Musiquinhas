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

    @Column(nullable = false)
    private Integer duracao; // em segundos

    @ManyToOne
    @JoinColumn(name = "id_album", nullable = false)
    private Album album;

    public Musica() {}

    public Musica(String titulo, Integer duracao, Album album) {
        this.titulo = titulo;
        this.duracao = duracao;
        this.album = album;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getDuracao() {
        return duracao;
    }
    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Album getAlbum() {
        return album;
    }
    public void setAlbum(Album album) {
        this.album = album;
    }

    @Override
    public String toString() {
        return "Musica{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", duracao=" + duracao +
                ", album=" + (album != null ? album.getTitulo() : "null") +
                '}';
    }

    
}