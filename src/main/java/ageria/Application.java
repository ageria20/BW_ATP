package ageria;

import ageria.DAO.*;
import ageria.entities.*;
import ageria.enums.AbbonamentoType;
import ageria.enums.Manutenzione;
import ageria.enums.RivenditoreType;
import ageria.enums.TipoMezzo;
import ageria.exceptions.NotFoundEx;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

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
        PercorsoEffettuatoDAO percorsoEffettuatoDAO = new PercorsoEffettuatoDAO(em);


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
                    inputUtente(scanner, utenteDAO, tesseraDAO, peD, bigliettoDAO, abbonamentoDAO, mezzoDAO, bigliettoVidimatoDAO);
                    break;

                case 2:
                    inputAdmin(scanner, trattaDAO, percorsoEffettuatoDAO,peD);
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
    public static void inputUtente(Scanner scanner, UtenteDAO utenteDAO, TesseraDAO tesseraDAO, PuntodiEmissioneDAO puntodiEmissioneDAO, BigliettoDAO bigliettoDAO, AbbonamentoDAO abbonamentoDAO, MezzoDAO mezzoDAO, BigliettoVidimatoDAO bigliettoVidimatoDAO) {
        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.println("Premi 1 per CREARE un nuovo UTENTE e relativa TESSERA");
            System.out.println("Premi 2 per ACQUISIRE uno o più BIGLIETTI ");
            System.out.println("Premi 3 per ACQUISIRE un ABBONAMENTO ");
            System.out.println("Premi 4 per VERIFICARE la validità del ABBONAMENTO");
            System.out.println("Premi 5 per RINNOVARE la TESSERA scaduta");
            System.out.println("Premi 6 per VIDIMARE il biglietto");
            System.out.println("Premi 7 per VERIFICARE la validità della TESSERA");
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
                    acquistoBiglietto(scanner, tesseraDAO,bigliettoDAO);
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
                    break;
                case 5:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Hai scelto rinnovare l'abbonamento");
                    rinnovoTessera(scanner,tesseraDAO);
                    break;
                case 6:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Hai scelto di vidimare il biglietto");
                    vidimazioneBiglietto(mezzoDAO,bigliettoVidimatoDAO,scanner,bigliettoDAO);
                    break;
                case 7:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Hai scelto verifica validità tessera");
                    verificaValiditàTessera(scanner,tesseraDAO);
                    break;
                case 0:
                    System.out.println("Chiusura in corso...");
                    return;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
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
    public static void vidimazioneBiglietto(MezzoDAO mezzoDAO, BigliettoVidimatoDAO bigliettoVidimatoDAO, Scanner scanner, BigliettoDAO bigliettoDAO) {
        while (true) {
            try {
                System.out.println("Hai scelto di vidimare un biglietto. Inserisci l'ID del biglietto per verificare che non sia già stato utilizzato:");
                long bigliettoID = scanner.nextLong();
                Biglietto biglietto = bigliettoDAO.findByID(bigliettoID);

                if (biglietto.isBigliettoVidimato()) {
                    System.out.println("Questo biglietto è già stato vidimato.");
                    continue;
                }

                System.out.println("Inserisci la linea del mezzo in cui ti trovi:");
                long mezzoID = scanner.nextLong();
                Mezzo mezzo = mezzoDAO.findByID(mezzoID);

                LocalDateTime oraVidimazione = LocalDateTime.now();
                BigliettoVidimato bigliettoVidimato = new BigliettoVidimato(biglietto, mezzo, oraVidimazione);

                biglietto.vidimazione();
                mezzo.setBigliettiValidati(mezzo.getBigliettiValidati() + 1);

                bigliettoVidimatoDAO.save(bigliettoVidimato);
                bigliettoDAO.save(biglietto);
                mezzoDAO.save(mezzo);
                System.out.println("Biglietto vidimato correttamente!");
                break;

            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero valido.");
                scanner.nextLine();
            } catch (NotFoundEx e) {
                System.out.println("Elemento non trovato: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Si è verificato un errore: " + e.getMessage());
                break;
            }
        }
    }
    public static void acquistoBiglietto(Scanner scanner, TesseraDAO tesseraDAO,BigliettoDAO bigliettoDAO) {
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
    public static void rinnovoTessera(Scanner scanner, TesseraDAO tesseraDAO){
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
    public static void verificaValiditàTessera(Scanner scanner,TesseraDAO tesseraDAO){
        long numeroTessera = -1;
        while (numeroTessera == -1) {
            System.out.println("Inserire il numero di tessera da verificare: ");
            if (scanner.hasNextLong()) {
                try {
                    numeroTessera = scanner.nextLong();
                    Tessera tessera = tesseraDAO.findByID(numeroTessera);
                    if(tessera.getDataScadenza().isAfter(LocalDate.now())){
                        System.out.println("La tessera inserita è scaduta!");
                        System.out.println("Rinnovo automatico in corso...");
                        tessera.rinnovoAutomatico();
                        System.out.println("Tessera con ID: "+tessera.getNumeroTessera()+" è stata rinnovata con successo!");
                        System.out.println("Nuova data di scadenza: "+tessera.getDataScadenza());
                    }else {
                        System.out.println("la tessera è ancora valida ed scade il: "+tessera.getDataScadenza());
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Inserisci un valore valido");
                    scanner.next();
                }
            }else{
                    System.out.println("Inserire un numero valido");
                    scanner.next();
                }

        }
    }
    public static void monitoraggioNBigliettiAbbonamenti (Scanner scanner,PuntodiEmissioneDAO puntodiEmissioneDAO,MezzoDAO mezzoDAO) {
        while (true) {
            System.out.println("Premi 1 per MONITORARE i BIGLIETTI emessi in un determinato periodo");
            System.out.println("Premi 2 per MONITORARE gli ABBONAMENTI emessi in un determinato periodo");
            System.out.println("Premi 3 per MONITORARE il TOTALE BIGLIETTI/ABBONAMENTI emessi in un determinato periodo");
            System.out.println("Premi 4 per MONITORARE i BIGLIETTI VIDIMATI in un determinato MEZZO");
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
                  conteggioBigliettiEmessi(scanner,puntodiEmissioneDAO);
                break;
              case 2:
                System.out.println("-------------------------------------------------");
                  conteggioAbbonamentiEmessi(scanner,puntodiEmissioneDAO);
                break;
              case 3:
                System.out.println("-------------------------------------------------");
                  conteggioBigliettiEAbbonamentiTotali(scanner,puntodiEmissioneDAO);
                break;
              case 4:
                System.out.println("-------------------------------------------------");
                  conteggioBigliettiVidimatiSuUnMezzo(scanner,mezzoDAO);
                  break;
              case 0:
                System.out.println("Chiusura in corso...");
                return;
              default:
                System.out.println("Opzione non valida. Riprova.");
           }
        }
    }
    public static LocalDate recuperaData(Scanner scanner, String tipo) {
        int anno = -1, mese = -1, giorno = -1;

        while (true) {
            System.out.println("Inserisci l'anno di " + tipo + ": ");
            try {
                anno = scanner.nextInt();
                if (anno >= LocalDate.now().getYear() - 90 && anno <= LocalDate.now().getYear()) {
                    break;
                } else {
                    System.out.println("Errore: l'anno deve essere compreso tra " + (LocalDate.now().getYear() - 90) + " e " + (LocalDate.now().getYear()));
                }
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un numero valido!");
                scanner.nextLine();
            }
        }

        while (true) {
            System.out.println("Inserisci il mese di " + tipo + " in formato numerico (da 1 a 12): ");
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

        while (true) {
            System.out.println("Inserisci il giorno di " + tipo + " in formato numerico: ");
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

        return LocalDate.of(anno, mese, giorno);
    }
    public static void conteggioBigliettiEmessi(Scanner scanner,PuntodiEmissioneDAO puntodiEmissioneDAO){
        long puntoEmissioneID=-1;
        while (puntoEmissioneID == -1) {
            System.out.println("Inserisci ID del punto di Emissione: ");
            if (scanner.hasNextLong()) {
                try {
                    puntoEmissioneID = scanner.nextLong();
                    PuntodiEmissione punto1=puntodiEmissioneDAO.findByID(puntoEmissioneID);
                    System.out.println("Il punto di emissione selezionato è: "+punto1.getId()+" di nome "+punto1.getNome());

                } catch (InputMismatchException e) {
                    System.out.println("Inserisci un valore valido");
                    scanner.next();
                }
            } else {
                System.out.println("Inserire un numero valido");
                scanner.next();
            }
        }

        LocalDate dateInizio = recuperaData(scanner,"inizio");
        System.out.println("Data Inizio: "+dateInizio+" inserita con successo!");

        LocalDate dateFine = recuperaData(scanner,"fine");
        System.out.println("Data Fine: "+dateFine+" inserita con successo!");
        Long conteggioBiglietti=puntodiEmissioneDAO.countBigliettiEmessiInPeriodo(puntoEmissioneID,dateInizio,dateFine);
        System.out.println("I biglietti presenti in periodo è: "+conteggioBiglietti+"!");
}
    public static void conteggioAbbonamentiEmessi(Scanner scanner,PuntodiEmissioneDAO puntodiEmissioneDAO){
    long puntoEmissioneID=-1;
    while (puntoEmissioneID == -1) {
        System.out.println("Inserisci ID del punto di Emissione: ");
        if (scanner.hasNextLong()) {
            try {
                puntoEmissioneID = scanner.nextLong();
                PuntodiEmissione punto1=puntodiEmissioneDAO.findByID(puntoEmissioneID);
                System.out.println("Il punto di emissione selezionato è: "+punto1.getId()+" di nome "+punto1.getNome());

            } catch (InputMismatchException e) {
                System.out.println("Inserisci un valore valido");
                scanner.next();
            }
        } else {
            System.out.println("Inserire un numero valido");
            scanner.next();
        }
    }

        LocalDate dateInizio = recuperaData(scanner,"inizio");
    System.out.println("Data Inizio: "+dateInizio+" inserita con successo!");

        LocalDate dateFine = recuperaData(scanner,"fine");
    System.out.println("Data Fine: "+dateFine+" inserita con successo!");
    Long conteggioAbbonamenti=puntodiEmissioneDAO.countAbbonamentiEmessiInPeriodo(puntoEmissioneID,dateInizio,dateFine);
    System.out.println("Gli ABBONAMENTI presenti in quel periodo sono: "+conteggioAbbonamenti+"!");
}
    public static void conteggioBigliettiEAbbonamentiTotali(Scanner scanner,PuntodiEmissioneDAO puntodiEmissioneDAO){
    long puntoEmissioneID=-1;
    while (puntoEmissioneID == -1) {
        System.out.println("Inserisci ID del punto di Emissione: ");
        if (scanner.hasNextLong()) {
            try {
                puntoEmissioneID = scanner.nextLong();
                PuntodiEmissione punto1=puntodiEmissioneDAO.findByID(puntoEmissioneID);
                System.out.println("Il punto di emissione selezionato è: "+punto1.getId()+" di nome "+punto1.getNome());

            } catch (InputMismatchException e) {
                System.out.println("Inserisci un valore valido");
                scanner.next();
            }
        } else {
            System.out.println("Inserire un numero valido");
            scanner.next();
        }
    }

        LocalDate dateInizio = recuperaData(scanner,"inizio");
        System.out.println("Data Inizio: "+dateInizio+" inserita con successo!");
        LocalDate dateFine = recuperaData(scanner,"fine");
        System.out.println("Data Fine: "+dateFine+" inserita con successo!");

    Long conteggioAbbonamenti=puntodiEmissioneDAO.countAbbonamentiEmessiInPeriodo(puntoEmissioneID,dateInizio,dateFine);
    Long conteggioBiglietti=puntodiEmissioneDAO.countAbbonamentiEmessiInPeriodo(puntoEmissioneID,dateInizio,dateFine);
    Long Totale=conteggioBiglietti+conteggioAbbonamenti;
    System.out.println("Gli ABBONAMENTI e BIGLIETTI TOTALI presenti in quel periodo sono: "+Totale+"!");
}
public static void conteggioBigliettiVidimatiSuUnMezzo(Scanner scanner,MezzoDAO mezzoDAO){
    long mezzoID=-1;
    while (mezzoID == -1) {
        System.out.println("Inserisci ID del MEZZO: ");
        if (scanner.hasNextLong()) {
            try {
                mezzoID = scanner.nextLong();
                Mezzo mezzo=mezzoDAO.findByID(mezzoID);
                System.out.println("Il mezzo selezionato è: "+mezzo.getId()+" di tipo "+mezzo.getTipoMezzo());
            } catch (InputMismatchException e) {
                System.out.println("Inserisci un valore valido");
                scanner.next();
            }
        } else {
            System.out.println("Inserire un numero valido");
            scanner.next();
        }
    }
    System.out.println("Conteggio biglietti vidimati in corso...");
    Long conteggio=mezzoDAO.countBigliettiVidimatiPerMezzo(mezzoID);
    System.out.println("Il numero dei biglietti vidimati sul mezzo selezionato è di: "+conteggio);
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

        // Inizializza la lista vuota per i mezzi assegnati (se necessario)
        // In questo esempio, la lista è vuota poiché non richiediamo dettagli sui mezzi
        // Puoi implementare una logica più complessa per popolare questa lista se lo desideri.

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
    public static void creazioneMezzo(Scanner scanner,TrattaDAO trattaDAO){
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
       trattaAssegnata=creazioneTratta(scanner, trattaDAO);
        Mezzo mezzo = new Mezzo(tipoMezzo, targa, capienza, trattaAssegnata);
        System.out.println("Mezzo creato correttamente: " + mezzo);

    }
    public static void inputAdmin(Scanner scanner, TrattaDAO trattaDAO, PercorsoEffettuatoDAO percorsoEffettuatoDAO,PuntodiEmissioneDAO puntodiEmissioneDAO) {
        while (true) {
            System.out.println("------------------------------------------------------");
            System.out.println("Premi 1 per CREARE una nuova TRATTA");
            System.out.println("Premi 2 per SCEGLIERE una TRATTA");
            System.out.println("Premi 3 per MONITORARE le attività dei BIGLIETTI e/o ABBONAMENTI");
            System.out.println("Scegli un'opzione: ");
            int sceltaAdmin = -1;
            try {
                sceltaAdmin = scanner.nextInt();
                scanner.nextLine();
                if (sceltaAdmin == -1) break;
            } catch (InputMismatchException e) {
                System.out.println("inserisci un numero valido!");
                scanner.nextLine();
                continue;
            }
            switch (sceltaAdmin) {
                case 1:
                    System.out.println("------------------------------------------------------");
                    System.out.println("creazione nuova tratta in corso...");
                    creazioneTratta(scanner, trattaDAO);
                    break;

                case 2:
                    System.out.println("------------------------------------------------------");
                    System.out.println("scelta della tratta in corso...");
                    selezioneTrattaEModificaTE(scanner, trattaDAO, percorsoEffettuatoDAO);
                    break;
                case 3:
                    System.out.println("------------------------------------------------------");
                    System.out.println("Menu MONITORAGGIO BIGLIETTI ED ABBONAMENTI in apertura...");
                    monitoraggioNBigliettiAbbonamenti(scanner,puntodiEmissioneDAO);
                    break;
                case 0:
                    System.out.println("Chiusura in corso...");
                    return;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }
    public static void selezioneTrattaEModificaTE(Scanner scanner, TrattaDAO trattaDAO, PercorsoEffettuatoDAO percorsoEffettuatoDAO) {
        String partenzaInput = null;
        String arrivoInput = null;

        while (true) {
            System.out.println("Seleziona la tratta da percorrere : ");
            try {
                long trattaId = scanner.nextLong();
                System.out.println("inserisci l'ID del percorso effettuato: ");
                long percorsoEffettuatoId = scanner.nextLong();
                scanner.nextLine(); // Consuma la nuova linea dopo il numero

                if (trattaId < 1) break; // Esce dal loop se l'ID della tratta è minore di 1

                // Trova la tratta per ID
                Tratta tratta = trattaDAO.findByID(trattaId);
                PercorsoEffettuato percorsoEffettuato = percorsoEffettuatoDAO.findByID(percorsoEffettuatoId);

                System.out.println("La tratta selezionata è: " + tratta);

                // Formatter per il parsing della data
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                LocalDateTime partenzaEffettiva = null;
                LocalDateTime arrivoEffettivo = null;

                // Chiede l'orario di partenza effettivo
                while (partenzaEffettiva == null) {
                    try {
                        System.out.println("Inserisci orario di partenza effettivo (formato: yyyy-MM-dd HH:mm): ");
                        partenzaInput = scanner.nextLine();
                        partenzaEffettiva = LocalDateTime.parse(partenzaInput, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Errore: formato del tempo previsto non valido. Usa il formato yyyy-MM-dd HH:mm.");
                    }
                }

                // Chiede l'orario di arrivo effettivo
                while (arrivoEffettivo == null) {
                    try {
                        System.out.println("Inserisci orario di arrivo effettivo (formato: yyyy-MM-dd HH:mm): ");
                        arrivoInput = scanner.nextLine();
                        arrivoEffettivo = LocalDateTime.parse(arrivoInput, formatter);
                    } catch (DateTimeParseException e) {
                        System.out.println("Errore: formato del tempo previsto non valido. Usa il formato yyyy-MM-dd HH:mm.");
                    }
                }

                // Calcola la differenza tra orario di partenza e arrivo
                Duration durataEffettiva = Duration.between(partenzaEffettiva, arrivoEffettivo);

                // Mostra la differenza in ore e minuti
                System.out.println("Differenza: " + durataEffettiva.toHours() + " ore, " +
                        durataEffettiva.toMinutesPart() + " minuti");

                // Qui puoi salvare la differenza nel database o effettuare altre operazioni con la tratta
                percorsoEffettuato.setTempoEffettivo(durataEffettiva.toMinutes()); // Ad esempio in minuti
                percorsoEffettuatoDAO.save(percorsoEffettuato);

            } catch (InputMismatchException e) {
                System.out.println("Errore: inserisci un numero valido!");
                scanner.nextLine(); // Consuma la nuova linea errata
            } catch (NullPointerException e) {
                System.out.println("Errore: tratta non trovata!");
            }
        }
    }
    public void getStatusMezzo(Scanner scanner, Mezzo mezzo, MezzoDAO mezzoDAO){

        System.out.println("Inserisci l'id del mezzo di cui vuoi informazioni");
        while(true) {
            try {
                long mezzoId = scanner.nextLong();
                scanner.nextLine();
                // dovrebbe essere una lista con tutte le info della Tabella SatusMezzo
                List<StatoMezzo> statusList = new ArrayList<>(mezzoDAO.statiManutenzioneMezzo(mezzoId));
                if(statusList.isEmpty()){
                    System.out.println("Il mezzo inserito non presenta alcun periodo di manutenzione");
                }else {
                    for (StatoMezzo statoMezzo : statusList) {

                        if (statoMezzo.getDataFine() == null) {
                            mezzo.setStatoManutenzione(true);
                            System.out.println("Il Mezzo con targa: " + mezzo.getTarga() + " risulta il manutenzione dal " + statoMezzo.getDataInizio());
                        } else {
                            mezzo.setStatoManutenzione(false);
                            System.out.println("Il mezzo con targa: " + mezzo.getTarga() + "  risulta in circolazione dal " + statoMezzo.getDataFine());
                        }
                    }
                }
            } catch (InputMismatchException ex){
                System.out.println("Inserisci un valore valido");
            }
        }

        // si potrebbe creare un altro attributo in Stato Mezzo con l'enum Manutenzione

    }
}















