package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.Configurator;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.services.exceptions.ServiceException;

public interface ConfiguratorService {

	public List<Configurator> listConfigurator() throws ServiceException;



}
