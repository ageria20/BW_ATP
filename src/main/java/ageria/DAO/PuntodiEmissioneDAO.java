package ageria.DAO;


import ageria.entities.PuntodiEmissione;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class PuntodiEmissioneDAO {
    private final EntityManager em;

    public PuntodiEmissioneDAO(EntityManager em){this.em=em;}

    public void save(PuntodiEmissione puntoE){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(puntoE);
        transaction.commit();
        System.out.println("il punto di Emissione (rivenditore/distributore) con ID: "+ puntoE.getId()+"è stato salvato");
    }
    public PuntodiEmissione findByID(long id) {
        PuntodiEmissione found = em.find(PuntodiEmissione.class, id);
        if (found == null) throw new NotFoundEx(id);
        return found;
    }

    public void delete(long id) {
        PuntodiEmissione found = this.findByID(id);
        if (found == null) throw new NotFoundEx(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("il punto di emissione con ID: "+id+"è stato rimosso correttamente");
    }
}
