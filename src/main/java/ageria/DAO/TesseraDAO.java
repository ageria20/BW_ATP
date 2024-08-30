package ageria.DAO;

import ageria.entities.Abbonamento;
import ageria.entities.Tessera;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class TesseraDAO {
    private final EntityManager em;

    public TesseraDAO(EntityManager em){
        this.em=em;
    }

    public void save(Tessera tessera){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(tessera);
        transaction.commit();
        System.out.println("l'abbonamento con ID: "+ tessera.getNumeroTessera()+"è stata salvata");
    }
    public Tessera findByID(long id) {
        Tessera found = em.find(Tessera.class, id);
        return found;
    }

    public void delete(long id) {
        Tessera found = this.findByID(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("la tessera con ID: "+ id +" è stato rimossa correttamente");
    }

}
