package it.sincrono.services;

import java.util.List;


import it.sincrono.entities.Anagrafica;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import it.sincrono.services.exceptions.ServiceException;

public interface DashboardService {


	public List<AnagraficaDto> getCommesseInscadenza() throws ServiceException;
	
	public List<AnagraficaDto> getContrattiInscadenza() throws ServiceException;
	
	public List<AnagraficaDto> listCommesse(AnagraficaDto anagraficaDto) throws ServiceException;
	
	public List<AnagraficaDto> listAllCommesse() throws ServiceException;


	
}
