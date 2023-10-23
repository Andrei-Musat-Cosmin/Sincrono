package it.sincrono.repositories;

import java.util.List;

import it.sincrono.entities.Commessa;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface StoricoCommesseCustomRepository extends BaseCustomRepository {

	List<Commessa> getStoricoCommesseByAnagrafica(Integer id) throws RepositoryException;

}
