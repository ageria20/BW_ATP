package ageria.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tessera")
public class Tessera {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long numeroTessera;

    @Column(name = "data_emissione")
    private LocalDate dataEmissione;

    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;

    @OneToMany(mappedBy = "tessera")
    private List<Biglietto> biglietti;

    @OneToMany(mappedBy = "tessera")
    private List<Abbonamento> abbonamenti;

    @OneToOne
    @JoinColumn(name = "utente_id", unique = true)
    private Utente utente;

    public Tessera() {}

    public Tessera( Utente utente) {
        this.dataEmissione = LocalDate.now();
        this.dataScadenza =calcolaDataScadenzaTessera();
        this.utente = utente;
    }

    public LocalDate calcolaDataScadenzaTessera() {
        return dataEmissione.plusYears(1);
    }

    // Getter & Setter

    public long getNumeroTessera() {
        return numeroTessera;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public List<Biglietto> getBiglietti() {
        return biglietti;
    }

    public void setBiglietti(List<Biglietto> biglietti) {
        this.biglietti = biglietti;
    }

    public List<Abbonamento> getAbbonamenti() {
        return abbonamenti;
    }

    public void setAbbonamenti(List<Abbonamento> abbonamenti) {
        this.abbonamenti = abbonamenti;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "numeroTessera=" + numeroTessera +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                ", biglietti=" + biglietti +
                ", abbonamenti=" + abbonamenti +
                ", utente= ID: " + utente.getId() +" di nome e cognome: "+utente.getNome()+" "+utente.getCognome()+", nato il: "+utente.getDataDiNascita()+
                '}';
    }
}