package ageria.entities;

import ageria.enums.StateTypeMezzo;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("distributore_automatico")
public class DistributoreAutomatico extends PuntodiEmissione{
@Column(name = "stato")
private boolean inFunzione;

    public DistributoreAutomatico(String nome, String indirizzo, boolean inFunzione) {
        super(nome, indirizzo);
        this.inFunzione = inFunzione;
    }

    public DistributoreAutomatico() {

    }

    @Override
    public String toString() {
        return "DistributoreAutomatico{"
                + super.toString() +
                "inFunzione=" + inFunzione +
                "} " ;
    }
}
