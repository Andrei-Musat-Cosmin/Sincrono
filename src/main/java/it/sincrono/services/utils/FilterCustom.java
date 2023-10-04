package it.sincrono.services.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import it.sincrono.services.utils.DateUtil;

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
public class FilterCustom {

	@Autowired
	ContrattoRepository contrattoRepository;
	@Autowired
	AnagraficaRepository anagraficaRepository;
	@Autowired
	CommessaRepository commessaRepository;

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

	public Boolean checkScaduta(Commessa commessa) {

		Boolean check = false;

		if (DateUtil.convertorDate(commessa.getDataFine()).isBefore(LocalDate.now())) {

			check = true;

		}

		return check;

	}

	public boolean checkCommesseInScadenza(Commessa commessa) {

		boolean check = false;

		LocalDate localDateFine = DateUtil.convertorDate(commessa.getDataFine());

		if (localDateFine.isAfter(LocalDate.now())

				&&

				localDateFine.isBefore(LocalDate.now().plus(40, ChronoUnit.DAYS))) {

			check = true;
		}

		return check;

	}

	public boolean checkListCommesse(List<Commessa> list) {

		return !list.isEmpty();

	}

	public boolean checkContrattiInScadenza(Contratto contratto) {

		boolean check = false;

		if (contratto != null && (contratto.getId() != 2 && contratto.getId() != 4)) {

			LocalDate dataAssunzione = DateUtil.convertorDate(contratto.getDataAssunzione());

			LocalDate dataSommata = dataAssunzione.plusMonths(contratto.getMesiDurata());

			if (dataSommata.isAfter(LocalDate.now())

					&&

					dataSommata.isBefore(LocalDate.now().plus(40, ChronoUnit.DAYS))) {

				check = true;

			}

		}

		return check;

	}

	public Boolean toFilterCommesse(AnagraficaDto anagraficaDto, AnagraficaRequestDto anagraficaRequestDto) {

		if (anagraficaRequestDto == null || anagraficaRequestDto.getAnagraficaDto() == null) {
			return true;

		}

		Anagrafica anagrafica = anagraficaDto.getAnagrafica();
		Anagrafica anagraficaFilter = anagraficaRequestDto.getAnagraficaDto().getAnagrafica();

		if (anagraficaFilter!=null &&  anagrafica != null) {

			if (anagraficaFilter.getCognome() != null
					&& !anagrafica.getCognome().startsWith(anagraficaFilter.getCognome())) {

				return false;

			}
			if (anagraficaFilter.getNome() != null && !anagrafica.getNome().startsWith(anagraficaFilter.getNome())) {

				return false;
			}

			if (anagraficaFilter.getTipoAzienda() != null) {

				if (anagrafica.getTipoAzienda().getId() != null
						&& anagrafica.getTipoAzienda().getId() != anagraficaFilter.getTipoAzienda().getId()) {

					return false;

				}

			}

		}

		if (anagraficaRequestDto.getAnagraficaDto().getCommesse() != null
				&& anagraficaRequestDto.getAnagraficaDto().getCommesse().size() > 0
				&& anagraficaRequestDto.getAnagraficaDto().getCommesse().get(0) != null
				&& anagraficaRequestDto.getAnagraficaDto().getCommesse().get(0).getAziendaCliente() != null) {

			Commessa commessaFilter = anagraficaRequestDto.getAnagraficaDto().getCommesse().get(0);

			List<Commessa> listAppCommesse = new ArrayList<Commessa>();

			for (Commessa commessa : anagraficaDto.getCommesse()) {

				boolean checkCommessa = true;

				if (!commessa.getAziendaCliente()
						.startsWith(anagraficaRequestDto.getAnagraficaDto().getCommesse().get(0).getAziendaCliente())) {

					checkCommessa = false;

				}

				if (anagraficaRequestDto.getAnnoDataFine() != null) {

					if (commessa.getDataFine() != null) {

						if (!DateUtil.compareYear(commessa.getDataFine(), anagraficaRequestDto.getAnnoDataFine())) {

							checkCommessa = false;

						}

						if (anagraficaRequestDto.getMeseDataFine() != null) {

							if (!DateUtil.compareYear(commessa.getDataFine(), anagraficaRequestDto.getMeseDataFine())) {

								checkCommessa = false;

							}

						}

					} else {

						checkCommessa = false;

					}

				}

				if (anagraficaRequestDto.getAnnoDataInizio() != null) {

					if (commessa.getDataInizio() != null) {

						if (!DateUtil.compareYear(commessa.getDataInizio(), anagraficaRequestDto.getAnnoDataInizio())) {

							checkCommessa = false;

						}

						if (anagraficaRequestDto.getMeseDataInizio() != null) {

							if (!DateUtil.compareYear(commessa.getDataInizio(),
									anagraficaRequestDto.getMeseDataInizio())) {

								checkCommessa = false;

							}

						}

					} else {

						checkCommessa = false;

					}

				}

				if (checkCommessa)
					listAppCommesse.add(commessa);

			}

			if (listAppCommesse == null && listAppCommesse.isEmpty()) {

				return true;
			}

			anagraficaDto.setCommesse(listAppCommesse);

		}

		if (anagraficaRequestDto.getAnnoFineContratto() != null) {

		

				if ( anagraficaDto.getContratto() != null && anagraficaDto.getContratto().getDataFineRapporto() != null) {

					if (!DateUtil.compareYear(anagraficaDto.getContratto().getDataFineRapporto(),
							anagraficaRequestDto.getAnnoFineContratto())) {

						return false;

					}

					if (anagraficaRequestDto.getMeseDataFine() != null) {

						if (!DateUtil.compareYear(anagraficaDto.getContratto().getDataFineRapporto(),
								anagraficaRequestDto.getMeseFineContratto())) {

							return false;

						}

					}

				}else {

					return false;

				}

			

		}

		return true;

	}

}
