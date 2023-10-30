package it.sincrono.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import it.sincrono.entities.Commessa;
import it.sincrono.entities.StoricoCommesse;
import it.sincrono.repositories.exceptions.RepositoryException;

@Repository
public interface StoricoCommesseRepository
		extends JpaRepository<StoricoCommesse, Integer>, StoricoCommesseCustomRepository {

	@Query("SELECT b FROM StoricoCommesse a INNER JOIN Commessa b ON b.id = a.commessa.id WHERE a.anagrafica.id = ?1 AND a.commessa.id>0 order by b.clienteFinale")
	List<Commessa> getStoricoCommesseByAnagrafica(Integer id) throws RepositoryException;

}
