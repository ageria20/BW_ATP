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
    private PuntodiEmissione puntoDiEmissione;
    @ManyToOne
    @JoinColumn(name = "tipo_tessera")
    private Tessera tessera;

    public Biglietto() {
    }

    public Biglietto(LocalDate dataEmissione, PuntodiEmissione puntoDiEmissione, Tessera tessera) {
        this.dataEmissione = dataEmissione;
        this.puntoDiEmissione = puntoDiEmissione;
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
        return puntoDiEmissione;
    }

    public void setPuntoDiEmissione(PuntodiEmissione puntoDiEmissione) {
        this.puntoDiEmissione = puntoDiEmissione;
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
                ", puntoDiEmissione=" + puntoDiEmissione +
                ", tessera=" + tessera +
                '}';
    }
}
