package controller;

import dao.AssinaturaDAO;
import entities.Assinatura;

import java.util.List;

public class AssinaturaController {
    private final AssinaturaDAO assinaturaDao = new AssinaturaDAO();

    public void criarAssinatura(String nome, String tipo, String preco, String duracao, String beneficios) {
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

    public void inicializarAssinaturas() {
        try {
            this.criarAssinatura("Plano Mensal", "Individual", "R$ 15,90/mês", "1 mês",
                    "Sem anúncios, músicas ilimitadas");
           // this.criarAssinatura("Plano Duo", "Dupla", "R$ 14,90", "1 mês",
             //       "Sem anúncios, áudio HD");

            System.out.println(assinaturaDao.listarTodos());
        } catch (Exception e) {
            System.out.println("Erro ao criar assinaturas: " + e.getMessage());
        }
    }

}
