package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.RapportinoInviato;

public interface RapportinoRepository extends JpaRepository<RapportinoInviato, Integer>, RapportinoCustomRepository {

	@Query("SELECT a FROM RapportinoInviato a WHERE a.checkFreeze = false AND a.codiceFiscale LIKE ?1 AND a.anno = ?2 AND a.mese = ?3")
	public RapportinoInviato findByData(String codiceFiscale, Integer anno, Integer mese);

}