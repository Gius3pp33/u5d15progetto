package giuseppelongo.u5d15progetto.repositories;

import giuseppelongo.u5d15progetto.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
   
}
