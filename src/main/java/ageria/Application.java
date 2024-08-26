package ageria;

import ageria.DAO.PuntodiEmissioneDAO;
import ageria.entities.PuntodiEmissione;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Application {
private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw_atp");
    public static void main(String[] args) {
        EntityManager em = emf.createEntityManager();
        //qui mettiamo i DAO
        PuntodiEmissioneDAO peD=new PuntodiEmissioneDAO(em);


        //qui cominciamo con lo scanner
        Scanner scanner=new Scanner(System.in);






        System.out.println("TUTTO OK!");
        em.close();
        emf.close();
    }
}
