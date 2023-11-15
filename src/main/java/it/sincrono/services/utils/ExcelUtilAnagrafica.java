package it.sincrono.services.utils;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.repositories.TipologicheRepository;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.services.impl.RapportinoServiceImpl;

@Component
public class ExcelUtilAnagrafica {

	private static final Logger LOGGER = LogManager.getLogger(ExcelUtilAnagrafica.class);

	@Autowired
	ExcelUtilAnagraficaCreate excelUtilAnagraficaCreate;

	public List<AnagraficaDto> createAnagraficaDtoExcel(String base64) throws Exception {

		List<AnagraficaDto> listAnagrafiche = new ArrayList<>();

		try {

			byte[] decodedBytes = Base64.decodeBase64(base64);

			Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(decodedBytes));

			listAnagrafiche = createAnagraficaDto(workbook);

			workbook.close();

		} catch (Exception e) {

			System.out.println(e);

		}

		return listAnagrafiche;

	}

	private List<AnagraficaDto> createAnagraficaDto(Workbook workbook) throws Exception {

		AnagraficaDto anagraficaDto = null;

		List<AnagraficaDto> listAnagrafiche = new ArrayList<>();

		Sheet riepilogoGeneraleSheet = workbook.getSheet("Riepilogo Generale");

		if (riepilogoGeneraleSheet != null) {

			for (int rowIndex = 3; rowIndex <= riepilogoGeneraleSheet.getLastRowNum(); rowIndex++) {
				Row row = riepilogoGeneraleSheet.getRow(rowIndex);
				if (row != null) {

					anagraficaDto = new AnagraficaDto();

					createAnagrafica(row, anagraficaDto);
					createContratto(row, anagraficaDto);

					listAnagrafiche.add(anagraficaDto);
				}

			}

		}

		Sheet commesseSheet = workbook.getSheet("Commesse");

		if (commesseSheet != null) {

			Integer i = 0;

			for (int rowIndex = 2; rowIndex <= commesseSheet.getLastRowNum(); rowIndex++) {
				Row row = commesseSheet.getRow(rowIndex);
				if (row != null) {

					createCommessa(row, listAnagrafiche.get(i));

					i++;

				}

			}

		}

		return listAnagrafiche;

	}

	private void createCommessa(Row row, AnagraficaDto anagraficaDto) throws Exception {

		Commessa commessa = new Commessa();

		if (row.getCell(0) != null) {

			commessa.setAttivo(((int) row.getCell(0).getNumericCellValue()) == 1 ? true : false);

		}

		if (row.getCell(2) != null) {

			commessa.setAziendaDiFatturazioneInterna(row.getCell(2).getStringCellValue());

		}

		if (row.getCell(4) != null) {

			excelUtilAnagraficaCreate.getTipoAziendaCliente(commessa, row.getCell(4).getStringCellValue());

		}

		if (row.getCell(5) != null) {

			commessa.setClienteFinale(row.getCell(5).getStringCellValue());

		}

		if (row.getCell(6) != null) {

			commessa.setTitoloPosizione(row.getCell(6).getStringCellValue());

		}

		/*if (row.getCell(7) != null) {

			commessa.setDataInizio(row.getCell(7).getDateCellValue());

		}

		if (row.getCell(8) != null) {

			commessa.setDataFine(row.getCell(8).getDateCellValue());

		}*/

		if (row.getCell(9) != null) {

			commessa.setTariffaGiornaliera(row.getCell(9).getStringCellValue());

		}
		
		List<Commessa> list = new ArrayList<>();
		
		list.add(commessa);
		
		anagraficaDto.setCommesse(list);

	}

	private void createAnagrafica(Row row, AnagraficaDto anagraficaDto) throws Exception {

		DataFormatter dataFormatter = new DataFormatter();

		Anagrafica anagrafica = new Anagrafica();

		if (row.getCell(0) != null) {

			anagrafica.setAttivo(((int) row.getCell(0).getNumericCellValue()) == 1 ? true : false);

		}

		if (row.getCell(1) != null) {

			excelUtilAnagraficaCreate.convertNominativo(anagrafica, row.getCell(1).getStringCellValue());

		}

		if (row.getCell(2) != null) {

			excelUtilAnagraficaCreate.getTipoAzienda(anagrafica, row.getCell(2).getStringCellValue());

		}

		if (row.getCell(4) != null) {

			anagrafica.setCodiceFiscale(row.getCell(4).getStringCellValue());

		}

		if (row.getCell(5) != null) {

			anagrafica.setComuneDiNascita(row.getCell(5).getStringCellValue());

		}

		if (row.getCell(6) != null) {

			anagrafica.setDataDiNascita(row.getCell(6).getDateCellValue());

		}

		if (row.getCell(7) != null) {

			anagrafica.setResidenza(row.getCell(7).getStringCellValue());

		}

		if (row.getCell(8) != null) {

			anagrafica.setDomicilio(row.getCell(8).getStringCellValue());

		}

		if (row.getCell(9) != null) {

			anagrafica.setCellularePrivato(dataFormatter.formatCellValue(row.getCell(9)));

		}

		if (row.getCell(10) != null) {

			anagrafica.setMailPrivata(row.getCell(10).getStringCellValue());

		}

		if (row.getCell(11) != null) {

			anagrafica.setMailAziendale(row.getCell(11).getStringCellValue());

		}

		if (row.getCell(12) != null) {

			anagrafica.setMailPec(row.getCell(12).getStringCellValue());

		}

		if (row.getCell(13) != null) {

			anagrafica.setTitoliDiStudio(row.getCell(13).getStringCellValue());

		}

		if (row.getCell(14) != null) {

			anagrafica.setAltriTitoli(row.getCell(14).getStringCellValue());

		}

		if (row.getCell(15) != null) {

			anagrafica.setConiugato(row.getCell(15).getStringCellValue().equals("si") ? true : false);

		}

		if (row.getCell(16) != null) {

			anagrafica.setFigliACarico(row.getCell(16).getStringCellValue().equals("si") ? true : false);

		}
		
		anagraficaDto.setAnagrafica(anagrafica);

	}

	private void createContratto(Row row, AnagraficaDto anagraficaDto) throws Exception {

		Contratto contratto = new Contratto();

		if (row.getCell(19) != null) {

			excelUtilAnagraficaCreate.getTipoccnl(contratto, row.getCell(19).getStringCellValue());

		}

		if (row.getCell(20) != null) {

			contratto.setDataAssunzione(row.getCell(20).getDateCellValue());
		}

		if (row.getCell(21) != null) {

			contratto.setDataFineProva(row.getCell(21).getDateCellValue());
		}

		if (row.getCell(22) != null) {

			excelUtilAnagraficaCreate.getTipoContratto(contratto, row.getCell(22).getStringCellValue());

		}

		if (row.getCell(23) != null) {

			contratto.setMesiDurata(((int) row.getCell(23).getNumericCellValue()));

		}

		if (row.getCell(24) != null) {

			excelUtilAnagraficaCreate.getLivelloContratto(contratto,
					Integer.toString(((int) row.getCell(24).getNumericCellValue())));

		}

		if (row.getCell(25) != null) {

			contratto.setLivelloAttuale(Integer.toString(((int) row.getCell(25).getNumericCellValue())));

		}

		if (row.getCell(26) != null) {

			contratto.setLivelloFinale(Integer.toString(((int) row.getCell(26).getNumericCellValue())));

		}

		if (row.getCell(27) != null) {

			contratto.setPfi(row.getCell(27).getStringCellValue().equals("si") ? true : false);

		}

		if (row.getCell(28) != null) {

			contratto.setTutor(row.getCell(28).getStringCellValue());

		}

		if (row.getCell(29) != null) {

			contratto.setAssicurazioneObbligatoria(row.getCell(29).getStringCellValue().equals("si") ? true : false);

		}

		/*
		 * if (row.getCell(30) != null) {
		 * 
		 * contratto.setDataCorsoSicurezza(row.getCell(30).getDateCellValue());
		 * 
		 * if (contratto.getDataCorsoSicurezza() != null) {
		 * 
		 * contratto.setCorsoSicurezza(true);
		 * 
		 * }
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(32) != null) {
		 * 
		 * contratto.setDataFineRapporto(row.getCell(32).getDateCellValue());
		 * 
		 * }
		 */

		if (row.getCell(35) != null) {

			excelUtilAnagraficaCreate.getCausaFineRapporto(contratto, row.getCell(35).getStringCellValue());

		}

		if (row.getCell(36) != null) {

			contratto.setPercentualePartTime(row.getCell(36).getNumericCellValue());

		}

		if (row.getCell(37) != null) {

			contratto.setPc(row.getCell(37).getStringCellValue().equals("si") ? true : false);

		}

		if (row.getCell(38) != null) {

			contratto.setDataVisitaMedica(row.getCell(38).getDateCellValue());

		}

		if (row.getCell(40) != null) {

			contratto.setRetribuzioneMensileLorda(row.getCell(40).getNumericCellValue());

		}

		if (row.getCell(41) != null) {

			contratto.setScattiAnzianita(row.getCell(41).getNumericCellValue());

		}

		if (row.getCell(42) != null) {

			contratto.setSuperminimoMensile(row.getCell(42).getNumericCellValue());

		}

		if (row.getCell(46) != null) {

			contratto.setRalAnnua(row.getCell(46).getNumericCellValue());

		}

		if (row.getCell(47) != null) {

			contratto.setSuperminimoRal(row.getCell(47).getNumericCellValue());

		}

		if (row.getCell(49) != null) {

			contratto.setDiariaMensile(row.getCell(49).getNumericCellValue());

		}

		if (row.getCell(50) != null) {

			contratto.setDiariaGiornaliera(row.getCell(50).getNumericCellValue());

		}
		if (row.getCell(51) != null) {

			contratto.setDiariaAnnua(row.getCell(51).getNumericCellValue());

		}
		
		
		anagraficaDto.setContratto(contratto);

	}

}
