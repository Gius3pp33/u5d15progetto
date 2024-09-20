package giuseppelongo.u5d15progetto.controllers;

import giuseppelongo.u5d15progetto.payloads.NewEventoDTO;
import giuseppelongo.u5d15progetto.payloads.NewEventoRespDTO;
import giuseppelongo.u5d15progetto.services.EventoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/eventi")
public class EventoController {

    @Autowired
    private EventoService eventoService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewEventoRespDTO creaEvento(@RequestBody @Valid NewEventoDTO eventoDTO) {
        return eventoService.creaEvento(eventoDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<NewEventoRespDTO> trovaEventoPerId(@PathVariable UUID id) {
        NewEventoRespDTO evento = eventoService.trovaEventoPerId(id);
        return ResponseEntity.ok(evento);
    }


    @GetMapping
    public ResponseEntity<List<NewEventoRespDTO>> trovaTuttiGliEventi() {
        List<NewEventoRespDTO> eventi = eventoService.trovaTuttiGliEventi();
        return ResponseEntity.ok(eventi);
    }

   
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaEvento(@PathVariable UUID id) {
        eventoService.eliminaEventoPerId(id);
    }
}
