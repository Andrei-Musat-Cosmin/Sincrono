package it.sincrono.repositories.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import it.sincrono.entities.Anagrafica;
import it.sincrono.repositories.AnagraficaCustomRepository;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;






public class AnagraficaRepositoryImpl extends BaseRepositoryImpl implements AnagraficaCustomRepository  {
	
	

	@Autowired
	private AnagraficaRepository anagraficaRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Anagrafica> search(AnagraficaDto anagraficaDto) throws RepositoryException {
		List<Anagrafica> list;
		try {

			String queryString = SqlStrings.SQL_LIST_ANAGRAFICA_FILTER;
			String subString = "";

			if (anagraficaDto != null) {

				if (anagraficaDto.getNome() != null) {

					subString = "AND anagrafica.nome LIKE :nome ";
				}
				if (anagraficaDto.getCognome() != null) {
					subString += "AND anagrafica.cognome LIKE :cognome ";
				}
				if (anagraficaDto.getContratto().getRal_annua() != null) {
					subString += "AND anagrafica.contratto.ral_annua LIKE :ral_annua ";
				}
				if (anagraficaDto.getContratto().getData_assunzione()!= null) {
					subString += "AND anagrafica.contratto.data_assunzione LIKE :data_assunzione ";
				}
				if (anagraficaDto.getContratto().getData_fine_rapporto()!= null) {
					subString += "AND anagrafica.contratto.data_fine_rapporto LIKE :data_fine_rapporto ";
				}
				if (anagraficaDto.getContratto().getTipoContratto()!= null) {
					subString += "AND anagrafica.contratto.tipoContratto LIKE :tipoContratto ";
				}
				if (anagraficaDto.getCommessa().getAzienda()!= null) {
					subString += "AND anagrafica.commessa.cliente LIKE :cliente ";
				}
				if (anagraficaDto.getCommessa().getCliente()!= null) {
					subString += "AND anagrafica.commessa.azienda LIKE :azienda ";
				}




			}
			queryString = queryString.replace("{0}", subString);
			Query query = entityManager.createQuery(queryString);
			if (anagraficaDto != null) {
				if (anagraficaDto.getNome() != null) {
					query.setParameter("nome", anagraficaDto.getNome() + "%");
				}
			if (anagraficaDto.getCognome() != null) {
				query.setParameter("cognome", anagraficaDto.getCognome() + "%");
			}
			if (anagraficaDto.getContratto().getRal_annua() != null) {
				query.setParameter("ral_annua", anagraficaDto.getContratto().getRal_annua() + "%");
			}
			if (anagraficaDto.getContratto().getData_assunzione()!= null) {
				query.setParameter("data_assunzione", anagraficaDto.getContratto().getData_assunzione() + "%");
			}
			if (anagraficaDto.getContratto().getData_fine_rapporto()!= null) {
				query.setParameter("data_fine_rapporto", anagraficaDto.getContratto().getData_fine_rapporto()+ "%");
			}
			if (anagraficaDto.getContratto().getTipoContratto()!= null) {
				query.setParameter("tipoContratto", anagraficaDto.getContratto().getTipoContratto() + "%");
			}
			if (anagraficaDto.getCommessa().getAzienda()!= null) {
				query.setParameter("cliente", anagraficaDto.getCommessa().getAzienda() + "%");
			}
			if (anagraficaDto.getCommessa().getCliente()!= null) {
				query.setParameter("azienda", anagraficaDto.getCommessa().getCliente() + "%");
			}
			}

			list = query.getResultList();

			
		} catch (Exception e) {
			throw new RepositoryException(e);
		}

		return list;

	}

}
