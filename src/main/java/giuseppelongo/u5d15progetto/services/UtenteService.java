package giuseppelongo.u5d15progetto.services;

import giuseppelongo.u5d15progetto.entities.Utente;
import giuseppelongo.u5d15progetto.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    public Utente registrati(Utente utente) {
        return utenteRepository.save(utente);
    }

    public Optional<Utente> findById(UUID id) {
        return utenteRepository.findById(id);

    }


    public void eliminaUtente(UUID id) {
        utenteRepository.deleteById(id);
    }


}
