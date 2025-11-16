package dao;

import entities.Album;
import jakarta.persistence.*;

import java.util.List;

public class AlbumDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("_musiquinhasPU");

    public void criar(Album album) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(album);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public Album buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Album.class, id);
        } finally {
            em.close();
        }
    }

    public List<Album> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT a FROM Album a", Album.class)
                     .getResultList();
        } finally {
            em.close();
        }
    }

    public void atualizar(Long id, String titulo, int ano, String capa, Long idArtistaLong) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Album album = em.find(Album.class, id);
            if (album != null) {
                album.setTitulo(titulo);
                album.setAno(ano);
                album.setCapa(capa);
                em.merge(album);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Album album = em.find(Album.class, id);
            if (album != null) em.remove(album);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}