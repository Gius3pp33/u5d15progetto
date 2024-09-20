package giuseppelongo.u5d15progetto.services;

import giuseppelongo.u5d15progetto.entities.Role;
import giuseppelongo.u5d15progetto.entities.Utente;
import giuseppelongo.u5d15progetto.payloads.NewUtenteDTO;
import giuseppelongo.u5d15progetto.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UtenteService {

    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Utente save(NewUtenteDTO dto) {
        Utente utente = new Utente();
        utente.setUsername(dto.username());
        utente.setPassword(passwordEncoder.encode(dto.password()));
        utente.setRole(Role.ORGANIZZATORE_EVENTI);

        return utenteRepository.save(utente);
    }

    public Optional<Utente> findById(UUID id) {
        return utenteRepository.findById(id);
    }

    public Optional<Utente> findByUsername(String username) {
        return utenteRepository.findByUsername(username);
    }

    public void eliminaUtente(UUID id) {
        utenteRepository.deleteById(id);
    }
}
