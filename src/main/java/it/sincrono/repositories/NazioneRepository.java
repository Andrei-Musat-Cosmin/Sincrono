package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.Nazione;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface NazioneRepository extends JpaRepository<Nazione, Integer>{
	
	@Query(value = "SELECT a FROM Nazione a ORDER BY a.id")
	public List<Nazione> getNazioniList() throws RepositoryException;

}
