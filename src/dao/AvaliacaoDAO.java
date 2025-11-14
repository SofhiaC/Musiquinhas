package dao;

import entities.Avaliacao;
import entities.Musica;
import jakarta.persistence.*;

import java.util.List;

public class AvaliacaoDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("_musiquinhasPU");

    public void criar(Avaliacao avaliacao) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(avaliacao);
            em.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Erro ao criar avaliacao: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Avaliacao buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Avaliacao.class, id);
        } finally {
            em.close();
        }
    }

    public List<Avaliacao> listarTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Avaliacao a", Avaliacao.class).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Avaliacao> listarPorMusica(Musica musica) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Avaliacao> q = em.createQuery(
                    "SELECT a FROM Avaliacao a WHERE a.musica = :musica", Avaliacao.class);
            q.setParameter("musica", musica);
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Avaliacao avaliacao) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(avaliacao);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Avaliacao a = em.find(Avaliacao.class, id);
            if (a != null) em.remove(a);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}

