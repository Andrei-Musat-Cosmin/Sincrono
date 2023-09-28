package it.sincrono.controllers;

import java.util.List;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.responses.AnagraficaDtoListResponse;
import it.sincrono.responses.AnagraficaDtoResponse;
import it.sincrono.responses.GenericResponse;
import it.sincrono.responses.RapportinoDtoResponse;
import it.sincrono.services.AnagraficaService;
import it.sincrono.services.RapportinoService;
import it.sincrono.services.UtenteService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class RapportinoController {
	private static final Logger logger = LogManager.getLogger(RapportinoController.class);
	
	@Autowired
	RapportinoService rapportinoService;

	
	
	@GetMapping("/get-rapportino")
	public @ResponseBody HttpEntity<RapportinoDtoResponse> getRapportino() {

		HttpEntity<RapportinoDtoResponse> httpEntity = null;

		RapportinoDtoResponse rapportinoDtoResponse = new RapportinoDtoResponse();
		try {
			logger.log(Level.INFO, "Inizio chiamata al meotodo getRapportino");

			RapportinoDto rapportinoDto = rapportinoService.getRapportino();

			rapportinoDtoResponse.setRapportinoDto(rapportinoDto);
			rapportinoDtoResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<RapportinoDtoResponse>(rapportinoDtoResponse);

		} catch (ServiceException e) {
			logger.log(Level.ERROR, e.getMessage());
			rapportinoDtoResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<RapportinoDtoResponse>(rapportinoDtoResponse);
		}
		logger.log(Level.INFO, "Fine chiamata al meotodo getRapportino\n");

		return httpEntity;
	}

	




}
