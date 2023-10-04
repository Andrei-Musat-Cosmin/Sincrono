package it.sincrono.controllers;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.responses.ContrattoListResponse;
import it.sincrono.services.StoricoContrattiService;

@RestController
@CrossOrigin
public class StoricoContrattiController {
	private static final Logger LOGGER = LogManager.getLogger(StoricoContrattiController.class);

	@Autowired
	private StoricoContrattiService storicoContrattiService;

	@GetMapping("/storico-contratti/{id}")
	public @ResponseBody HttpEntity<ContrattoListResponse> getStoricoContratti(@PathVariable("id") Integer id) {

		HttpEntity<ContrattoListResponse> httpEntity;

		ContrattoListResponse contrattoListResponse = new ContrattoListResponse();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al metodo getStoricoContratti");

			contrattoListResponse.setList(storicoContrattiService.getStoricoContratti(id));
			contrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<ContrattoListResponse>(contrattoListResponse);

		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			contrattoListResponse.setEsito(new Esito(501, e.getMessage(), null));
			httpEntity = new HttpEntity<ContrattoListResponse>(contrattoListResponse);
		}
		LOGGER.log(Level.INFO, "Fine chiamata al metodo getStoricoContratti\n");

		return httpEntity;
	}

//	@GetMapping("/storico-contratti/{id}")
//	public @ResponseBody HttpEntity<StoricoContrattiResponse> getStoricoContrattiById(@PathVariable Integer id) {
//
//		HttpEntity<StoricoContrattiResponse> httpEntity;
//
//		StoricoContrattiResponse storicoContrattiResponse = new StoricoContrattiResponse();
//
//		try {
//			StoricoContratti storicoContratti = storicoContrattiService.getStoricoContrattiById(id);
//
//			storicoContrattiResponse.setStoricoContratti(storicoContratti);
//			storicoContrattiResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<StoricoContrattiResponse>(storicoContrattiResponse);
//		} catch (Exception e) {
//			storicoContrattiResponse.setEsito(new Esito(404, e.getMessage(), new String[] { String.valueOf(id) }));
//			httpEntity = new HttpEntity<StoricoContrattiResponse>(storicoContrattiResponse);
//		}
//		return httpEntity;
//	}

//	@GetMapping("/storico-contratti")
//	public @ResponseBody HttpEntity<StoricoContrattiListResponse> fetchAllStoricoContratti() {
//		HttpEntity<StoricoContrattiListResponse> httpEntity;
//
//		StoricoContrattiListResponse storicoContrattiListResponse = new StoricoContrattiListResponse();
//
//		try {
//			List<StoricoContratti> contratti = storicoContrattiService.listStoricoContratti();
//
//			storicoContrattiListResponse.setList(contratti);
//			storicoContrattiListResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<StoricoContrattiListResponse>(storicoContrattiListResponse);
//		} catch (Exception e) {
//			storicoContrattiListResponse.setEsito(new Esito(404, e.getMessage(), null));
//			httpEntity = new HttpEntity<StoricoContrattiListResponse>(storicoContrattiListResponse);
//		}
//		return httpEntity;
//
//	}

//	@PostMapping("/storico-contratti")
//	public @ResponseBody HttpEntity<GenericResponse> saveStoricoContratti(
//			@RequestBody StoricoContrattiRequest storicoContrattiRequest) {
//		HttpEntity<GenericResponse> httpEntity;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//			storicoContrattiService.insert(storicoContrattiRequest.getStoricoContratti());
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		} catch (Exception e) {
//			genericResponse.setEsito(new Esito(404, e.getMessage(), new String[] { null }));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//		return httpEntity;
//	}

//	@PutMapping("/storico-Contratti")
//	public @ResponseBody HttpEntity<GenericResponse> updateStoricoContratti(
//			@RequestBody StoricoContrattiRequest storicoContrattiRequest) {
//		HttpEntity<GenericResponse> httpEntity;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//			storicoContrattiService.update(storicoContrattiRequest.getStoricoContratti());
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		} catch (Exception e) {
//			genericResponse.setEsito(new Esito(404, e.getMessage(), new String[] { null }));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//		return httpEntity;
//	}

//	@DeleteMapping("/storico-contratti/{id}")
//	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer id) {
//
//		HttpEntity<GenericResponse> httpEntity;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//
//			storicoContrattiService.delete(id);
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
