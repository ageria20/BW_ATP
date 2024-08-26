package ageria.entities;

import ageria.enums.StateTypeMezzo;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("distributore_automatico")
public class DistributoreAutomatico extends PuntodiEmissione{
@Column(name = "tipo_mezzo")
@Enumerated(EnumType.STRING)
private StateTypeMezzo tipo;

public DistributoreAutomatico( String nome, String indirizzo, Biglietto bigliettiEmessi, Abbonamento abbonamentiEmessi, StateTypeMezzo tipo) {
        super(nome, indirizzo, bigliettiEmessi, abbonamentiEmessi);
        this.tipo = tipo;
    }
    public DistributoreAutomatico() {

    }

    public StateTypeMezzo getStato() {
        return tipo;
    }

    public void setStato(StateTypeMezzo tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "DistributoreAutomatico{"+super.toString() +
                "stato=" + tipo +
                "} " ;
    }
}
