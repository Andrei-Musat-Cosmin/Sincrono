package it.sincrono.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Commessa;
import it.sincrono.responses.CommessaListResponse;
import it.sincrono.services.StoricoCommesseService;

@RestController
@CrossOrigin
public class StoricoCommesseController {

	@Autowired
	private StoricoCommesseService storicoCommesseService;

	@GetMapping("/storico-commesse/{id}")
	public @ResponseBody HttpEntity<CommessaListResponse> getStoricoCommesseByAnagrafica(@PathVariable Integer id) {

		HttpEntity<CommessaListResponse> httpEntity;

		CommessaListResponse commessaListResponse = new CommessaListResponse();

		try {
			System.out.println("\nInizio chiamata al metodo getStoricoCommesseByAnagrafica");

			List<Commessa> storicoCommesse = storicoCommesseService.getStoricoCommesseByAnagrafica(id);

			commessaListResponse.setList(storicoCommesse);
			commessaListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<CommessaListResponse>(commessaListResponse);

		} catch (Exception e) {
			commessaListResponse.setEsito(new Esito(501, e.getMessage(), null));
			httpEntity = new HttpEntity<CommessaListResponse>(commessaListResponse);
		}
		System.out.println("Fine chiamata al metodo getStoricoCommesseByAnagrafica\n");

		return httpEntity;
	}

//	@GetMapping("/storico-commesse")
//	public @ResponseBody HttpEntity<StoricoCommesseListResponse> fetchAllStoricoCommesse() {
//		HttpEntity<StoricoCommesseListResponse> httpEntity;
//
//		StoricoCommesseListResponse storicoCommesseListResponse = new StoricoCommesseListResponse();
//
//		try {
//			List<StoricoCommesse> commesse = storicoCommesseService.listStoricoCommesse();
//
//			storicoCommesseListResponse.setList(commesse);
//			storicoCommesseListResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<StoricoCommesseListResponse>(storicoCommesseListResponse);
//
//		} catch (Exception e) {
//			storicoCommesseListResponse.setEsito(new Esito(404, e.getMessage(), null));
//			httpEntity = new HttpEntity<StoricoCommesseListResponse>(storicoCommesseListResponse);
//		}
//		return httpEntity;
//
//	}

//	@GetMapping("/storico-commesse/{id}")
//	public @ResponseBody HttpEntity<StoricoCommesseResponse> getStoricoCommesseById(@PathVariable Integer id) {
//
//		HttpEntity<StoricoCommesseResponse> httpEntity;
//
//		StoricoCommesseResponse storicoCommesseResponse = new StoricoCommesseResponse();
//
//		try {
//			StoricoCommesse storicoCommesse = storicoCommesseService.getStoricoCommesseById(id);
//
//			storicoCommesseResponse.setStoricoCommesse(storicoCommesse);
//			storicoCommesseResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<StoricoCommesseResponse>(storicoCommesseResponse);
//
//		} catch (Exception e) {
//			storicoCommesseResponse.setEsito(new Esito(404, e.getMessage(), new String[] { String.valueOf(id) }));
//			httpEntity = new HttpEntity<StoricoCommesseResponse>(storicoCommesseResponse);
//		}
//		return httpEntity;
//	}

//	@PostMapping("/storico-commesse")
//	public @ResponseBody HttpEntity<GenericResponse> saveStoricoCommesse(
//			@RequestBody StoricoCommesseRequest storicoCommesseRequest) {
//		HttpEntity<GenericResponse> httpEntity;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//			storicoCommesseService.insert(storicoCommesseRequest.getStoricoCommesse());
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

//	@PutMapping("/storico-commesse")
//	public @ResponseBody HttpEntity<GenericResponse> updateStoricoCommesse(
//			@RequestBody StoricoCommesseRequest storicoCommesseRequest) {
//		HttpEntity<GenericResponse> httpEntity;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//			storicoCommesseService.update(storicoCommesseRequest.getStoricoCommesse());
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

//	@DeleteMapping("/storico-commesse/{id}")
//	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer id) {
//
//		HttpEntity<GenericResponse> httpEntity;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//
//			storicoCommesseService.delete(id);
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
