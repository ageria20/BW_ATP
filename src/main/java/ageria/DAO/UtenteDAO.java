package ageria.DAO;

import ageria.entities.Abbonamento;
import ageria.entities.Utente;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class UtenteDAO {
    private final EntityManager em;

    public UtenteDAO(EntityManager em){
        this.em=em;
    }

    public void save(Utente utente){
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(utente);
        transaction.commit();
        System.out.println("------------------------SALVATAGGIO RIUSCITO------------------------");;
    }
    public Utente findByID(long id) {
        Utente found = em.find(Utente.class, id);

        return found;
    }

    public void delete(long id) {
        Utente found = this.findByID(id);
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("l'utente con ID: "+ id +" è stato rimosso correttamente");
    }

}
