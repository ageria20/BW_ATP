package ageria.entities;


import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "percorso_effettuato")
public class PercorsoEffettuato {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "percorsi_effettuati", nullable = false)
    private Mezzo mezzo;

    @OneToOne
    @JoinColumn(name = "tratta")
    private Tratta tratta;

    @Column(name = "data_inizio")
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;

    @Column(name = "tempo_effettivo")
    private Long tempoEffettivo;

    public PercorsoEffettuato() {
    }

    public PercorsoEffettuato(Mezzo mezzo, Tratta tratta, LocalDate dataInizio, LocalDate dataFine, Long tempoEffettivo) {
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
