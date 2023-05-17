package it.sincrono.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Profilo;
import it.sincrono.requests.ProfiloRequest;
import it.sincrono.responses.GenericResponse;
import it.sincrono.responses.ProfiloResponse;
import it.sincrono.services.AnagraficaService;
import it.sincrono.services.ProfiloService;
import it.sincrono.services.costants.ControllerMaps;
import it.sincrono.services.exceptions.ServiceException;

public class ProfiloController {
	
	@Autowired
	private ProfiloService profiloService;
	
	@Autowired
	private AnagraficaService anagraficaService;
	
	@RequestMapping(value = "/profilo/{id}", method = RequestMethod.GET, produces = ControllerMaps.JSON)
    public @ResponseBody HttpEntity<ProfiloResponse> get(@PathVariable("id") Integer Id) {

        HttpEntity<ProfiloResponse> httpEntity = null;

        ProfiloResponse profiloResponse = new ProfiloResponse();

        try {
           
            

            Profilo profilo = profiloService.get(Id);

            profiloResponse.setProfilo(profilo);
            profiloResponse.setEsito(new Esito());

            httpEntity = new HttpEntity<ProfiloResponse>(profiloResponse);

         

        } catch(ServiceException e) {
            profiloResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] {String.valueOf(Id)}));
            httpEntity = new HttpEntity<ProfiloResponse>(profiloResponse);
        }

        return httpEntity;
    }

	@RequestMapping(value = "/profilo", method = RequestMethod.POST, consumes = ControllerMaps.JSON)
    public @ResponseBody HttpEntity<GenericResponse> insert(@RequestBody ProfiloRequest profiloRequest) {

        HttpEntity<GenericResponse> httpEntity = null;

        GenericResponse genericResponse = new GenericResponse();

        try {
         

            profiloService.insert(profiloRequest.getProfilo());

            genericResponse.setEsito(new Esito());

            httpEntity = new HttpEntity<GenericResponse>(genericResponse);

         
            
        } catch(ServiceException e) {
            genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
            httpEntity = new HttpEntity<GenericResponse>(genericResponse);
        }

        return httpEntity;
    }

	@RequestMapping(value = "/profilo", method = RequestMethod.PUT, consumes = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> update(@RequestBody ProfiloRequest profiloRequest) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
		

			profiloService.update(profiloRequest.getProfilo());

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

		

		} catch(ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}

	@RequestMapping(value = "/profilo/{id}", method = RequestMethod.DELETE, consumes = ControllerMaps.JSON)
	public @ResponseBody HttpEntity<GenericResponse> delete(@PathVariable("id") Integer ID) {

		HttpEntity<GenericResponse> httpEntity = null;

		GenericResponse genericResponse = new GenericResponse();

		try {
		

			profiloService.delete(ID);

			genericResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<GenericResponse>(genericResponse);

			

		} catch(ServiceException e) {
			genericResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] {String.valueOf(ID)}));
			httpEntity = new HttpEntity<GenericResponse>(genericResponse);
		}

		return httpEntity;
	}
	
}
