package ageria.entities;

import ageria.enums.TipoMezzo;
import com.sun.source.doctree.LinkTree;
import jakarta.persistence.*;

import javax.xml.namespace.QName;
import java.util.List;

@Entity
@Table(name = "mezzo")
public class Mezzo {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private String targa;

    @Column(name = "tipo_mezzo")
    @Enumerated(EnumType.STRING)
    private TipoMezzo tipoMezzo;


    @Column
    private int capienza;

    @Column (name = "stato_manutenzione")
    private boolean statoManutenzione;

    @Column (name = "biglietti_validati")
    private int bigliettiValidati;

    @OneToMany(mappedBy = "mezzo")
    private List<PercorsoEffettuato> percorsiEffettuati;

    @OneToMany(mappedBy = "mezzo")
    private List<StatoMezzo> statoMezzo;

    @ManyToOne
    @JoinColumn(name="tratta_assegnata")
    private Tratta trattaAssegnata;

    @OneToMany(mappedBy = "mezzo")
    private List<Biglietto> bigliettiVidimati;

    public Mezzo() {
    }



    public Mezzo(TipoMezzo tipoMezzo, int capienza, boolean statoManutenzione, int bigliettiValidati, List<PercorsoEffettuato> percorsiEffettuati, List<StatoMezzo> staotMezzo, Tratta trattaAssegnata) {
        this.tipoMezzo = tipoMezzo;
        this.capienza = capienza;
        this.statoManutenzione = statoManutenzione;
        this.bigliettiValidati = bigliettiValidati;
        this.percorsiEffettuati = percorsiEffettuati;
        this.statoMezzo = staotMezzo;
        this.trattaAssegnata = trattaAssegnata;
    }

    public Mezzo(TipoMezzo tipoMezzo, int capienza, boolean statoManutenzione, int bigliettiValidati, List<PercorsoEffettuato> percorsiEffettuati, List<StatoMezzo> statoMezzo, Tratta trattaAssegnata, List<Biglietto> bigliettiVidimati) {
        this.tipoMezzo = tipoMezzo;
        this.capienza = capienza;
        this.statoManutenzione = statoManutenzione;
        this.bigliettiValidati = bigliettiValidati;
        this.percorsiEffettuati = percorsiEffettuati;
        this.statoMezzo = statoMezzo;
        this.trattaAssegnata = trattaAssegnata;
        this.bigliettiVidimati = bigliettiVidimati;
    }

    public Mezzo(TipoMezzo tipoMezzo, String targa, int capienza) {
        this.tipoMezzo = tipoMezzo;
        this.targa = targa;
        this.capienza = capienza;
    }

    public List<PercorsoEffettuato> getPercorsiEffettuati() {
        return percorsiEffettuati;
    }

    public void setPercorsiEffettuati(List<PercorsoEffettuato> percorsiEffettuati) {
        this.percorsiEffettuati = percorsiEffettuati;
    }

    public long getId() {
        return id;
    }

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        this.targa = targa;
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





    public List<StatoMezzo> getStatoMezzo() {
        return statoMezzo;
    }

    public void setStatoMezzo(List<StatoMezzo> statoMezzo) {
        this.statoMezzo = statoMezzo;
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
                ", statoMezzo=" + statoMezzo +
                ", trattaAssegnata=" + trattaAssegnata +
                '}';
    }
}
