package it.sincrono.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.services.ProfiloService;

/**
 * POSSIBILE CANCELLAZIONE
 */

@RestController
@CrossOrigin
public class ProfiloController {

	@Autowired
	private ProfiloService profiloService;

//	@GetMapping("/profilo/{id}")
//	public @ResponseBody HttpEntity<ProfiloResponse> get(@PathVariable("id") Integer id) {
//
//		HttpEntity<ProfiloResponse> httpEntity = null;
//
//		ProfiloResponse profiloResponse = new ProfiloResponse();
//
//		try {
//
//			Profilo profilo = profiloService.get(id);
//
//			profiloResponse.setProfilo(profilo);
//			profiloResponse.setEsito(new Esito());
//
//			httpEntity = new HttpEntity<ProfiloResponse>(profiloResponse);
//
//		} catch (ServiceException e) {
//			profiloResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] { String.valueOf(id) }));
//			httpEntity = new HttpEntity<ProfiloResponse>(profiloResponse);
//		}
//
//		return httpEntity;
//	}

//	@PostMapping("/profilo")
//	public @ResponseBody HttpEntity<GenericResponse> insert(@RequestBody ProfiloRequest profiloRequest) {
//
//		HttpEntity<GenericResponse> httpEntity = null;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//
//			profiloService.insert(profiloRequest.getProfilo());
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

//	@PutMapping("/profilo")
//	public @ResponseBody HttpEntity<GenericResponse> update(@RequestBody ProfiloRequest profiloRequest) {
//
//		HttpEntity<GenericResponse> httpEntity = null;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//
//			profiloService.update(profiloRequest.getProfilo());
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

//	@DeleteMapping("/profilo/{id}")
//	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer ID) {
//
//		HttpEntity<GenericResponse> httpEntity = null;
//
//		GenericResponse genericResponse = new GenericResponse();
//
//		try {
//
//			profiloService.delete(ID);
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

}
