package ageria.DAO;


import ageria.entities.Abbonamento;
import ageria.entities.PuntodiEmissione;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class AbbonamentoDAO {
    private final EntityManager em;

    public AbbonamentoDAO(EntityManager em){this.em=em;}

    public void save(Abbonamento abbD){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(abbD);
        transaction.commit();
        System.out.println("l'abbonamento con ID: "+ abbD.getId()+"è stato salvato");
    }
    public Abbonamento findByID(long id) {
        Abbonamento found = em.find(Abbonamento.class, id);
        if (found == null) throw new NotFoundEx(id);
        return found;
    }

    public void delete(long id) {
        Abbonamento found = this.findByID(id);
        if (found == null) throw new NotFoundEx(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("l'abbonamento con ID: "+ id +" è stato rimosso correttamente");
    }
}
