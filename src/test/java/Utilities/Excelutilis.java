package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excelutilis {
Workbook wb;

	

	public int rowcount(String sheet) throws Exception {
		FileInputStream fi = new FileInputStream("D:\\mahiselenium\\Stockaccounting_BDD\\Testinput\\InputSheet.xlsx");
		wb = new XSSFWorkbook(fi);
		
		return wb.getSheet(sheet).getLastRowNum();
	}

	public int colcount(String sheet, int row) throws Exception {
		FileInputStream fi = new FileInputStream("D:\\mahiselenium\\Stockaccounting_BDD\\Testinput\\InputSheet.xlsx");
		wb = new XSSFWorkbook(fi);
		return wb.getSheet(sheet).getRow(row).getLastCellNum();
	}

	public String getcelldata(String sheet, int row, int col) throws Exception {
		FileInputStream fi = new FileInputStream("D:\\mahiselenium\\Stockaccounting_BDD\\Testinput\\InputSheet.xlsx");
		wb = new XSSFWorkbook(fi);
		String data;
		if (wb.getSheet(sheet).getRow(row).getCell(col).getCellType() == Cell.CELL_TYPE_NUMERIC) {
			int d = (int) wb.getSheet(sheet).getRow(row).getCell(col).getNumericCellValue();
			data = String.valueOf(d);
		} else {

			data = wb.getSheet(sheet).getRow(row).getCell(col).getStringCellValue();
		}
		return data;
	}

	public void setcelldata(String sheet, int row, int col, String status) throws Exception {
		FileInputStream fi = new FileInputStream("D:\\mahiselenium\\Stockaccounting_BDD\\Testinput\\InputSheet.xlsx");
		wb = new XSSFWorkbook(fi);
		org.apache.poi.ss.usermodel.Sheet ws = wb.getSheet(sheet);
		Row rc = ws.getRow(row);
		Cell cc = rc.createCell(col);
		cc.setCellValue(status);
		if (status.equalsIgnoreCase("pass")) {
			CellStyle style = wb.createCellStyle();
			Font fn = wb.createFont();
			fn.setColor(IndexedColors.GREEN.getIndex());
			fn.setBold(true);
			style.setFont(fn);
			rc.getCell(col).setCellStyle(style);
		} else if (status.equalsIgnoreCase("fail")) {
			CellStyle style = wb.createCellStyle();
			Font fn = wb.createFont();
			fn.setColor(IndexedColors.RED.getIndex());
			fn.setBold(true);
			style.setFont(fn);
			rc.getCell(col).setCellStyle(style);

		}
		FileOutputStream fo = new FileOutputStream("D:\\mahiselenium\\Stockaccounting_BDD\\Testinput\\InputSheet.xlsx");
		wb.write(fo);
		wb.close();
		fo.close();
	}

}
