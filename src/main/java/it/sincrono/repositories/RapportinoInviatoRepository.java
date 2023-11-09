package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.RapportinoInviato;

public interface RapportinoInviatoRepository
		extends JpaRepository<RapportinoInviato, Integer>, RapportinoInviatoCustomRepository {

	@Query("SELECT a FROM RapportinoInviato a WHERE a.checkFreeze = false AND a.codiceFiscale LIKE ?1 AND a.anno = ?2 AND a.mese = ?3")
	public RapportinoInviato findByData(String codiceFiscale, Integer anno, Integer mese);

	@Query("SELECT a FROM RapportinoInviato a WHERE a.codiceFiscale LIKE ?1 AND a.anno = ?2 AND a.mese = ?3")
	public RapportinoInviato findByCodiceFiscaleAnnoMese(String codiceFiscale, Integer anno, Integer mese);

	@Query("SELECT a FROM RapportinoInviato a WHERE a.checkFreeze = false and a.mese=?1 and a.anno=?2")
	public List<RapportinoInviato> getRapportiniNotFreeze(Integer mese, Integer anno);

	@Query("SELECT  a FROM RapportinoInviato a WHERE a.checkFreeze = true and a.mese=?1 and a.anno=?2")
	public List<RapportinoInviato> getRapportiniFreeze(Integer mese, Integer anno);

	@Query("SELECT CASE WHEN r.anno = ?2 AND r.mese = ?3 AND r.codiceFiscale = ?1 THEN true ELSE false END FROM RapportinoInviato r")
	public List<Boolean> checkInviato(String codiceFiscale, Integer anno, Integer mese);

}