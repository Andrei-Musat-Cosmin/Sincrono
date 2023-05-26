package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.sincrono.entities.Contratto;
import it.sincrono.repositories.exceptions.RepositoryException;

@Repository
public interface ContrattoRepository extends JpaRepository<Contratto, Integer>, ContrattoCustomRepository {
	
	@Query(value = "SELECT c.id FROM Contratto c ORDER BY id DESC LIMIT 1")
	public Integer getLastId() throws RepositoryException;

}
