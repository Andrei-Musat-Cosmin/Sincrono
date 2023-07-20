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
import it.sincrono.entities.Contratto;
import it.sincrono.requests.ContrattoRequest;
import it.sincrono.responses.ContrattoListResponse;
import it.sincrono.responses.ContrattoResponse;
import it.sincrono.responses.GenericResponse;
import it.sincrono.responses.OrganicoDtoListResponse;
import it.sincrono.services.ContrattoService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class ContrattoController {

	@Autowired
	private ContrattoService contrattoService;

	@PostMapping("/organico")
	public @ResponseBody HttpEntity<OrganicoDtoListResponse> organico() {
		HttpEntity<OrganicoDtoListResponse> httpEntity;

		OrganicoDtoListResponse organicoDtoListResponse = new OrganicoDtoListResponse();

		try {

			organicoDtoListResponse.setEsito(new Esito());
			organicoDtoListResponse.setList(contrattoService.organico());
			httpEntity = new HttpEntity<OrganicoDtoListResponse>(organicoDtoListResponse);
		} catch (Exception e) {
			organicoDtoListResponse.setEsito(new Esito(404, e.getMessage(), new String[] { null }));
			httpEntity = new HttpEntity<OrganicoDtoListResponse>(organicoDtoListResponse);
		}
		return httpEntity;
	}

//	@GetMapping("/contratto-list")
//	public @ResponseBody HttpEntity<ContrattoListResponse> fetchAllContratto() {
//		HttpEntity<ContrattoListResponse> httpEntity;
//
//		ContrattoListResponse contrattoListResponse = new ContrattoListResponse();
//
//		try {
//			List<Contratto> oggetti = contrattoService.listContratto();
//
//			contrattoListResponse.setList(oggetti);
//			contrattoListResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<ContrattoListResponse>(contrattoListResponse);
//		} catch (Exception e) {
//			contrattoListResponse.setEsito(new Esito(404, e.getMessage(), null));
//			httpEntity = new HttpEntity<ContrattoListResponse>(contrattoListResponse);
//		}
//		return httpEntity;
//
//	}

//	@GetMapping("/contratto/{id}")
//	public @ResponseBody HttpEntity<ContrattoResponse> getContrattoById(@PathVariable Integer id) {
//
//		HttpEntity<ContrattoResponse> httpEntity;
//
//		ContrattoResponse contrattoResponse = new ContrattoResponse();
//
//		try {
//			Contratto contratto = contrattoService.getContrattoById(id);
//
//			contrattoResponse.setContratto(contratto);
//			contrattoResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<ContrattoResponse>(contrattoResponse);
//			System.out.println("ciao");
//		} catch (Exception e) {
//			contrattoResponse.setEsito(new Esito(404, e.getMessage(), new String[] { String.valueOf(id) }));
//			httpEntity = new HttpEntity<ContrattoResponse>(contrattoResponse);
//		}
//		return httpEntity;
//	}

//	@PostMapping("/contratto")
//	public @ResponseBody HttpEntity<GenericResponse> saveContratto(@RequestBody ContrattoRequest contrattoRequest) {
//		HttpEntity<GenericResponse> httpEntity;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//			contrattoService.insert(contrattoRequest.getContratto());
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		} catch (Exception e) {
//			genericResponse.setEsito(new Esito(404, e.getMessage(), new String[] { null }));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//		return httpEntity;
//	}

//	@PutMapping("/contratto")
//	public @ResponseBody HttpEntity<GenericResponse> updateContratto(@RequestBody ContrattoRequest contrattoRequest) {
//		HttpEntity<GenericResponse> httpEntity;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//			contrattoService.update(contrattoRequest.getContratto());
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//			System.out.println("ciao");
//		} catch (Exception e) {
//			genericResponse.setEsito(new Esito(404, e.getMessage(), new String[] { null }));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//		return httpEntity;
//	}

//	@DeleteMapping("/contratto/{id}")
//	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer id) {
//
//		HttpEntity<GenericResponse> httpEntity;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//
//			contrattoService.delete(id);
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
