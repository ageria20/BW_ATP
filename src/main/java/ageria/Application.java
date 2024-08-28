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
        PuntodiEmissioneDAO peD = new PuntodiEmissioneDAO(em);
        AbbonamentoDAO abbonamentoDAO = new AbbonamentoDAO(em);
        BigliettoDAO bigliettoDAO = new BigliettoDAO(em);
        TesseraDAO tesseraDAO = new TesseraDAO(em);
        TrattaDAO trattaDAO = new TrattaDAO(em);
        MezzoDAO mezzoDAO = new MezzoDAO(em);
        UtenteDAO utenteDAO = new UtenteDAO(em);
        BigliettoVidimatoDAO bigliettoVidimatoDAO = new BigliettoVidimatoDAO(em);

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
                    inputCreazione(scanner, utenteDAO, tesseraDAO,peD,bigliettoDAO,abbonamentoDAO);
                break;
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

    public static void creazioneUtenteTessera(Scanner scanner, UtenteDAO utenteDAO, TesseraDAO tesseraDAO) {
        System.out.println("------------------------------------------------------");
        String nome = "uno";
        while (nome.equals("uno")) {
            System.out.println("Inserisci nome: ");
            nome = scanner.nextLine();
        }

        String cognome = "cognome";
        while (cognome.equals("cognome")) {
            System.out.println("Inserisci Cognome:");
            cognome = scanner.nextLine();
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
        LocalDate date = LocalDate.of(anno, mese, giorno);
        Utente utente = new Utente(nome, cognome, date);
        System.out.println("Utente con ID: " + utente.getId() + " generato con successo!");
        utenteDAO.save(utente);

        System.out.println("Creazione Tessera personale in corso...");
        Tessera tessera = new Tessera(utente);
        System.out.println("Creazione Tessera con ID: " + tessera.getNumeroTessera() + " creata con successo!");
        System.out.println("Data di emissione: " + tessera.getDataEmissione() + ", scade il: " + tessera.getDataScadenza());
        tesseraDAO.save(tessera);
        System.out.println(utente);
        System.out.println(tessera);
    }

    public static void inputCreazione(Scanner scanner, UtenteDAO utenteDAO, TesseraDAO tesseraDAO,PuntodiEmissioneDAO puntodiEmissioneDAO,BigliettoDAO bigliettoDAO,AbbonamentoDAO abbonamentoDAO) {
        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.println("Premi 1 per CREARE un nuovo UTENTE e relativa TESSERA");
            System.out.println("Premi 2 per ACQUISIRE uno o più BIGLIETTI ");
            System.out.println("Premi 3 per ACQUISIRE un ABBONAMENTO ");
            System.out.println("Premi 4 per VERIFICARE la validità del ABBONAMENTO");
            System.out.println("Premi 5 per RINNOVARE la TESSERA scaduta");
            System.out.println("Premi 0 per USCIRE");
            System.out.print("Scegli un'opzione: ");
            int sceltaUtente = -1;
            try {
                sceltaUtente = scanner.nextInt();
                scanner.nextLine();
                if (sceltaUtente == -1) break;
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero valido!");
                scanner.nextLine();
                continue;
            }
            switch (sceltaUtente) {
                case 1:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Creazione nuovo Utente e associazione Tessera in corso...");
                    creazioneUtenteTessera(scanner, utenteDAO, tesseraDAO);
                    break;
                case 2:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Hai scelto l'acquisto di uno o più biglietti");
                    acquistoBiglietto(scanner, tesseraDAO,puntodiEmissioneDAO,bigliettoDAO);
                    break;
                case 3:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Hai scelto l'acquisto di un abbonamento");
                    acquistoAbbonamento(scanner, tesseraDAO,puntodiEmissioneDAO,abbonamentoDAO);
                    break;
                case 4:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Hai scelto verifica validità abbonamento");
                    verificaValiditàAbbonamento(scanner,abbonamentoDAO);
                case 5:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Hai scelto rinnovare l'abbonamento");
                    rinnovoAbbonamento(scanner,tesseraDAO);
                case 6:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Hai scelto di vidimare il biglietto");
                    vidimazzioneBiglietto();
                case 0:
                    System.out.println("Chiusura in corso...");
                    return;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }

    public static void acquistoAbbonamento(Scanner scanner, TesseraDAO tesseraDAO,PuntodiEmissioneDAO puntodiEmissioneDAO,AbbonamentoDAO abbonamentoDAO) {
        boolean datavalida;
        LocalDate dataInizio;
        do {
            int annoInizio = LocalDate.now().getYear() - 1;
            while (annoInizio < LocalDate.now().getYear()) {
                System.out.println("Inserisci l'anno da cui Inizierà l'abbonamento: ");
                try {
                    if (scanner.hasNext()) {
                        annoInizio = scanner.nextInt();
                    }
                    if (annoInizio >= LocalDate.now().getYear()) {
                        break;
                    } else {
                        System.out.println("Errore: l'anno non può essere precedente all'anno corrente, inserisci un anno valido");
                    }
                } catch (InputMismatchException e) {
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
            dataInizio = LocalDate.of(annoInizio, meseInizio, giornoInizio);
            if (dataInizio.isBefore(LocalDate.now())) {
                System.out.println("la data di Inizio non può essere precedente alla data odierna, inserisci una nuova data di Inizio per favore");
                datavalida = false;
            } else {
                datavalida = true;
            }

        } while (!datavalida);
        LocalDate dataScadenza = null;
        AbbonamentoType tipoAbbonamento = null;
        while (tipoAbbonamento == null) {
            System.out.println("Che tipo di abbonamento hai intenzione di acquistare?");
            System.out.println("Premi 1 per quello Settimanale");
            System.out.println("Premi 2 per quello Mensile");

            try {
                int sceltaAbbonamento = scanner.nextInt();
                if (sceltaAbbonamento == 1) {
                    tipoAbbonamento = AbbonamentoType.SETTIMANALE;
                    dataScadenza = dataInizio.plusWeeks(1);
                } else if (sceltaAbbonamento == 2) {
                    tipoAbbonamento = AbbonamentoType.MENSILE;
                    dataScadenza = dataInizio.plusMonths(1);
                } else {
                    System.out.println("Inserire un valore valido");
                }
            } catch (InputMismatchException e) {
                System.out.println("Errore: inserisci un numero valido");
                scanner.nextLine();
            }
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
                    puntodiEmissioneDAO.save(puntoEmissione);
                } else if (sceltaPunto == 2) {
                    puntoEmissione = new DistributoreAutomatico("Distributore n1", "Stazione Termini", true);
                    puntodiEmissioneDAO.save(puntoEmissione);
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
            Tessera tessera = tesseraDAO.findByID(numeroTessera);
            System.out.println("tessera: " + tessera);
            Abbonamento abbonamento = new Abbonamento(tipoAbbonamento, dataInizio, dataScadenza, puntoEmissione, tessera);
            abbonamentoDAO.save(abbonamento);
            System.out.println("Abbonamento creato con successo!");

            System.out.println(abbonamento);
        } else {
            System.out.println("Non è stato selezionato un punto di emissione valido.");
        }


    }

    public static void vidimazzioneBiglietto(MezzoDAO mezzoDAO, BigliettoVidimato bigliettoVidimato, BigliettoVidimatoDAO bigliettoVidimatoDAO, Scanner scanner,BigliettoDAO bigliettoDAO,Biglietto biglietto) {
        while (true) {
            try {
                System.out.println("Hai scelto di vidimare un biglietto, inserisci l'ID del biglietto per verificare che questa non sia già stata utilizzata");
                long bigliettoID= scanner.nextLong();
                Biglietto biglietto1=bigliettoDAO.findByID(bigliettoID);
                System.out.println("Inserisci la linea del mezzo in cui stai: ");
                long mezzoID= scanner.nextLong();
                Mezzo mezzo1=mezzoDAO.findByID(mezzoID);
                LocalDateTime oraVidimazione=LocalDateTime.now();
                BigliettoVidimato bigliettoVidimato1=new BigliettoVidimato(biglietto1,mezzo1,oraVidimazione);
                biglietto1.vidimazione();
            } catch (InputMismatchException e) {
                System.out.println("inserisci un numero valido");
                scanner.nextLine();
            }
        }
    }
    public static void acquistoBiglietto(Scanner scanner, TesseraDAO tesseraDAO, PuntodiEmissioneDAO puntodiEmissioneDAO,BigliettoDAO bigliettoDAO) {
        int numeroBiglietti = 0;
        boolean inputValido = false;

        while (!inputValido) {
            System.out.println("Quanti biglietti vuoi acquistare?");
            try {
                numeroBiglietti = scanner.nextInt();
                if (numeroBiglietti <= 0) {
                    System.out.println("Il numero di biglietti deve essere maggiore di zero.");
                } else {
                    inputValido = true;
                }
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero valido");
                scanner.next();
            }
        }

        PuntodiEmissione puntoEmissione = null;
        inputValido = false;

        while (!inputValido) {
            System.out.println("Ora inserisci da dove stai acquistando:");
            System.out.println("Premi 1 se dal punto autorizzato");
            System.out.println("Premi 2 se dal distributore automatico");

            try {
                if (scanner.hasNextInt()) {
                    int sceltaPunto = scanner.nextInt();
                    if (sceltaPunto == 1) {
                        puntoEmissione = new RivenditoreAutorizzato("Tabaccheria n1", "Via Mario Rossi 2", RivenditoreType.TABACCHERIA);
                        inputValido = true;
                    } else if (sceltaPunto == 2) {
                        puntoEmissione = new DistributoreAutomatico("Distributore n1", "Stazione Termini", true);
                        inputValido = true;
                    } else {
                        System.out.println("Inserire un valore valido");
                    }
                } else {
                    System.out.println("Errore: inserisci un numero valido");
                    scanner.next();
                }
            } catch (InputMismatchException e) {
                System.out.println("Errore: inserisci un numero valido");
                scanner.next();
            }
        }

        if (puntoEmissione != null) {
            System.out.println("Inserisci numero di tessera dove caricare i biglietti: ");
            long numeroTessera = scanner.nextLong();
            scanner.nextLine();
            Tessera tessera = tesseraDAO.findByID(numeroTessera);
            System.out.println("tessera: " + tessera);

            System.out.println("Caricamento Biglietto/i in corso...");
            //Biglietto[] biglietti=new Biglietto[numeroBiglietti];
            for (int i = 0; i < numeroBiglietti; i++) {
                 Biglietto biglietto = new Biglietto(puntoEmissione, tessera,true);
                 bigliettoDAO.save(biglietto);
                System.out.println("Biglietto " + (i + 1) + " creato.");
            }
        } else {
            System.out.println("Non è stato selezionato un punto di emissione valido.");
        }
    }

    public static void verificaValiditàAbbonamento(Scanner scanner,AbbonamentoDAO abbonamentoDAO){
        long numeroTessera = -1;
        while (numeroTessera == -1) {
            System.out.println("Inserire il numero di tessera associato all'abbonamento: ");
            if (scanner.hasNextLong()) {
                try {
                    numeroTessera = scanner.nextLong();
                    List<Abbonamento> abbonamentiPresenti = abbonamentoDAO.findByNumeroTessera(numeroTessera);
                    if (abbonamentiPresenti.isEmpty()) {
                        System.out.println("Nessun abbonamento trovato per la tessera inserita.");
                    } else {
                        for (Abbonamento abbonamento : abbonamentiPresenti) {
                            System.out.println(abbonamento);
                            if (abbonamento.getDataScadenza().isAfter(LocalDate.now())) {
                                System.out.println("L'abbonamento con ID: " + abbonamento.getId() + " è valido fino al: " + abbonamento.getDataScadenza());
                            } else {
                                System.out.println("L'abbonamento con ID: " + abbonamento.getId() + " è scaduto.");
                            }
                        }
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Inserisci un valore valido");
                    scanner.next();
                }
            } else {
                System.out.println("Inserire un numero valido");
                scanner.next();
            }
        }
    }
    public static void rinnovoAbbonamento(Scanner scanner,TesseraDAO tesseraDAO){

        long numeroTessera=-1;
        while (numeroTessera == -1) {
            System.out.println("Inserisci ID tessera da rinnovare: ");
            if (scanner.hasNextLong()) {
                try {
                    numeroTessera = scanner.nextLong();
                    Tessera tesseraRinnovo=tesseraDAO.findByID(numeroTessera);
                    tesseraRinnovo.rinnovoAutomatico();
                } catch (InputMismatchException e) {
                    System.out.println("Inserisci un valore valido");
                    scanner.next();
                }
            } else {
                System.out.println("Inserire un numero valido");
                scanner.next();
            }
        }
    }

    public static Tratta creazioneTratta(Scanner scanner, TrattaDAO trattaDAO){
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



        // Creazione dell'oggetto Tratta
        Tratta tratta = new Tratta(zonaDiPartenza, capolinea, tempoPrevisto);
        trattaDAO.save(tratta);

        // Stampa l'oggetto creato
        System.out.println("Oggetto Tratta creato: " + tratta);
        return tratta;

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

    public static void creazioneMezzo(Scanner scanner){
        String targa;
        TipoMezzo tipoMezzo;
        int capienza = 0;
        Tratta trattaAssegnata;


        while (true) {
            try {
                System.out.println("Inserisci il tipo di mezzo che vuoi creare: AUTOBUS, TRAM");
                String sceltaMezzo = scanner.nextLine().toUpperCase();
                tipoMezzo = TipoMezzo.valueOf(sceltaMezzo);
                break;
            } catch (IllegalArgumentException ex) {
                System.out.println("Errore: Inserisci un valore valido (AUTOBUS, TRAM).");
            }
        }
        while (true) {
            try {
                System.out.println("Inserisci una targa identificativa per il mezzo: ");
                targa = scanner.nextLine();
                if (!targa.isEmpty()) {
                    break; // Esce dal ciclo se la targa non è vuota
                } else {
                    System.out.println("Errore: La targa non può essere vuota.");
                }
            } catch (InputMismatchException ex) {
                System.out.println("Errore: Inserisci un valore valido.");
                scanner.nextLine(); // Pulisce il buffer dello scanner
            }
        }

        while(capienza <= 0){
            try{
                System.out.println("Inserisci la capienza del mezzo");
                capienza = scanner.nextInt();
                scanner.nextLine();
                if(capienza > 29 & capienza < 50){
                    break;
                }
            } catch(InputMismatchException ex ){
                System.out.println("Inserire un valore valido");
                scanner.next();
            }
        }
        trattaAssegnata = creazioneTratta(scanner, trattaDAO);
        Mezzo mezzo = new Mezzo(tipoMezzo, targa, capienza, trattaAssegnata);
        System.out.println("Mezzo creato correttamente: " + mezzo);

        }
    }


            }

            }




}

