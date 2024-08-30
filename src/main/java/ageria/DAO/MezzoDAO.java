package ageria.DAO;

import ageria.entities.Abbonamento;
import ageria.entities.Mezzo;
import ageria.entities.StatoMezzo;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class MezzoDAO {
    private final EntityManager em;

    public MezzoDAO(EntityManager em){this.em=em;}

    public void save(Mezzo mezzo){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(mezzo);
        transaction.commit();
        System.out.println("il mezzo con ID: "+ mezzo.getId()+"è stato salvato");
    }
    public Mezzo findByID(long id) {
        Mezzo found = em.find(Mezzo.class, id);
        if (found == null) throw new NotFoundEx(id);
        return found;
    }

    public void delete(long id) {
        Mezzo found = this.findByID(id);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("il mezzo con ID: "+ id +" è stato rimosso correttamente");
    }

    public List<StatoMezzo> statiManutenzioneMezzo(long mezzoId){
        TypedQuery<StatoMezzo> query = em.createQuery("SELECT s FROM StatoMezzo s WHERE s.mezzo.id = :mezzoId", StatoMezzo.class);
        query.setParameter("mezzoId", mezzoId);
        return query.getResultList();
    }
    public long countBigliettiVidimatiPerMezzo(long mezzoId) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(bv) FROM BigliettoVidimato bv WHERE bv.mezzo.id = :mezzoId", Long.class);
        query.setParameter("mezzoId", mezzoId);
        return query.getSingleResult();
    }

}
