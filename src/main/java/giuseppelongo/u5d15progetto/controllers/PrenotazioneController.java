package giuseppelongo.u5d15progetto.controllers;

import giuseppelongo.u5d15progetto.payloads.NewPrenotazioneDTO;
import giuseppelongo.u5d15progetto.payloads.NewPrenotazioneRespDTO;
import giuseppelongo.u5d15progetto.services.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public NewPrenotazioneRespDTO creaPrenotazione(@RequestBody @Valid NewPrenotazioneDTO prenotazioneDTO) {
        return prenotazioneService.creaPrenotazione(prenotazioneDTO);
    }


    @GetMapping("/{id}")
    public NewPrenotazioneRespDTO trovaPrenotazionePerId(@PathVariable UUID id) {
        return prenotazioneService.trovaPrenotazionePerId(id);
    }


    @GetMapping
    public List<NewPrenotazioneRespDTO> trovaTutteLePrenotazioni() {
        return prenotazioneService.trovaTutteLePrenotazioni();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminaPrenotazione(@PathVariable UUID id) {
        prenotazioneService.eliminaPrenotazionePerId(id);
    }
}
