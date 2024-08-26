package ageria;

import ageria.DAO.*;
import ageria.entities.Mezzo;
import ageria.entities.PuntodiEmissione;
import ageria.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.lang.reflect.Member;
import java.util.Scanner;

public class Application {
private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw_atp");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        //qui mettiamo i DAO
        PuntodiEmissioneDAO peD=new PuntodiEmissioneDAO(em);
        AbbonamentoDAO abbonamentoDAO=new AbbonamentoDAO(em);
        BigliettoDAO bigliettoDAO=new BigliettoDAO(em);
        TesseraDAO tesseraDAO=new TesseraDAO(em);
        TrattaDAO trattaDAO=new TrattaDAO(em);
        MezzoDAO mezzoDAO=new MezzoDAO(em);
        UtenteDAO utenteDAO=new UtenteDAO(em);



        //qui cominciamo con lo scanner
        Scanner scanner=new Scanner(System.in);






        System.out.println("TUTTO OK!");
        em.close();
        emf.close();
    }
}
