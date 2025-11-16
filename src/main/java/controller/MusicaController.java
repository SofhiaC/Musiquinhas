package controller;

import dao.AlbumDAO;
import dao.MusicaDAO;
import entities.Album;
import entities.Musica;

import java.util.List;

public class MusicaController {

    private MusicaDAO musicaDAO = new MusicaDAO();
    //CREATE
    public void criarMusica(String titulo, double duracao, Long albumId) {
        AlbumDAO albumDAO = new AlbumDAO();
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
    //READ
    public List<Musica> listarTodasMusicas() {
        return musicaDAO.listarTodas();
    }
    //UPDATE
    public void atualizarMusica(Long id, String titulo, int duracao, Long albumId, Long idArtista) {
        musicaDAO.atualizar(id, titulo, duracao, albumId, idArtista);
    }
    //DELETE
    public void deletarMusica(Long id) {
        musicaDAO.deletar(id);
    }

}