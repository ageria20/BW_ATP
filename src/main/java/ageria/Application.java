package ageria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Application {
private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw_atp");
    public static void main(String[] args) {

        EntityManager em = emf.createEntityManager();


        System.out.println("TUTTO OK!");
        em.close();
        emf.close();
    }
}
