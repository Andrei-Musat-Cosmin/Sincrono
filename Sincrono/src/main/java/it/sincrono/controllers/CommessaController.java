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
import it.sincrono.entities.Commessa;
import it.sincrono.requests.CommessaRequest;
import it.sincrono.responses.CommessaListResponse;
import it.sincrono.responses.CommessaResponse;
import it.sincrono.responses.GenericResponse;
import it.sincrono.services.CommessaService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
public class CommessaController {

	@Autowired
	private CommessaService CommessaService;

	@GetMapping("/Commessa")
	public @ResponseBody HttpEntity<CommessaListResponse> fetchAllCommessa() {
		HttpEntity<CommessaListResponse> httpEntity;

		CommessaListResponse CommessaListResponse = new CommessaListResponse();

		try {
			List<Commessa> commesse = CommessaService.listCommessa();

			CommessaListResponse.setList(commesse);
			//CommessaListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<CommessaListResponse>(CommessaListResponse);
			System.out.println("bau");
		} catch (Exception e) {
			 CommessaListResponse.setEsito(new Esito(404, e.getMessage(), null));
			httpEntity = new HttpEntity<CommessaListResponse>(CommessaListResponse);
		}
		return httpEntity;

	}

	@GetMapping("/Commessa/{id}")
	public @ResponseBody HttpEntity<CommessaResponse> getCommessaById(@PathVariable Integer id) {

		HttpEntity<CommessaResponse> httpEntity;

		CommessaResponse CommessaResponse = new CommessaResponse();

		try {
			Commessa Commessa = CommessaService.getCommessaById(id);

			CommessaResponse.setCommessa(Commessa);
			CommessaResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<CommessaResponse>(CommessaResponse);
			System.out.println("ciao");
		} catch (Exception e) {
			CommessaResponse.setEsito(new Esito(404, e.getMessage(), new String[] { String.valueOf(id) }));
			httpEntity = new HttpEntity<CommessaResponse>(CommessaResponse);
		}
		return httpEntity;
	}

	@PostMapping("/Commessa")
	public @ResponseBody HttpEntity<GenericResponse> saveCommessa(@RequestBody CommessaRequest CommessaRequest) {
		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {
			CommessaService.insert(CommessaRequest.getCommessa());
			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
			System.out.println("ciao");
		} catch (Exception e) {
			genericResponse.setEsito(new Esito(404, e.getMessage(), new String[] { null }));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		return httpEntity;
	}

	@PutMapping("/Commessa")
	public @ResponseBody HttpEntity<GenericResponse> updateCommessa(@RequestBody CommessaRequest CommessaRequest) {
		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {
			CommessaService.update(CommessaRequest.getCommessa());
			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
			System.out.println("ciao");
		} catch (Exception e) {
			genericResponse.setEsito(new Esito(404, e.getMessage(), new String[] { null }));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		return httpEntity;
	}

	@DeleteMapping("/Commessa/{id}")
	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer id) {

		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {

			CommessaService.delete(id);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] { String.valueOf(id) }));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}
}
