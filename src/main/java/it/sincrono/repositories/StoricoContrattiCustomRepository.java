package it.sincrono.repositories;

import java.util.List;

import it.sincrono.entities.Contratto;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface StoricoContrattiCustomRepository {

	public List<Contratto> getStoricoContratti(Integer id) throws RepositoryException;

}
