package it.sincrono.services;

import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.services.exceptions.ServiceException;

public interface RapportinoService {

	public RapportinoDto getRapportino(String codiceFiscale) throws ServiceException;

	public RapportinoDto updateRapportino(RapportinoRequestDto rapportinoRequestDto) throws ServiceException;

}
