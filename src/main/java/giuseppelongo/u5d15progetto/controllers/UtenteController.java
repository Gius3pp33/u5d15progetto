package giuseppelongo.u5d15progetto.controllers;

import giuseppelongo.u5d15progetto.entities.Utente;
import giuseppelongo.u5d15progetto.payloads.NewUtenteDTO;
import giuseppelongo.u5d15progetto.services.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteService utenteService;

    @PostMapping("/registrati")
    public ResponseEntity<UUID> registrati(@RequestBody NewUtenteDTO dto) {
        Utente nuovoUtente = utenteService.save(dto);
        return new ResponseEntity<>(nuovoUtente.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utente> findById(@PathVariable UUID id) {
        return utenteService.findById(id)
                .map(utente -> new ResponseEntity<>(utente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaUtente(@PathVariable UUID id) {
        utenteService.eliminaUtente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
