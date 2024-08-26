package ageria.DAO;

import ageria.entities.Biglietto;
import ageria.entities.PuntodiEmissione;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class BigliettoDAO {
    private final EntityManager em;

    public BigliettoDAO(EntityManager em){this.em=em;}

    public void save(Biglietto biglietto){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(biglietto);
        transaction.commit();
        System.out.println("il biglietto con ID: "+ biglietto.getId()+"è stato salvato");
    }
    public Biglietto findByID(long id) {
        Biglietto found = em.find(Biglietto.class, id);
        if (found == null) throw new NotFoundEx(id);
        return found;
    }

    public void delete(long id) {
        Biglietto found = this.findByID(id);
        if (found == null) throw new NotFoundEx(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("il biglietto con ID: "+id+"è stato rimosso correttamente");
    }
}
