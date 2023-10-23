package it.sincrono.services;

import it.sincrono.requests.DocumentRequest;
import it.sincrono.responses.DocumentResponse;
import it.sincrono.services.exceptions.ServiceException;

public interface DocumentService {

	public void addImage(DocumentRequest documentRequest) throws ServiceException;

	public DocumentResponse getImage(DocumentRequest documentRequest) throws ServiceException;

}
