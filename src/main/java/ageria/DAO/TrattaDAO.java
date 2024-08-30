package ageria.DAO;

import ageria.entities.Tratta;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TrattaDAO {

    private final EntityManager em;

    public TrattaDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Tratta tratta) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(tratta);
        transaction.commit();
        System.out.println("------------------------SALVATAGGIO RIUSCITO------------------------");
    }

    public Tratta findByID(long id) {
        Tratta found = em.find(Tratta.class, id);

        return found;
    }

    public void delete(long id) {
        Tratta found = this.findByID(id);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("la tratta con ID: " + id + " è stata rimossa correttamente");
    }
}
