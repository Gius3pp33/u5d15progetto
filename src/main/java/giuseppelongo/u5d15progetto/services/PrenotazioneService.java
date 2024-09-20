package giuseppelongo.u5d15progetto.services;

import giuseppelongo.u5d15progetto.entities.Evento;
import giuseppelongo.u5d15progetto.entities.Prenotazione;
import giuseppelongo.u5d15progetto.entities.Utente;
import giuseppelongo.u5d15progetto.exceptions.NotFoundException;
import giuseppelongo.u5d15progetto.payloads.NewPrenotazioneDTO;
import giuseppelongo.u5d15progetto.payloads.NewPrenotazioneRespDTO;
import giuseppelongo.u5d15progetto.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private UtenteService utenteService;

    public NewPrenotazioneRespDTO creaPrenotazione(NewPrenotazioneDTO dto) {
        Evento evento = eventoService.trovaEventoEntityPerId(UUID.fromString(dto.eventoId()));
        Utente utente = utenteService.findById(UUID.fromString(dto.utenteId()))
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        if (evento.getPostiDisponibili() < dto.postiPrenotati()) {
            throw new IllegalArgumentException("Numero di posti prenotati eccede i posti disponibili");
        }

        Prenotazione prenotazione = new Prenotazione();
        prenotazione.setEvento(evento);
        prenotazione.setUtente(utente);
        prenotazione.setPostiPrenotati(dto.postiPrenotati());

        evento.setPostiDisponibili(evento.getPostiDisponibili() - dto.postiPrenotati());
        eventoService.salvaEvento(evento);

        Prenotazione prenotazioneSalvata = prenotazioneRepository.save(prenotazione);

        return new NewPrenotazioneRespDTO(
                prenotazioneSalvata.getId().toString(),
                evento.getId().toString(),
                utente.getId().toString(),
                prenotazioneSalvata.getPostiPrenotati()
        );
    }

    public NewPrenotazioneRespDTO trovaPrenotazionePerId(UUID id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Prenotazione non trovata"));

        return new NewPrenotazioneRespDTO(
                prenotazione.getId().toString(),
                prenotazione.getEvento().getId().toString(),
                prenotazione.getUtente().getId().toString(),
                prenotazione.getPostiPrenotati()
        );
    }

    public List<NewPrenotazioneRespDTO> trovaTutteLePrenotazioni() {
        return prenotazioneRepository.findAll().stream()
                .map(prenotazione -> new NewPrenotazioneRespDTO(
                        prenotazione.getId().toString(),
                        prenotazione.getEvento().getId().toString(),
                        prenotazione.getUtente().getId().toString(),
                        prenotazione.getPostiPrenotati()))
                .collect(Collectors.toList());
    }


    public void eliminaPrenotazionePerId(UUID id) {
        Prenotazione prenotazione = prenotazioneRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prenotazione non trovata"));
        prenotazioneRepository.delete(prenotazione);
    }
}
