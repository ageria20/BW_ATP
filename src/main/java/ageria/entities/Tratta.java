package ageria.entities;


import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@DiscriminatorValue("tratta")
public class Tratta {

    @Id
    @GeneratedValue
    private long id;

    @Column (name = "zona_di_partenza")
    private String zonaDiPartenza;

    @Column
    private String capolinea;

    @Column (name = "tempo_previsto")
    private Timestamp tempoPrevisto;

    @OneToMany(mappedBy = "tratta_assegnata")
    private List<Mezzo> mezziAssegnati;
}
