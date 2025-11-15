package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "playlist_musica")
public class MontagemPlaylist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "playlist_id", nullable = false)
    private Playlist playlist;

    @ManyToOne
    @JoinColumn(name = "musica_id", nullable = false)
    private Musica musica;

    public MontagemPlaylist() {}

    public MontagemPlaylist(Playlist playlist, Musica musica) {
        this.playlist = playlist;
        this.musica = musica;
    }

    public Long getId() {
        return id;
    }

    public Playlist getPlaylist() {
        return playlist;
    }

    public Musica getMusica() {
        return musica;
    }
}
