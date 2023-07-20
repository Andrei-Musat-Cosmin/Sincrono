package it.sincrono.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Utente;
import it.sincrono.requests.CambioPasswordRequest;
import it.sincrono.requests.UtenteRequest;
import it.sincrono.responses.GenericResponse;
import it.sincrono.responses.UtenteListResponse;
import it.sincrono.responses.UtenteResponse;
import it.sincrono.services.UtenteService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class UtenteController {

	@Autowired
	private UtenteService utenteService;

	@GetMapping("/dettaglio-utente/{id}")
	public @ResponseBody HttpEntity<UtenteResponse> dettaglioUtente(@PathVariable("id") Integer id) {
		HttpEntity<UtenteResponse> httpEntity = null;
		UtenteResponse utenteResponse = new UtenteResponse();
		try {
			System.out.println("START invocation dettaglioUtente of controller layer");
			Utente utente = utenteService.getById(id);
			utenteResponse.setUtente(utente);
			utenteResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<UtenteResponse>(utenteResponse);
			System.out.println("END invocation dettaglioUtente of controller layer");

		} catch (ServiceException e) {
			utenteResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<UtenteResponse>(utenteResponse);
		}
		return httpEntity;
	}

	@PutMapping("/modifica-utente")
	public @ResponseBody HttpEntity<GenericResponse> updateUtente(@RequestBody UtenteRequest utenteRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("START invocation updateUtene of controller layer");

			utenteService.updateUtente(utenteRequest);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			System.out.println("END invocation insert(anagrafica) of controller layer");

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	@PutMapping("/reset-password")
	public @ResponseBody HttpEntity<GenericResponse> updateUtente(
			@RequestBody CambioPasswordRequest cambioPasswordRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("START invocation updateUtene of controller layer");

			utenteService.updateUtente(cambioPasswordRequest);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			System.out.println("END invocation insert(anagrafica) of controller layer");

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	@GetMapping("/utenti-list")
	public @ResponseBody HttpEntity<UtenteListResponse> getUtenti() {

		HttpEntity<UtenteListResponse> httpEntity = null;

		UtenteListResponse utenteListResponse = new UtenteListResponse();

		try {
			System.out.println("START invocation getAll() of controller layer");

			List<Utente> utenti = utenteService.list();

			utenteListResponse.setList(utenti);
			utenteListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<UtenteListResponse>(utenteListResponse);

			System.out.println("END invocation getAll() of controller layer");

		} catch (ServiceException e) {
			utenteListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<UtenteListResponse>(utenteListResponse);
		}

		return httpEntity;
	}

}
