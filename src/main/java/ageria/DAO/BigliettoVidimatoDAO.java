package ageria.DAO;

import ageria.entities.BigliettoVidimato;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BigliettoVidimatoDAO {

    private final EntityManager em;

    public BigliettoVidimatoDAO(EntityManager em) {
        this.em = em;
    }

    public void save(BigliettoVidimato bigliettoVidimato) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(bigliettoVidimato);
        transaction.commit();
        System.out.println("il biglietto è stato vidimato correttamente!");
    }

    public BigliettoVidimato findByID(long id) {
        BigliettoVidimato found = em.find(BigliettoVidimato.class, id);
        if (found == null) throw new NotFoundEx(id);
        return found;
    }

    public void delete(long id) {
        BigliettoVidimato found = this.findByID(id);
        if (found == null) throw new NotFoundEx(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("il biglietto con ID: " + id + " è stato rimosso correttamente");
    }

}
