package ageria.entities;


import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "percorso_effettuato")
public class PercorsoEffettuato {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "percorsi_effettuati", nullable = false)
    private Mezzo mezzo;

    @ManyToOne
    @JoinColumn(name = "tratta")
    private Tratta tratta;

    @Column(name = "data_inizio")
    private LocalDateTime dataInizio;

    @Column(name = "data_fine")
    private LocalDateTime dataFine;

    @Column(name = "tempo_effettivo")
    private Long tempoEffettivo;

    public PercorsoEffettuato() {
    }

    public PercorsoEffettuato(Mezzo mezzo, Tratta tratta, LocalDateTime dataInizio, LocalDateTime dataFine, Long tempoEffettivo) {

        this.mezzo = mezzo;
        this.tratta = tratta;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.tempoEffettivo = tempoEffettivo;
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

    public LocalDateTime getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDateTime dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDateTime getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDateTime dataFine) {
        this.dataFine = dataFine;
    }

    public Long getTempoEffettivo() {
        return tempoEffettivo;
    }

    public void setTempoEffettivo(Long tempoEffettivo) {
        this.tempoEffettivo = tempoEffettivo;
    }

    @Override
    public String toString() {
        return "PercorsoEffettuato{" +
                "id=" + id +
                ", mezzo=" + mezzo +
                ", tratta=" + tratta +
                ", dataInizio=" + dataInizio +
                ", dataFine=" + dataFine +
                ", tempoEffettivo=" + tempoEffettivo +
                '}';
    }
}



