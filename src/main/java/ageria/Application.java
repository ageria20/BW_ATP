package ageria;

import ageria.DAO.*;
import ageria.entities.*;
import ageria.enums.AbbonamentoType;
import ageria.enums.Manutenzione;
import ageria.enums.RivenditoreType;
import ageria.enums.TipoMezzo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.lang.reflect.Member;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Application {
private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bw_atp");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
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
                    inputCreazione(scanner,tesseraDAO);
               case 2:
                   creazioneTratta(scanner, trattaDAO);
                   break;
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
    public static void creazioneUtenteTessera(Scanner scanner){
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
        int anno = -1;
        while (true) {
            System.out.println("Inserisci l'anno di nascita: ");
            try {
                anno = scanner.nextInt();
                if (anno >= LocalDate.now().getYear() - 90 && anno <= LocalDate.now().getYear() - 8) {
                    break;
                } else {
                    System.out.println("Errore: l'anno deve essere compreso tra " + (LocalDate.now().getYear() - 90) + " e " + (LocalDate.now().getYear() - 8));
                }
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero valido!");
                scanner.nextLine();
            }
        }
        int mese = -1;
        while (true) {
            System.out.println("Inserisci il mese di nascita in formato numerico (da 1 a 12): ");
            try {
                mese = scanner.nextInt();
                if (mese >= 1 && mese <= 12) {
                    break;
                } else {
                    System.out.println("Errore: il numero inserito deve essere compreso tra 1 e 12");
                }
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero valido!");
                scanner.nextLine();
            }
        }
        int giorno = -1;
        while (true) {
            System.out.println("Inserisci il giorno di nascita in formato numerico: ");
            try {
                giorno = scanner.nextInt();
                if (giorno >= 1 && giorno <= 31) {
                    break;
                } else {
                    System.out.println("Errore: il numero inserito deve essere compreso tra 1 e 31");
                }
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero valido!");
                scanner.nextLine();
            }
        }
        LocalDate date=LocalDate.of(anno,mese,giorno);
        Utente utente=new Utente(nome,cognome,date);
        System.out.println("Utente con ID: "+ utente.getId()+" generato con successo!");
        //utenteDAO.save(utente);

        System.out.println("Creazione Tessera personale in corso...");
        Tessera tessera=new Tessera(utente);
        System.out.println("Creazione Tessera con ID: "+tessera.getNumeroTessera() +" creata con successo!");
        System.out.println("Data di emissione: "+tessera.getDataEmissione()+", scade il: "+tessera.getDataScadenza());
        //tesseraDAO.save(tessera);
        System.out.println(utente);
        System.out.println(tessera);
    }

    public static void inputCreazione( Scanner scanner,TesseraDAO tesseraDAO){
        while (true){
            System.out.println("------------------------------------------------------");
            System.out.println("Premi 1 per la creazione di un nuovo UTENTE e relativa TESSERA");
            System.out.println("Premi 2 per ACQUISIRE uno o più BIGLIETTI ");
            System.out.println("Premi 3 per ACQUISIRE un ABBONAMENTO ");
            System.out.println("Premi 0 per USCIRE");
            System.out.print("Scegli un'opzione: ");
            int sceltaUtente = -1;
            try {
                sceltaUtente = scanner.nextInt();
                scanner.nextLine();
                if(sceltaUtente == -1) break;
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero valido!");
                scanner.nextLine();
                continue;
            }
            switch (sceltaUtente){
                case 1:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Creazione nuovo Utente e associazione Tessera in corso...");
                    creazioneUtenteTessera(scanner);
                    break;
                case 2:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Hai scelto l'acquisto di uno o più biglietti");
                    break;
                case 3:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Hai scelto l'acquisto di un abbonamento");
                    acquistoAbbonamento(scanner,tesseraDAO);
                    break;
                case 0:
                    System.out.println("Chiusura in corso...");
                    return;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }

    public static void acquistoAbbonamento(Scanner scanner,TesseraDAO tesseraDAO){
        int annoInizio=LocalDate.now().getYear()-1;
        while (annoInizio<LocalDate.now().getYear()){
            System.out.println("Inserisci l'anno da cui Inizierà l'abbonamento: ");
            try{
                if(scanner.hasNext()){
                    annoInizio= scanner.nextInt();
                }if(annoInizio>=LocalDate.now().getYear()){
                    break;
                }else {
                    System.out.println("Errore: l'anno non può essere precedente all'anno corrente, inserisci un anno valido");
                }
            }catch (InputMismatchException e){
                System.out.println("Inserisci un anno valido");
                scanner.nextLine();
            }
        }
        int meseInizio = -1;
        while (meseInizio == -1) {
            System.out.println("Inserisci il mese in formato numerico (da 1 a 12): ");
            try {
                if (scanner.hasNextInt()) {
                    int input = scanner.nextInt();
                    if (input >= 1 && input <= 12) {
                        meseInizio = input;
                    } else {
                        System.out.println("Errore: il numero inserito deve essere compreso tra 1 e 12");
                    }
                } else {
                    System.out.println("Inserisci un mese (numerico) valido");
                    scanner.next();
                }
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un mese (numerico) valido");
                scanner.next();
            }
        }

        int giornoInizio = -1;
        while (giornoInizio == -1) {
            System.out.println("Inserisci il giorno in formato numerico (da 1 a 31): ");
            try {
                if (scanner.hasNextInt()) {
                    int input = scanner.nextInt();
                    if (input >= 1 && input <= 31) {
                        giornoInizio = input;
                    } else {
                        System.out.println("Errore: il numero inserito deve essere compreso tra 1 e 31");
                    }
                } else {
                    System.out.println("Inserisci un giorno (numerico) valido");
                    scanner.next();
                }
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero valido");
                scanner.next();
            }
        }
        LocalDate dataInizio= LocalDate.of(annoInizio,meseInizio,giornoInizio);
        LocalDate dataScadenza=null;

        System.out.println("Che tipo di abbonamento hai intenzione di acquistare?");
        System.out.println("Premi 1 per quello Settimanale");
        System.out.println("Premi 2 per quello Mensile");
        AbbonamentoType tipoAbbonamento = null;
        try {
            if (scanner.hasNextInt()) {
                int sceltaAbbonamento = scanner.nextInt();
                if (sceltaAbbonamento == 1) {
                    tipoAbbonamento = AbbonamentoType.SETTIMANALE;
                    dataScadenza=dataInizio.plusWeeks(1);
                } else if (sceltaAbbonamento == 2) {
                    tipoAbbonamento = AbbonamentoType.MENSILE;
                    dataScadenza=dataInizio.plusMonths(1);
                } else {
                    System.out.println("Inserire un valore valido");
                }
            } else {
                System.out.println("Errore: inserisci un numero valido");
                scanner.nextLine();
            }
        } catch (InputMismatchException e) {
            System.out.println("Errore: inserisci un numero valido");
            scanner.nextLine();
        }

        if (tipoAbbonamento != null) {
            System.out.println("Hai scelto un abbonamento di tipo: " + tipoAbbonamento);
        } else {
            System.out.println("Non è stato selezionato un tipo di abbonamento valido.");
        }


        System.out.println("Ora inserisci da dove stai acquistando:");
        System.out.println("Premi 1 se dal punto autorizzato");
        System.out.println("Premi 2 se dal distributore automatico");

        PuntodiEmissione puntoEmissione = null;
        try {
            if (scanner.hasNextInt()) {
                int sceltaPunto = scanner.nextInt();
                if (sceltaPunto == 1) {
                    puntoEmissione = new RivenditoreAutorizzato("Tabaccheria n1", "Via Mario Rossi 2", RivenditoreType.TABACCHERIA);
                } else if (sceltaPunto == 2) {
                    puntoEmissione = new DistributoreAutomatico("Distributore n1", "Stazione Termini", true);
                } else {
                    System.out.println("Inserire un valore valido");
                }
            } else {
                System.out.println("Errore: inserisci un numero valido");
                scanner.nextLine();
            }
        } catch (InputMismatchException e) {
            System.out.println("Errore: inserisci un numero valido");
            scanner.nextLine();
        }

        if (puntoEmissione != null) {
            System.out.println("Inserisci numero di tessera dove attivare l'abbonamento scelto: ");
           long numeroTessera = scanner.nextLong();
            scanner.nextLine();
            Tessera tessera=tesseraDAO.findByID(numeroTessera);
            System.out.println("tessera: "+tessera);
            Abbonamento abbonamento1 = new Abbonamento(tipoAbbonamento,dataInizio,dataScadenza,puntoEmissione,tessera);
            System.out.println("Abbonamento creato con successo!");

            System.out.println(abbonamento1);
        } else {
            System.out.println("Non è stato selezionato un punto di emissione valido.");
        }



    }

    public static void creazioneTratta(Scanner scanner, TrattaDAO trattaDAO){
        String zonaDiPartenza = null;
        String capolinea = null;
        Timestamp tempoPrevisto = null;

        // Gestione della Zona di Partenza
        while (zonaDiPartenza == null || zonaDiPartenza.trim().isEmpty()) {
            System.out.println("Inserisci la zona di partenza: ");
            zonaDiPartenza = scanner.nextLine();
            if (zonaDiPartenza.trim().isEmpty()) {
                System.out.println("Errore: La zona di partenza non può essere vuota.");
            }
        }

        // Gestione del Capolinea
        while (capolinea == null || capolinea.trim().isEmpty()) {
            System.out.println("Inserisci il capolinea: ");
            capolinea = scanner.nextLine();
            if (capolinea.trim().isEmpty()) {
                System.out.println("Errore: Il capolinea non può essere vuoto.");
            }
        }

        // Gestione del Tempo Previsto
        while (tempoPrevisto == null) {
            try {
                System.out.println("Inserisci il tempo previsto (formato: yyyy-mm-dd hh:mm:ss): ");
                String tempoPrevistoInput = scanner.nextLine();
                tempoPrevisto = Timestamp.valueOf(tempoPrevistoInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: Formato del tempo previsto non valido. Usa il formato yyyy-mm-dd hh:mm:ss.");
            }
        }

        // Inizializza la lista vuota per i mezzi assegnati (se necessario)
        // In questo esempio, la lista è vuota poiché non richiediamo dettagli sui mezzi
        // Puoi implementare una logica più complessa per popolare questa lista se lo desideri.

        // Creazione dell'oggetto Tratta
        Tratta tratta = new Tratta(zonaDiPartenza, capolinea, tempoPrevisto);
        trattaDAO.save(tratta);

        // Stampa l'oggetto creato
        System.out.println("Oggetto Tratta creato: " + tratta);
    }

    public static void creazioneStatoMezzo(Scanner scanner, Mezzo mezzo){
         // Presupponiamo che l'oggetto Mezzo esista già e sia stato inizializzato
        Manutenzione stato = null;
        LocalDate dataInizio = null;
        LocalDate dataFine = null;

        // Gestione dello stato di manutenzione
        while (stato == null) {
            try {
                System.out.println("Inserisci lo stato di manutenzione (IN_MANUTENZIONE, FUORI_MANUTENZIONE): ");
                String statoInput = scanner.nextLine().toUpperCase();
                stato = Manutenzione.valueOf(statoInput);
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: Stato di manutenzione non valido. Inserisci IN_MANUTENZIONE o FUORI_MANUTENZIONE.");
            }
        }

        // Gestione della data di inizio
        while (dataInizio == null) {
            try {
                System.out.println("Inserisci la data di inizio (formato: yyyy-mm-dd): ");
                String dataInizioInput = scanner.nextLine();
                dataInizio = LocalDate.parse(dataInizioInput);
            } catch (DateTimeParseException e) {
                System.out.println("Errore: Formato della data di inizio non valido. Usa il formato yyyy-mm-dd.");
            }
        }

        // Gestione della data di fine
        while (dataFine == null) {
            try {
                System.out.println("Inserisci la data di fine (formato: yyyy-mm-dd), oppure premi invio se non disponibile: ");
                String dataFineInput = scanner.nextLine();
                if (dataFineInput.isEmpty()) {
                    break; // Nessuna data di fine inserita
                }
                dataFine = LocalDate.parse(dataFineInput);

                if (dataFine.isBefore(dataInizio)) {
                    System.out.println("Errore: La data di fine non può essere precedente alla data di inizio.");
                    dataFine = null; // Resetta la data di fine
                }
            } catch (DateTimeParseException e) {
                System.out.println("Errore: Formato della data di fine non valido. Usa il formato yyyy-mm-dd.");
            }
        }

        // Creazione dell'oggetto StatoMezzo
        StatoMezzo statoMezzo = new StatoMezzo(mezzo, stato, dataInizio, dataFine);

        // Stampa l'oggetto creato
        System.out.println("Oggetto StatoMezzo creato: " + statoMezzo);
    }

    public static void creazioneMezzo(){}


   /*  public static void creazioneElementoAdmin(Scanner scanner){
        while(true){
            System.out.println("Premi 1 per inserire un nuovo Mezzo");
            //System.out.println("Premi 2 per inserire un nuovo Punto di Emissione");
            System.out.println("Premi 2 per inserire un nuova Tratta");
           //System.out.println("Premi 4 per creare un nuova Tessera");
           // System.out.println("Premi 5 per creare un nuovo Abbonamento");
            System.out.println("Premi 6 per creare un nuovo Biglietto");
            System.out.println("Premi 0 per USCIRE");
            System.out.print("Scegli un'opzione: ");
            int scelta = -1;
            try{
                scelta = scanner.nextInt();
               scanner.nextLine();
            }catch(InputMismatchException ex){
               System.out.println("Inserisci un numero valido");
            }
            switch(scelta){
                case 1:
                    TipoMezzo tipoMezzo = null;
                    int capienza = 0;
                    boolean statoManutenzione = false;
                    int bigliettiValidati = 0;
                    Tratta trattaAssegnata = null;

                    while(tipoMezzo == null) {
                        try {
                            System.out.println("Inserisci il tipo di Mezzo: AUTOBUS, TRAM");
                            String sceltaMezzo = scanner.nextLine();
                            tipoMezzo = TipoMezzo.valueOf(sceltaMezzo);
                        } catch (IllegalArgumentException ex) {
                            System.out.println("Inserisci un valore valido");
                        }
                    }
                    while(true) {
                        try {
                            System.out.println("Inserisci la capienza del Mezzo");
                            capienza = scanner.nextInt();
                            scanner.nextLine();
                            if(capienza <=0) {
                                System.out.println("La capienza deve essere maggiore di 0");
                            }
                            else break;

                        }catch(InputMismatchException ex){
                            System.out.println("Inserisci un valore valido");
                        }
                        while(true){
                            try{
                                System.out.println("Inserisci lo stato di manutenzione del mezzo: (true/false)");
                                statoManutenzione = scanner.nextBoolean();
                                break;
                            } catch(InputMismatchException ex) {
                                System.out.println("Inserisci un valore valido");
                            }
                        }

                    }


            }

            }

        }
     }*/



    }

