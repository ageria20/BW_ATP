package ageria.entities;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Tessera {
    private long id;
    private LocalDate data_emissione;
    private LocalDate data_scandenza;
    private int nBiglietti;
    private int abbonamenti;

    public Tessera(){}

    public Tessera(long id, LocalDate data_emissione, LocalDate data_scandenza, int nBiglietti, int abbonamenti) {
        this.id = id;
        this.data_emissione = data_emissione;
        this.data_scandenza = data_scandenza;
        this.nBiglietti = nBiglietti;
        this.abbonamenti = abbonamenti;
    }

    //Getter & Setters


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDate getData_emissione() {
        return data_emissione;
    }

    public void setData_emissione(LocalDate data_emissione) {
        this.data_emissione = data_emissione;
    }

    public LocalDate getData_scandenza() {
        return data_scandenza;
    }

    public void setData_scandenza(LocalDate data_scandenza) {
        this.data_scandenza = data_scandenza;
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
                ", data_scandenza=" + data_scandenza +
                ", nBiglietti=" + nBiglietti +
                ", abbonamenti=" + abbonamenti +
                '}';
    }
}
