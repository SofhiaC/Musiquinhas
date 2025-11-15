package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "avaliacao")
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Avaliação pertence a um usuário
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Avaliação pertence a uma música
    @ManyToOne(optional = false)
    @JoinColumn(name = "musica_id")
    private Musica musica;

    @Column(nullable = false)
    private Integer nota; // 1 a 5

    @Column(length = 1000)
    private String comentario;

    public Avaliacao() {}

    public Avaliacao(Usuario usuario, Musica musica, Integer nota, String comentario) {
        this.usuario = usuario;
        this.musica = musica;
        this.nota = nota;
        this.comentario = comentario;
    }

    // Getters / setters
    public Long getId() { return id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Musica getMusica() { return musica; }
    public void setMusica(Musica musica) { this.musica = musica; }

    public Integer getNota() { return nota; }
    public void setNota(Integer nota) { this.nota = nota; }

    public String getComentario() { return comentario; }
    public void setComentario(String comentario) { this.comentario = comentario; }

    @Override
    public String toString() {
        String usuarioStr = (usuario != null) ? usuario.getNome() : "N/A";
        String musicaStr = (musica != null) ? musica.getTitulo() : "N/A";
        return "Avaliacao{id=" + id + ", usuario=" + usuarioStr + ", musica=" + musicaStr +
                ", nota=" + nota + ", comentario='" + comentario + "'}";
    }
}
