package it.sincrono.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.requests.AnagraficaRequestDto;
import it.sincrono.requests.CommessaRequest;
import it.sincrono.responses.CommessaDtoListResponse;
import it.sincrono.responses.GenericResponse;
import it.sincrono.services.CommessaService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class CommessaController {
	private static final Logger LOGGER = LogManager.getLogger(CommessaController.class);

	@Autowired
	private CommessaService commessaService;

	@GetMapping("/dashboard")
	public @ResponseBody HttpEntity<CommessaDtoListResponse> dashboard() {
		HttpEntity<CommessaDtoListResponse> httpEntity;

		CommessaDtoListResponse commessaDtoListResponse = new CommessaDtoListResponse();

		try {
			System.out.println("\nInizio chiamata al meotodo dashboard");
			commessaDtoListResponse.setEsito(new Esito());
			commessaDtoListResponse.setList(commessaService.dashboard());
			httpEntity = new HttpEntity<CommessaDtoListResponse>(commessaDtoListResponse);
		} catch (Exception e) {
			commessaDtoListResponse.setEsito(new Esito(501, e.getMessage(), null));
			httpEntity = new HttpEntity<CommessaDtoListResponse>(commessaDtoListResponse);
		}
		System.out.println("\nFine chiamata al meotodo dashboard");
		return httpEntity;
	}

	@PutMapping("/commessa")
	public @ResponseBody HttpEntity<GenericResponse> updateCommessa(@RequestBody CommessaRequest commessaRequest) {
		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {
			System.out.println("Inizio chiamata al meotodo updateCommessa\n");

			commessaService.update(commessaRequest.getCommessa());
			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (Exception e) {
			genericResponse.setEsito(new Esito(501, e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		System.out.println("\nFine chiamata al meotodo updateCommessa");
		return httpEntity;
	}

	@PutMapping("/retain-commessa")
	public @ResponseBody HttpEntity<GenericResponse> retainCommessa(
			@RequestBody AnagraficaRequestDto anagraficaRequestDto) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();
		try {
			System.out.println("\nInizio chiamata al meotodo retainCommessa");

			commessaService.retainCommessa(anagraficaRequestDto.getAnagraficaDto().getCommesse().get(0).getId());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}
		System.out.println("Fine chiamata al meotodo retainCommessa\n");

		return httpEntity;
	}

//	@GetMapping("/commessa-list")
//	public @ResponseBody HttpEntity<CommessaListResponse> fetchAllCommessa() {
//		HttpEntity<CommessaListResponse> httpEntity;
//
//		CommessaListResponse commessaListResponse = new CommessaListResponse();
//
//		try {
//			List<Commessa> commesse = commessaService.listCommessa();
//
//			commessaListResponse.setList(commesse);
//			commessaListResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<CommessaListResponse>(commessaListResponse);
//
//		} catch (Exception e) {
//			commessaListResponse.setEsito(new Esito(404, e.getMessage(), null));
//			httpEntity = new HttpEntity<CommessaListResponse>(commessaListResponse);
//		}
//		return httpEntity;
//
//	}

//	@GetMapping("/commessa/{id}")
//	public @ResponseBody HttpEntity<CommessaResponse> getCommessaById(@PathVariable Integer id) {
//
//		HttpEntity<CommessaResponse> httpEntity;
//
//		CommessaResponse commessaResponse = new CommessaResponse();
//
//		try {
//			Commessa commessa = commessaService.getCommessaById(id);
//
//			commessaResponse.setCommessa(commessa);
//			commessaResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<CommessaResponse>(commessaResponse);
//
//		} catch (Exception e) {
//			commessaResponse.setEsito(new Esito(404, e.getMessage(), new String[] { String.valueOf(id) }));
//			httpEntity = new HttpEntity<CommessaResponse>(commessaResponse);
//		}
//		return httpEntity;
//	}

//	@PostMapping("/commessa")
//	public @ResponseBody HttpEntity<GenericResponse> saveCommessa(@RequestBody CommessaRequest commessaRequest) {
//		HttpEntity<GenericResponse> httpEntity;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//			commessaService.insert(commessaRequest.getCommessa());
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//
//		} catch (Exception e) {
//			genericResponse.setEsito(new Esito(404, e.getMessage(), new String[] { null }));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//		return httpEntity;
//	}

//	@DeleteMapping("/commessa/{id}")
//	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer id) {
//
//		HttpEntity<GenericResponse> httpEntity;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//
//			commessaService.delete(id);
//
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//
//		} catch (ServiceException e) {
//			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] { String.valueOf(id) }));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//
//		return httpEntity;
//	}
}
