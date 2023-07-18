package it.sincrono.repositories;

import java.util.List;

import it.sincrono.entities.Funzione;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface FunzioneCustomRepository extends BaseCustomRepository {

	List<Funzione> funzioneTree() throws RepositoryException;

	List<Funzione> funzioneTree(Integer id) throws RepositoryException;

	Integer getFunzioniDalRuolo(Integer id) throws RepositoryException;

}
