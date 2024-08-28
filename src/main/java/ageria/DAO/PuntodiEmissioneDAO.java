package ageria.DAO;


import ageria.entities.Biglietto;
import ageria.entities.PuntodiEmissione;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public long countBigliettiEmessiInPeriodo(long puntoEmissioneId, LocalDate dataInizio, LocalDate dataFine) {
        String selezione = "SELECT COUNT(b) FROM Biglietto b WHERE b.puntoEmissione.id = :puntoEmissioneId AND b.dataEmissione BETWEEN :dataInizio AND :dataFine";
        return  em.createQuery(selezione,Long.class)
                 .setParameter("puntoEmissioneId", puntoEmissioneId)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getSingleResult();

    }
    public long countAbbonamentiEmessiInPeriodo(long puntoEmissioneId, LocalDate dataInizio, LocalDate dataFine) {
        String selezione = "SELECT COUNT(a) FROM Abbonamento a WHERE a.puntoEmissione.id = :puntoEmissioneId AND a.dataInizio BETWEEN :dataInizio AND :dataFine";
        return em.createQuery(selezione, Long.class)
                .setParameter("puntoEmissioneId", puntoEmissioneId)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getSingleResult();
    }
    public Map<PuntodiEmissione, Long> countTotaleBigliettiEmessiInPeriodoPerTutti(LocalDate dataInizio, LocalDate dataFine) {
        String selezione = "SELECT p, COUNT(b) FROM PuntodiEmissione p JOIN p.bigliettiEmessi b WHERE b.dataEmissione BETWEEN :dataInizio AND :dataFine GROUP BY p";
        List<Object[]> results = em.createQuery(selezione, Object[].class)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getResultList();

        Map<PuntodiEmissione, Long> bigliettiPerPunto = new HashMap<>();
        for (Object[] result : results) {
            PuntodiEmissione punto = (PuntodiEmissione) result[0];
            Long count = (Long) result[1];
            bigliettiPerPunto.put(punto, count);
        }
        return bigliettiPerPunto;
    }

    public Map<PuntodiEmissione, Long> countTotaleAbbonamentiEmessiInPeriodoPerTutti(LocalDate dataInizio, LocalDate dataFine) {
        String selezione = "SELECT p, COUNT(a) FROM PuntodiEmissione p JOIN p.abbonamentiEmessi a WHERE a.dataInizio BETWEEN :dataInizio AND :dataFine GROUP BY p";
        List<Object[]> results = em.createQuery(selezione, Object[].class)
                .setParameter("dataInizio", dataInizio)
                .setParameter("dataFine", dataFine)
                .getResultList();

        Map<PuntodiEmissione, Long> abbonamentiPerPunto = new HashMap<>();
        for (Object[] result : results) {
            PuntodiEmissione punto = (PuntodiEmissione) result[0];
            Long count = (Long) result[1];
            abbonamentiPerPunto.put(punto, count);
        }
        return abbonamentiPerPunto;
    }
}
