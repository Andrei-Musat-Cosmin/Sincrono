package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.RapportinoInviato;

public interface RapportinoInviatoRepository extends JpaRepository<RapportinoInviato, Integer>, RapportinoInviatoCustomRepository {

	@Query("SELECT a FROM RapportinoInviato a WHERE a.checkFreeze = false AND a.codiceFiscale LIKE ?1 AND a.anno = ?2 AND a.mese = ?3")
	public RapportinoInviato findByData(String codiceFiscale, Integer anno, Integer mese);
	
	@Query("SELECT a FROM RapportinoInviato a WHERE a.checkFreeze = false")
	public List<RapportinoInviato> getRapportiniNotFreeze();
	
	@Query("SELECT a FROM RapportinoInviato a WHERE a.checkFreeze = true")
	public List<RapportinoInviato> getRapportiniFreeze();

}