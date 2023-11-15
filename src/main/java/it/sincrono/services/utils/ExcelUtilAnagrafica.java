package it.sincrono.services.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.ByteArrayInputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Anagrafica;
import it.sincrono.entities.Commessa;
import it.sincrono.entities.Contratto;
import it.sincrono.repositories.dto.AnagraficaDto;
import it.sincrono.services.impl.RapportinoServiceImpl;

@Component
public class ExcelUtilAnagrafica {

	private static final Logger LOGGER = LogManager.getLogger(RapportinoServiceImpl.class);

	public List<AnagraficaDto> createAnagraficaDtoExcel(String base64) throws Exception {

		List<AnagraficaDto> listAnagrafiche = new ArrayList<>();

		try {

			byte[] decodedBytes = Base64.decodeBase64(base64);

			Workbook workbook = new XSSFWorkbook(new ByteArrayInputStream(decodedBytes));

			listAnagrafiche = createAnagraficaDto(workbook);

			workbook.close();

		} catch (Exception e) {

		}

		return listAnagrafiche;

	}

	private List<AnagraficaDto> createAnagraficaDto(Workbook workbook) {

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

	private void createCommessa(Row row, AnagraficaDto anagraficaDto) {

		Commessa commessa = new Commessa();

		if (row.getCell(0) != null) {

			commessa.setAttivo(((int) row.getCell(0).getNumericCellValue()) == 1 ? true : false);

		}

		/*
		 * if (row.getCell(1) != null) {
		 * 
		 * commessa.setNominativo(row.getCell(1).getStringCellValue()); nominativo
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(2) != null) {
		 * 
		 * commessa.setAttivo(((int) row.getCell(2).getNumericCellValue()) == 1 ? true :
		 * false); azienda di appertenennza
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(3) != null) {
		 * 
		 * commessa.setAttivo(((int) row.getCell(3).getNumericCellValue()) == 1 ? true :
		 * false); attessa lavori
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(4) != null) {
		 * 
		 * commessa.setAttivo(((int) row.getCell(4).getNumericCellValue()) == 1 ? true :
		 * false); tipoAziendaCliente
		 * 
		 * }
		 */

		if (row.getCell(5) != null) {

			commessa.setClienteFinale(row.getCell(5).getStringCellValue());

		}

		if (row.getCell(6) != null) {

			commessa.setTitoloPosizione(row.getCell(6).getStringCellValue());

		}

		/*
		 * if (row.getCell(7) != null) {
		 * 
		 * commessa.setAttivo(((int) row.getCell(7).getNumericCellValue()) == 1 ? true :
		 * false); data inzio
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(8) != null) {
		 * 
		 * commessa.setAttivo(((int) row.getCell(8).getNumericCellValue()) == 1 ? true :
		 * false); data fine
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(9) != null) {
		 * 
		 * commessa.setAttivo(((int) row.getCell(9).getNumericCellValue()) == 1 ? true :
		 * false); tariffa giornaliera
		 * 
		 * }
		 */

	}

	private void createAnagrafica(Row row, AnagraficaDto anagraficaDto) {

		Anagrafica anagrafica = new Anagrafica();

		if (row.getCell(0) != null) {

			anagrafica.setAttivo(((int) row.getCell(0).getNumericCellValue()) == 1 ? true : false);

		}

		if (row.getCell(1) != null) {

			anagrafica.setNome(row.getCell(1).getStringCellValue());

		}

		if (row.getCell(2) != null) {

			anagrafica.setNome(row.getCell(2).getStringCellValue());

		}

		/*
		 * if (row.getCell(3) != null) {
		 * 
		 * anagrafica.setCodiceFiscale(row.getCell(3).getStringCellValue());
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(4) != null) {
		 * 
		 * anagrafica.setCodiceFiscale(row.getCell(4).getStringCellValue());
		 * 
		 * }
		 */

		if (row.getCell(5) != null) {

			anagrafica.setCodiceFiscale(row.getCell(5).getStringCellValue());

		}

		if (row.getCell(6) != null) {

			anagrafica.setComuneDiNascita(row.getCell(6).getStringCellValue());

		}

		if (row.getCell(7) != null) {

			anagrafica.setComuneDiNascita(row.getCell(7).getStringCellValue());

		}

		/*
		 * if (row.getCell(8) != null) {
		 * 
		 * anagrafica.setComuneDiNascita(row.getCell(8).getStringCellValue()); data di
		 * nascita
		 * 
		 * }
		 */

		if (row.getCell(9) != null) {

			anagrafica.setResidenza(row.getCell(9).getStringCellValue());

		}

		if (row.getCell(10) != null) {

			anagrafica.setDomicilio(row.getCell(10).getStringCellValue());

		}

		if (row.getCell(11) != null) {

			anagrafica.setCellularePrivato(row.getCell(11).getStringCellValue());

		}

		if (row.getCell(12) != null) {

			anagrafica.setMailPrivata(row.getCell(12).getStringCellValue());

		}

		if (row.getCell(13) != null) {

			anagrafica.setMailAziendale(row.getCell(13).getStringCellValue());

		}

		if (row.getCell(14) != null) {

			anagrafica.setMailPec(row.getCell(14).getStringCellValue());

		}

		if (row.getCell(15) != null) {

			anagrafica.setTitoliDiStudio(row.getCell(15).getStringCellValue());

		}

		if (row.getCell(16) != null) {

			anagrafica.setAltriTitoli(row.getCell(16).getStringCellValue());

		}

		/*
		 * if (row.getCell(17) != null) {
		 * 
		 * anagrafica.setMailPec(row.getCell(17).getStringCellValue()); coniugato
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(18) != null) {
		 * 
		 * anagrafica.setMailPec(row.getCell(18).getStringCellValue()); figli a carico
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(19) != null) {
		 * 
		 * anagrafica.setSedeAssuzione(row.getCell(19).getStringCellValue()); sede
		 * assunzione assunzione
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(20) != null) {
		 * 
		 * anagrafica.setQualifica(row.getCell(20).getStringCellValue()); qualifica
		 * 
		 * }
		 */

	}

	private void createContratto(Row row, AnagraficaDto anagraficaDto) {

		Contratto contratto = new Contratto();

		/*
		 * if (row.getCell(21) != null) {
		 * 
		 * contratto.(row.getCell(21).getStringCellValue()); fare algoritmo tipo ccnl
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(22) != null) {
		 * 
		 * contratto.setDataAssunzione(row.getCell(22).getStringCellValue()); fare
		 * algoritmo data assunzione
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(23) != null) {
		 * 
		 * contratto.(row.getCell(23).getStringCellValue());fare algoritmo data fine
		 * prova
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(24) != null) {
		 * 
		 * contratto.(row.getCell(24).getStringCellValue());fare algoritmo tipo
		 * contratto
		 * 
		 * }
		 */

		if (row.getCell(25) != null) {

			contratto.setMesiDurata(((int) row.getCell(25).getNumericCellValue()));

		}

		/*
		 * if (row.getCell(26) != null) {
		 * 
		 * contratto.(row.getCell(26).getStringCellValue());fare algoritmo tipo livello
		 * contratto
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(27) != null) {
		 * 
		 * contratto.setLivelloAttuale(((int) row.getCell(27).getNumericCellValue()));
		 * vedere livelloAttuale
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(28) != null) {
		 * 
		 * contratto.setLivelloAttuale(((int) row.getCell(28).getNumericCellValue()));
		 * vedere livelloFinale
		 * 
		 * }
		 */

		if (row.getCell(29) != null) {

			contratto.setPfi(row.getCell(29).getStringCellValue().equals("si") ? true : false);

		}

		if (row.getCell(30) != null) {

			contratto.setTutor(row.getCell(30).getStringCellValue());

		}

		if (row.getCell(31) != null) {

			contratto.setAssicurazioneObbligatoria(row.getCell(31).getStringCellValue().equals("si") ? true : false);

		}

		/*
		 * if (row.getCell(32) != null) {
		 * 
		 * contratto.setLivelloAttuale(((int) row.getCell(32).getNumericCellValue()));
		 * vedere corsoSicurezza Date
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(33) != null) {
		 * 
		 * contratto.setLivelloAttuale(((int) row.getCell(33).getNumericCellValue()));
		 * vedere Formazione Obbligatoria
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(34) != null) {
		 * 
		 * contratto.setLivelloAttuale(((int) row.getCell(34).getNumericCellValue()));
		 * vedere data fineRapporto
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(37) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(37).getStringCellValue()); tipo causa
		 * fine rapporto
		 * 
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(36) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(36).getStringCellValue()); data fine
		 * rapporto
		 * 
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(37) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(36).getStringCellValue()); vedere %part
		 * time
		 * 
		 * 
		 * }
		 */

		if (row.getCell(38) != null) {

			contratto.setPc(row.getCell(38).getStringCellValue().equals("si") ? true : false);

		}

		/*
		 * if (row.getCell(39) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(36).getStringCellValue()); data visita
		 * medica
		 * 
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(40) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(40).getStringCellValue()); idoneta
		 * sanitaria
		 * 
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(41) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(41).getStringCellValue()); lorda mensile
		 * 
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(42) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(42).getStringCellValue()); scatti
		 * anzianita
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(43) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(43).getStringCellValue()); superminimo
		 * mensile
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(44) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(44).getStringCellValue()); retribuzione
		 * mensile lorda
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(45) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(45).getStringCellValue()); percentuale
		 * part time 2
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(46) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(46).getStringCellValue()); ral annua
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(47) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(47).getStringCellValue()); superMinimo
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(48) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(48).getStringCellValue()); netto annuo
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(49) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(49).getStringCellValue()); diaria mensile
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(50) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(50).getStringCellValue()); diaria
		 * giornaliera
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(51) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(51).getStringCellValue()); diaria annua
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(52) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(52).getStringCellValue()); ticket
		 * 
		 * 
		 * }
		 */

		/*
		 * if (row.getCell(53) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(53).getStringCellValue()); stage mensile
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(54) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(54).getStringCellValue()); benefit
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(55) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(55).getStringCellValue()); non concorenza
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(56) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(56).getStringCellValue()); bonus
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(57) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(57).getStringCellValue()); assicurazione
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(58) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(58).getStringCellValue()); sim
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(59) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(59).getStringCellValue()); tariffa
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(60) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(52).getStringCellValue()); tax_max
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(61) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(61).getStringCellValue());
		 * basic_annual_cost
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(62) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(62).getStringCellValue());
		 * toatl_annual_cost
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(63) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(63).getStringCellValue()); dipendente da
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(64) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(64).getStringCellValue()); titolo
		 * posizione
		 * 
		 * 
		 * }
		 */
		/*
		 * if (row.getCell(65) != null) {
		 * 
		 * contratto.setDimmisioni(row.getCell(65).getStringCellValue()); source
		 * 
		 * 
		 * }
		 */

	}

}
