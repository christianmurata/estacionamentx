package app.repositories;

import app.models.Veiculo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
	
}
