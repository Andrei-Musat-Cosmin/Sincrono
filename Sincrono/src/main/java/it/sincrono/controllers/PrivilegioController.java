package it.sincrono.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Funzione;
import it.sincrono.requests.PrivilegioRequest;
import it.sincrono.responses.FunzioniListResponse;
import it.sincrono.responses.GenericResponse;
import it.sincrono.responses.PrivilegioResponse;
import it.sincrono.services.FunzioneService;
import it.sincrono.services.PrivilegioService;
import it.sincrono.services.costants.ControllerMaps;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class PrivilegioController {

	@Autowired
	private FunzioneService funzioneService;

	@Autowired
	private PrivilegioService privilegioService;

	@RequestMapping(value = "/privilegi", method = RequestMethod.POST, consumes = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> insert(@RequestBody PrivilegioRequest privilegioRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {

			privilegioService.insert(privilegioRequest.getPrivilegio());

			privilegioService.checkPrivilegioFunzionePadre(privilegioRequest.getPrivilegio().getFunzione().getId());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	/**
	 * @param id
	 * @return HttpEntity
	 */
	@RequestMapping(value = "/privilegi/{id}", method = RequestMethod.DELETE, consumes = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer id) {

		HttpEntity<GenericResponse> httpEntity;

		GenericResponse genericResponse = new GenericResponse();

		try {

			privilegioService.delete(id);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		} catch (ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] { String.valueOf(id) }));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	/**
	 * @param id
	 * @return HttpEntity
	 */

	// Tree funzioni che può effettuare utente in base al ruolo
	@RequestMapping(value = "/privilegi/funzioni-ruolo/tree/{id}", method = RequestMethod.GET, produces = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<FunzioniListResponse> treeFunzioniRuolo(@PathVariable("id") Integer id) {

		HttpEntity<FunzioniListResponse> httpEntity = null;

		FunzioniListResponse funzioneListResponse = new FunzioniListResponse();

		try {

			List<Funzione> funzioni = funzioneService.funzioneTree(id);

			funzioneListResponse.setList(funzioni);
			funzioneListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<FunzioniListResponse>(funzioneListResponse);

		} catch (ServiceException e) {
			funzioneListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<FunzioniListResponse>(funzioneListResponse);
		}

		return httpEntity;
	}

	@RequestMapping(value = "/get-id-privilegio", method = RequestMethod.POST, consumes = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<PrivilegioResponse> getIdPrivilegio(
			@RequestBody PrivilegioRequest privilegioRequest) {

		HttpEntity<PrivilegioResponse> httpEntity = null;

		PrivilegioResponse privilegioResponse = new PrivilegioResponse();

		try {

			privilegioResponse.setEsito(new Esito());

			privilegioResponse.setPrivilegio(privilegioService.getIdPrivilegio(privilegioRequest.getPrivilegio()));

			httpEntity = new HttpEntity<PrivilegioResponse>(privilegioResponse);

		} catch (ServiceException e) {
			privilegioResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<PrivilegioResponse>(privilegioResponse);
		}

		return httpEntity;
	}

}
