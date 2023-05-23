package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.StoricoContratti;
import it.sincrono.services.exceptions.ServiceException;

public interface StoricoContrattiService {

	public List<StoricoContratti> listStoricoContratti() throws ServiceException;

	public StoricoContratti getStoricoContrattiById(Integer id) throws ServiceException;

	public void insert(StoricoContratti storicoContratti) throws ServiceException;

	public void update(StoricoContratti storicoContratti) throws ServiceException;

	public void delete(Integer id) throws ServiceException;

}
