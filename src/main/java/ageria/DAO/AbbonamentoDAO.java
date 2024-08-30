package ageria.DAO;


import ageria.entities.Abbonamento;
import ageria.entities.PuntodiEmissione;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AbbonamentoDAO {
    private final EntityManager em;

    public AbbonamentoDAO(EntityManager em){
        this.em=em;
    }

    public void save(Abbonamento abbD){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(abbD);
        transaction.commit();
        System.out.println("l'abbonamento con ID: "+ abbD.getId()+"è stato salvato");
    }
    public Abbonamento findByID(long id) {
        Abbonamento found = em.find(Abbonamento.class, id);

        return found;
    }

    public void delete(long id) {
        Abbonamento found = this.findByID(id);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("l'abbonamento con ID: "+ id +" è stato rimosso correttamente");
    }

    public List<Abbonamento> findByNumeroTessera(long numeroTessera){
        String selezione="SELECT a FROM Abbonamento a WHERE a.tessera.id=:numeroTessera";
        TypedQuery<Abbonamento> query=em.createQuery(selezione,
                Abbonamento.class);
        query.setParameter("numeroTessera",numeroTessera);
        return query.getResultList();
    }
}
