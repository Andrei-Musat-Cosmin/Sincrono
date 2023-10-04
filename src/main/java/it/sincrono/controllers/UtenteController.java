package it.sincrono.controllers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.requests.CambioPasswordRequest;
import it.sincrono.requests.ModificaPasswordRequest;
import it.sincrono.responses.GenericResponse;
import it.sincrono.services.UtenteService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class UtenteController {
	private static final Logger LOGGER = LogManager.getLogger(UtenteController.class);

	@Autowired
	private UtenteService utenteService;

	@PutMapping("/modifica-utente")
	public @ResponseBody HttpEntity<GenericResponse> updateUtente(
			@RequestBody ModificaPasswordRequest modificaUtenteRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al metodo updateUtente");

			utenteService.updateUtente(modificaUtenteRequest);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al metodo updateUtente\n");

		return httpEntity;
	}

	@PutMapping("/reset-password")
	public @ResponseBody HttpEntity<GenericResponse> cambiaPasswordUtente(
			@RequestBody CambioPasswordRequest cambioPasswordRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al metodo cambiaPasswordUtente");

			utenteService.updateUtente(cambioPasswordRequest);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al metodo cambiaPasswordUtente\n");

		return httpEntity;
	}

//	@GetMapping("/dettaglio-utente/{id}")
//	public @ResponseBody HttpEntity<UtenteResponse> dettaglioUtente(@PathVariable("id") Integer id) {
//		HttpEntity<UtenteResponse> httpEntity = null;
//		UtenteResponse utenteResponse = new UtenteResponse();
//		try {
//			Utente utente = utenteService.getById(id);
//			utenteResponse.setUtente(utente);
//			utenteResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<UtenteResponse>(utenteResponse);
//
//		} catch (ServiceException e) {
//			utenteResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<UtenteResponse>(utenteResponse);
//		}
//		return httpEntity;
//	}

//	@GetMapping("/utenti-list")
//	public @ResponseBody HttpEntity<UtenteListResponse> getUtenti() {
//
//		HttpEntity<UtenteListResponse> httpEntity = null;
//
//		UtenteListResponse utenteListResponse = new UtenteListResponse();
//
//		try {
//
//			List<Utente> utenti = utenteService.list();
//
//			utenteListResponse.setList(utenti);
//			utenteListResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<UtenteListResponse>(utenteListResponse);
//
//
//		} catch (ServiceException e) {
//			utenteListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<UtenteListResponse>(utenteListResponse);
//		}
//
//		return httpEntity;
//	}

}
