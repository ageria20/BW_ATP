package ageria.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_rivenditore")
public abstract class PuntodiEmissione {
    @Id
    @GeneratedValue
    protected long id;
    @Column(name="nome")
    protected String nome;
    @Column(name="indirizzo")
    protected String indirizzo;
    @OneToMany(mappedBy = "puntoEmissione")
    protected List<Biglietto> bigliettiEmessi;
    @OneToMany(mappedBy = "puntoEmissione")
    protected List<Abbonamento> abbonamentiEmessi;
    public PuntodiEmissione(String nome, String indirizzo) {

        this.nome = nome;
        this.indirizzo = indirizzo;
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

    public List<Abbonamento> getAbbonamentiEmessi() {
        return abbonamentiEmessi;
    }

    public void setAbbonamentiEmessi(List<Abbonamento> abbonamentiEmessi) {
        this.abbonamentiEmessi = abbonamentiEmessi;
    }

    public List<Biglietto> getBigliettiEmessi() {
        return bigliettiEmessi;
    }

    public void setBigliettiEmessi(List<Biglietto> bigliettiEmessi) {
        this.bigliettiEmessi = bigliettiEmessi;
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
