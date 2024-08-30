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
    @Column(name="biglietto_vidimato")
    private boolean bigliettoVidimato;
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

    public Biglietto( PuntodiEmissione puntoEmissione, Tessera tessera) {

        this.puntoEmissione = puntoEmissione;
        this.tessera = tessera;
        this.bigliettoVidimato = false;
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

    public boolean isBigliettoVidimato() {
        return bigliettoVidimato;
    }

    public void setBigliettoVidimato(boolean bigliettoVidimato) {
        this.bigliettoVidimato = bigliettoVidimato;
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
    public void vidimazione(){
        if(!this.bigliettoVidimato){
            this.bigliettoVidimato=true;
          mezzo.setBigliettiValidati(mezzo.getBigliettiValidati()+1);
            System.out.println("Biglietto vidimato su "+mezzo.getTipoMezzo()+"con ID "+mezzo.getId());
        }else {
            System.out.println("Biglietto già vidimato");
        }
    }
}
