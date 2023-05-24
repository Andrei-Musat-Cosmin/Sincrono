package it.sincrono.controllers;

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
import it.sincrono.entities.Profilo;
import it.sincrono.requests.ProfiloRequest;
import it.sincrono.responses.GenericResponse;
import it.sincrono.responses.ProfiloResponse;
import it.sincrono.services.ProfiloService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class ProfiloController {

	@Autowired
	private ProfiloService profiloService;

	@GetMapping("/profilo/{id}")
	public @ResponseBody HttpEntity<ProfiloResponse> get(@PathVariable("id") Integer id) {

		HttpEntity<ProfiloResponse> httpEntity = null;

		ProfiloResponse profiloResponse = new ProfiloResponse();

		try {

			Profilo profilo = profiloService.get(id);

			profiloResponse.setProfilo(profilo);
			profiloResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<ProfiloResponse>(profiloResponse);

		} catch (ServiceException e) {
			profiloResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] { String.valueOf(id) }));
			httpEntity = new HttpEntity<ProfiloResponse>(profiloResponse);
		}

		return httpEntity;
	}

	@PostMapping("/profilo")
	public @ResponseBody HttpEntity<GenericResponse> insert(@RequestBody ProfiloRequest profiloRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {

			profiloService.insert(profiloRequest.getProfilo());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	@PutMapping("/profilo")
	public @ResponseBody HttpEntity<GenericResponse> update(@RequestBody ProfiloRequest profiloRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {

			profiloService.update(profiloRequest.getProfilo());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	@DeleteMapping("/profilo/{id}")
	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer ID) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {

			profiloService.delete(ID);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] { String.valueOf(ID) }));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

}
