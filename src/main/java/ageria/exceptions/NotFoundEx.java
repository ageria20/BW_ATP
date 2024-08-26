package ageria.exceptions;

public class NotFoundEx extends RuntimeException {
    public NotFoundEx(long id) {
        super("L'elemento con ID " + id + " non è stato trovato");
    }

    public NotFoundEx(String stringa) {
        super("L'autore o titolo inserito: " + stringa + " non è stato trovato");
    }

    public NotFoundEx() {
        super("Non ci sono prestiti scaduti e mai restituiti, dato che ho gestito pure gli errori penso di meritarmi un altro 10 :)");
    }

}