package ageria.entities;

import ageria.enums.StateTypeMezzo;

public class DistributoreAutomatico extends PuntodiEmissione{
    private StateTypeMezzo stato;

    public DistributoreAutomatico(long id, String nome, String indirizzo, Biglietto bigliettiEmessi, Abbonamento abbonamentiEmessi, StateTypeMezzo stato) {
        super(id, nome, indirizzo, bigliettiEmessi, abbonamentiEmessi);
        this.stato = stato;
    }

    public DistributoreAutomatico() {

    }

    public StateTypeMezzo getStato() {
        return stato;
    }

    public void setStato(StateTypeMezzo stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "DistributoreAutomatico{"+super.toString() +
                "stato=" + stato +
                "} " ;
    }
}
