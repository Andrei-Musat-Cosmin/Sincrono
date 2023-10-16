package it.sincrono.controllers;

import java.util.List;



import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Configurator;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.requests.DocumentRequest;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.responses.ConfiguratorListResponse;
import it.sincrono.responses.DocumentResponse;
import it.sincrono.responses.GenericResponse;
import it.sincrono.services.ConfiguratorService;
import it.sincrono.services.DocumentService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class DocumentController {
	private static final Logger LOGGER = LogManager.getLogger(DocumentController.class);

	@Autowired
	private DocumentService documentService;

	@PostMapping("/add-document-image")
	public @ResponseBody HttpEntity<GenericResponse> addImage(@RequestBody DocumentRequest documentRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo addImage");

			documentService.addImage(documentRequest);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo addImage\n");

		return httpEntity;
	}
	
	@PostMapping("/get-document-image")
	public @ResponseBody HttpEntity<DocumentResponse> getImage(
			@RequestBody DocumentRequest documentRequest) {

		HttpEntity<DocumentResponse> httpEntity = null;

		DocumentResponse documentResponse = new DocumentResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getImage");

			documentResponse=documentService.getImage(documentRequest);

			documentResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<DocumentResponse>(documentResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			documentResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<DocumentResponse>(documentResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getImage\n");

		return httpEntity;
	}


}