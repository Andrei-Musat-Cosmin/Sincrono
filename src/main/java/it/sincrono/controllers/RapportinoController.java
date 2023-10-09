package it.sincrono.controllers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.entities.RapportinoInviato;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.RapportinoInviatoRequest;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.responses.GenericResponse;
import it.sincrono.responses.RapportinoDtoResponse;
import it.sincrono.services.RapportinoService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class RapportinoController {
	private static final Logger LOGGER = LogManager.getLogger(RapportinoController.class);

	@Autowired
	RapportinoService rapportinoService;

	@PostMapping("/get-rapportino")
	public @ResponseBody HttpEntity<RapportinoDtoResponse> getRapportino(
			@RequestBody RapportinoRequestDto rapportinoRequestDto) {

		HttpEntity<RapportinoDtoResponse> httpEntity = null;

		RapportinoDtoResponse rapportinoDtoResponse = new RapportinoDtoResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getRapportino");

			RapportinoDto rapportinoDto = rapportinoService.getRapportino(rapportinoRequestDto);

			rapportinoDtoResponse.setRapportinoDto(rapportinoDto);
			rapportinoDtoResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<RapportinoDtoResponse>(rapportinoDtoResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			rapportinoDtoResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<RapportinoDtoResponse>(rapportinoDtoResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getRapportino\n");

		return httpEntity;
	}

	@PostMapping("/update-rapportino")
	public @ResponseBody HttpEntity<GenericResponse> updateRapportino(
			@RequestBody RapportinoRequestDto rapportinoRequestDto) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo updateRapportino");

			rapportinoService.updateRapportino(rapportinoRequestDto);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo updateRapportino\n");

		return httpEntity;
	}

	@PostMapping("/aggiungi-note")
	public @ResponseBody HttpEntity<GenericResponse> aggiungiNote(
			@RequestBody RapportinoRequestDto rapportinoRequestDto) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo aggiungiNote");
			RapportinoInviato rapportinoInviato = rapportinoService.findByData(rapportinoRequestDto.getRapportinoDto());
			if (rapportinoInviato != null) {
				rapportinoService.aggiungiNote(rapportinoRequestDto);
				deleteRapportinoInviato(rapportinoInviato.getId());
			}
			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo aggiungiNote\n");

		return httpEntity;
	}

	@PutMapping("/insert-rapportino")
	public @ResponseBody HttpEntity<GenericResponse> insertRapportino(
			@RequestBody RapportinoInviatoRequest rapportinoInviatoRequestDto) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo insertRapportino");

			rapportinoService.insertRapportino(rapportinoInviatoRequestDto.getRapportino());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo insertRapportino\n");

		return httpEntity;
	}

	@PostMapping("/update-check-freeze")
	public @ResponseBody HttpEntity<GenericResponse> updateFreeze(
			@RequestBody RapportinoInviatoRequest rapportinoInviatoRequestDto) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo updateFreeze");

			rapportinoService.updateFreeze(rapportinoInviatoRequestDto.getRapportino());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo updateFreeze\n");

		return httpEntity;
	}

	@DeleteMapping("/delete-rapportino-inviato/{id}")
	public @ResponseBody HttpEntity<GenericResponse> deleteRapportinoInviato(@PathVariable Integer id) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo deleteRapportinoInviato");

			rapportinoService.delete(id);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo deleteRapportinoInviato\n");

		return httpEntity;
	}

}
