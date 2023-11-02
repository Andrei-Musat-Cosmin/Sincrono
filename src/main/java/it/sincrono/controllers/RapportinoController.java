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
import it.sincrono.entities.RapportinoInviato;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.dto.RapportinoDto;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.requests.RapportinoInviatoRequest;
import it.sincrono.requests.RapportinoRequest;
import it.sincrono.requests.RapportinoRequestDto;
import it.sincrono.responses.AnagraficaDtoListResponse;
import it.sincrono.responses.CheckRapportinoInviatoResponse;
import it.sincrono.responses.GenericResponse;
import it.sincrono.responses.RapportiniInviatiListResponse;
import it.sincrono.responses.RapportinoB64Response;
import it.sincrono.responses.RapportinoDtoResponse;
import it.sincrono.services.RapportinoService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class RapportinoController {
	private static final Logger LOGGER = LogManager.getLogger(RapportinoController.class);

	@Autowired
	RapportinoService rapportinoService;

	@PostMapping("/export-rapportino")
	public @ResponseBody HttpEntity<RapportinoB64Response> getRapportinoB64(@RequestBody RapportinoRequest rapportino) {
		HttpEntity<RapportinoB64Response> httpEntity = null;

		RapportinoB64Response rapportinoB64Response = new RapportinoB64Response();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getRapportinoB64");

			String rapportinoB64 = rapportinoService.getRapportinoB64(rapportino.getAnno(), rapportino.getMese());

			rapportinoB64Response.setRapportinoB64(rapportinoB64);
			rapportinoB64Response.setEsito(new Esito());

			httpEntity = new HttpEntity<RapportinoB64Response>(rapportinoB64Response);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			rapportinoB64Response.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<RapportinoB64Response>(rapportinoB64Response);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getRapportinoB64\n");

		return httpEntity;
	}

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

	@GetMapping("/list-not-freeze")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> getRapportiniNotFreeze() {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getRapportiniNotFreeze");

			List<AnagraficaDto> rapportiniInviatiNotFreeze = rapportinoService.getRapportiniNotFreeze();

			anagraficaDtoListResponse.setList(rapportiniInviatiNotFreeze);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getRapportiniNotFreeze\n");

		return httpEntity;
	}

	@GetMapping("/list-freeze")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> getRapportiniFreeze() {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getRapportiniFreeze");

			List<AnagraficaDto> rapportiniInviatiNotFreeze = rapportinoService.getRapportiniFreeze();

			anagraficaDtoListResponse.setList(rapportiniInviatiNotFreeze);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getRapportiniFreeze\n");

		return httpEntity;
	}

	@GetMapping("/list-not-freeze-filter")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> getRapportiniNotFreezeFilter(
			@RequestBody AnagraficaRequestDto anagraficaRequestDto) {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getRapportiniNotFreezeFilter");

			List<AnagraficaDto> rapportiniInviatiNotFreeze = rapportinoService
					.getRapportiniNotFreezeFilter(anagraficaRequestDto);

			anagraficaDtoListResponse.setList(rapportiniInviatiNotFreeze);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getRapportiniNotFreezeFilter\n");

		return httpEntity;
	}

	@GetMapping("/list-freeze-filter")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> getRapportiniFreezeFilter(
			@RequestBody AnagraficaRequestDto anagraficaRequestDto) {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getRapportiniFreezeFilter");

			List<AnagraficaDto> rapportiniInviatiNotFreeze = rapportinoService
					.getRapportiniNotFreezeFilter(anagraficaRequestDto);

			anagraficaDtoListResponse.setList(rapportiniInviatiNotFreeze);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getRapportiniFreezeFilter\n");

		return httpEntity;
	}

	@PostMapping("/insert-rapportino")
	public @ResponseBody HttpEntity<GenericResponse> addRapportinoInDatabase(
			@RequestBody RapportinoRequest rapportinoRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo addRapportinoInDatabase");

			rapportinoService.addRapportinoInDatabase(rapportinoRequest);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo addRapportinoInDatabase\n");

		return httpEntity;
	}

	@PostMapping("/delete-rapportino")
	public @ResponseBody HttpEntity<GenericResponse> deleteRapportinoInDatabase(
			@RequestBody RapportinoRequest rapportinoRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo addRapportinoInDatabase");

			rapportinoService.deleteRapportinoInDatabase(rapportinoRequest);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo addRapportinoInDatabase\n");

		return httpEntity;
	}

	@PostMapping("/get-check-rapportino-inviato")
	public @ResponseBody HttpEntity<CheckRapportinoInviatoResponse> getCheckRapportinoInviato(
			@RequestBody RapportinoRequest RapportinoRequest) {

		HttpEntity<CheckRapportinoInviatoResponse> httpEntity = null;

		CheckRapportinoInviatoResponse checkRapportinoInviatoResponse = new CheckRapportinoInviatoResponse();
		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getRapportino");

			checkRapportinoInviatoResponse
					.setCheckInviato(rapportinoService.getCheckRapportinoInviato(RapportinoRequest));

			checkRapportinoInviatoResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<CheckRapportinoInviatoResponse>(checkRapportinoInviatoResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			checkRapportinoInviatoResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<CheckRapportinoInviatoResponse>(checkRapportinoInviatoResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getRapportino\n");

		return httpEntity;
	}

}
