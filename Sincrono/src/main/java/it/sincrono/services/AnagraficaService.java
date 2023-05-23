package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Anagrafica;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.services.exceptions.ServiceException;

public interface AnagraficaService {

	public List<Anagrafica> list() throws ServiceException;

	public Anagrafica getById(Integer ID) throws ServiceException;

	public void insert(Anagrafica anagrafica) throws ServiceException;

	public void update(Anagrafica anagrafica) throws ServiceException;

	public void delete(Integer ID) throws ServiceException;

	List<AnagraficaDto> search(AnagraficaDto anagraficaDto) throws ServiceException;

}
