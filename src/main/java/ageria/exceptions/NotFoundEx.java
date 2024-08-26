package ageria.exceptions;

public class NotFoundEx extends RuntimeException {
    public NotFoundEx(long id) {
        super("L'elemento con ID " + id + " non è stato trovato");
    }

    public NotFoundEx(String stringa) {
        super("l'elemento inserito: " + stringa + " non è stato trovato");
    }

    public NotFoundEx() {
        super("hey!");
    }

}