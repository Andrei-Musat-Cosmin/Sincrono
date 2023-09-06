package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.Anagrafica;

public interface AnagraficaRepository extends JpaRepository<Anagrafica, Integer>, AnagraficaCustomRepository {

//	@Query("SELECT a FROM Anagrafica a WHERE a.attivo = 1")
//	List<Anagrafica> getAllActive();

}