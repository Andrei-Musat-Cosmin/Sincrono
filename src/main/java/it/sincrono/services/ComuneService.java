package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Comune;
import it.sincrono.services.exceptions.ServiceException;

public interface ComuneService {

	public List<Comune> getComuniMap() throws ServiceException;

	public List<Comune> getComuniByProvinciaMap(String siglaProvincia) throws ServiceException;

}
