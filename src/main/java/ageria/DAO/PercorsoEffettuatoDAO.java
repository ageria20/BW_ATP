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

        return found;
    }

    public void delete(long id) {
        PercorsoEffettuato found = this.findByID(id);

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(found);
        transaction.commit();
        System.out.println("il percorso effettuato con ID: " + id + " è stato rimosso correttamente");
    }

    public Double avgPercorsiEffettuati (long mezzo_id){
        String selezione = "SELECT AVG (a.tempoEffettivo) FROM PercorsoEffettuato a WHERE a.mezzo.id = :mezzo_id";
        return em.createQuery(selezione,Double.class)
                .setParameter("mezzo_id",mezzo_id)
                .getSingleResult();
    }

}
