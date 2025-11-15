package dao;

import jakarta.persistence.*;
import entities.Musica;
import entities.Playlist;
import entities.Usuario;

import java.util.List;

public class PlaylistDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("_musiquinhasPU");

    public void criar(Playlist playlist) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            // Garantir que a referência ao usuário esteja no contexto de persistência
            if (playlist.getUsuario() != null && playlist.getUsuario().getId() != null) {
                Usuario managedUsuario = em.getReference(Usuario.class, playlist.getUsuario().getId());
                playlist.setUsuario(managedUsuario);
            }
            em.persist(playlist);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao criar playlist: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Playlist buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Playlist.class, id);
        } finally {
            em.close();
        }
    }

    public void atualizar(Playlist playlist) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(playlist);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao atualizar playlist: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List<Playlist> listarTodas() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT p FROM Playlist p", Playlist.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void deletar(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            Playlist playlist = em.find(Playlist.class, id);
            if (playlist != null) {
                // Remove as relações many-to-many primeiro
                playlist.getMusicas().clear();
                em.merge(playlist);
                em.flush();

                // Agora remove a playlist
                em.remove(playlist);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao deletar playlist: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List<Playlist> listarPlaylistsDoUsuario(Long usuarioId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT p FROM Playlist p WHERE p.usuario.id = :uid", Playlist.class)
                    .setParameter("uid", usuarioId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    public void adicionarMusica(Long playlistId, Long musicaId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Playlist playlist = em.find(Playlist.class, playlistId);
            Musica musica = em.find(Musica.class, musicaId);

            if (playlist != null && musica != null) {
                // Carrega a lista de músicas para garantir que está no contexto de persistência
                playlist.getMusicas().size();
                playlist.getMusicas().add(musica);
                em.merge(playlist);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao adicionar música: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void removerMusica(Long playlistId, Long musicaId) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            Playlist playlist = em.find(Playlist.class, playlistId);
            Musica musica = em.find(Musica.class, musicaId);

            if (playlist != null && musica != null) {
                // Carrega a lista de músicas para garantir que está no contexto de persistência
                playlist.getMusicas().size();
                playlist.getMusicas().remove(musica);
                em.merge(playlist);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao remover música: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List<Musica> listarMusicasDaPlaylist(Long playlistId) {
        EntityManager em = emf.createEntityManager();
        try {
            Playlist playlist = em.find(Playlist.class, playlistId);
            if (playlist != null) {
                // Force load da lista de músicas
                playlist.getMusicas().size();
                return playlist.getMusicas();
            }
            return List.of();
        } finally {
            em.close();
        }
    }

    public Playlist buscarPlaylistComMusicas(Long playlistId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                            "SELECT DISTINCT p FROM Playlist p LEFT JOIN FETCH p.musicas WHERE p.id = :id",
                            Playlist.class)
                    .setParameter("id", playlistId)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public boolean musicaEstaNaPlaylist(Long playlistId, Long musicaId) {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery(
                            "SELECT COUNT(pm) FROM Playlist p JOIN p.musicas pm WHERE p.id = :playlistId AND pm.id = :musicaId",
                            Long.class)
                    .setParameter("playlistId", playlistId)
                    .setParameter("musicaId", musicaId)
                    .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }


    public int contarMusicasNaPlaylist(Long playlistId) {
        EntityManager em = emf.createEntityManager();
        try {
            Long count = em.createQuery(
                            "SELECT COUNT(m) FROM Playlist p JOIN p.musicas m WHERE p.id = :id",
                            Long.class)
                    .setParameter("id", playlistId)
                    .getSingleResult();
            return count.intValue();
        } finally {
            em.close();
        }
    }
}