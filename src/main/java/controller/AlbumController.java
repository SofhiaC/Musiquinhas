package controller;

import dao.AlbumDAO;
import dao.ArtistaDAO;
import entities.Album;
import entities.Artista;

import java.util.List;

public class AlbumController {

    private final AlbumDAO albumDAO = new AlbumDAO();
    private final ArtistaDAO artistaDAO = new ArtistaDAO();

    //CREATE
    public void criarAlbum(String titulo, int ano, String capa, Long idArtista) {

        if (titulo == null || titulo.isBlank())
            throw new IllegalArgumentException("Título obrigatório.");

        Artista artista = artistaDAO.buscarPorId(idArtista);
        if (artista == null)
            throw new IllegalArgumentException("Artista não encontrado.");

        Album album = new Album(titulo, ano, capa, artista);
        albumDAO.criar(album);
    }

    public Album buscarAlbum(Long id) {
        return albumDAO.buscarPorId(id);
    }

    //READ
    public List<Album> listarAlbuns() {
        return albumDAO.listarTodos();
    }

    //UPDATE
    public void atualizarAlbum(Long id, String titulo, int ano, String capa, Long idArtista) {
        Album album = albumDAO.buscarPorId(id);
        if (album == null || album.getId() == null)
            throw new IllegalArgumentException("Álbum inválido.");

        Artista artista = artistaDAO.buscarPorId(idArtista);
        if (artista == null)
            throw new IllegalArgumentException("Artista não encontrado.");

        album.setTitulo(titulo);
        album.setAno(ano);
        album.setCapa(capa);
        album.setArtista(artista);

        albumDAO.atualizar(id, titulo, ano, capa, idArtista);
    }

    //DELETE
    public void deletarAlbum(Long id) {
        albumDAO.deletar(id);
    }

}