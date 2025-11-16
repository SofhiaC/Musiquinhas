package controller;

import dao.ArtistaDAO;
import entities.Artista;

import java.util.List;

public class ArtistaController {

    private final ArtistaDAO dao = new ArtistaDAO();

    //CREATE
    public void criarArtista(String nome){ 
        Artista artista = new Artista(nome);
        dao.criarArtista(artista);
    }
    //READ
    public List<Artista> listarArtistas(){
        return dao.listarTodos();
    }
    //UPTDATE
    public void atualizarArtista(Long id, String nome){
        dao.atualizar(id, nome);
    }
    //DELETE
    public void deletarArtista(Long id){
        dao.deletar(id);
    }
}