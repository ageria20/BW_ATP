package ageria.DAO;


import ageria.entities.PercorsoEffettuato;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PercorsoEffettuatoDAO {
    private final EntityManager em;

    public PercorsoEffettuatoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(PercorsoEffettuato PE) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(PE);
        transaction.commit();
        System.out.println("il percorso effettuato con ID: " + PE.getId() + "è stato salvato");
    }

    public PercorsoEffettuato findByID(long id) {
        PercorsoEffettuato found = em.find(PercorsoEffettuato.class, id);
        if (found == null) throw new NotFoundEx(id);
        return found;
    }

    public void delete(long id) {
        PercorsoEffettuato found = this.findByID(id);
        if (found == null) throw new NotFoundEx(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("il percorso effettuato con ID: " + id + " è stato rimosso correttamente");
    }
}
