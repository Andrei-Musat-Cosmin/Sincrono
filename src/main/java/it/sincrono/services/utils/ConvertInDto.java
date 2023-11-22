package it.sincrono.services.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import it.sincrono.entities.TipoRichieste;
import it.sincrono.repositories.dto.DuplicazioniRichiestaDto;
import it.sincrono.repositories.dto.RichiestaDto;

@Component
public class ConvertInDto {

	public void convertInRichiestaDto(RichiestaDto richiestaDto, List<TipoRichieste> tipoRichieste) {

		List<DuplicazioniRichiestaDto> list = new ArrayList<>();

		DuplicazioniRichiestaDto duplicazioniRichiestaDto = new DuplicazioniRichiestaDto();

		for (TipoRichieste tipoRichiesta : tipoRichieste) {

			duplicazioniRichiestaDto.setaOra(tipoRichiesta.getaOra());

			duplicazioniRichiestaDto.setDaOra(tipoRichiesta.getDaOra());

			duplicazioniRichiestaDto.setPermessi(tipoRichiesta.getPermesso());

			duplicazioniRichiestaDto.setFerie(tipoRichiesta.getFerie());

			duplicazioniRichiestaDto.setnGiorno(tipoRichiesta.getnGiorno());

			list.add(duplicazioniRichiestaDto);

			duplicazioniRichiestaDto = new DuplicazioniRichiestaDto();

		}
		
		richiestaDto.setList(list);

	}

}
