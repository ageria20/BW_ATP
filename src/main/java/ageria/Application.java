package ageria;

import ageria.DAO.*;
import ageria.entities.Mezzo;
import ageria.entities.PuntodiEmissione;
import ageria.entities.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Locale;
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
        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.println("Premi 1 se sei un utente");
            System.out.println("Premi 2 se sei admin");
            System.out.println("Premi 0 per USCIRE");
            System.out.print("Scegli un'opzione: ");
            int scelta = -1;
            try {
                scelta = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero valido!");
                scanner.nextLine();
                continue;
            }
            switch (scelta) {
                case 1:
                    while (true){
                        System.out.println("------------------------------------------------------");
                        System.out.println("Premi 1 per creare un nuovo utente");
                        System.out.println("Premi 2 se hai già un ID utente");
                        System.out.println("Premi 0 per USCIRE");
                        System.out.print("Scegli un'opzione: ");
                        int sceltaUtente = -1;
                        try {
                            sceltaUtente = scanner.nextInt();
                            scanner.nextLine();
                        } catch (InputMismatchException e) {
                            System.out.println("Inserisci un numero valido!");
                            scanner.nextLine();
                            continue;
                        }
                        switch (sceltaUtente){
                            case 1:
                                System.out.println("------------------------------------------------------");
                                String nome="uno";
                                while (nome.equals("uno")){
                                    System.out.println("Inserisci nome: ");
                                       nome=scanner.nextLine();
                                }

                                String cognome= "cognome";
                                while (cognome.equals("cognome")){
                                    System.out.println("Inserisci Cognome:");
                                    cognome= scanner.nextLine();
                                }
                                int anno=-1;
                                while (anno==-1){
                                    System.out.println("Inserisci l'anno di nascita: ");
                                    try{
                                        if(scanner.hasNext()){
                                            anno= scanner.nextInt();
                                        }if(anno>=1900 && anno<=2999){
                                            break;
                                        }else {
                                            System.out.println("Errore: l'anno deve essere compresso tra 1900 e 2999");
                                        }
                                    }catch (InputMismatchException e){
                                        System.out.println("Inserisci un numero valido");
                                        scanner.nextLine();
                                    }
                                }
                                int mese=-1;
                                while (mese==-1){
                                    System.out.println("Inserisci il mese di nascita in formato numerico(da 1 a 12): ");
                                    try{
                                        if(scanner.hasNext()){
                                            mese= scanner.nextInt();
                                        }if(mese>0 && mese<13){
                                            break;
                                        }else {
                                            System.out.println("Errore: il numero inserito deve essere compresso tra 1 e 12");
                                        }
                                    }catch (InputMismatchException e){
                                        System.out.println("Inserisci un numero valido");
                                        scanner.nextLine();
                                    }
                                }
                                int giorno=-1;
                                while (giorno==-1){
                                    System.out.println("Inserisci il giorno di nascita in formato numerico(da 1 a 12): ");
                                    try{
                                        if(scanner.hasNext()){
                                            giorno= scanner.nextInt();
                                        }if(giorno>0 && giorno<13){
                                            break;
                                        }else {
                                            System.out.println("Errore: il numero inserito deve essere compresso tra 1 e 12");
                                        }
                                    }catch (InputMismatchException e){
                                        System.out.println("Inserisci un numero valido");
                                        scanner.nextLine();
                                    }
                                }

                        }
                    }

//                case 2:
//
//                    break;
//                case 3:
//
//                    break;
//                case 4:
//
//                    break;
//                case 5:
//
//                    break;
//                case 6:
//
//                    break;
//                case 7:
//
//                    break;
//                case 8:
//
//                    break;
                case 0:
                    System.out.println("Chiusura in corso...");
                    return;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
            System.out.println("TUTTO OK!");
            em.close();
            emf.close();
        }
     }
    }

