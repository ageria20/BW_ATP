package ageria.entities;

import jakarta.persistence.*;

@Entity

public abstract class PuntodiEmissione {
    @Id
    @GeneratedValue
    protected long id;
    @Column(name="nome")
    protected String nome;
    @Column(name="cognome")
    protected String indirizzo;
   @OneToMany(mappedBy = "puntoEmissione")
    protected Biglietto bigliettiEmessi;
   @OneToMany(mappedBy = "puntoEmissione")
    protected Abbonamento abbonamentiEmessi;

    public PuntodiEmissione(String nome, String indirizzo, Biglietto bigliettiEmessi, Abbonamento abbonamentiEmessi) {

        this.nome = nome;
        this.indirizzo = indirizzo;
        this.bigliettiEmessi = bigliettiEmessi;
        this.abbonamentiEmessi = abbonamentiEmessi;
    }
    public PuntodiEmissione(){}

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public Biglietto getBigliettiEmessi() {
        return bigliettiEmessi;
    }

    public void setBigliettiEmessi(Biglietto bigliettiEmessi) {
        this.bigliettiEmessi = bigliettiEmessi;
    }

    public Abbonamento getAbbonamentiEmessi() {
        return abbonamentiEmessi;
    }

    public void setAbbonamentiEmessi(Abbonamento abbonamentiEmessi) {
        this.abbonamentiEmessi = abbonamentiEmessi;
    }

    @Override
    public String toString() {
        return "PuntodiEmissione{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                ", bigliettiEmessi=" + bigliettiEmessi +
                ", abbonamentiEmessi=" + abbonamentiEmessi +
                '}';
    }
}
