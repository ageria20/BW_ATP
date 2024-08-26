package ageria.entities;

import ageria.enums.TipoMezzo;
import com.sun.source.doctree.LinkTree;
import jakarta.persistence.*;

import javax.xml.namespace.QName;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table
public class Mezzo {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "tipo_mezzo")
    private TipoMezzo tipoMezzo;

    @Column
    private int capienza;

    @Column (name = "stato_manutenzione")
    private boolean statoManutenzione;

    @Column (name = "biglietti_validati")
    private int bigliettiValidati;

    @Column (name = "percorsi_effettuati")
    private int percorsiEffettuati;

    @OneToMany(mappedBy = "mezzo")
    private List<StatoMezzo> staotMezzo;

    @ManyToOne
    @JoinColumn(name="tratta_assegnata")
    private Tratta trattaAssegnata;
}
