package it.sincrono.repositories;

import java.util.List;

import it.sincrono.entities.Ruolo;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface RuoloCustomRepository extends BaseCustomRepository {
	public List<Ruolo> tree() throws RepositoryException;

	public List<Ruolo> tree(Integer id) throws RepositoryException;

	public Ruolo getPadre(Integer id) throws RepositoryException;
	
}
