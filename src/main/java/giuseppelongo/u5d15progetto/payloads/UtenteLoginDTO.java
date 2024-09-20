package giuseppelongo.u5d15progetto.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UtenteLoginDTO(@NotEmpty(message = "L'username è obbligatorio")
                             @Size(min = 3, message = "L'username deve contenere almeno 3 caratteri")
                             String username,

                             @NotEmpty(message = "La password è obbligatoria")
                             @Size(min = 4, message = "La password deve avere almeno 4 caratteri")
                             String password
) {
}

