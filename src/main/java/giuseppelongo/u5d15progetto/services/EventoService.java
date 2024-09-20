package giuseppelongo.u5d15progetto.services;

import giuseppelongo.u5d15progetto.entities.Evento;
import giuseppelongo.u5d15progetto.entities.Utente;
import giuseppelongo.u5d15progetto.exceptions.NotFoundException;
import giuseppelongo.u5d15progetto.payloads.NewEventoDTO;
import giuseppelongo.u5d15progetto.payloads.NewEventoRespDTO;
import giuseppelongo.u5d15progetto.repositories.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private UtenteService utenteService;

    // Metodo per creare un evento
    public NewEventoRespDTO creaEvento(NewEventoDTO dto) {
        Utente organizzatore = utenteService.findById(UUID.fromString(dto.utenteId()))
                .orElseThrow(() -> new IllegalArgumentException("Utente non trovato"));

        Evento evento = new Evento();
        evento.setTitolo(dto.titolo());
        evento.setDescrizione(dto.descrizione());
        evento.setData(dto.data());
        evento.setPosizione(dto.luogo());
        evento.setPostiDisponibili(dto.postiDisponibili());
        evento.setOrganizzatore(organizzatore);

        Evento eventoCreato = eventoRepository.save(evento);

        return new NewEventoRespDTO(
                eventoCreato.getId().toString(),
                eventoCreato.getTitolo(),
                eventoCreato.getDescrizione(),
                eventoCreato.getPosizione(),
                eventoCreato.getPostiDisponibili(),
                organizzatore.getId().toString()
        );
    }


    public NewEventoRespDTO trovaEventoPerId(UUID id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento con Id " + id + " non trovato"));

        return new NewEventoRespDTO(
                evento.getId().toString(),
                evento.getTitolo(),
                evento.getDescrizione(),
                evento.getPosizione(),
                evento.getPostiDisponibili(),
                evento.getOrganizzatore().getId().toString()
        );
    }


    public List<NewEventoRespDTO> trovaTuttiGliEventi() {
        return eventoRepository.findAll().stream()
                .map(evento -> new NewEventoRespDTO(
                        evento.getId().toString(),
                        evento.getTitolo(),
                        evento.getDescrizione(),
                        evento.getPosizione(),
                        evento.getPostiDisponibili(),
                        evento.getOrganizzatore().getId().toString()))
                .collect(Collectors.toList());
    }


    public void eliminaEventoPerId(UUID id) {
        Evento evento = eventoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evento con Id " + id + " non trovato"));
        eventoRepository.delete(evento);
    }
}
