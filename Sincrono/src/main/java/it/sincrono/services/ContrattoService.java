package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Contratto;
import it.sincrono.services.exceptions.ServiceException;

public interface ContrattoService {

	public List<Contratto> listContratto() throws ServiceException;

	public Contratto getContrattoById(Integer id) throws ServiceException;

	public void insert(Contratto contratto) throws ServiceException;

	public void update(Contratto contratto) throws ServiceException;

	public void delete(Integer id) throws ServiceException;

	public List<Object> search() throws ServiceException;
}
