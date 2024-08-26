package ageria.entities;

import ageria.enums.RivenditoreType;

public class RivenditoreAutorizzato extends PuntodiEmissione {
    private RivenditoreType tipo;

    public RivenditoreAutorizzato(long id, String nome, String indirizzo, Biglietto bigliettiEmessi, Abbonamento abbonamentiEmessi, RivenditoreType tipo) {
        super(id, nome, indirizzo, bigliettiEmessi, abbonamentiEmessi);
        this.tipo = tipo;
    }

    public RivenditoreAutorizzato() {

    }

    public RivenditoreType getTipo() {
        return tipo;
    }

    public void setTipo(RivenditoreType tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "RivenditoreAutorizzato{" + super.toString() +
                "tipo=" + tipo +
                "} " ;
    }
}
