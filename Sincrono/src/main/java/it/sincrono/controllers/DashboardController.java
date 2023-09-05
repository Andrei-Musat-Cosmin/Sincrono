package it.sincrono.controllers;

import java.util.List;

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
import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Utente;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.requests.AnagraficaRequest;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.responses.AnagraficaDtoListResponse;
import it.sincrono.responses.AnagraficaDtoResponse;
import it.sincrono.responses.AnagraficaListResponse;
import it.sincrono.responses.AnagraficaResponse;
import it.sincrono.responses.GenericResponse;
import it.sincrono.responses.UtenteListResponse;
import it.sincrono.services.AnagraficaService;
import it.sincrono.services.DashboardService;
import it.sincrono.services.UtenteService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;


	@GetMapping("/listCommesse")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> getCommesseInscadenza() {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			System.out.println("START invocation of listAnagraficaDto");

			List<AnagraficaDto> anagrafiche = dashboardService.getCommesseInscadenza();

			anagraficaDtoListResponse.setList(anagrafiche);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

			System.out.println("END invocation of listAnagraficaDto");

		} catch (ServiceException e) {
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}

		return httpEntity;
	}
	

	@GetMapping("/listContratti")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> getContrattiInscadenza() {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			System.out.println("START invocation of listAnagraficaDto");

			List<AnagraficaDto> anagrafiche = dashboardService.getContrattiInscadenza();

			anagraficaDtoListResponse.setList(anagrafiche);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

			System.out.println("END invocation of listAnagraficaDto");

		} catch (ServiceException e) {
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}

		return httpEntity;
	}

	@PostMapping("/listFilter")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> listCommesse(@RequestBody AnagraficaRequestDto anagraficaRequestDto) {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			System.out.println("START invocation of listAnagraficaDto");

			List<AnagraficaDto> anagrafiche = dashboardService.listCommesse(anagraficaRequestDto.getAnagraficaDto());

			anagraficaDtoListResponse.setList(anagrafiche);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

			System.out.println("END invocation of listAnagraficaDto");

		} catch (ServiceException e) {
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}

		return httpEntity;
	}


	

	
}
