package ageria;

import ageria.DAO.*;
import ageria.entities.*;
import ageria.enums.AbbonamentoType;
import ageria.enums.RivenditoreType;
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
                    inputCreazione(scanner, utenteDAO, tesseraDAO);
                case 2:
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

    public static void inputCreazione(Scanner scanner, UtenteDAO utenteDAO, TesseraDAO tesseraDAO) {
        while (true) {
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
                    acquistoBiglietto(scanner, tesseraDAO,);
                    break;
                case 3:
                    System.out.println("-------------------------------------------------");
                    System.out.println("Hai scelto l'acquisto di un abbonamento");
                    acquistoAbbonamento(scanner, tesseraDAO);
                    break;
                case 0:
                    System.out.println("Chiusura in corso...");
                    return;
                default:
                    System.out.println("Opzione non valida. Riprova.");
            }
        }
    }

    public static void acquistoAbbonamento(Scanner scanner, TesseraDAO tesseraDAO) {
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
            Tessera tessera = tesseraDAO.findByID(numeroTessera);
            System.out.println("tessera: " + tessera);
            Abbonamento abbonamento1 = new Abbonamento(tipoAbbonamento, dataInizio, dataScadenza, puntoEmissione, tessera);
            System.out.println("Abbonamento creato con successo!");

            System.out.println(abbonamento1);
        } else {
            System.out.println("Non è stato selezionato un punto di emissione valido.");
        }


    }

    public static void acquistoBiglietto(Scanner scanner, TesseraDAO tesseraDAO, PuntodiEmissioneDAO puntodiEmissioneDAO) {
        System.out.println("Hai scelto di acquistare uno o più biglietti");

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

        System.out.println("Ora mi serve sapere il numero di tessera su cui caricare il numero di biglietti (" + numeroBiglietti + ") richiesti");

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
            Biglietto[] biglietti=new Biglietto[numeroBiglietti];
            for (int i = 0; i < numeroBiglietti; i++) {
                biglietti[i] = new Biglietto(puntoEmissione, tessera);
                System.out.println("Biglietto " + (i + 1) + " creato.");
            }
            System.out.println(tessera);
        } else {
            System.out.println("Non è stato selezionato un punto di emissione valido.");
        }
        System.out.println(tessera);

//     public static void creazioneElementoAdmin(int scelta){
//        while(true){
//            System.out.println("Premi 1 per inserire un nuovo Mezzo");
//            System.out.println("Premi 2 per inserire un nuovo Punto di Emissione");
//            System.out.println("Premi 3 per inserire un nuova Tratta");
//            System.out.println("Premi 4 per creare un nuova Tessera");
//            System.out.println("Premi 5 per creare un nuovo Abbonamento");
//            System.out.println("Premi 6 per creare un nuovo Biglietto");
//            System.out.println("Premi 0 per USCIRE");
//            System.out.print("Scegli un'opzione: ");
//            try{
//                scelta = scanner.nextInt();
//                scanner.nextLine();
//            }catch(InputMismatchException ex){
//                System.out.println("Inserisci un numero valido");
//            }
//
//
//        }
//     }


    }
}

