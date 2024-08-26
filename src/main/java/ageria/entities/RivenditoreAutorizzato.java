package ageria.entities;

import ageria.enums.RivenditoreType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
@Entity
@Table(name="rivenditore_autorizzato")
public class RivenditoreAutorizzato extends PuntodiEmissione {
    @Column(name = "tipo_rivenditore")
    private RivenditoreType tipo;

    public RivenditoreAutorizzato(String nome, String indirizzo, Biglietto bigliettiEmessi, Abbonamento abbonamentiEmessi, RivenditoreType tipo) {
        super(nome, indirizzo, bigliettiEmessi, abbonamentiEmessi);
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
