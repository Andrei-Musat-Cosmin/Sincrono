package it.sincrono.services;

import java.util.List;


import it.sincrono.repositories.dto.RichiestaDto;
import it.sincrono.requests.RichiestaRequest;
import it.sincrono.services.exceptions.ServiceException;

public interface RichiestaService {

	public RichiestaDto getRichiesta(Integer id) throws ServiceException;
	
	public void insertRichiesta(RichiestaDto richiestaDto) throws ServiceException;

	public List<RichiestaDto> listRichiesteDto(RichiestaDto richiestaDto) throws ServiceException;;


}
