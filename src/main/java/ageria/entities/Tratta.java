package ageria.entities;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "tratta")
public class Tratta {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "zona_di_partenza")
    private String zonaDiPartenza;

    @Column
    private String capolinea;

    @Column(name = "tempo_previsto")
    private Timestamp tempoPrevisto;

    @OneToMany(mappedBy = "trattaAssegnata")
    private List<Mezzo> mezziAssegnati;


    public Tratta() {
    }


    public Tratta(String zonaDiPartenza, String capolinea, Timestamp tempoPrevisto) {
        this.zonaDiPartenza = zonaDiPartenza;
        this.capolinea = capolinea;
        this.tempoPrevisto = tempoPrevisto;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getZonaDiPartenza() {
        return zonaDiPartenza;
    }

    public void setZonaDiPartenza(String zonaDiPartenza) {
        this.zonaDiPartenza = zonaDiPartenza;
    }

    public String getCapolinea() {
        return capolinea;
    }

    public void setCapolinea(String capolinea) {
        this.capolinea = capolinea;
    }

    public Timestamp getTempoPrevisto() {
        return tempoPrevisto;
    }

    public void setTempoPrevisto(Timestamp tempoPrevisto) {
        this.tempoPrevisto = tempoPrevisto;
    }

    public List<Mezzo> getMezziAssegnati() {
        return mezziAssegnati;
    }

    public void setMezziAssegnati(List<Mezzo> mezziAssegnati) {
        this.mezziAssegnati = mezziAssegnati;
    }


    @Override
    public String toString() {
        return "Tratta{" +
                "id=" + id +
                ", zonaDiPartenza='" + zonaDiPartenza + '\'' +
                ", capolinea='" + capolinea + '\'' +
                ", tempoPrevisto=" + tempoPrevisto +
                ", mezziAssegnati=" + mezziAssegnati +
                '}';
    }
}
