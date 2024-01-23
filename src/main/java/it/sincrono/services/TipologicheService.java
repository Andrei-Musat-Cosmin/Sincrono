package it.sincrono.services;

import java.util.List;

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
import it.sincrono.services.exceptions.ServiceException;

public interface TipologicheService {

	public List<TipoAzienda> getTipoAziendaMap() throws ServiceException;

	public List<TipoAziendaCliente> getTipoAziendaClienteMap() throws ServiceException;

	public List<TipoContratto> getTipoContrattoMap() throws ServiceException;

	public List<TipoCcnl> getTipoCcnlMap() throws ServiceException;

	public List<TipoLivelloContratto> getTipoLivelloContrattoMap() throws ServiceException;

	public List<TipoLivelloContratto> getTipoLivelloContrattoMap(String ccnl) throws ServiceException;

	public List<TipoCanaleReclutamento> getTipoCanaleReclutamentoMap() throws ServiceException;

	public List<TipoCausaFineRapporto> getTipoCausaFineRapportoMap() throws ServiceException;

	public List<TipoCausaFineContratto> getTipoCausaFineContratto() throws ServiceException;
	
	public List<Comune> getComuniMap() throws ServiceException;
	
	public List<Provincia> getProvinceMap() throws ServiceException;

}
