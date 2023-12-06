package it.sincrono.services.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Richieste;
import it.sincrono.entities.TipoRichieste;
import it.sincrono.repositories.RichiestaRepository;
import it.sincrono.repositories.dto.DuplicazioniRichiestaDto;
import it.sincrono.repositories.dto.RichiestaDto;

@Component
public class ConvertInDto {

	@Autowired
	RichiestaRepository richiestaRepository;

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

		richiestaDto.setId(tipoRichieste.get(0).getRichiesta().getId());

		richiestaDto.setStato(tipoRichieste.get(0).getRichiesta().getStato());

		richiestaDto.setMese(tipoRichieste.get(0).getRichiesta().getMese());

		richiestaDto.setAnno(tipoRichieste.get(0).getRichiesta().getAnno());

		richiestaDto.setCodiceFiscale(tipoRichieste.get(0).getRichiesta().getAnagrafica().getCodiceFiscale());

		richiestaDto.setList(list);

	}

	public List<TipoRichieste> convertInTipoRichieste(RichiestaDto richiestaDto, Integer idRichiesta) {

		List<TipoRichieste> list = new ArrayList<>();

		TipoRichieste tipoRichiesta = new TipoRichieste();

		Richieste richiesta = new Richieste();

		richiesta.setId(idRichiesta);

		for (DuplicazioniRichiestaDto duplicazioniRichiestaDto : richiestaDto.getList()) {

			tipoRichiesta.setaOra(duplicazioniRichiestaDto.getaOra());

			tipoRichiesta.setDaOra(duplicazioniRichiestaDto.getDaOra());

			tipoRichiesta.setPermesso(duplicazioniRichiestaDto.getPermessi());

			tipoRichiesta.setFerie(duplicazioniRichiestaDto.getFerie());

			tipoRichiesta.setnGiorno(duplicazioniRichiestaDto.getnGiorno());

			tipoRichiesta.setRichiesta(richiesta);

			list.add(tipoRichiesta);

			tipoRichiesta = new TipoRichieste();

		}

		return list;

	}

	public List<RichiestaDto> convertInDifferentRichiestaDto(List<TipoRichieste> tipoRichieste) {

		Map<Integer, List<Integer>> raggruppamento = tipoRichieste.stream().map(elem -> elem.getRichiesta().getId())
				.collect(Collectors.groupingBy(id -> id));

		RichiestaDto richiestaDto = new RichiestaDto();

		List<RichiestaDto> listRichiestaDto = new ArrayList<>();

		for (Integer key : raggruppamento.keySet()) {

			convertInRichiestaDto(richiestaDto, getTipoRichiesteById(key, tipoRichieste));

			listRichiestaDto.add(richiestaDto);

			richiestaDto = new RichiestaDto();

		}

		return listRichiestaDto;

	}

	public List<TipoRichieste> getTipoRichiesteById(Integer idRichiesta, List<TipoRichieste> tipoRichieste) {

		return tipoRichieste.stream().filter(elem -> elem.getRichiesta().getId() == idRichiesta)
				.collect(Collectors.toList());

	}

	public void addNote(RichiestaDto richiestaDto, Integer id) {

		richiestaDto.setNote(richiestaRepository.getNoteRichiesta(id).getNote());

	}

	public void addNote(List<RichiestaDto> richiesteDto) {

		for (RichiestaDto richiestaDto : richiesteDto) {

			richiestaDto.setNote(richiestaRepository.getNoteRichiesta(richiestaDto.getId()).getNote());
		}

	}

}
