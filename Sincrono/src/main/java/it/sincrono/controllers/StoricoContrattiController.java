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
import it.sincrono.entities.StoricoContratti;
import it.sincrono.requests.StoricoContrattiRequest;
import it.sincrono.responses.GenericResponse;
import it.sincrono.responses.StoricoContrattiListResponse;
import it.sincrono.responses.StoricoContrattiResponse;
import it.sincrono.services.StoricoContrattiService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
public class StoricoContrattiController {

	@Autowired
	private StoricoContrattiService storicoContrattiService;

	@GetMapping("/storico-Contratti")
	public @ResponseBody HttpEntity<StoricoContrattiListResponse> fetchAllStoricoContratti() {
		HttpEntity<StoricoContrattiListResponse> httpEntity;

		StoricoContrattiListResponse storicoContrattiListResponse = new StoricoContrattiListResponse();

		try {
			List<StoricoContratti> contratti = storicoContrattiService.listStoricoContratti();

			storicoContrattiListResponse.setStoricoContrattiList(contratti);
			storicoContrattiListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<StoricoContrattiListResponse>(storicoContrattiListResponse);
		} catch (Exception e) {
			storicoContrattiListResponse.setEsito(new Esito(404, e.getMessage(), null));
			httpEntity = new HttpEntity<StoricoContrattiListResponse>(storicoContrattiListResponse);
		}
		return httpEntity;

	}

	@GetMapping("/storico-Contratti/{id}")
	public @ResponseBody HttpEntity<StoricoContrattiResponse> getStoricoContrattiById(@PathVariable Integer id) {

		HttpEntity<StoricoContrattiResponse> httpEntity;

		StoricoContrattiResponse storicoContrattiResponse = new StoricoContrattiResponse();

		try {
			StoricoContratti storicoContratti = storicoContrattiService.getStoricoContrattiById(id);

			storicoContrattiResponse.setStoricoContratti(storicoContratti);
			storicoContrattiResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<StoricoContrattiResponse>(storicoContrattiResponse);
		} catch (Exception e) {
			storicoContrattiResponse.setEsito(new Esito(404, e.getMessage(), new String[] { String.valueOf(id) }));
			httpEntity = new HttpEntity<StoricoContrattiResponse>(storicoContrattiResponse);
		}
		return httpEntity;
	}

	@PostMapping("/storico-Contratti")
	public @ResponseBody HttpEntity<GenericResponse> saveStoricoContratti(
			@RequestBody StoricoContrattiRequest storicoContrattiRequest) {
		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {
			storicoContrattiService.insert(storicoContrattiRequest.getStoricoContratti());
			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		} catch (Exception e) {
			genericResponse.setEsito(new Esito(404, e.getMessage(), new String[] { null }));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		return httpEntity;
	}

	@PutMapping("/storico-Contratti")
	public @ResponseBody HttpEntity<GenericResponse> updateStoricoContratti(
			@RequestBody StoricoContrattiRequest storicoContrattiRequest) {
		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {
			storicoContrattiService.update(storicoContrattiRequest.getStoricoContratti());
			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		} catch (Exception e) {
			genericResponse.setEsito(new Esito(404, e.getMessage(), new String[] { null }));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		return httpEntity;
	}

	@DeleteMapping("/storico-Contratti/{id}")
	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer id) {

		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {

			storicoContrattiService.delete(id);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] { String.valueOf(id) }));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

}
