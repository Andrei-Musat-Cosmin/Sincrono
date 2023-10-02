package it.sincrono.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.Anagrafica;

public interface AnagraficaRepository extends JpaRepository<Anagrafica, Integer>, AnagraficaCustomRepository {

	@Query("SELECT a.id FROM Anagrafica a")
	Collection<Integer> findAllId();

	@Query("SELECT a FROM Anagrafica a WHERE a.attivo = 1 AND a.utente.tokenPassword LIKE ?1")
	Anagrafica findByToken(String token);

	@Query("SELECT a.id FROM Anagrafica a WHERE a.attivo = true")
	Collection<Integer> findAllactiveId();

//	@Query("SELECT a FROM Anagrafica a WHERE a.attivo = 1")
//	List<Anagrafica> getAllActive();

}