package ageria.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "biglietto")
public class Biglietto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "data_emissione")
    private LocalDate dataEmissione;
    @ManyToOne
    @JoinColumn(name = "punto_emissione")
    private PuntodiEmissione puntoEmissione;
    @ManyToOne
    @JoinColumn(name = "tipo_tessera")
    private Tessera tessera;
    @ManyToOne
    @JoinColumn(name = "biglietti_vidimati")
    private Mezzo mezzo;


    public Biglietto() {
    }

    public Biglietto(LocalDate dataEmissione, PuntodiEmissione puntoDiEmissione, Tessera tessera) {
        this.dataEmissione = dataEmissione;
        this.puntoEmissione = puntoDiEmissione;
        this.tessera = tessera;
    }

    public long getId() {
        return id;
    }

    public LocalDate getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(LocalDate dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public PuntodiEmissione getPuntoDiEmissione() {
        return puntoEmissione;
    }

    public void setPuntoDiEmissione(PuntodiEmissione puntoDiEmissione) {
        this.puntoEmissione = puntoDiEmissione;
    }

    public Tessera getTessera() {
        return tessera;
    }

    public void setTessera(Tessera tessera) {
        this.tessera = tessera;
    }

    @Override
    public String toString() {
        return "Biglietto{" +
                "id=" + id +
                ", dataEmissione=" + dataEmissione +
                ", puntoDiEmissione=" + puntoEmissione +
                ", tessera=" + tessera +
                '}';
    }
}
