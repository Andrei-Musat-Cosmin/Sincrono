package it.sincrono.repositories;

import java.util.List;

import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import it.sincrono.requests.AnagraficaRequestDto;

public interface DashboardCustomRepository extends BaseCustomRepository {

	public List<AnagraficaDto> listCommesseInScadenza() throws RepositoryException;
	
	public List<AnagraficaDto> listContrattiInScadenza() throws RepositoryException;
	
	public List<AnagraficaDto> listCommesse(AnagraficaRequestDto anagraficaRequestDto) throws RepositoryException;
	
	public List<AnagraficaDto> listAllCommesse() throws RepositoryException;
	
	


	
	
	

}