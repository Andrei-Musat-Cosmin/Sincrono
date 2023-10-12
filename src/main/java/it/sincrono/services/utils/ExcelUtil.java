package it.sincrono.services.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
			CellStyle baseCellStyle = workbook.createCellStyle();
			baseCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			baseCellStyle.setAlignment(HorizontalAlignment.CENTER);
			baseCellStyle.setBorderTop(BorderStyle.MEDIUM);
			baseCellStyle.setBorderLeft(BorderStyle.MEDIUM);
			baseCellStyle.setBorderRight(BorderStyle.MEDIUM);
			baseCellStyle.setBorderBottom(BorderStyle.MEDIUM);

			/* STYLE FOR DEFAULT */
			CellStyle cellStyleDefault = baseCellStyle;
			cellStyleDefault.setFillForegroundColor(IndexedColors.WHITE.getIndex());
			cellStyleDefault.setFillBackgroundColor(IndexedColors.WHITE.getIndex());

			/* STYLE FOR FERIE */
			CellStyle cellStyleFerie = baseCellStyle;
			cellStyleFerie.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			cellStyleFerie.setFillBackgroundColor(IndexedColors.YELLOW.getIndex());

			/* STYLE FOR MALATTIA */
			CellStyle cellStyleMalattia = baseCellStyle;
			cellStyleMalattia.setFillForegroundColor(IndexedColors.BRIGHT_GREEN.getIndex());
			cellStyleMalattia.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex());

			/* STYLE FOR PERMESSI */
			CellStyle cellStylePermessi = baseCellStyle;
			cellStylePermessi.setFillForegroundColor(IndexedColors.DARK_RED.getIndex());
			cellStylePermessi.setFillBackgroundColor(IndexedColors.DARK_RED.getIndex());

			/* STYLE FOR ROL */
			CellStyle cellStyleRol = baseCellStyle;
			cellStyleRol.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
			cellStyleRol.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());

			/* STYLE FOR SABDOM */
			CellStyle cellStyleSABDOM = baseCellStyle;
			cellStyleSABDOM.setFillForegroundColor(IndexedColors.DARK_GREEN.getIndex());
			cellStyleRol.setFillBackgroundColor(IndexedColors.DARK_GREEN.getIndex());

			/* STYLE FOR PERMESSI */
			CellStyle cellStyleNODAY = baseCellStyle;
			cellStyleNODAY.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			cellStyleNODAY.setFillBackgroundColor(IndexedColors.GREY_25_PERCENT.getIndex());

			Sheet sheet = workbook.createSheet("Dati Excel");
//			Row headerRow = sheet.createRow(0);
			int rowNum = 0;
			int cellNum = 0;
			String cognomeNome = "";
			Row row = null;
			Cell cell = null;
			row = sheet.createRow(rowNum);
			Calendar calendar = Calendar.getInstance();
			int anno = rapportini.get(0).getAnno();
			int mese = rapportini.get(0).getMese();
			calendar.set(anno, mese - 1, 1);

			int numeroGiorniNelMese = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

			for (int i = 0; i < 31; i++) {
				cell = row.createCell(i + 1);
				cell.setCellValue(i + 1);
				cell.setCellStyle(cellStyleSABDOM);
				sheet.autoSizeColumn(i);
				if (i == 30) {
					sheet.autoSizeColumn(i + 1);
				}
			}

			for (Rapportino rapportino : rapportini) {

				if (!cognomeNome
						.equals(rapportino.getAnagrafica().getCognome() + " " + rapportino.getAnagrafica().getNome())) {
					cognomeNome = rapportino.getAnagrafica().getCognome() + " " + rapportino.getAnagrafica().getNome();
					cellNum = 0;
					rowNum++;
					row = sheet.createRow(rowNum);
					cell = null;
					cell = row.createCell(cellNum);
					cell.setCellStyle(cellStyleDefault);
					cell.setCellValue(cognomeNome);
					cellNum++;
				}

				calendar.set(anno, mese - 1, cellNum - 1);
				int giornoSettimana = calendar.get(Calendar.DAY_OF_WEEK);

				if (giornoSettimana > numeroGiorniNelMese) {
					cell = null;
					cell = row.createCell(cellNum);
					cell.setCellStyle(cellStyleNODAY);
					cell.setCellValue("Q");
				}
				if (giornoSettimana == Calendar.SATURDAY || giornoSettimana == Calendar.SUNDAY) {
					if (giornoSettimana == Calendar.SATURDAY) {
						cell = null;
						cell = row.createCell(cellNum);
						cell.setCellStyle(cellStyleSABDOM);
						cell.setCellValue("q");
					}
				}
				if (rapportino.getOre() != null) {
					cell = null;
					cell = row.createCell(cellNum);
					cell.setCellStyle(cellStyleDefault);
					cell.setCellValue("");
				} else if (rapportino.getFerie() != null) {
					cell = null;
					cell = row.createCell(cellNum);
					cell.setCellStyle(cellStyleFerie);
					cell.setCellValue("F");
				} else if (rapportino.getMalattie() != null) {
					cell = null;
					cell = row.createCell(cellNum);
					cell.setCellStyle(cellStyleMalattia);
					cell.setCellValue("M");
				} else if (rapportino.getPermessi() != null) {
					cell = null;
					cell = row.createCell(cellNum);
					cell.setCellStyle(cellStylePermessi);
					cell.setCellValue(rapportino.getPermessi() + "p");
				}
				cellNum++;

			}
			FileOutputStream outputStream = new FileOutputStream(EXCELPATH);
			workbook.write(outputStream);
			outputStream.close();

			// return excelToBase64();
			return "fatto";
		} catch (IOException e) {
			LOGGER.log(Level.ERROR, e.getMessage());
			throw new ServiceException(ServiceMessages.ERRORE_GENERICO);
		}

	}

	private void write(FileOutputStream outputStream, Row row, Cell cell, Workbook workbook) {

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
}
