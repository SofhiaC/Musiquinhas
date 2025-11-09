package view;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando teste do JPA + H2...");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("musiquinhasPU");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.getTransaction().commit();

        em.close();
        emf.close();

        System.out.println("Conexão JPA + H2 OK!");
    }
}
