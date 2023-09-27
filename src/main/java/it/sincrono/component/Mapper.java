package it.sincrono.component;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.CommessaRepository;
import it.sincrono.repositories.ContrattoRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.requests.AnagraficaRequestDto;

@Component
public class Mapper {

	@Autowired
	ContrattoRepository contrattoRepository;
	@Autowired
	AnagraficaRepository anagraficaRepository;
	@Autowired
	CommessaRepository commessaRepository;

	public AnagraficaDto toAnagraficaDto(Integer id) {
		AnagraficaDto anagraficaDto = new AnagraficaDto();

		Anagrafica anagrafica = anagraficaRepository.findById(id).get();

		anagrafica.getUtente().setPassword(null);
		anagrafica.getUtente().setTokenPassword(null);
		anagrafica.getUtente().setUsername(null);

		anagraficaDto.setAnagrafica(anagrafica);

		anagraficaDto.setContratto(toContratto(id));

		anagraficaDto.setCommesse(toCommessaArray(id));

		return anagraficaDto;
	}

	public AnagraficaDto toAnagraficaDto(String token) {
		AnagraficaDto anagraficaDto = new AnagraficaDto();

		Anagrafica anagrafica = anagraficaRepository.findByToken(token);

		anagrafica.getUtente().setPassword(null);
		anagrafica.getUtente().setTokenPassword(null);
		anagrafica.getUtente().setUsername(null);

		anagraficaDto.setAnagrafica(anagrafica);

		anagraficaDto.setContratto(toContratto(anagrafica.getId()));

		anagraficaDto.setCommesse(toCommessaArray(anagrafica.getId()));

		return anagraficaDto;
	}

	public Contratto toContratto(Integer id) {
		return contrattoRepository.findByIdAnagrafica(id);
	}

	public List<Commessa> toCommessaArray(Integer id) {
		return commessaRepository.findByIdAnagrafica(id);
	}

	public Boolean toFilter(AnagraficaDto anagraficaDto, AnagraficaRequestDto anagraficaRequestDto) {

		if (anagraficaRequestDto == null || anagraficaRequestDto.getAnagraficaDto() == null) {
			return true; // Se non ci sono filtri, return true
		}

		Anagrafica anagrafica = anagraficaDto.getAnagrafica();
		Anagrafica anagraficaFilter = anagraficaRequestDto.getAnagraficaDto().getAnagrafica();

		if (anagraficaFilter != null) {
			if (anagraficaFilter.getNome() != null && !anagrafica.getNome().startsWith(anagraficaFilter.getNome())) {
				return false;
			}
			if (anagraficaFilter.getCognome() != null
					&& !anagrafica.getCognome().startsWith(anagraficaFilter.getCognome())) {
				return false;
			}
			if (anagraficaFilter.getAttesaLavori() != null
					&& anagrafica.getAttesaLavori() != anagraficaFilter.getAttesaLavori()) {
				return false;
			}
			if (anagraficaFilter.getAttivo() != null && anagrafica.getAttivo() != anagraficaFilter.getAttivo()) {
				return false;
			}
		}
		if (anagraficaRequestDto.getAnagraficaDto().getContratto() != null) {
			Contratto contratto = anagraficaDto.getContratto();
			Contratto contrattoFilter = anagraficaRequestDto.getAnagraficaDto().getContratto();

			if (contrattoFilter.getRalAnnua() != null
					&& !contratto.getRalAnnua().toString().startsWith(contrattoFilter.getRalAnnua().toString())) {
				return false;
			}
			if (contrattoFilter.getTipoLivelloContratto() != null && contratto.getTipoLivelloContratto()
					.getId() != contrattoFilter.getTipoLivelloContratto().getId()) {
				return false;
			}
			if (contrattoFilter.getTipoContratto() != null
					&& contratto.getTipoContratto().getId() != contrattoFilter.getTipoContratto().getId()) {
				return false;
			}
			if (contrattoFilter.getTipoCcnl() != null
					&& contratto.getTipoCcnl().getId() != contrattoFilter.getTipoCcnl().getId()) {
				return false;
			}
			if (contrattoFilter.getTipoCanaleReclutamento() != null && contratto.getTipoCanaleReclutamento()
					.getId() != contrattoFilter.getTipoCanaleReclutamento().getId()) {
				return false;
			}
			if (contrattoFilter.getTipoCausaFineRapporto() != null && contratto.getTipoCausaFineRapporto()
					.getId() != contrattoFilter.getTipoCausaFineRapporto().getId()) {
				return false;
			}
		}
		if (anagraficaRequestDto.getAnnoDataInizio() != null) {
			if (anagraficaDto.getContratto().getDataAssunzione().getYear() != anagraficaRequestDto
					.getAnnoDataInizio()) {
				return false;
			}

			if (anagraficaRequestDto.getMeseDataInizio() != null) {
				if (anagraficaDto.getContratto().getDataAssunzione().getMonth() != anagraficaRequestDto
						.getMeseDataInizio()) {
					return false;
				}
			}
		}
		if (anagraficaRequestDto.getAnnoDataFine() != null) {
			if (anagraficaDto.getContratto().getDataFineRapporto().getYear() != anagraficaRequestDto
					.getAnnoDataFine()) {
				return false;
			}
			if (anagraficaRequestDto.getMeseDataFine() != null) {
				if (anagraficaDto.getContratto().getDataFineRapporto().getMonth() != anagraficaRequestDto
						.getMeseDataFine()) {
					return false;
				}
			}
		}
		if (anagraficaRequestDto.getAnagraficaDto().getCommesse() != null
				&& anagraficaRequestDto.getAnagraficaDto().getCommesse().size() > 0
				&& anagraficaRequestDto.getAnagraficaDto().getCommesse().get(0).getAziendaCliente() != null) {
			String aziendaCliente = anagraficaRequestDto.getAnagraficaDto().getCommesse().get(0).getAziendaCliente();
			for (Commessa commessa : anagraficaDto.getCommesse()) {
				if (aziendaCliente != null && !commessa.getAziendaCliente().startsWith(aziendaCliente)) {
					return false;
				}
			}
		}
		return true;
	}
}
