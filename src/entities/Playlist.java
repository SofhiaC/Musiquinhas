package entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "playlist")
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToMany
    @JoinTable(
            name = "playlist_musica",
            joinColumns = @JoinColumn(name = "playlist_id"),
            inverseJoinColumns = @JoinColumn(name = "musica_id")
    )
    private List<Musica> musicas = new ArrayList<>();

    public Playlist() {}

    public Playlist(String nome, Usuario usuario) {
        this.nome = nome;
        this.usuario = usuario;
    }

    public Long getId() { return id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public List<Musica> getMusicas() { return musicas; }

    public void adicionarMusica(Musica m) { musicas.add(m); }

    public void removerMusica(Musica m) { musicas.remove(m); }

    @Override
    public String toString() {
        return nome + " (" + musicas.size() + " músicas)";
    }
}
