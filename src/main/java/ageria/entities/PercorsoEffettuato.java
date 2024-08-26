package ageria.entities;


import jakarta.persistence.*;

import javax.annotation.processing.Generated;
import java.time.LocalDate;

@Entity
@DiscriminatorValue("percorso_effettuato")
public class PercorsoEffettuato {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private Mezzo mezzo;

    @Column
    private Tratta tratta;

    @Column(name = "data_inizio")
    private LocalDate dataInizio;

    @Column(name = "data_fine")
    private LocalDate dataFine;
}
