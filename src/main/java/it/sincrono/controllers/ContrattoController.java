package it.sincrono.controllers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.responses.OrganicoDtoListResponse;
import it.sincrono.services.ContrattoService;

@RestController
@CrossOrigin
public class ContrattoController {
	private static final Logger LOGGER = LogManager.getLogger(ContrattoController.class);

	@Autowired
	private ContrattoService contrattoService;

	@GetMapping("/organico")
	public @ResponseBody HttpEntity<OrganicoDtoListResponse> organico() {
		HttpEntity<OrganicoDtoListResponse> httpEntity;

		OrganicoDtoListResponse organicoDtoListResponse = new OrganicoDtoListResponse();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al metodo organico");
			organicoDtoListResponse.setEsito(new Esito());
			organicoDtoListResponse.setList(contrattoService.organico());
			httpEntity = new HttpEntity<OrganicoDtoListResponse>(organicoDtoListResponse);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			organicoDtoListResponse.setEsito(new Esito(501, e.getMessage(), null));
			httpEntity = new HttpEntity<OrganicoDtoListResponse>(organicoDtoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al metodo organico\n");

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
