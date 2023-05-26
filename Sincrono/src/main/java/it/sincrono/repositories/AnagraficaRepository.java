package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import it.sincrono.entities.Anagrafica;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface AnagraficaRepository extends JpaRepository<Anagrafica, Integer>, AnagraficaCustomRepository {
	
	@Query(value = "SELECT a.id FROM Anagrafica a ORDER BY id DESC LIMIT 1")
	public Integer getLastId() throws RepositoryException;

}