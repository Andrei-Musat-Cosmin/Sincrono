package it.sincrono.services;

import java.util.List;

import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.services.exceptions.ServiceException;

public interface RapportinoService {


	public RapportinoDto getRapportino() throws ServiceException;



}
