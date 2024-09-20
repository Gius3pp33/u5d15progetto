package giuseppelongo.u5d15progetto.payloads;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record NewEventoDTO(
        @NotEmpty(message = "Il titolo è obbligatorio")
        @Size(max = 100, message = "Il titolo non può superare i 100 caratteri")
        String titolo,

        @NotEmpty(message = "La descrizione è obbligatoria")
        String descrizione,

        @Future(message = "La data deve essere futura")
        LocalDateTime data,

        @NotEmpty(message = "Il luogo è obbligatorio")
        String luogo,

        @Positive(message = "Il numero di posti disponibili deve essere maggiore di uno")
        int postiDisponibili,

        @NotEmpty(message = "L'ID dell'utente è obbligatorio")
        String utenteId
) {
    public NewEventoDTO {
        if (postiDisponibili <= 1) {
            throw new IllegalArgumentException("Il numero di posti disponibili deve essere maggiore di uno.");
        }
    }
}
