package giuseppelongo.u5d15progetto.payloads;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record NewUtenteDTO(
        @NotEmpty(message = "L'username è obbligatorio")
        @Size(min = 3, max = 40, message = "Il nome proprio deve essere compreso tra 3 e 40 caratteri")
        String username,

        @NotEmpty(message = "La password è obbligatoria")
        @Size(min = 4, message = "La password deve avere almeno 4 caratteri")
        String password

) {
}
