package giuseppelongo.u5d15progetto.repositories;

import giuseppelongo.u5d15progetto.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventoRepository extends JpaRepository<Evento, UUID> {

}
