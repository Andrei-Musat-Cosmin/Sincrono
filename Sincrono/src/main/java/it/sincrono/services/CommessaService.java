package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Commessa;
import it.sincrono.repositories.dto.CommessaDto;
import it.sincrono.services.exceptions.ServiceException;

public interface CommessaService {

	//public List<Commessa> listCommessa() throws ServiceException;

	//public Commessa getCommessaById(Integer id) throws ServiceException;

	//public void insert(Commessa Commessa) throws ServiceException;

	public void update(Commessa Commessa) throws ServiceException;

	//public void delete(Integer id) throws ServiceException;

	public List<CommessaDto> dashboard() throws ServiceException;

}
