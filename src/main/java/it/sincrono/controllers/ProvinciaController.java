package it.sincrono.controllers;

import java.util.List;

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
import it.sincrono.entities.Provincia;
import it.sincrono.responses.TipologicheListResponse;
import it.sincrono.services.ProvinciaService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class ProvinciaController {

	private static final Logger LOGGER = LogManager.getLogger(TipologicheController.class);

	@Autowired
	private ProvinciaService provinciaService;

	@GetMapping("/province-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<Provincia>> getProvinceMap() {

		HttpEntity<TipologicheListResponse<Provincia>> httpEntity = null;

		TipologicheListResponse<Provincia> comuniListResponse = new TipologicheListResponse<Provincia>();

		try {
			LOGGER.log(Level.INFO, "Inizio chiamata al meotodo getComuniMap");

			List<Provincia> list = provinciaService.getProvinceMap();

			comuniListResponse.setList(list);
			comuniListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<Provincia>>(comuniListResponse);

		} catch (ServiceException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			comuniListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<Provincia>>(comuniListResponse);

		}
		LOGGER.log(Level.INFO, "Fine chiamata al meotodo getComuniMap\n");

		return httpEntity;

	}
}
