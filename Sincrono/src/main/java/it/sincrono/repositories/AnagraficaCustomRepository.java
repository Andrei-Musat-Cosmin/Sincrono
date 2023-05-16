package it.sincrono.repositories;

import java.util.List;

import it.sincrono.entities.Anagrafica;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;

public interface AnagraficaCustomRepository extends BaseCustomRepository {
	
	public List<Anagrafica> search(AnagraficaDto anagraficaDto) throws RepositoryException;



}