package it.sincrono.services;

import it.sincrono.repositories.dto.RichiestaDto;
import it.sincrono.requests.RichiestaRequest;
import it.sincrono.services.exceptions.ServiceException;

public interface RichiestaService {

	public RichiestaDto getRichiesta(Integer id) throws ServiceException;

}
