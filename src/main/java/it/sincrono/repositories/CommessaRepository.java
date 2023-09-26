package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.sincrono.entities.Commessa;

@Repository
public interface CommessaRepository extends JpaRepository<Commessa, Integer>, CommessaCustomRepository {
	@Query("SELECT c FROM Commessa c INNER JOIN StoricoCommesse sc ON sc.commessa.id=c.id WHERE sc.anagrafica.id=?1 AND c.id!=0 AND c.attivo=true")
	List<Commessa> findByIdAnagrafica(Integer id);

	//
}
