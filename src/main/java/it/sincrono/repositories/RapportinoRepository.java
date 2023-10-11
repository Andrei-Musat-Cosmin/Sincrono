package it.sincrono.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import it.sincrono.entities.Rapportino;

public interface RapportinoRepository extends JpaRepository<Rapportino, Integer> {

	@Modifying
	@Query("DELETE FROM Rapportino r WHERE r.mese = ?1 AND r.anno = ?2 AND r.anagrafica.id=?3")
	void deleteByMeseAndAnnoAndId(int mese, int anno, int idAnagrafica);

}