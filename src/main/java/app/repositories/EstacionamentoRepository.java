package app.repositories;

import app.models.Estacionamento;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamentoRepository extends JpaRepository<Estacionamento, Integer> {
	
	Optional <Estacionamento> findBySlug(String slug);

}
