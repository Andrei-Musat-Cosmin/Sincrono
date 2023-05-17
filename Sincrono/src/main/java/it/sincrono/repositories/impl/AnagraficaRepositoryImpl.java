package it.sincrono.repositories.impl;
import java.util.List;
import it.sincrono.entities.Anagrafica;
import it.sincrono.repositories.AnagraficaCustomRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;






public class AnagraficaRepositoryImpl extends BaseRepositoryImpl implements AnagraficaCustomRepository  {
	
	

	/*@SuppressWarnings("unchecked")
	@Override
	public List<Object> search(AnagraficaDto anagraficaDto) throws RepositoryException {
		List<AnagraficaDto> list;
		try {

			String queryString = SqlStrings.SQL_LIST_ANAGRAFICA_FILTER;
			String subString = "";

			if (anagraficaDto != null) {

				if (anagraficaDto.getNome() != null) {

					subString = "AND a.nome LIKE '"+anagraficaDto.getNome()+"'";
				}
				if (anagraficaDto.getCognome() != null) {
					subString += "AND a.cognome LIKE '"+anagraficaDto.getCognome()+"'";
				}
				if (anagraficaDto.getContratto().getRal_annua() != null) {
					subString += "AND c.ral_annua LIKE '"+anagraficaDto.getContratto().getRal_annua()+"'";
				}
				if (anagraficaDto.getContratto().getData_assunzione()!= null) {
					subString += "AND c.data_assunzione LIKE '"+anagraficaDto.getContratto().getData_assunzione()+"'";
				}
				if (anagraficaDto.getContratto().getData_fine_rapporto()!= null) {
					subString += "AND c.data_fine_rapporto LIKE '"+anagraficaDto.getContratto().getData_fine_rapporto()+"'";
				}
				if (anagraficaDto.getContratto().getLivelloContratto().getDescrizione()!= null) {
					subString += "AND g.descrizione LIKE  '"+anagraficaDto.getContratto().getLivelloContratto().getDescrizione()+"'";
				}
				if (anagraficaDto.getCommessa().getAzienda()!= null) {
					subString += "AND t.cliente LIKE '"+anagraficaDto.getCommessa().getAzienda()+"'";
				}
				if (anagraficaDto.getCommessa().getCliente()!= null) {
					subString += "AND t.azienda LIKE '"+anagraficaDto.getCommessa().getCliente()+"'";
				}
			}
			queryString = queryString.replace("{0}", subString);
			Query query = entityManager.createNativeQuery(queryString);
			
			List<Object> listFilter = query.getResultList();
			
			
			return listFilter;
				
				
			

			
		} catch (Exception e) {
			throw new RepositoryException(e);
		}

		return listFilter;

	}*/

}
