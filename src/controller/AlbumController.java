package controller;

import dao.AlbumDAO;
import dao.ArtistaDAO;
import entities.Album;
import entities.Artista;

import java.util.List;

public class AlbumController {

    private final AlbumDAO albumDAO = new AlbumDAO();
    private final ArtistaDAO artistaDAO = new ArtistaDAO();

    // ----------- CRUD -----------

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

    public List<Album> listarAlbuns() {
        return albumDAO.listarTodos();
    }

    public void atualizarAlbum(Album album) {
        if (album == null || album.getId() == null)
            throw new IllegalArgumentException("Álbum inválido.");

        albumDAO.atualizar(album);
    }

    public void deletarAlbum(Long id) {
        albumDAO.deletar(id);
    }


    // ----------- INICIALIZAÇÃO -----------

    public void inicializarAlbuns() {
        try {

            // IDs gerados automaticamente pelo DAO após inicializar artistas
            Artista gaga   = artistaDAO.buscarPorId(1L);
            Artista bruno  = artistaDAO.buscarPorId(2L);

            if (gaga == null || bruno == null) {
                throw new RuntimeException("Os artistas ainda não foram inicializados.");
            }

            // Gaga
            criarAlbum("Born This Way", 2011, "capa1.jpg", gaga.getId());
            criarAlbum("Chromatica", 2020, "capa2.jpg", gaga.getId());

            // Bruno & Marrone
            criarAlbum("Inevitável", 2002, "capa3.jpg", bruno.getId());
            criarAlbum("Acústico", 2004, "capa4.jpg", bruno.getId());

            System.out.println("Álbuns inicializados com sucesso.");

        } catch (Exception e) {
            System.out.println("Erro ao inicializar álbuns: " + e.getMessage());
        }
    }
}
