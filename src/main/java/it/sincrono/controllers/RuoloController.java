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
import it.sincrono.entities.Ruolo;
import it.sincrono.requests.RuoloRequest;
import it.sincrono.responses.GenericResponse;
import it.sincrono.responses.RuoloListResponse;
import it.sincrono.responses.RuoloResponse;
import it.sincrono.services.RuoloService;
import it.sincrono.services.exceptions.ServiceException;


/**
 * POSSIBILE CANCELLAZIONE
 */

@RestController
@CrossOrigin
public class RuoloController {

	@Autowired
	private RuoloService ruoloService;

//	@GetMapping("/ruolo/{id}")
//	public @ResponseBody HttpEntity<RuoloResponse> get(@PathVariable("id") Integer ID) {
//
//		HttpEntity<RuoloResponse> httpEntity = null;
//
//		RuoloResponse ruoloResponse = new RuoloResponse();
//
//		try {
//
//			Ruolo ruolo = ruoloService.get(ID);
//
//			ruoloResponse.setRuolo(ruolo);
//			ruoloResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<RuoloResponse>(ruoloResponse);
//
//		} catch (ServiceException e) {
//			ruoloResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] { String.valueOf(ID) }));
//			httpEntity = new HttpEntity<RuoloResponse>(ruoloResponse);
//		}
//
//		return httpEntity;
//	}
//
//	@GetMapping("/get-ruolo-utente/{username}")
//	public @ResponseBody HttpEntity<RuoloResponse> getRuoloByUsername(@PathVariable("username") String username) {
//
//		HttpEntity<RuoloResponse> httpEntity = null;
//
//		RuoloResponse ruoloResponse = new RuoloResponse();
//
//		try {
//
//			Integer idRuolo = ruoloService.getRuoloByUsername(username);
//
//			Ruolo ruolo = ruoloService.get(idRuolo);
//
//			ruoloResponse.setRuolo(ruolo);
//			ruoloResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<RuoloResponse>(ruoloResponse);
//
//		} catch (ServiceException e) {
//			ruoloResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<RuoloResponse>(ruoloResponse);
//		}
//
//		return httpEntity;
//	}
//
//	@PostMapping("/ruolo")
//	public @ResponseBody HttpEntity<GenericResponse> insert(@RequestBody RuoloRequest ruoloRequest) {
//
//		HttpEntity<GenericResponse> httpEntity = null;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//
//			ruoloService.insert(ruoloRequest.getRuolo());
//
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//
//		} catch (ServiceException e) {
//			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//
//		return httpEntity;
//	}
//
//	@PutMapping("/ruolo")
//	public @ResponseBody HttpEntity<GenericResponse> update(@RequestBody RuoloRequest ruoloRequest) {
//
//		HttpEntity<GenericResponse> httpEntity = null;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//
//			ruoloService.update(ruoloRequest.getRuolo());
//
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//
//		} catch (ServiceException e) {
//			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//
//		return httpEntity;
//	}
//
//	@DeleteMapping("/ruolo/{id}")
//	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer ID) {
//
//		HttpEntity<GenericResponse> httpEntity = null;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//
//			ruoloService.delete(ID);
//
//			genericResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//
//		} catch (ServiceException e) {
//			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] { String.valueOf(ID) }));
//			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
//		}
//
//		return httpEntity;
//	}
//
//	@GetMapping("/ruoli/tree")
//	public @ResponseBody HttpEntity<RuoloListResponse> tree() {
//
//		HttpEntity<RuoloListResponse> httpEntity = null;
//
//		RuoloListResponse ruoloListResponse = new RuoloListResponse();
//
//		try {
//
//			List<Ruolo> ruoli = ruoloService.tree();
//
//			ruoloListResponse.setList(ruoli);
//			ruoloListResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<RuoloListResponse>(ruoloListResponse);
//
//		} catch (ServiceException e) {
//			ruoloListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
//			httpEntity = new HttpEntity<RuoloListResponse>(ruoloListResponse);
//		}
//
//		return httpEntity;
//	}
//
	@GetMapping("/ruoli-map")
	public @ResponseBody HttpEntity<RuoloListResponse> map() {

		HttpEntity<RuoloListResponse> httpEntity = null;

		RuoloListResponse ruoloListResponse = new RuoloListResponse();

		try {
			System.out.println("\nInizio chiamata al metodo map");

			List<Ruolo> ruoli = ruoloService.map();

			ruoloListResponse.setList(ruoli);
			ruoloListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<RuoloListResponse>(ruoloListResponse);

		} catch (ServiceException e) {
			ruoloListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<RuoloListResponse>(ruoloListResponse);
		}
		System.out.println("Fine chiamata al metodo map\n");

		return httpEntity;
	}
}
