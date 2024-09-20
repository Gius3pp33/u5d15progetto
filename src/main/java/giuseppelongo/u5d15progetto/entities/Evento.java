package giuseppelongo.u5d15progetto.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "eventi")
public class Evento {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotBlank(message = "Il titolo è richiesto!!")
    private String titolo;

    @NotBlank(message = "La descrizione è richiesta!!")
    private String descrizione;

    @FutureOrPresent(message = "La data è richiesta!!")
    private LocalDateTime data;

    @NotBlank(message = "La posizione è richiesta!!")
    private String posizione;

    @Min(value = 1, message = "I posti disponibili devono essere maggiori di 0!!")
    private int postiDisponibili;

    @ManyToOne
    private Utente organizzatore;
}
