package ageria.DAO;

import ageria.entities.Abbonamento;
import ageria.entities.Mezzo;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class MezzoDAO {
    private final EntityManager em;

    public MezzoDAO(EntityManager em){this.em=em;}

    public void save(Mezzo mezzo){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(mezzo);
        transaction.commit();
        System.out.println("l'abbonamento con ID: "+ mezzo.getId()+"è stato salvato");
    }
    public Mezzo findByID(long id) {
        Mezzo found = em.find(Mezzo.class, id);
        if (found == null) throw new NotFoundEx(id);
        return found;
    }

    public void delete(long id) {
        Mezzo found = this.findByID(id);
        if (found == null) throw new NotFoundEx(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("l'abbonamento con ID: "+ id +" è stato rimosso correttamente");
    }
}
