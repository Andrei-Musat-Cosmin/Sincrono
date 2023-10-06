package it.sincrono.services.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
			if (anagraficaFilter.getNome() != null && anagrafica.getNome() != null
					&& !anagrafica.getNome().toLowerCase().startsWith(anagraficaFilter.getNome().toLowerCase())) {
				return false;
			}
			if (anagraficaFilter.getCognome() != null && anagrafica.getCognome() != null
					&& !anagrafica.getCognome().toLowerCase().startsWith(anagraficaFilter.getCognome().toLowerCase())) {
				return false;
			}
			if (anagraficaFilter.getAttesaLavori() != null && anagrafica.getAttesaLavori() != null
					&& anagrafica.getAttesaLavori() != anagraficaFilter.getAttesaLavori()) {
				return false;
			}
			if (anagraficaFilter.getAttivo() != null && anagrafica.getAttivo() != null
					&& anagrafica.getAttivo() != anagraficaFilter.getAttivo()) {
				return false;
			}
		}
		if (anagraficaRequestDto.getAnagraficaDto().getContratto() != null) {
			Contratto contratto = anagraficaDto.getContratto();
			Contratto contrattoFilter = anagraficaRequestDto.getAnagraficaDto().getContratto();
			if (contratto != null) {
				if (contrattoFilter.getRalAnnua() != null) {
					if (contratto.getRalAnnua() == null || Double.compare(contratto.getRalAnnua().doubleValue(),
							contrattoFilter.getRalAnnua().doubleValue()) != 0) {
						return false;
					}
				}

				if (contrattoFilter.getTipoLivelloContratto() != null) {
					if (contratto.getTipoLivelloContratto() == null || contratto.getTipoLivelloContratto()
							.getId() != contrattoFilter.getTipoLivelloContratto().getId()) {
						return false;
					}

				}
				if (contrattoFilter.getTipoContratto() != null) {
					if (contratto.getTipoContratto() == null
							|| contratto.getTipoContratto().getId() != contrattoFilter.getTipoContratto().getId()) {
						return false;
					}
				}

				if (contrattoFilter.getTipoCcnl() != null) {
					if (contratto.getTipoCcnl() == null
							|| contratto.getTipoCcnl().getId() != contrattoFilter.getTipoCcnl().getId()) {
						return false;
					}
				}

				if (contrattoFilter.getTipoCanaleReclutamento() != null) {
					if (contratto.getTipoCanaleReclutamento() == null || contratto.getTipoCanaleReclutamento()
							.getId() != contrattoFilter.getTipoCanaleReclutamento().getId()) {
						return false;
					}
				}

				if (contrattoFilter.getTipoCausaFineRapporto() != null) {
					if (contratto.getTipoCausaFineRapporto() == null || contratto.getTipoCausaFineRapporto()
							.getId() != contrattoFilter.getTipoCausaFineRapporto().getId()) {
						return false;
					}
				}

				if (anagraficaRequestDto.getAnnoDataInizio() != null) {
					if (anagraficaDto.getContratto().getDataAssunzione() == null) {
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
				}
				if (anagraficaRequestDto.getAnnoDataFine() != null) {
					if (anagraficaDto.getContratto().getDataFineRapporto() == null) {
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
				}
			} else
				return false;
		}
		if (anagraficaRequestDto.getAnagraficaDto().getCommesse() != null
				&& anagraficaRequestDto.getAnagraficaDto().getCommesse().size() > 0
				&& anagraficaRequestDto.getAnagraficaDto().getCommesse().get(0).getAziendaCliente() != null) {
			String aziendaCliente = anagraficaRequestDto.getAnagraficaDto().getCommesse().get(0).getAziendaCliente();
			for (Commessa commessa : anagraficaDto.getCommesse()) {
				if (aziendaCliente != null
						&& !commessa.getAziendaCliente().toLowerCase().startsWith(aziendaCliente.toLowerCase())) {
					return false;
				}
			}
		}
		return true;
	}

	public Boolean checkScaduta(Commessa commessa) {

		Boolean check = false;

		if (commessa.getDataFine() != null) {

			if (DateUtil.convertorDate(commessa.getDataFine()).isBefore(LocalDate.now())) {

				check = true;

			}

		}

		return check;

	}

	public boolean checkCommesseInScadenza(Commessa commessa) {

		boolean check = false;

		if (commessa.getDataFine() != null) {

			LocalDate localDateFine = DateUtil.convertorDate(commessa.getDataFine());

			if (localDateFine.isAfter(LocalDate.now())

					&&

					localDateFine.isBefore(LocalDate.now().plus(40, ChronoUnit.DAYS))) {

				check = true;
			}

		}

		return check;

	}

	public boolean checkListCommesse(List<Commessa> list) {

		return !list.isEmpty();

	}

	public boolean checkContrattiInScadenza(Contratto contratto) {

		boolean check = false;

		if (contratto != null
				&& (contratto.getTipoContratto().getId() != 2 && contratto.getTipoContratto().getId() != 4)) {

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

	public Boolean toFilterAnagraficaDto(AnagraficaDto anagraficaDto,
			AnagraficaRequestDto anagraficaRequestDto) {

		if (anagraficaRequestDto == null || anagraficaRequestDto.getAnagraficaDto() == null) {
			return true;

		}

		return toFilterCommesseAnagrafica(anagraficaDto, anagraficaRequestDto)
				&& toFilterCommesseContratto(anagraficaDto, anagraficaRequestDto);
				

	}

	private Boolean toFilterCommesseAnagrafica(AnagraficaDto anagraficaDto, AnagraficaRequestDto anagraficaRequestDto) {

		Anagrafica anagrafica = anagraficaDto.getAnagrafica();
		Anagrafica anagraficaFilter = anagraficaRequestDto.getAnagraficaDto().getAnagrafica();

		if (anagraficaFilter != null && anagrafica != null) {

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

		return true;

	}

	public Boolean toFilterCommesse(Commessa commessa, AnagraficaRequestDto anagraficaRequestDto) {

		if (anagraficaRequestDto.getAnagraficaDto().getCommesse() != null
				&& anagraficaRequestDto.getAnagraficaDto().getCommesse().size() > 0
				&& anagraficaRequestDto.getAnagraficaDto().getCommesse().get(0) != null) {

			
				 return false;
				

		}
		
		
		return toFilterCommessaAziendaCliente(commessa,anagraficaRequestDto) && 
		toFilterCommesseYearMonth(commessa,anagraficaRequestDto);
		

		

	}
	
	private boolean toFilterCommessaAziendaCliente(Commessa commessa, AnagraficaRequestDto anagraficaRequestDto) {
		
		boolean check=false;
	
		if (anagraficaRequestDto.getAnagraficaDto().getCommesse().get(0).getAziendaCliente() != null) {
		
		 if(commessa.getAziendaCliente()
				.startsWith(anagraficaRequestDto.getAnagraficaDto().getCommesse().get(0).getAziendaCliente())) {
			 
			 check=true;
		 }
		 
		}
		
		return check;
		
	}



	private Boolean toFilterCommesseYearMonth(Commessa commessa, AnagraficaRequestDto anagraficaRequestDto) {

		if (anagraficaRequestDto.getAnnoDataFine() != null) {

			if (commessa.getDataFine() != null) {

				if (!DateUtil.compareYear(commessa.getDataFine(), anagraficaRequestDto.getAnnoDataFine())) {

					return false;

				}

				if (anagraficaRequestDto.getMeseDataFine() != null) {

					if (!DateUtil.compareYear(commessa.getDataFine(), anagraficaRequestDto.getMeseDataFine())) {

						return false;
					}

				}

			}
		}

		if (anagraficaRequestDto.getAnnoDataInizio() != null) {

			if (commessa.getDataInizio() != null) {

				if (!DateUtil.compareYear(commessa.getDataInizio(), anagraficaRequestDto.getAnnoDataInizio())) {

					return false;

				}

				if (anagraficaRequestDto.getMeseDataInizio() != null) {

					if (!DateUtil.compareYear(commessa.getDataInizio(), anagraficaRequestDto.getMeseDataInizio())) {

						return false;

					}

				}

			}

		}

		return true;

	}
	
	private Boolean toFilterCommesseContratto(AnagraficaDto anagraficaDto, AnagraficaRequestDto anagraficaRequestDto) {

		if (anagraficaRequestDto.getAnnoFineContratto() != null) {

			if (anagraficaDto.getContratto() != null && anagraficaDto.getContratto().getDataFineRapporto() != null) {

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

			} else {

				return false;

			}

		}

		return true;

	}

}
