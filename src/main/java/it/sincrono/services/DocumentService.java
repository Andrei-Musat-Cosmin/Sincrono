package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.RapportinoInviato;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.DocumentRequest;
import it.sincrono.requests.RapportinoRequest;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.responses.DocumentResponse;
import it.sincrono.services.exceptions.ServiceException;

public interface DocumentService {
	
	
	public void addImage(DocumentRequest documentRequest) throws ServiceException;
	
	public DocumentResponse getImage(DocumentRequest documentRequest) throws ServiceException;




}
