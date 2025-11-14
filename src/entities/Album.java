package entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "album")
public class Album {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String titulo;

    private int ano;
    private String capa;

    @ManyToOne
    @JoinColumn(name = "id_artista", nullable = false)
    private Artista artista;

    @OneToMany(mappedBy = "album", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Musica> musicas = new ArrayList<>();

    public Album() {}

    public Album(String titulo, int ano, String capa, Artista artista) {
        this.titulo = titulo;
        this.ano = ano;
        this.capa = capa;
        this.artista = artista;
    }

    public Long getId() { return id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getAno() { return ano; }

    public void setAno(int ano) { this.ano = ano; }

    public String getCapa() { return capa; }

    public void setCapa(String capa) { this.capa = capa; }

    public Artista getArtista() { return artista; }

    public void setArtista(Artista artista) { this.artista = artista; }

    public List<Musica> getMusicas() { return musicas; }

    public void adicionarMusica(Musica m) {
        musicas.add(m);
        m.setAlbum(this);
    }

    public void removerMusica(Musica m) {
        musicas.remove(m);
        m.setAlbum(null);
    }

    @Override
    public String toString() {
        return "Album{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", ano=" + ano +
                ", capa='" + capa + '\'' +
                ", artista=" + (artista != null ? artista.getNome() : "null") +
                '}';
    }

    public void inicializarAlbuns(Artista gaga, Artista bruno) {
        try {
            criarAlbum("The Fame Monster", 2009,
                    "https://i.scdn.co/image/ab67616d0000b273d5d1a5d2c22e130c5bcaca7f",
                    gaga);

            criarAlbum("Acústico Ao Vivo", 2001,
                    "https://i.scdn.co/image/ab67616d0000b273a34f8a3fbbf5f9916907eeb5",
                    bruno);

        } catch (Exception e) {
            System.out.println("Erro ao criar álbuns: " + e.getMessage());
        }
    }
    
}
