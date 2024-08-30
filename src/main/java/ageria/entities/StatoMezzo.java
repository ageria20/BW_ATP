package ageria.entities;

import ageria.enums.Manutenzione;
import ageria.enums.TipoMezzo;
import jakarta.persistence.*;

import javax.xml.namespace.QName;
import java.time.LocalDate;

@Entity
@Table(name = "stato_mezzo")
public class StatoMezzo {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "mezzo")
    private Mezzo mezzo;

    @Column
    private Manutenzione stato;

    @Column(name = "data_inizio")
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;

    public StatoMezzo() {
    }

    public StatoMezzo(Mezzo mezzo, Manutenzione stato, LocalDate dataInizio, LocalDate dataFine) {
        this.mezzo = mezzo;
        this.stato = stato;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
    }

    public Mezzo getMezzo() {
        return mezzo;
    }

    public long getId() {
        return id;
    }



    public void setMezzo(Mezzo mezzo) {
        this.mezzo = mezzo;
    }

    public Manutenzione getStato() {
        return stato;
    }

    public void setStato(Manutenzione stato) {
        this.stato = stato;
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
        return "StatoMezzo{" +
                "mezzo=" + mezzo.getId() +
                ", stato=" + stato +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                '}';
    }
}
