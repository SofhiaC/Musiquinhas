package dao;

import jakarta.persistence.*;
import entities.Assinatura;

import java.util.List;

public class AssinaturaDAO {
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("_musiquinhasPU");

    //insert
    public void criar(Assinatura assinatura) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); //inicia a transação
            em.persist(assinatura); //coloca o objeto no contexto de persitencia
            em.getTransaction().commit(); //confirma a transação
        } finally {
            em.close();
        }
    }

    //select por pk (primary key)
    public Assinatura buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Assinatura.class, id);
        } finally {
            em.close();
        }
    }

    //select * 
    public List<Assinatura> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Assinatura a", Assinatura.class).getResultList();
        } finally {
            em.close();
        }
    }

    //update 
    public void atualizar(Assinatura assinatura) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(assinatura); //atualiza o objeto no contexto de persistencia
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    //delete
    public void deletar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            Assinatura assinatura = em.find(Assinatura.class, id);
            if (assinatura != null) {
                em.getTransaction().begin();
                em.remove(assinatura); //remove o objeto do contexto de persistencia
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }
}
