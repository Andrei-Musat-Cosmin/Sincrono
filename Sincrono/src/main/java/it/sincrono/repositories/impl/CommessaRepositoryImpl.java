package it.sincrono.repositories.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.ContrattoNazionale;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoContratto;
import it.sincrono.repositories.CommessaCustomRepository;
import it.sincrono.repositories.dto.CommessaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;

public class CommessaRepositoryImpl extends BaseRepositoryImpl implements CommessaCustomRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<CommessaDto> dashboard() throws RepositoryException {
		try {

			String queryString = SqlStrings.SQL_DASHBOARD;

			Query query = entityManager.createNativeQuery(queryString);

			List<Object> list = query.getResultList();
			List<CommessaDto> commessaDtoList = new ArrayList<CommessaDto>();

			for (Iterator<Object> it = list.iterator(); it.hasNext();) {

				Object[] object = (Object[]) it.next();

				CommessaDto commessaDto = new CommessaDto();
				commessaDto.setCommessa(new Commessa());
				commessaDto
						.setContratto(new Contratto(new TipoContratto(), new TipoAzienda(), new ContrattoNazionale()));

				if (object[0] != null)
					commessaDto.getCommessa().setNominativo((String) object[0]);
				if (object[1] != null)
					commessaDto.getContratto().getTipoContratto().setDescrizione((String) object[1]);
				if (object[2] != null)
					commessaDto.getContratto().getTipoAzienda().setDescrizione((String) object[2]);
				if (object[3] != null)
					commessaDto.getContratto().getContrattoNazionale().setDescrizione((String) object[3]);
				if (object[4] != null)
					commessaDto.getCommessa()
							.setDataInizio(new SimpleDateFormat("yyyy-MM-dd").parse((String) object[4]));
				if (object[5] != null)
					commessaDto.getCommessa().setDataFine(new SimpleDateFormat("yyyy-MM-dd").parse((String) object[5]));
				if (object[6] != null)
					commessaDto.getContratto().setMesiDurata((Integer) object[6]);

				commessaDtoList.add(commessaDto);
			}
			return commessaDtoList;

		} catch (Exception e) {
			throw new RepositoryException(e);
		}

	}
}