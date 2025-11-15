package controller;

import dao.MusicaDAO;
import entities.Musica;

import java.util.List;

public class MusicaController {
    private MusicaDAO musicaDAO = new MusicaDAO();

    public void criarMusica(String titulo, String artista, Integer duracao) {
        Musica musica = new Musica(titulo, artista, duracao);
        musicaDAO.criar(musica);
    }

    public Musica buscarMusica(Long id) {
        return musicaDAO.buscarPorId(id);
    }

    public List<Musica> listarTodasMusicas() {
        return musicaDAO.listarTodas();
    }

    public void atualizarMusica(Musica musica) {
        musicaDAO.atualizar(musica);
    }

    public void deletarMusica(Long id) {
        musicaDAO.deletar(id);
    }

    public void inicializarMusicas() {
        try {
            this.criarMusica("Bijuteria", "Bruno e Marrone", 267);
            this.criarMusica("LoveDrug", "Lady Gaga", 192);
            this.criarMusica("Lost", "Frank Ocean", 234);
            System.out.println(listarTodasMusicas());
        } catch (Exception e) {
            System.out.println("Erro ao criar músicas: " + e.getMessage());
        }
    }
}
