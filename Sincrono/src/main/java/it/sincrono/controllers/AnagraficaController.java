package it.sincrono.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
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
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.requests.AnagraficaRequest;
import it.sincrono.responses.AnagraficaListResponse;
import it.sincrono.responses.AnagraficaResponse;
import it.sincrono.responses.GenericResponse;
import it.sincrono.services.AnagraficaService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
public class AnagraficaController {

	@Autowired
	private AnagraficaService anagraficaService;

	@GetMapping("/angrafica/{id}")
	public @ResponseBody HttpEntity<AnagraficaResponse> getById(@PathVariable Integer id) {

		HttpEntity<AnagraficaResponse> httpEntity = null;

		AnagraficaResponse anagraficaResponse = new AnagraficaResponse();

		try {
			System.out.println("START invocation getAll() of controller layer");

			Anagrafica anagrafica = anagraficaService.getById(id);

			anagraficaResponse.setAnagrafica(anagrafica);
			anagraficaResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaResponse>(anagraficaResponse);

			System.out.println("END invocation getAll() of controller layer");

		} catch (ServiceException e) {
			anagraficaResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaResponse>(anagraficaResponse);
		}

		return httpEntity;
	}
	
	@GetMapping("/anagrafica-list")
	public @ResponseBody HttpEntity<AnagraficaListResponse> getAll() {

		HttpEntity<AnagraficaListResponse> httpEntity = null;

		AnagraficaListResponse anagraficaListResponse = new AnagraficaListResponse();

		try {
			System.out.println("START invocation getAll() of controller layer");

			List<Anagrafica> anagrafiche = anagraficaService.list();

			anagraficaListResponse.setList(anagrafiche);
			anagraficaListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaListResponse>(anagraficaListResponse);

			System.out.println("END invocation getAll() of controller layer");

		} catch (ServiceException e) {
			anagraficaListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaListResponse>(anagraficaListResponse);
		}

		return httpEntity;
	}

	@PostMapping("/Anagrafica")
	public @ResponseBody HttpEntity<GenericResponse> insert(@RequestBody AnagraficaRequest anagraficaRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("START invocation insert(ruolo) of controller layer");

			anagraficaService.insert(anagraficaRequest.getAnagrafica());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			System.out.println("END invocation insert(ruolo) of controller layer");

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	@PutMapping("/angrafica")
	public @ResponseBody HttpEntity<GenericResponse> update(@RequestBody AnagraficaRequest anagraficaRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("START invocation insert(ruolo) of controller layer");

			anagraficaService.update(anagraficaRequest.getAnagrafica());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			System.out.println("END invocation insert(ruolo) of controller layer");

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	@DeleteMapping("/anagrafica/{id}")
	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer ID) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("START invocation insert(ruolo) of controller layer");

			anagraficaService.delete(ID);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			System.out.println("END invocation insert(ruolo) of controller layer");

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	/*@PostMapping("/Anagrafica-filter")
	public @ResponseBody HttpEntity<AnagraficaListResponse> Search(@RequestBody AnagraficaDto anagraficaDto) {

		HttpEntity<AnagraficaListResponse> httpEntity = null;

		AnagraficaListResponse anagraficaListResponse = new AnagraficaListResponse();
		try {
			System.out.println("START invocation getAll() of controller layer");

			List<Object> anagrafiche = anagraficaService.search(anagraficaDto);

			//anagraficaListResponse.setList(anagrafiche);
			anagraficaListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<AnagraficaListResponse>(anagraficaListResponse);

			System.out.println("END invocation getAll() of controller layer");

		} catch (ServiceException e) {
			anagraficaListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<AnagraficaListResponse>(anagraficaListResponse);
		}

		return httpEntity;
	}*/

}
