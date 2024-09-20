package giuseppelongo.u5d15progetto.payloads;

import jakarta.validation.constraints.NotEmpty;

public record NewPrenotazioneDTO(@NotEmpty(message = "L'ID dell'evento è obbligatorio")
                                 String eventoId,

                                 @NotEmpty(message = "L'ID dell'utente è obbligatorio")
                                 String utenteId,

                                 int postiPrenotati) {
}
