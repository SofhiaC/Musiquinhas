package dao;

import jakarta.persistence.*;
import entities.Musica;

import java.util.List;

public class MusicaDAO {
    private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("_musiquinhasPU");

    public void criar(Musica musica) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(musica);
            em.getTransaction().commit();
            System.out.println("Música inserida: " + musica.getTitulo() + " - " + musica.getArtista());
        } catch (Exception e) {
            System.out.println("Erro ao inserir música: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Musica buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Musica.class, id);
        } finally {
            em.close();
        }
    }

    public List<Musica> listarTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT m FROM Musica m", Musica.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Musica musica) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(musica);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Musica musica = em.find(Musica.class, id);
            if (musica != null) {
                em.remove(musica);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
