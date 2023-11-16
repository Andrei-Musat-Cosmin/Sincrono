package it.sincrono.services.utils;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.mapping.Collection;

import java.io.ByteArrayInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.entities.TipoAzienda;
import it.sincrono.entities.TipoCausaFineRapporto;
import it.sincrono.repositories.TipologicheRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.repositories.exceptions.RepositoryException;
import it.sincrono.services.impl.RapportinoServiceImpl;
import it.sincrono.entities.TipoCcnl;
import it.sincrono.entities.TipoContratto;
import it.sincrono.entities.TipoLivelloContratto;
import it.sincrono.entities.TipoAziendaCliente;

@Component
public class ExcelUtilAnagraficaCreate {

	private static final Logger LOGGER = LogManager.getLogger(ExcelUtilAnagraficaCreate.class);

	@Autowired
	TipologicheRepository tipologicheRepository;

	public void convertNominativo(Anagrafica anagrafica, String nominativo) throws Exception {

		if (nominativo != null && !nominativo.equals("")) {

			String[] nomeAndCognome = nominativo.split(" ");

			anagrafica.setNome(nomeAndCognome[1]);

			anagrafica.setCognome(nomeAndCognome[0]);

		}

	}

	public void convertNominativo(Commessa commessa, String nominativo) throws Exception {

		if (nominativo != null && !nominativo.equals("")) {

			String[] nomeAndCognome = nominativo.split(" ");

			commessa.setNome(nomeAndCognome[1]);

			commessa.setCognome(nomeAndCognome[0]);

		}

	}

	public void getTipoAzienda(Anagrafica anagrafica, String azienda) throws Exception {

		List<TipoAzienda> list = tipologicheRepository.getTipoAziendaMap();

		anagrafica.setTipoAzienda(list.stream()
				.filter(elem -> elem.getDescrizione().toLowerCase().replaceAll("\\s", "")
						.equals(azienda.toLowerCase().replaceAll("\\s", "")))
				.collect(Collectors.toList()).size() > 0
						? list.stream()
								.filter(elem -> elem.getDescrizione().toLowerCase().replaceAll("\\s", "")
										.equals(azienda.toLowerCase().replaceAll("\\s", "")))
								.collect(Collectors.toList()).get(0)
						: null);

	}

	public void getTipoccnl(Contratto contratto, String ccnl) throws Exception {

		List<TipoCcnl> list = tipologicheRepository.getTipoCcnlMap();

		contratto.setTipoCcnl(list.stream()
				.filter(elem -> elem.getDescrizione().toLowerCase().replaceAll("\\s", "")
						.equals(ccnl.toLowerCase().replaceAll("\\s", "")))
				.collect(Collectors.toList()).size() > 0
						? list.stream()
								.filter(elem -> elem.getDescrizione().toLowerCase().replaceAll("\\s", "")
										.equals(ccnl.toLowerCase().replaceAll("\\s", "")))
								.collect(Collectors.toList()).get(0)
						: null);

	}

	public void getTipoContratto(Contratto contratto, String tipoContratto) throws Exception {

		List<TipoContratto> list = tipologicheRepository.getTipoContrattoMap();

		contratto.setTipoContratto(list.stream()
				.filter(elem -> elem.getDescrizione().toLowerCase().replaceAll("\\s", "")
						.equals(tipoContratto.toLowerCase().replaceAll("\\s", "")))
				.collect(Collectors.toList()).size() > 0
						? list.stream()
								.filter(elem -> elem.getDescrizione().toLowerCase().replaceAll("\\s", "")
										.equals(tipoContratto.toLowerCase().replaceAll("\\s", "")))
								.collect(Collectors.toList()).get(0)
						: null);

	}

	public void getCausaFineRapporto(Contratto contratto, String causaFineRapporto) throws Exception {

		List<TipoCausaFineRapporto> list = tipologicheRepository.getTipoCausaFineRapportoMap();

		contratto.setTipoCausaFineRapporto(list.stream()
				.filter(elem -> elem.getDescrizione().toLowerCase().replaceAll("\\s", "")
						.equals(causaFineRapporto.toLowerCase().replaceAll("\\s", "")))
				.collect(Collectors.toList()).size() > 0
						? list.stream()
								.filter(elem -> elem.getDescrizione().toLowerCase().replaceAll("\\s", "")
										.equals(causaFineRapporto.toLowerCase().replaceAll("\\s", "")))
								.collect(Collectors.toList()).get(0)
						: null);

	}

	public void getLivelloContratto(Contratto contratto, String livelloContratto) throws Exception {

		if (contratto.getTipoContratto() != null) {

			List<TipoLivelloContratto> list = tipologicheRepository.getTipoLivelloContrattoMap(
					contratto.getTipoCcnl().getDescrizione().toUpperCase());

			contratto.setTipoLivelloContratto(list.stream()
					.filter(elem -> elem.getLivello().toLowerCase().replaceAll("\\s", "")
							.equals(livelloContratto.toLowerCase().replaceAll("\\s", "")))
					.collect(Collectors.toList()).size() > 0
							? list.stream()
									.filter(elem -> elem.getLivello().toLowerCase().replaceAll("\\s", "")
											.equals(livelloContratto.toLowerCase().replaceAll("\\s", "")))
									.collect(Collectors.toList()).get(0)
							: null);

		}

	}

	public void getTipoAziendaCliente(Commessa commessa, String AziendaCliente) throws Exception {

		List<TipoAziendaCliente> list = tipologicheRepository.getTipoAziendaClienteMap();

		commessa.setTipoAziendaCliente(list.stream()
				.filter(elem -> elem.getDescrizione().toLowerCase().replaceAll("\\s", "")
						.equals(AziendaCliente.toLowerCase().replaceAll("\\s", "")))
				.collect(Collectors.toList()).size() > 0
						? list.stream()
								.filter(elem -> elem.getDescrizione().toLowerCase().replaceAll("\\s", "")
										.equals(AziendaCliente.toLowerCase().replaceAll("\\s", "")))
								.collect(Collectors.toList()).get(0)
						: null);

	}

}
