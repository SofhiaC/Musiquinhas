package controller;

import dao.AssinaturaDAO;
import entities.Assinatura;

import java.util.List;

public class AssinaturaController {
    private final AssinaturaDAO assinaturaDao = new AssinaturaDAO();

    public void criarAssinatura(String nome, String tipo, double preco, String duracao, String beneficios) {
        Assinatura assinatura = new Assinatura(nome, tipo, preco, duracao, beneficios);
        assinaturaDao.criar(assinatura);
    }

    public void atualizarAssinatura(Assinatura assinatura) {
        assinaturaDao.atualizar(assinatura);
    }

    public void deletarAssinatura(Long id) {
        assinaturaDao.deletar(id);
    }

    public List<Assinatura> listarAssinaturas() {
        return assinaturaDao.listarTodos();
    }


}
