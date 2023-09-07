package it.sincrono.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import it.sincrono.beans.Esito;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoCanaleReclutamento;
import it.sincrono.entities.TipoCausaFineRapporto;
import it.sincrono.entities.TipoCcnl;
import it.sincrono.entities.TipoContratto;
import it.sincrono.entities.TipoLivelloContratto;
import it.sincrono.responses.TipologicheListResponse;
import it.sincrono.services.TipologicheContrattoService;
import it.sincrono.services.exceptions.ServiceException;

@RestController
@CrossOrigin
public class TipologicheContrattoController {

	@Autowired
	private TipologicheContrattoService tipologicheContrattoService;

	@GetMapping("/tipo-azienda-map")
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

	@GetMapping("/tipo-contratto-map")
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

	@GetMapping("/tipo-ccnl-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoCcnl>> getCcnlMap() {

		HttpEntity<TipologicheListResponse<TipoCcnl>> httpEntity = null;

		TipologicheListResponse<TipoCcnl> ccnlListResponse = new TipologicheListResponse<TipoCcnl>();

		try {

			List<TipoCcnl> list = tipologicheContrattoService.getCcnlMap();

			ccnlListResponse.setList(list);
			ccnlListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoCcnl>>(ccnlListResponse);

		} catch (ServiceException e) {
			ccnlListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoCcnl>>(ccnlListResponse);
		}

		return httpEntity;
	}

	@GetMapping("/tipo-livelli-contrattuali-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoLivelloContratto>> getTipoLivelloContrattoMap() {

		HttpEntity<TipologicheListResponse<TipoLivelloContratto>> httpEntity = null;

		TipologicheListResponse<TipoLivelloContratto> livelloContrattoListResponse = new TipologicheListResponse<TipoLivelloContratto>();

		try {

			List<TipoLivelloContratto> list = tipologicheContrattoService.getTipoLivelliContrattualiMap();

			livelloContrattoListResponse.setList(list);
			livelloContrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoLivelloContratto>>(livelloContrattoListResponse);

		} catch (ServiceException e) {
			livelloContrattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoLivelloContratto>>(livelloContrattoListResponse);
		}

		return httpEntity;
	}

	@GetMapping("/tipo-causa-fine-rapporto-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoCausaFineRapporto>> getTipoCausaFineRapportoMap() {

		HttpEntity<TipologicheListResponse<TipoCausaFineRapporto>> httpEntity = null;

		TipologicheListResponse<TipoCausaFineRapporto> livelloContrattoListResponse = new TipologicheListResponse<TipoCausaFineRapporto>();

		try {

			List<TipoCausaFineRapporto> list = tipologicheContrattoService.getTipoCausaFineRapporto();

			livelloContrattoListResponse.setList(list);
			livelloContrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoCausaFineRapporto>>(livelloContrattoListResponse);

		} catch (ServiceException e) {
			livelloContrattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoCausaFineRapporto>>(livelloContrattoListResponse);
		}

		return httpEntity;
	}

	@GetMapping("/tipo-canale-reclutamento-map")
	public @ResponseBody HttpEntity<TipologicheListResponse<TipoCanaleReclutamento>> getTipoCanaleReclutamentoMap() {

		HttpEntity<TipologicheListResponse<TipoCanaleReclutamento>> httpEntity = null;

		TipologicheListResponse<TipoCanaleReclutamento> livelloContrattoListResponse = new TipologicheListResponse<TipoCanaleReclutamento>();

		try {

			List<TipoCanaleReclutamento> list = tipologicheContrattoService.getTipoCanaleReclutamentoMap();

			livelloContrattoListResponse.setList(list);
			livelloContrattoListResponse.setEsito(new Esito());

			httpEntity = new HttpEntity<TipologicheListResponse<TipoCanaleReclutamento>>(livelloContrattoListResponse);

		} catch (ServiceException e) {
			livelloContrattoListResponse.setEsito(new Esito(e.getCode(), e.getMessage(), null));
			httpEntity = new HttpEntity<TipologicheListResponse<TipoCanaleReclutamento>>(livelloContrattoListResponse);
		}

		return httpEntity;
	}
}
