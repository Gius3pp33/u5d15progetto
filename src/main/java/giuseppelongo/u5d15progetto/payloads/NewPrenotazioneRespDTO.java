package giuseppelongo.u5d15progetto.payloads;

public record NewPrenotazioneRespDTO(String idPrenotazione,
                                     String eventoId,
                                     String utenteId,
                                     int postiPrenotati) {
}
