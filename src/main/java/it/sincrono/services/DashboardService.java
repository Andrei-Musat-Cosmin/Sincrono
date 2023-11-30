package it.sincrono.services;

import java.util.List;

import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.services.exceptions.ServiceException;

public interface DashboardService {

	public List<AnagraficaDto> getCommesseInscadenza() throws ServiceException;

	public List<AnagraficaDto> getContrattiInscadenza() throws ServiceException;

	public List<AnagraficaDto> listCommesse(AnagraficaRequestDto anagraficaRequestDto) throws ServiceException;

	public List<AnagraficaDto> listAllCommesse() throws ServiceException;

}
