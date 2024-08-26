package ageria.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tessera")
public class Tessera {
    @Id
    @GeneratedValue
    private long id;
    @Column(name = "data_emissione")
    private LocalDate data_emissione;
    @Column(name = "data_scadenza")
    private LocalDate data_scadenza;

    private int nBiglietti;
    private int abbonamenti;

    public Tessera(){}

    public Tessera( LocalDate data_emissione) {
        this.data_emissione = data_emissione;
        this.data_scadenza=calcolaDataScadenzaTessera();
    }

    public LocalDate calcolaDataScadenzaTessera(){
        return data_emissione.plusYears(1);
    }
    //Getter & Setters

    public long getId() {
        return id;
    }

    public LocalDate getData_emissione() {
        return data_emissione;
    }

    public void setData_emissione(LocalDate data_emissione) {
        this.data_emissione = data_emissione;
    }

    public LocalDate getData_scadenza() {
        return data_scadenza;
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

    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", data_emissione=" + data_emissione +
                ", data_scadenza=" + data_scadenza +
                ", nBiglietti=" + nBiglietti +
                ", abbonamenti=" + abbonamenti +
                '}';
    }
}
