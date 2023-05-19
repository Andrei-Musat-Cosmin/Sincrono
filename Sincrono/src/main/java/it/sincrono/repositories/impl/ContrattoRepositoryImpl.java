package it.sincrono.repositories.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import it.sincrono.repositories.ContrattoCustomRepository;
import it.sincrono.repositories.dto.OrganicoDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import jakarta.persistence.Query;

public class ContrattoRepositoryImpl extends BaseRepositoryImpl implements ContrattoCustomRepository {

	@SuppressWarnings("unchecked")
	@Override
	public List<OrganicoDto> organico() throws RepositoryException {
		try {
			String queryString = SqlStrings.SQL_ORGANICO;

			Query query = entityManager.createNativeQuery(queryString);
			List<Object> list = query.getResultList();
			Long l;
			List<OrganicoDto> organicoDtoList = new ArrayList<OrganicoDto>();

			for (Iterator<Object> it = list.iterator(); it.hasNext();) {
				Object[] object = (Object[]) it.next();

				OrganicoDto organicoDto = new OrganicoDto();

				if (object[0] != null)
					organicoDto.setAzienda((String) object[0]);
				if (object[1] != null)
					organicoDto.setNumeroDipendenti((((Long) object[1])).intValue());
				if (object[2] != null)
					organicoDto.setIndeterminati((((Long) object[2])).intValue());
				if (object[3] != null)
					organicoDto.setDeterminati((((Long) object[3])).intValue());
				if (object[4] != null)
					organicoDto.setApprendistato((((Long) object[4])).intValue());
				if (object[5] != null)
					organicoDto.setConsulenza((((Long) object[5])).intValue());
				if (object[6] != null)
					organicoDto.setStage((((Long) object[6])).intValue());
				if (object[7] != null)
					organicoDto.setPartitaIva((((Long) object[7])).intValue());

				organicoDtoList.add(organicoDto);

			}

			return organicoDtoList;

		} catch (

		Exception e) {
			throw new RepositoryException(e);
		}
	}

}
