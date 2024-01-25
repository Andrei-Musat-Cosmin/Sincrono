package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Comune;
import it.sincrono.entities.Provincia;
import it.sincrono.services.exceptions.ServiceException;

public interface ComuneProvinciaService {
	
	public List<Comune> getComuniMap() throws ServiceException;

	public List<Provincia> getProvinceMap() throws ServiceException;

}
