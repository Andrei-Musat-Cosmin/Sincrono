package it.sincrono.repositories.impl;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.ContrattoNazionale;
import it.sincrono.entities.LivelloContratto;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoContratto;
import it.sincrono.repositories.AnagraficaCustomRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;






public class AnagraficaRepositoryImpl extends BaseRepositoryImpl implements AnagraficaCustomRepository  {
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<AnagraficaDto> search(AnagraficaDto anagraficaDto) throws RepositoryException {
		List<AnagraficaDto> list;
		try {

			String queryString = SqlStrings.SQL_LIST_ANAGRAFICA_FILTER;
			String subString = "";

			if (anagraficaDto != null) {

				if (anagraficaDto.getNome() != null) {

					subString = "AND a.nome LIKE '"+anagraficaDto.getNome()+"'";
				}
				if (anagraficaDto.getCognome() != null) {
					subString += " AND a.cognome LIKE '"+anagraficaDto.getCognome()+"'";
				}
				if(anagraficaDto.getContratto()!=null) {
					
					if (anagraficaDto.getContratto().getRalAnnua() != null) {
						subString += " AND c.ral_annua LIKE '"+anagraficaDto.getContratto().getRalAnnua()+"'";
					}
					if (anagraficaDto.getContratto().getDataAssunzione()!= null) {
						subString += " AND c.data_assunzione LIKE '"+anagraficaDto.getContratto().getDataAssunzione()+"'";
					}
					if (anagraficaDto.getContratto().getDataFineRapporto()!= null) {
						subString += " AND c.data_fine_rapporto LIKE '"+anagraficaDto.getContratto().getDataFineRapporto()+"'";
					}
					if (anagraficaDto.getContratto().getLivelloContratto().getDescrizione()!= null) {
						subString += " AND g.descrizione LIKE  '"+anagraficaDto.getContratto().getLivelloContratto().getDescrizione()+"'";
					}
					
					
				}
				
				if(anagraficaDto.getCommessa()!=null){

					if (anagraficaDto.getCommessa().getAzienda()!= null) {
						subString += "AND t.cliente LIKE '"+anagraficaDto.getCommessa().getAzienda()+"'";
					}
					if (anagraficaDto.getCommessa().getCliente()!= null) {
						subString += "AND t.azienda LIKE '"+anagraficaDto.getCommessa().getCliente()+"'";
					}
					
					
				}
				
			
			}
			queryString = queryString.replace("{0}", subString);
			Query query = entityManager.createNativeQuery(queryString);
			
			List<Object> listFilter = query.getResultList();
			
			List<AnagraficaDto> listAnagraficaDto = new ArrayList<AnagraficaDto>();
		
			for (Iterator<Object> it = listFilter.iterator(); it.hasNext();) {
				Object[] object = (Object[]) it.next();
				
					
					AnagraficaDto anagraficDtoFilter= new AnagraficaDto();
					anagraficDtoFilter.setContratto(new Contratto(new LivelloContratto()));
					anagraficDtoFilter.setCommessa(new Commessa());
					
					if(object[1]!=null)anagraficDtoFilter.setNome((String)object[1]);
					if(object[2]!=null)anagraficDtoFilter.setCognome((String)object[2]);
					if(object[3]!=null)anagraficDtoFilter.getCommessa().setAzienda((String)object[3]);
					if(object[4]!=null)anagraficDtoFilter.getContratto().getLivelloContratto().setDescrizione((String)object[4]);
					if(object[5]!=null)
						anagraficDtoFilter.getContratto().setDataAssunzione(new SimpleDateFormat("dd/MM/yyyy").parse((String)object[5]));
					if(object[6]!=null)
						anagraficDtoFilter.getContratto().setDataFineRapporto(new SimpleDateFormat("dd/MM/yyyy").parse((String)object[6]));
					if(object[7]!=null)
						anagraficDtoFilter.getContratto().setRalAnnua((String)object[7]);
					if(object[8]!=null)
						anagraficDtoFilter.getCommessa().setCliente((String)object[8]);
					
					
					listAnagraficaDto.add(anagraficDtoFilter);

			}
	

			return listAnagraficaDto;
				
				
			

			
		} catch (Exception e) {
			throw new RepositoryException(e);
		}

		
	}

}
