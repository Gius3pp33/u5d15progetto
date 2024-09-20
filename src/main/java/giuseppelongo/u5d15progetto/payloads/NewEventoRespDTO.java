package giuseppelongo.u5d15progetto.payloads;

public record NewEventoRespDTO(String idEvento,
                               String titolo,
                               String descrizione,
                               String luogo,
                               int postiDisponibili,
                               String utenteId) {
}
