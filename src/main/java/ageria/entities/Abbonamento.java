package ageria.entities;

import ageria.enums.AbbonamentoType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "abbonamento")
public class Abbonamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "tipo_di_abbonamento")
    @Enumerated(EnumType.STRING)
    private AbbonamentoType tipoDiAbbonamento;
    @Column(name = "data_inizio")
    private LocalDate dataInizio;
    @Column(name = "data_scadenza")
    private LocalDate dataScadenza;
    @ManyToOne
    @JoinColumn(name = "punto_emissione")
    private PuntodiEmissione puntoEmissione;
    @ManyToOne
    @JoinColumn(name = "tipo_tessera")
    private Tessera tessera;

    public Abbonamento() {
    }

    public Abbonamento(AbbonamentoType tipoDiAbbonamento, LocalDate dataInizio, LocalDate dataScadenza, PuntodiEmissione puntoEmissione, Tessera tessera) {
        this.tipoDiAbbonamento = tipoDiAbbonamento;
        this.dataInizio = dataInizio;
        this.dataScadenza = dataScadenza;
        this.puntoEmissione = puntoEmissione;
        this.tessera = tessera;
    }

    public long getId() {
        return id;
    }

    public AbbonamentoType getTipoDiAbbonamento() {
        return tipoDiAbbonamento;
    }

    public void setTipoDiAbbonamento(AbbonamentoType tipoDiAbbonamento) {
        this.tipoDiAbbonamento = tipoDiAbbonamento;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataScadenza() {
        return dataScadenza;
    }

    public void setDataScadenza(LocalDate dataScadenza) {
        this.dataScadenza = dataScadenza;
    }

    public PuntodiEmissione getPuntoEmissione() {
        return puntoEmissione;
    }

    public void setPuntoEmissione(PuntodiEmissione puntoEmissione) {
        this.puntoEmissione = puntoEmissione;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    @Override
    public String toString() {
        return "Abbonamento{" +
                "id=" + id +
                ", tipoDiAbbonamento=" + tipoDiAbbonamento +
                ", dataInizio=" + dataInizio +
                ", dataScadenza=" + dataScadenza +
                ", puntoEmissione=" + puntoEmissione +
                ", tessera=" + tessera +
                '}';
    }
}
