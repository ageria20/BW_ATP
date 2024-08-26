package ageria.entities;


import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("percorso_effettuato")
public class PercorsoEffettuato {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private Mezzo mezzo;

    @Column
    private Tratta tratta;

    @Column(name = "data_inizio")
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;

    public PercorsoEffettuato() {
    }

    public PercorsoEffettuato( Mezzo mezzo, Tratta tratta, LocalDate dataInizio, LocalDate dataFine) {
        this.mezzo = mezzo;
        this.tratta = tratta;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Tratta getTratta() {
        return tratta;
    }

    public void setTratta(Tratta tratta) {
        this.tratta = tratta;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    @Override
    public String toString() {
        return "PercorsoEffettuato{" +
                "id=" + id +
                ", mezzo=" + mezzo +
                ", tratta=" + tratta +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                '}';
    }
}
