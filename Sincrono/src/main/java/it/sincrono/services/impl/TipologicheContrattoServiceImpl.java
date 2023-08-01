package it.sincrono.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.sincrono.entities.Ccnl;
import it.sincrono.entities.LivelloContratto;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoContratto;
import it.sincrono.repositories.TipologicheContrattoRepository;
import it.sincrono.services.TipologicheContrattoService;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;

@Service
public class TipologicheContrattoServiceImpl extends BaseServiceImpl implements TipologicheContrattoService {

	@Autowired
	private TipologicheContrattoRepository tipologicheContrattoRepository;

	@Override
	public List<TipoAzienda> getAziendeMap() throws ServiceException {
		List<TipoAzienda> list = null;
		try {
			list = tipologicheContrattoRepository.getAziendeMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;

	}

	@Override
	public List<TipoContratto> getTipoContrattoMap() throws ServiceException {
		List<TipoContratto> list = null;
		try {
			list = tipologicheContrattoRepository.getTipoContrattoMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<Ccnl> getCcnlMap() throws ServiceException {
		List<Ccnl> list = null;
		try {
			list = tipologicheContrattoRepository.getCcnlMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

	@Override
	public List<LivelloContratto> getTipoLivelliContrattualiMap() throws ServiceException {
		List<LivelloContratto> list = null;
		try {
			list = tipologicheContrattoRepository.getTipoLivelliContrattualiMap();
		} catch (Exception e) {
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

		return list;
	}

}