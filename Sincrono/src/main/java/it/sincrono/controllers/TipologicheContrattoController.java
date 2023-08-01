package it.sincrono.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.entities.Ccnl;
import it.sincrono.entities.LivelloContratto;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoContratto;
import it.sincrono.responses.TipologicheListResponse;
import it.sincrono.services.TipologicheContrattoService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class TipologicheContrattoController {

	@Autowired
	private TipologicheContrattoService tipologicheContrattoService;

	@GetMapping("/tipo-azienda/map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoAzienda>> getAziendeMap() {

		HttpEntity<TipologicheListResponse<TipoAzienda>> httpEntity = null;

		TipologicheListResponse<TipoAzienda> aziendeListResponse = new TipologicheListResponse<TipoAzienda>();

		try {

			List<TipoAzienda> list = tipologicheContrattoService.getAziendeMap();

			aziendeListResponse.setList(list);
			aziendeListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoAzienda>>(aziendeListResponse);

		} catch (ServiceException e) {
			aziendeListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoAzienda>>(aziendeListResponse);
		}

		return httpEntity;
	}

	@GetMapping("/tipo-contratto/map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoContratto>> getTipoContrattoMap() {

		HttpEntity<TipologicheListResponse<TipoContratto>> httpEntity = null;

		TipologicheListResponse<TipoContratto> tipoCotnrattoListResponse = new TipologicheListResponse<TipoContratto>();

		try {

			List<TipoContratto> list = tipologicheContrattoService.getTipoContrattoMap();

			tipoCotnrattoListResponse.setList(list);
			tipoCotnrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoContratto>>(tipoCotnrattoListResponse);

		} catch (ServiceException e) {
			tipoCotnrattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoContratto>>(tipoCotnrattoListResponse);
		}

		return httpEntity;
	}

	@GetMapping("/tipo-ccnl/map")
	public @ResponseBody HttpEntity<TipologicheListResponse<Ccnl>> getCcnlMap() {

		HttpEntity<TipologicheListResponse<Ccnl>> httpEntity = null;

		TipologicheListResponse<Ccnl> ccnlListResponse = new TipologicheListResponse<Ccnl>();

		try {

			List<Ccnl> list = tipologicheContrattoService.getCcnlMap();

			ccnlListResponse.setList(list);
			ccnlListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<Ccnl>>(ccnlListResponse);

		} catch (ServiceException e) {
			ccnlListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<Ccnl>>(ccnlListResponse);
		}

		return httpEntity;
	}

	@GetMapping("/tipo-livelli-contrattuali/map")
	public @ResponseBody HttpEntity<TipologicheListResponse<LivelloContratto>> getTipoLivelloContrattoMap() {

		HttpEntity<TipologicheListResponse<LivelloContratto>> httpEntity = null;

		TipologicheListResponse<LivelloContratto> livelloContrattoListResponse = new TipologicheListResponse<LivelloContratto>();

		try {

			List<LivelloContratto> list = tipologicheContrattoService.getTipoLivelliContrattualiMap();

			livelloContrattoListResponse.setList(list);
			livelloContrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<LivelloContratto>>(livelloContrattoListResponse);

		} catch (ServiceException e) {
			livelloContrattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<LivelloContratto>>(livelloContrattoListResponse);
		}

		return httpEntity;
	}
}
