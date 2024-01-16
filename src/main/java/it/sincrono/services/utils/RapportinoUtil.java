package it.sincrono.services.utils;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.repositories.AnagraficaRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.dto.DuplicazioniGiornoDto;
import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.RapportinoDto;

@Component
public class RapportinoUtil {

	@Autowired
	private MapperCustom mapper;

	@Autowired
	private AnagraficaRepository anagraficaRepository;

	public void calcoloRapportinoGiorniUtiliLavoro(RapportinoDto rapportinoDto) {

		rapportinoDto.setGiorniUtili(DateUtil.calcolaGiorniUtiliLavoro(rapportinoDto));

	}

	public void calcoloRapportinoDtoGiorniLavorati(RapportinoDto rapportinoDto) {

		Double sommaOreLavorate = 0.0;

		for (GiornoDto giornoDto : rapportinoDto.getMese().getGiorni()) {

			sommaOreLavorate = sommaOreLavorate
					+ giornoDto.getDuplicazioniGiornoDto().stream().filter(dto -> dto.getOreOrdinarie() != null)
							.mapToDouble(DuplicazioniGiornoDto::getOreOrdinarie).sum();

		}

		rapportinoDto.setGiorniLavorati(sommaOreLavorate / 8);

	}

	public void calcoloCommesseRapportino(RapportinoDto rapportinoDto) {

		AnagraficaDto anagraficaDto = null;

		Anagrafica anagrafica = anagraficaRepository
				.findByCodiceFiscale(rapportinoDto.getAnagrafica().getCodiceFiscale());

		anagraficaDto = mapper.toAnagraficaDto(anagrafica.getId());

		if (anagraficaDto.getCommesse() != null && anagraficaDto.getCommesse().size() == 1) {

			Calendar cal = Calendar.getInstance();

			for (GiornoDto giornoDto : rapportinoDto.getMese().getGiorni()) {

				cal.set(rapportinoDto.getAnnoRequest(), rapportinoDto.getMeseRequest() - 1,
						giornoDto.getNumeroGiorno());

				Date data = cal.getTime();

				if (anagraficaDto.getCommesse().get(0).getDataFine().before(data)) {
					giornoDto.getDuplicazioniGiornoDto().get(0)
							.setCliente(anagraficaDto.getCommesse().get(0).getNome());

				}

			}
		}

	}

}
