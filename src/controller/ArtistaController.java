package controller;

import dao.ArtistaDAO;
import entities.Artista;
import java.util.List;

public class ArtistaController {

    private final ArtistaDAO dao = new ArtistaDAO();

    // ----------- CRUD -----------

    public void criarArtista(String nome) {
        if (nome == null || nome.isBlank())
            throw new IllegalArgumentException("Nome do artista obrigatório.");

        Artista artista = new Artista(nome);
        dao.criar(artista);
    }

    public Artista buscarArtista(Long id) {
        return dao.buscarPorId(id);
    }

    public List<Artista> listarArtistas() {
        return dao.listarTodos();
    }

    public void atualizarArtista(Long id, String novoNome) {
        Artista artista = dao.buscarPorId(id);

        if (artista == null)
            throw new IllegalArgumentException("Artista não encontrado.");

        artista.setNome(novoNome);
        dao.atualizar(artista);
    }

    public void deletarArtista(Long id) {
        dao.deletar(id);
    }


    // ----------- INICIALIZAÇÃO -----------

    public void inicializarArtistas() {
        try {
            criarArtista("Lady Gaga");
            criarArtista("Bruno & Marrone");
            System.out.println("Artistas inicializados com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao inicializar artistas: " + e.getMessage());
        }
    }
}
