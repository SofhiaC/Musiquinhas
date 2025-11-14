package controller;

import dao.AlbumDAO;
import dao.MusicaDAO;
import entities.Album;
import entities.Musica;

import java.util.List;

public class MusicaController {

    private MusicaDAO musicaDAO = new MusicaDAO();
    private AlbumDAO albumDAO = new AlbumDAO();

    // ----------- CRUD -----------

    public void criarMusica(String titulo, double duracao, Long albumId) {
        Album album = albumDAO.buscarPorId(albumId);

        if (album == null) {
            throw new RuntimeException("Álbum não encontrado: " + albumId);
        }

        int duracaoSegundos = (int)(duracao * 60);

        Musica musica = new Musica(titulo, duracaoSegundos, album);
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


    // ----------- INICIALIZAÇÃO -----------

    public void inicializarMusicas() {
        try {

            // Verificando se os álbuns existem
            Album a1 = albumDAO.buscarPorId(1L);
            Album a2 = albumDAO.buscarPorId(2L);
            Album a3 = albumDAO.buscarPorId(3L);

            if (a1 == null || a2 == null || a3 == null) {
                throw new RuntimeException("Álbuns não foram inicializados corretamente.");
            }

            // Lady Gaga
            criarMusica("Born This Way", 4.21, a1.getId());
            criarMusica("Marry The Night", 4.40, a1.getId());

            criarMusica("Stupid Love", 3.14, a2.getId());
            criarMusica("Rain On Me", 3.02, a2.getId());

            // Bruno & Marrone
            criarMusica("Dormi na Praça", 3.58, a3.getId());
            criarMusica("Choram as Rosas", 4.12, a3.getId());

            System.out.println("Músicas inicializadas com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao inicializar músicas: " + e.getMessage());
        }
    }
}
