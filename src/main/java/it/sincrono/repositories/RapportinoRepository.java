package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.Rapportino;

public interface RapportinoRepository extends JpaRepository<Rapportino, Integer> {

	@Modifying
	@Query("DELETE FROM Rapportino r WHERE r.mese = ?1 AND r.anno = ?2 AND r.anagrafica.id=?3")
	void deleteByMeseAndAnnoAndId(int mese, int anno, int idAnagrafica);

	@Query("SELECT a FROM Rapportino a WHERE a.anno = ?1 AND a.mese = ?2 ORDER BY (a.anagrafica.id)")
	List<Rapportino> findByMeseAndAnno(int anno, int mese);

}