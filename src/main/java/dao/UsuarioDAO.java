package dao;

import jakarta.persistence.*;
import entities.Usuario;

import java.util.List;

public class UsuarioDAO {
        private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("_musiquinhasPU");

    //insert
    public void criar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin(); //inicia a transação
            em.persist(usuario); //coloca o objeto no contexto de persitencia
            em.getTransaction().commit(); //confirma a transação
        } finally {
            em.close();
        }
        System.out.println(
            Thread.currentThread().getContextClassLoader().getResource("META-INF/persistence.xml") //verifica se o arquivo persistence.xml está sendo carregado corretamente
        );
    }
    //select por pk (primary key)
    public Usuario buscarPorId(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }
    //select com filtro por email
    public Usuario buscarPorEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Usuario> q = em.createQuery("SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
            q.setParameter("email", email);
            java.util.List<Usuario> results = q.getResultList();
            return results.isEmpty() ? null : results.get(0);
        } finally {
            em.close();
        }
    }
    //select * 
    public List<Usuario> listarTodos() {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
        } finally {
            em.close();
        }
    }
    //update 
    public void atualizar(Usuario usuario) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(usuario);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    //delete
    public void deletar(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Usuario usuario = em.find(Usuario.class, id);
            if (usuario != null) {
                em.remove(usuario);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
}