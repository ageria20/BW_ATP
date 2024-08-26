package ageria.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    private int nBiglietti;
    private int abbonamenti;

    @OneToOne(mappedBy = "tessera")
    private Utente utente;

    public Tessera() {}

    public Tessera(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
        this.dataScadenza = calcolaDataScadenzaTessera();
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

    public int getnBiglietti() {
        return nBiglietti;
    }

    public void setnBiglietti(int nBiglietti) {
        this.nBiglietti = nBiglietti;
    }

    public int getAbbonamenti() {
        return abbonamenti;
    }

    public void setAbbonamenti(int abbonamenti) {
        this.abbonamenti = abbonamenti;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "numeroTessera=" + numeroTessera +
                ", dataEmissione=" + dataEmissione +
                ", dataScadenza=" + dataScadenza +
                ", nBiglietti=" + nBiglietti +
                ", abbonamenti=" + abbonamenti +
                '}';
    }
}