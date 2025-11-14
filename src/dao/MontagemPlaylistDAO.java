package dao;

import entities.MontagemPlaylist;
import entities.Musica;
import entities.Playlist;
import jakarta.persistence.*;

import java.util.List;

public class MontagemPlaylistDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("_musiquinhasPU");

    public void adicionarMusica(Playlist playlist, Musica musica) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            MontagemPlaylist mp = new MontagemPlaylist(playlist, musica);
            em.persist(mp);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void removerMusica(Playlist playlist, Musica musica) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            TypedQuery<MontagemPlaylist> q = em.createQuery(
                    "SELECT mp FROM MontagemPlaylist mp WHERE mp.playlist = :p AND mp.musica = :m",
                    MontagemPlaylist.class
            );

            q.setParameter("p", playlist);
            q.setParameter("m", musica);

            List<MontagemPlaylist> resultados = q.getResultList();

            for (MontagemPlaylist mp : resultados) {
                em.remove(mp);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Musica> listarMusicasDaPlaylist(Long playlistId) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT mp.musica FROM MontagemPlaylist mp WHERE mp.playlist.id = :id",
                    Musica.class
            ).setParameter("id", playlistId).getResultList();
        } finally {
            em.close();
        }
    }
}
