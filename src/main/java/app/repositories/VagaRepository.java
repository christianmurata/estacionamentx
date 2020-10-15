package app.repositories;

import app.models.Vaga;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VagaRepository extends JpaRepository<Vaga, Integer> {

	long countByEstacionamentoId(int id);
	
	long countByEstacionamentoIdAndDisponivel(int id, boolean disponibilidade);
	
	List<Vaga> findAllByEstacionamentoIdOrderByNumero(int id);
	
	Optional<Vaga> findOneByEstacionamentoIdAndNumero(int id, int numero);
	
}
