package dao;

import entities.Artista;
import jakarta.persistence.*;
import java.util.List;

public class ArtistaDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("_musiquinhasPU");

    public void criar(Artista artista) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(artista);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Artista buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Artista.class, id);
        } finally {
            em.close();
        }
    }

    public List<Artista> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Artista a", Artista.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Artista artista) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(artista);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Artista artista = em.find(Artista.class, id);
            if (artista != null) em.remove(artista);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
