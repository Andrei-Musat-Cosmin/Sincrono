package it.sincrono.services;

import java.util.List;

import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoCanaleReclutamento;
import it.sincrono.entities.TipoCausaFineRapporto;
import it.sincrono.entities.TipoCcnl;
import it.sincrono.entities.TipoContratto;
import it.sincrono.entities.TipoLivelloContratto;
import it.sincrono.services.exceptions.ServiceException;

public interface TipologicheContrattoService {

	public List<TipoAzienda> getAziendeMap() throws ServiceException;

	public List<TipoContratto> getTipoContrattoMap() throws ServiceException;

	public List<TipoCcnl> getCcnlMap() throws ServiceException;

	public List<TipoLivelloContratto> getTipoLivelliContrattualiMap() throws ServiceException;

	public List<TipoCanaleReclutamento> getTipoCanaleReclutamentoMap() throws ServiceException;

	public List<TipoCausaFineRapporto> getTipoCausaFineRapporto() throws ServiceException;
}
