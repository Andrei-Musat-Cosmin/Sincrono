package it.sincrono.services.utils;

import org.springframework.stereotype.Component;

import it.sincrono.repositories.dto.DuplicazioniGiornoDto;
import it.sincrono.repositories.dto.GiornoDto;
import it.sincrono.repositories.dto.RapportinoDto;

@Component
public class RapportinoUtil {

	public void calcoloRapportinoGiorniUtiliLavoro(RapportinoDto rapportinoDto) {

		rapportinoDto.setGiorniUtili(
				DateUtil.calcolaGiorniUtiliLavoro(rapportinoDto));

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

}
