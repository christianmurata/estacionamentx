package app.repositories;

import app.models.Ticket;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository <Ticket, Integer> {

	Optional<Ticket> findByNumeroAndEstacionamentoIdAndStatusTrue(int numero, int id);
	
}
