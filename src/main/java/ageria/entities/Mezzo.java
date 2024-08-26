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

    public Mezzo() {
    }

    public Mezzo(TipoMezzo tipoMezzo, int capienza, boolean statoManutenzione, int bigliettiValidati, int percorsiEffettuati, List<StatoMezzo> staotMezzo, Tratta trattaAssegnata) {
        this.tipoMezzo = tipoMezzo;
        this.capienza = capienza;
        this.statoManutenzione = statoManutenzione;
        this.bigliettiValidati = bigliettiValidati;
        this.percorsiEffettuati = percorsiEffettuati;
        this.staotMezzo = staotMezzo;
        this.trattaAssegnata = trattaAssegnata;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TipoMezzo getTipoMezzo() {
        return tipoMezzo;
    }

    public void setTipoMezzo(TipoMezzo tipoMezzo) {
        this.tipoMezzo = tipoMezzo;
    }

    public int getCapienza() {
        return capienza;
    }

    public void setCapienza(int capienza) {
        this.capienza = capienza;
    }

    public boolean isStatoManutenzione() {
        return statoManutenzione;
    }

    public void setStatoManutenzione(boolean statoManutenzione) {
        this.statoManutenzione = statoManutenzione;
    }

    public int getBigliettiValidati() {
        return bigliettiValidati;
    }

    public void setBigliettiValidati(int bigliettiValidati) {
        this.bigliettiValidati = bigliettiValidati;
    }

    public int getPercorsiEffettuati() {
        return percorsiEffettuati;
    }

    public void setPercorsiEffettuati(int percorsiEffettuati) {
        this.percorsiEffettuati = percorsiEffettuati;
    }

    public List<StatoMezzo> getStaotMezzo() {
        return staotMezzo;
    }

    public void setStaotMezzo(List<StatoMezzo> staotMezzo) {
        this.staotMezzo = staotMezzo;
    }

    public Tratta getTrattaAssegnata() {
        return trattaAssegnata;
    }

    public void setTrattaAssegnata(Tratta trattaAssegnata) {
        this.trattaAssegnata = trattaAssegnata;
    }

    @Override
    public String toString() {
        return "Mezzo{" +
                "id=" + id +
                ", tipoMezzo=" + tipoMezzo +
                ", capienza=" + capienza +
                ", statoManutenzione=" + statoManutenzione +
                ", bigliettiValidati=" + bigliettiValidati +
                ", percorsiEffettuati=" + percorsiEffettuati +
                ", staotMezzo=" + staotMezzo +
                ", trattaAssegnata=" + trattaAssegnata +
                '}';
    }
}
