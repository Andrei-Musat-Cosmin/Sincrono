package it.sincrono.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Comune;
import it.sincrono.entities.Provincia;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoAziendaCliente;
import it.sincrono.entities.TipoCanaleReclutamento;
import it.sincrono.entities.TipoCausaFineContratto;
import it.sincrono.entities.TipoCausaFineRapporto;
import it.sincrono.entities.TipoCcnl;
import it.sincrono.entities.TipoContratto;
import it.sincrono.entities.TipoLivelloContratto;
import it.sincrono.repositories.TipologicheRepository;
import it.sincrono.services.TipologicheService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class TipologicheServiceImpl extends BaseServiceImpl implements TipologicheService {

	@Autowired
	private TipologicheRepository tipologicheRepository;

	@Override
	public List<TipoAzienda> getTipoAziendaMap() throws ServiceException {
		List<TipoAzienda> list = null;
		try {
			list = tipologicheRepository.getTipoAziendaMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;

	}

	@Override
	public List<TipoAziendaCliente> getTipoAziendaClienteMap() throws ServiceException {
		List<TipoAziendaCliente> list = null;
		try {
			list = tipologicheRepository.getTipoAziendaClienteMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;

	}

	@Override
	public List<TipoContratto> getTipoContrattoMap() throws ServiceException {
		List<TipoContratto> list = null;
		try {
			list = tipologicheRepository.getTipoContrattoMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<TipoCcnl> getTipoCcnlMap() throws ServiceException {
		List<TipoCcnl> list = null;
		try {
			list = tipologicheRepository.getTipoCcnlMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<TipoLivelloContratto> getTipoLivelloContrattoMap() throws ServiceException {
		List<TipoLivelloContratto> list = null;
		try {
			list = tipologicheRepository.getTipoLivelloContrattoMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<TipoLivelloContratto> getTipoLivelloContrattoMap(String ccnl) throws ServiceException {
		List<TipoLivelloContratto> list = null;
		try {
			list = tipologicheRepository.getTipoLivelloContrattoMap(ccnl);
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<TipoCanaleReclutamento> getTipoCanaleReclutamentoMap() throws ServiceException {
		List<TipoCanaleReclutamento> list = null;
		try {
			list = tipologicheRepository.getTipoCanaleReclutamentoMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<TipoCausaFineRapporto> getTipoCausaFineRapportoMap() throws ServiceException {
		List<TipoCausaFineRapporto> list = null;
		try {
			list = tipologicheRepository.getTipoCausaFineRapportoMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<Comune> getComuniMap() throws ServiceException {
		List<Comune> list = null;
		try {
			list = tipologicheRepository.getComuniMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;

	}

	@Override
	public List<Provincia> getProvinceMap() throws ServiceException {
		List<Provincia> list = null;
		try {
			list = tipologicheRepository.getProvinceMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;

	}

	@Override
	public List<TipoCausaFineContratto> getTipoCausaFineContratto() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

}