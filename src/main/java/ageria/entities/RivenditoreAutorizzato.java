package ageria.entities;

import ageria.enums.RivenditoreType;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("rivenditore_autorizzato")
public class RivenditoreAutorizzato extends PuntodiEmissione {
    @Column(name = "tipo_rivenditore", insertable=false, updatable=false)
    @Enumerated(EnumType.STRING)
    private RivenditoreType tipo;

    public RivenditoreAutorizzato(String nome, String indirizzo, RivenditoreType tipo) {
        super(nome, indirizzo);
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
