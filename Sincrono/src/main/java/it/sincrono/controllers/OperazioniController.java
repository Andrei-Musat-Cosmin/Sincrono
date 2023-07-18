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
import it.sincrono.entities.Operazione;
import it.sincrono.responses.OperazioniListResponse;
import it.sincrono.services.OperazioniService;
import it.sincrono.services.exceptions.ServiceException;


@RestController
@CrossOrigin
public class OperazioniController {
	
	@Autowired
	private OperazioniService operazioniService;

	@GetMapping("/operazioni/{id}")
	public @ResponseBody HttpEntity<OperazioniListResponse> get(@PathVariable("id") Integer ID) {

		HttpEntity<OperazioniListResponse> httpEntity = null;

	
		OperazioniListResponse operazioniListResponse = new OperazioniListResponse();

		try {

			List<Operazione> list = operazioniService.getOperazioniByFunzioni(ID);
			
			
			operazioniListResponse.setList(list);
			
			operazioniListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<OperazioniListResponse>(operazioniListResponse);

		} catch (ServiceException e) {
			operazioniListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), new String[] { String.valueOf(ID) }));
			httpEntity = new HttpEntity<OperazioniListResponse>(operazioniListResponse);
		}

		return httpEntity;
	}


}
