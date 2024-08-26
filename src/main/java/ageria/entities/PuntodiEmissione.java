package ageria.entities;

public abstract class PuntodiEmissione {
    protected long id;
    protected String nome;
    protected String indirizzo;
    protected Biglietto bigliettiEmessi;
    protected Abbonamento abbonamentiEmessi;

    public PuntodiEmissione(long id, String nome, String indirizzo, Biglietto bigliettiEmessi, Abbonamento abbonamentiEmessi) {
        this.id = id;
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
