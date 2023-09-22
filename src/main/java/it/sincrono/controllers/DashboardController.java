package it.sincrono.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.responses.AnagraficaDtoListResponse;
import it.sincrono.services.DashboardService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;

	@GetMapping("/list-commesse")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> getCommesseInscadenza() {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			System.out.println("\nInizio chiamata al metodo getCommesseInscadenza\n");

			List<AnagraficaDto> anagrafiche = dashboardService.getCommesseInscadenza();

			anagraficaDtoListResponse.setList(anagrafiche);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

		} catch (ServiceException e) {
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}
		System.out.println("Fine chiamata al metodo getCommesseInscadenza\n");

		return httpEntity;
	}

	@GetMapping("/list-contratti")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> getContrattiInscadenza() {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			System.out.println("\nInizio chiamata al metodo getContrattiInscadenza");

			List<AnagraficaDto> anagrafiche = dashboardService.getContrattiInscadenza();

			anagraficaDtoListResponse.setList(anagrafiche);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

		} catch (ServiceException e) {
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}
		System.out.println("Fine chiamata al metodo getContrattiInscadenza\n");

		return httpEntity;
	}

	@PostMapping("/list-filter")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> listCommesse(
			@RequestBody AnagraficaRequestDto anagraficaRequestDto) {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			System.out.println("\nInizio chiamata al metodo listCommesse");

			List<AnagraficaDto> anagrafiche = dashboardService.listCommesse(anagraficaRequestDto);

			anagraficaDtoListResponse.setList(anagrafiche);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

		} catch (ServiceException e) {
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}
		System.out.println("Fine chiamata al metodo listCommesse\n");

		return httpEntity;
	}

	@GetMapping("/list-all-commesse")
	public @ResponseBody HttpEntity<AnagraficaDtoListResponse> listAllCommesse() {

		HttpEntity<AnagraficaDtoListResponse> httpEntity = null;

		AnagraficaDtoListResponse anagraficaDtoListResponse = new AnagraficaDtoListResponse();
		try {
			System.out.println("\nInizio chiamata al metodo listAllCommesse");

			List<AnagraficaDto> anagrafiche = dashboardService.listAllCommesse();

			anagraficaDtoListResponse.setList(anagrafiche);
			anagraficaDtoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);

		} catch (ServiceException e) {
			anagraficaDtoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaDtoListResponse>(anagraficaDtoListResponse);
		}
		System.out.println("Fine chiamata al metodo listAllCommesse\n");

		return httpEntity;
	}

}
