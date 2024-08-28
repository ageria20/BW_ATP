package ageria.exceptions;

public class NotFoundEx extends RuntimeException {
    public NotFoundEx(long id) {
        super("L'elemento con ID " + id + " non è stato trovato");
    }

    public NotFoundEx(String stringa) {
        super("l'elemento inserito: " + stringa + " non è stato trovato");
    }

    public NotFoundEx() {
        super("Non ci sono elementi presenti in questa lista, riprova con un altro numero ID");
    }

}