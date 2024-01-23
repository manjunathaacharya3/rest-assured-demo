package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {

	private String path;

	public ExcelReader(String path) {
		this.path = path;
	}

	private XSSFWorkbook loadExcel(String path) {
		XSSFWorkbook workBook = null;
		try (FileInputStream fis = new FileInputStream(new File(path))) {
			workBook = new XSSFWorkbook(fis);
		} catch (Exception e) {
			throw new RuntimeException("Error while loading the file \n" + e.getMessage());
		}
		return workBook;
	}

	public String getCellData(String sheetName, String rowName, String colName) {
		XSSFWorkbook workBook = loadExcel(path);
		XSSFSheet targetSheet = workBook.getSheet(sheetName);
		int colIndex = -1;
		int rowIndex = -1;
		if (targetSheet == null) {
			throw new RuntimeException(String.format("Unable to find the sheet name %s in %s", sheetName, this.path));
		}
		Row firstRow = targetSheet.getRow(targetSheet.getFirstRowNum());
		for (int j = 0; j < firstRow.getLastCellNum(); j++) {
			if (firstRow.getCell(j).getStringCellValue().equals(colName)) {
				colIndex = j;
				break;
			}
		}
		if (colIndex == -1) {
			throw new RuntimeException("No colum found for - " + colName);
		}
		int totalRows = targetSheet.getLastRowNum();
		for (int i = 0; i < totalRows; i++) {
			if (targetSheet.getRow(i).getCell(targetSheet.getRow(i).getFirstCellNum()).getStringCellValue()
					.equals(rowName)) {
				rowIndex = i;
				break;
			}
		}
		if (rowIndex == -1) {
			throw new RuntimeException("No row found for - " + colName);
		}
		String cellDataInString = "";
		if (targetSheet.getRow(rowIndex).getCell(colIndex).getCellType() == CellType.STRING) {
			cellDataInString = targetSheet.getRow(rowIndex).getCell(colIndex).getStringCellValue();
		} else {
			cellDataInString = String.valueOf(targetSheet.getRow(rowIndex).getCell(colIndex).getNumericCellValue());
		}
		return cellDataInString;
	}
}
