package ageria.entities;

import ageria.enums.StatoVidimazione;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="biglietto_vidimato")
public class BigliettoVidimato {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "biglietto")
    private Biglietto biglietto;

    @ManyToOne
    @JoinColumn(name = "biglietti_vidimati")
    private Mezzo mezzo;

    @Column(name = "data_vidimazione")
    private LocalDate dataVidimazione;

    @Column(name = "stato_vidimazione")
    @Enumerated(EnumType.STRING)
    private StatoVidimazione statoVidimazione;

    public BigliettoVidimato() {
    }

    public BigliettoVidimato(Mezzo mezzo, LocalDate dataVidimazione, StatoVidimazione statoVidimazione) {
        this.mezzo = mezzo;
        this.dataVidimazione = dataVidimazione;
        this.statoVidimazione = statoVidimazione;
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

    public LocalDate getDataVidimazione() {
        return dataVidimazione;
    }

    public void setDataVidimazione(LocalDate dataVidimazione) {
        this.dataVidimazione = dataVidimazione;
    }

    public StatoVidimazione getStatoVidimazione() {
        return statoVidimazione;
    }

    public void setStatoVidimazione(StatoVidimazione statoVidimazione) {
        this.statoVidimazione = statoVidimazione;
    }

    @Override
    public String toString() {
        return "BigliettoVidimato{" +
                "id=" + id +
                ", mezzo=" + mezzo +
                ", dataVidimazione=" + dataVidimazione +
                ", statoVidimazione=" + statoVidimazione +
                '}';
    }
}
