package ageria.entities;

import ageria.enums.Manutenzione;
import ageria.enums.TipoMezzo;
import jakarta.persistence.*;

import javax.xml.namespace.QName;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("stato_mezzo")
public class StatoMezzo {

    @ManyToOne
    @JoinColumn(name = "mezzo")
    private TipoMezzo mezzo;

    @Column
    private Manutenzione stato;

    @Column(name = "data_inizio")
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;
}
