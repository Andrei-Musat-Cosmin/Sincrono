package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.Comune;
import it.sincrono.entities.Provincia;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface ComuneRepository extends JpaRepository<Comune, Integer>, TipologicheCustomRepository {
	
	@Query(value = "SELECT a FROM Comune a ORDER BY a.id")
	public List<Comune> getComuniMap() throws RepositoryException;
	
	

}