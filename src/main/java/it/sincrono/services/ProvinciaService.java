package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Provincia;
import it.sincrono.services.exceptions.ServiceException;

public interface ProvinciaService {
	public List<Provincia> getProvinceMap() throws ServiceException;
}
