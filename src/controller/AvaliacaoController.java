package controller;

import dao.AvaliacaoDAO;
import entities.Avaliacao;
import entities.Musica;
import entities.Usuario;

import java.util.List;

public class AvaliacaoController {

    private final AvaliacaoDAO dao = new AvaliacaoDAO();

    /**
     * Cria e persiste uma nova avaliação.
     * Valida nota (1..5) e presença de música/usuário.
     */
    public void criarAvaliacao(Usuario usuario, Musica musica, Integer nota, String comentario) {
        if (usuario == null) throw new IllegalArgumentException("Usuário obrigatório.");
        if (musica == null) throw new IllegalArgumentException("Música obrigatória.");
        if (nota == null || nota < 1 || nota > 5) throw new IllegalArgumentException("Nota deve ser entre 1 e 5.");

        Avaliacao a = new Avaliacao(usuario, musica, nota, comentario);
        dao.criar(a);
    }

    public List<Avaliacao> listarAvaliacoes() {
        return dao.listarTodas();
    }

    public List<Avaliacao> listarPorMusica(Musica musica) {
        return dao.listarPorMusica(musica);
    }

    public void atualizarAvaliacao(Avaliacao a) {
        dao.atualizar(a);
    }

    public void deletarAvaliacao(Long id) {
        dao.deletar(id);
    }
}

