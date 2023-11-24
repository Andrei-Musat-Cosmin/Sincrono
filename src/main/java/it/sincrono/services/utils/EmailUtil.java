package it.sincrono.services.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Richieste;
import it.sincrono.entities.TipoRichieste;
import it.sincrono.repositories.dto.DuplicazioniRichiestaDto;
import it.sincrono.repositories.dto.RichiestaDto;

@Component
public class EmailUtil {

	public String createBodyRichiesta(RichiestaDto richiestaDto, Anagrafica anagrafica) {

		String ferieOrPermesso = richiestaDto.getList().get(0).getFerie() != null
				&& richiestaDto.getList().get(0).getFerie() == true ? "ferie" : "permesso";

		String unoOrPiuGiorni = richiestaDto.getList().size() > 1
				? " è da: " + richiestaDto.getList().get(0).getnGiorno() + " a: "
						+ richiestaDto.getList().get(richiestaDto.getList().size() - 1).getnGiorno()
				: " è per il giorno: " + richiestaDto.getList().get(0).getnGiorno();

		String link = "http://localhost:4200/richieste/:" + richiestaDto.getId();

		return "la richiesta di " + ferieOrPermesso + unoOrPiuGiorni + " per visualizzare la richiesta: " + "<a href=\""
				+ link + "\">clicca qui</a>";

	}

	public String createSubjectRichiesta(RichiestaDto richiestaDto, Anagrafica anagrafica) {

		String ferieOrPermesso = richiestaDto.getList().get(0).getFerie() != null
				&& richiestaDto.getList().get(0).getFerie() == true ? "ferie" : "permesso";


		return "richiesta di " + ferieOrPermesso + " dal dipendente: " + anagrafica.getNome() + " "
				+ anagrafica.getCognome() + " codice fiscale: " + anagrafica.getCodiceFiscale();

	}

}
