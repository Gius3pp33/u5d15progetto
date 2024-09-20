package giuseppelongo.u5d15progetto.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.UUID;

@Getter
@Table(name = "utenti")
@Entity
@Setter
@ToString
@NoArgsConstructor

public class Utente {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;

    @NotBlank(message = "L'username è richiesto!! ")
    private String username;

    @NotBlank(message = "La password è richiesta!!")
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;
}
