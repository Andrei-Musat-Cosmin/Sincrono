package it.sincrono.services.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormatSymbols;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import it.sincrono.entities.Rapportino;
import it.sincrono.services.costants.ServiceMessages;
import it.sincrono.services.exceptions.ServiceException;
import it.sincrono.services.impl.RapportinoServiceImpl;

@Component
public class ExcelUtil {
	private static final Logger LOGGER = LogManager.getLogger(RapportinoServiceImpl.class);
	private static final String EXCELPATH = "C:/Users/SINCRONO/Desktop/provaSalvataggioExcel.xlsx";

	public String toExcel(List<Rapportino> rapportini) throws ServiceException {

		try (Workbook workbook = new XSSFWorkbook()) {
			/** SETUP CALENDAR PER I CONTROLLI SU SABATI E DOMENICHE **/
			Calendar calendar = Calendar.getInstance();
			int anno = rapportini.get(0).getAnno();
			int mese = rapportini.get(0).getMese();
			calendar.set(anno, mese - 1, 1);
			int numeroGiorniNelMese = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

			/** SETUP DEGLI GLI OGGETTI PER LA COSTRUZIONE DELL'EXCEL **/
			Sheet sheet = workbook.createSheet("Dati Excel");
			sheet.setColumnWidth(0, 30 * 256);
			for (int i = 1; i <= 31; i++) {
				sheet.setColumnWidth(i, 3 * 256);
			}
			for (int i = 33; i <= 37; i++) {
				sheet.setColumnWidth(i, 8 * 256);
			}
			Row row = null;
			Cell cell = null;

			/** DICHIARAZIONE PER PIU STILI PER LE CELLE CON VARI COLORI **/
			CellStyle cellStyleDefault = createCellStyle(workbook, IndexedColors.WHITE, false);
			CellStyle cellStyleAnnoMese = createCellStyle(workbook, IndexedColors.GOLD, false);
			CellStyle cellStyleGiorni = createCellStyle(workbook, IndexedColors.GREEN, false);

			CellStyle cellStyleFerieLabel = createCellStyle(workbook, IndexedColors.YELLOW, false);
			cellStyleFerieLabel.setRotation((short) -45);
			CellStyle cellStyleTotaleLabel = createCellStyle(workbook, IndexedColors.YELLOW, false);
			cellStyleTotaleLabel.setRotation((short) -45);
			CellStyle cellStyleMalattiaLabel = createCellStyle(workbook, IndexedColors.LIGHT_GREEN, false);
			cellStyleMalattiaLabel.setRotation((short) -45);
			CellStyle cellStylePermessiLabel = createCellStyle(workbook, IndexedColors.LIGHT_ORANGE, false);
			cellStylePermessiLabel.setRotation((short) -45);
			CellStyle cellStyleRolLabel = createCellStyle(workbook, IndexedColors.LIGHT_TURQUOISE, false);
			cellStyleRolLabel.setRotation((short) -45);

			CellStyle cellStyleFerie = createCellStyle(workbook, IndexedColors.YELLOW, false);
			CellStyle cellStyleTotale = createCellStyle(workbook, IndexedColors.YELLOW, false);
			CellStyle cellStyleMalattia = createCellStyle(workbook, IndexedColors.LIGHT_GREEN, false);
			CellStyle cellStylePermessi = createCellStyle(workbook, IndexedColors.LIGHT_ORANGE, false);
			CellStyle cellStyleRol = createCellStyle(workbook, IndexedColors.LIGHT_TURQUOISE, false);

			CellStyle cellStyleSABDOM = createCellStyle(workbook, IndexedColors.GREEN, false);
			CellStyle cellStyleNODAY = createCellStyle(workbook, IndexedColors.LIGHT_CORNFLOWER_BLUE, false);

			int rowNum = 0;
			int cellNum = 0;
			String nomeCognomePrecedente = "", nomeCognomeAttuale = "";
			row = sheet.createRow(rowNum);
			row.setHeightInPoints(18);
			cell = row.createCell(0);
			cell.setCellStyle(cellStyleAnnoMese);
			cell.setCellValue(rapportini.get(0).getAnno());

			cell = row.createCell(1);
			DateFormatSymbols symbols = new DateFormatSymbols();
			cell.setCellValue(symbols.getMonths()[rapportini.get(0).getMese() - 1]);
			cell.setCellStyle(cellStyleAnnoMese);

			for (int i = 2; i < 31; i++) {
				cell = row.createCell(i);
			}
			cell = row.createCell(32);
			cell.setCellStyle(cellStylePermessiLabel);
			cell.setCellValue("TOT");

			cell = row.createCell(33);
			cell.setCellStyle(cellStyleFerieLabel);
			cell.setCellValue("FERIE");

			cell = row.createCell(34);
			cell.setCellStyle(cellStyleMalattiaLabel);
			cell.setCellValue("MALATTIE");

			cell = row.createCell(35);
			cell.setCellStyle(cellStylePermessiLabel);
			cell.setCellValue("EX-FS");

			cell = row.createCell(36);
			cell.setCellStyle(cellStyleRolLabel);
			cell.setCellValue("ROL");

			sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 31));

			rowNum++;
			row = sheet.createRow(rowNum);
			row.setHeightInPoints(30);
			for (int i = 0; i < 32; i++) {
				cell = row.createCell(i + 1);
				cell.setCellValue(i + 1);
				cell.setCellStyle(cellStyleGiorni);
				if (i == 31) {
					cell = null;
					cell = row.createCell(++i);
					cell.setCellStyle(cellStylePermessiLabel);
					cell = row.createCell(++i);
					cell.setCellStyle(cellStyleFerieLabel);
					cell = row.createCell(++i);
					cell.setCellStyle(cellStyleMalattiaLabel);
					cell = row.createCell(++i);
					cell.setCellStyle(cellStylePermessiLabel);
					cell = row.createCell(++i);
					cell.setCellStyle(cellStyleRolLabel);
					sheet.addMergedRegion(new CellRangeAddress(0, 1, 32, 32));
					sheet.addMergedRegion(new CellRangeAddress(0, 1, 33, 33));
					sheet.addMergedRegion(new CellRangeAddress(0, 1, 34, 34));
					sheet.addMergedRegion(new CellRangeAddress(0, 1, 35, 35));
					sheet.addMergedRegion(new CellRangeAddress(0, 1, 36, 36));
					sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
				}

			}
			for (Rapportino rapportino : rapportini) {

				/** SET DELLA COMBINAZIONE NOME COGNOME DELLA N ITERAZIONE **/
				nomeCognomeAttuale = rapportino.getAnagrafica().getNome() + " "
						+ rapportino.getAnagrafica().getCognome();

				/**
				 * SE IL NOME VECCHIO E' DIVERSO DA QUELLO DELLA N ITERAZIONE SONO DIVERSI
				 * ALLORA E' UNA NUOVA RIGA
				 **/
				if (!nomeCognomePrecedente.equals(nomeCognomeAttuale)) {
					nomeCognomePrecedente = nomeCognomeAttuale;
					row = sheet.createRow(++rowNum);
					row.setHeightInPoints(15);
					cellNum = 0; // RICOMINCIA A CONTARE DA 0 CON LE CELLE
					cell = row.createCell(cellNum);
					cell.setCellStyle(cellStyleDefault);
					cell.setCellValue(nomeCognomePrecedente);

				}
				cell = row.createCell(++cellNum);
				calendar.set(anno, mese - 1, cellNum);
				int giorno = calendar.get(Calendar.DAY_OF_WEEK);

				/**
				 * CONTROLLO SE IL GIORNO ATTUALE E' PIU GRANDE DELL'ULTIMO GIORNO DEL MESE
				 * SELEZIONATO
				 **/
				if (giorno > numeroGiorniNelMese) { // SE VERO ALLORA CI SONO DEI GIORNI CHE 'NON ESISTONO'
					cell.setCellStyle(cellStyleNODAY);
					cell.setCellValue("Q");

					// CONTROLLO SE IL GIORNO ATTUALE E' UN SABATO O UNA DOMENICA
				}
				if (giorno == Calendar.SATURDAY || giorno == Calendar.SUNDAY) {
					cell.setCellStyle(cellStyleSABDOM);
					cell.setCellValue("q");
				}

				if (rapportino.getOre() != null) { // SE CI SONO SCRITTE DELLE ORE INSERISCO IL NUMERO DI ORE
					cell.setCellStyle(cellStyleDefault);
					if (rapportino.getOre() < 8) { // SE E' MINIORE DI OTTO ORE VA INSERITO COME "STRAORDINARIO"
						cell.setCellValue(rapportino.getOre());
					}
					cell.setCellValue("");

					// INSERISCE "F" SE SI E' PRESO LE FERIE PER QUEL GIORNO
				} else if (rapportino.getFerie() != null) {
					cell.setCellStyle(cellStyleFerie);
					cell.setCellValue("F");

					// INSERISCE "M" SE SI E' PRESO LE MALATTIE PER QUEL GIORNO
				} else if (rapportino.getMalattie() != null) {
					cell.setCellStyle(cellStyleMalattia);
					cell.setCellValue("M");

					// INSERISCE "P" SE SI E' PRESO DELLE ORE DI PERMESSO
				} else if (rapportino.getPermessi() != null) {
					cell.setCellStyle(cellStylePermessi);
					cell.setCellValue(rapportino.getPermessi() + "p");
				}
//				else if (rapportino.getRol() != null) {
//					cell.setCellStyle(cellStyleRol);
//					cell.setCellValue(rapportino.getPermessi() + "r");
//				}
				if (cellNum == 31) {
					int currentRowNum = rowNum + 1;
					cell = row.createCell(++cellNum, CellType.FORMULA);
					cell.setCellStyle(cellStyleTotale);
					cell.setCellFormula("COUNTIF(B" + currentRowNum + ":AF" + currentRowNum + ",\"\") + COUNTIF(B"
							+ currentRowNum + ":AF" + currentRowNum + ",\"<8\") - (SUMIF(B" + currentRowNum + ":AF"
							+ currentRowNum + ",\"<8\") / 8)");

					cell = row.createCell(++cellNum, CellType.FORMULA);
					cell.setCellStyle(cellStyleDefault);
					cell.setCellFormula("COUNTIFS(B" + currentRowNum + ":AF" + currentRowNum + ",\"F\")");

					cell = row.createCell(++cellNum, CellType.FORMULA);
					cell.setCellStyle(cellStyleDefault);
					cell.setCellFormula("COUNTIFS(B" + currentRowNum + ":AF" + currentRowNum + ",\"M\")");

					cell = row.createCell(++cellNum, CellType.FORMULA);
					cell.setCellStyle(cellStyleDefault);
					cell.setCellFormula("SUM(IF(MID($B" + currentRowNum + ":$AF" + currentRowNum
							+ ",2,1)=\"p\",VALUE(MID($B" + currentRowNum + ":$AF" + currentRowNum + ",1,1)),0))");

					cell = row.createCell(++cellNum, CellType.FORMULA);
					cell.setCellStyle(cellStyleDefault);
					cell.setCellFormula("SUM(IF(MID(B" + currentRowNum + ":AF" + currentRowNum
							+ ",2,1)=\"r\",VALUE(MID(B" + currentRowNum + ":AF" + currentRowNum + ",1,1)),0))");

				}
			}

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			workbook.write(outputStream);
			workbook.close();

			byte[] excelBytes = outputStream.toByteArray();
			// Convertiamo l'array di byte in una stringa Base64
			String base64EncodedString = Base64.getEncoder().encodeToString(excelBytes);

			return base64EncodedString;
		} catch (IOException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	public static String excelToBase64(String filePath) throws ServiceException {
		try {
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fis.read(bytes);
			fis.close();

			// Encode the bytes to Base64
			String base64EncodedString = Base64.getEncoder().encodeToString(bytes);

			return base64EncodedString;
		} catch (IOException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.IMPOSSIBILE_SCRIVERE_EXCEL);
		} catch (Exception e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}
	}

	private static CellStyle createCellStyle(Workbook workbook, IndexedColors bgColor, boolean checkGiorni) {
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setAlignment(HorizontalAlignment.CENTER);
		cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		cellStyle.setBorderTop(BorderStyle.MEDIUM);
		cellStyle.setBorderLeft(BorderStyle.MEDIUM);
		cellStyle.setBorderRight(BorderStyle.MEDIUM);
		cellStyle.setBorderBottom(BorderStyle.MEDIUM);

		// Set the background color using IndexedColors
		cellStyle.setFillForegroundColor(bgColor.index);
		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		Font fontStyle = workbook.createFont();
		if ((IndexedColors.GREEN.getIndex() == bgColor.index) && checkGiorni)
			fontStyle.setColor(bgColor.index);
		fontStyle.setFontName("Times New Roman");
		cellStyle.setFont(fontStyle);
		return cellStyle;
	}
}
