package com.org.mavenProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TestMethods {
	static WebDriver driver;
	static XSSFSheet ws, ws2;
	static XSSFWorkbook wb;
	static File src;
	static FileInputStream fis = null;
	static FileOutputStream fos = null;

	public static String[][] fetchExcelData(String path, String sheet) throws IOException {
		src = new File(path);
		fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);

		ws = wb.getSheet(sheet);
		ws2 = wb.getSheet("Sheet2");

		int RowNumber = ws.getLastRowNum() + 1;
		int colNumber = ws.getRow(0).getLastCellNum();

		String[][] data = new String[RowNumber][colNumber];

		for (int i = 0; i < RowNumber; i++) {
			XSSFRow row = ws.getRow(i);
			for (int j = 0; j < colNumber; j++) {
				XSSFCell cell = row.getCell(j);
				String value = cellToString(cell);
				data[i][j] = value;

			}
			/*
			 * XSSFCell cell1 = row.createCell(3);
			 * cell1.setCellType(cell1.CELL_TYPE_STRING);
			 * cell1.setCellValue("Passed"); FileOutputStream fos = new
			 * FileOutputStream(src); wb.write(fos); fos.close();
			 */
		}
		wb.close();
		fis.close();
		return data;

	}

	public static void UpdateSheetStatus(String sheet, String Result, int row, int col) throws IOException {
		fis = new FileInputStream(src);
		wb = new XSSFWorkbook(fis);
		ws = wb.getSheet(sheet);
		XSSFRow rows = ws.getRow(row);
		XSSFCell cell1 = rows.createCell(col);
		cell1.setCellType(Cell.CELL_TYPE_STRING);
		cell1.setCellValue(Result);
		try {
			fos = new FileOutputStream(src);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		wb.write(fos);
		fos.close();
	}

	public static String cellToString(XSSFCell cell) {
		Object result = null;
		String strReturn = null;

		if (cell == null) {
			strReturn = "";

		} else {
			switch (cell.getCellType()) {
			case 0:
				result = cell.getNumericCellValue();
				strReturn = result.toString();
				break;

			case 1:
				result = cell.getStringCellValue();
				strReturn = result.toString();
				break;

			default:
				strReturn = null;
			}
		}
		return strReturn;
	}

	public static String SendKeys(WebDriver driver, String strLocatorType, String strLocatorName,
			String StrLocatorValue) {
		String msg = "Success";
		try {
			switch (strLocatorType.toLowerCase()) {

			case "id":
				driver.findElement(By.id(strLocatorName)).sendKeys(StrLocatorValue);
				break;

			case "xpath":
				driver.findElement(By.xpath(strLocatorName)).sendKeys(StrLocatorValue);
				break;

			case "classname":
				driver.findElement(By.className(strLocatorName)).sendKeys(StrLocatorValue);
				break;

			case "cssselector":
				driver.findElement(By.cssSelector(strLocatorName)).sendKeys(StrLocatorValue);
				break;

			case "linktext":
				driver.findElement(By.linkText(strLocatorName)).sendKeys(StrLocatorValue);
				break;

			default:
				msg = "Fail";
			}
		} catch (Exception ex) {
			msg = ex.getMessage();
		}
		return msg;
	}

	public static String clickButton(WebDriver driver, String strLocatorType, String strLocatorName) {
		String msg = "Success";
		try {
			switch (strLocatorType.toLowerCase()) {

			case "id":
				driver.findElement(By.id(strLocatorName)).click();
				break;

			case "xpath":
				driver.findElement(By.xpath(strLocatorName)).click();
				break;

			case "classname":
				driver.findElement(By.className(strLocatorName)).click();
				break;

			case "cssselector":
				driver.findElement(By.cssSelector(strLocatorName)).click();
				break;

			case "linktext":
				driver.findElement(By.linkText(strLocatorName)).click();
				break;

			default:
				msg = "Fail";
			}
		} catch (Exception ex) {
			msg = ex.getMessage();
		}
		return msg;
	}

	public static String mouseOver(WebDriver driver, String strLocatorType, String strLocatorName) {
		Actions action = new Actions(driver);
		WebElement we = null;
		String msg = "Success";
		try {
			switch (strLocatorType.toLowerCase()) {

			case "id":
				we = driver.findElement(By.id(strLocatorName));
				break;

			case "xpath":
				we = driver.findElement(By.xpath(strLocatorName));
				break;

			case "classname":
				we = driver.findElement(By.className(strLocatorName));
				break;

			case "cssselector":
				we = driver.findElement(By.cssSelector(strLocatorName));
				break;

			case "linktext":
				we = driver.findElement(By.linkText(strLocatorName));
				break;

			default:
				msg = "Fail";
			}
		} catch (Exception ex) {
			msg = ex.getMessage();
		}
		action.moveToElement(we).build().perform();
		return msg;
	}

	public static List<Integer> findRow(String cellContent) {
		XSSFSheet sheet = ws2;
		String rowval = "";
		List<Integer> rownum = new ArrayList<Integer>();
		for (Row row : sheet) {
			if (row.getCell(CellReference.convertColStringToIndex("A")).getCellType() == Cell.CELL_TYPE_NUMERIC) {
				rowval = row.getCell(CellReference.convertColStringToIndex("A")).toString();
				if (rowval.equals(cellContent)) {
					// return row.getRowNum();
					rownum.add(row.getRowNum());
				}
			}
		}
		return rownum;
	}

	}