package app.repositories;

import app.models.Entrada;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Integer>{

}
