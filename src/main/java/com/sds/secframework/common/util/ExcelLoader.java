package com.sds.secframework.common.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
<PRE>
* ExcelLoader class

* Constructor
 ExcelLoader(String filePath) throws FileNotFounException, IOException
 ExcelLoader(InputStream inStream) throws IOException
 ExcelLoader(FileInputStream fileStream) throws IOException

* Provide Method List
 int       getSheetsCount()
 String    getSheetName(int)
 int       getRowsCount(int)
 int       getColsCount(int)
 String    getCellData(int, int, int)
 ArrayList getSheetData(int)
 
 Ex1. Excel File을 읽어 데이터를 출력 (getCellData 사용)
     ExcelLoader excel = new ExcelLoader("/USER_DEFINED_PATH/usage.xls");   // FileSystem의 usage.xls 파일을 읽는다.
     
     int sheetsCount = excel.getSheetsCount();                              // 읽은 Excel File의 Sheet 갯수를 얻는다.
     for(int i=0;i<sheetsCount;i++) {                                       // Sheet 갯수만큼 Loop
       for(int j=0;j<excel.getRowsCount(i);j++) {
         for(int k=0;k<excel.getColsCount(i);k++) {
         }
       }
     }

 Ex2. Excel File을 읽어 데이터를 출력 (getSheetData 사용)
     ExcelLoader excel = new ExcelLoader(stream);                           // File Upload를 통한 Stream Data를 읽는다. 
     
     int sheetsCount = excel.getSheetsCount();                              // 읽은 Excel File의 Sheet 갯수를 얻는다.
     for(int i=0;i<sheetsCount;i++) {                                       // Sheet 갯수만큼 Loop
       ArrayList sheetData = excel.getSheetData(i);                         // Sheet Data를 ArrayList 형태로 받음
       for(int j=0;j<excel.getRowsCount(i);j++) {
         ArrayList rowData = (ArrayList)sheetData.get(j);                   // Row Data를 ArrayList 형태로 받음
         for(int k=0;k<excel.getColsCount(i);k++) {
         }
       }
     }

* Required Method List
 N/A

* Dependency 3rd-party Library List
 poi-3.0.2-CUSTOM-20080402.jar (Modified from poi-3.0.2-FINAL-20080204.jar)
 poi-contrib-3.0.2-FINAL-20080204.jar
 poi-scratchpad-3.0.2-FINAL-20080204.jar
</PRE>

* @author 김판기

* @since
<PRE>
2008.01.30 created
</PRE>
*/
public class ExcelLoader {
	
	Sheet[] sheets;
	
	/**
	 * Constructor : FileSystem에서 Excel 파일을 읽는다.
	 * 
	 * @param filePath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public ExcelLoader(String filePath) throws FileNotFoundException, IOException {
		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));
		init(fs);
	}
	
	/**
	 * Constructor : InputStream으로부터 Excel Data를 읽는다.
	 * 
	 * @param inStream
	 * @throws IOException
	 */
	public ExcelLoader(InputStream inStream) throws IOException {
		POIFSFileSystem fs = new POIFSFileSystem(inStream);
		init(fs);
	}
	
	/**
	 * Constructor : FileInputStream으로부터 Excel Data를 읽는다.
	 * 
	 * @param fileStream
	 * @throws IOException
	 */
	public ExcelLoader(FileInputStream fileStream) throws IOException {
		POIFSFileSystem fs = new POIFSFileSystem(fileStream);
		init(fs);
	}
	
	private void init(POIFSFileSystem fs) throws IOException {
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		int sheetsCount = wb.getNumberOfSheets();
		HSSFSheet[] hsheets = new HSSFSheet[sheetsCount];
		
		sheets = new Sheet[sheetsCount];
		
		for(int i=0;i<sheetsCount;i++) {
			hsheets[i] = wb.getSheetAt(i);
			
			Sheet sheet = new Sheet(wb.getSheetName(i));
			
			for(int j=0;j<=hsheets[i].getLastRowNum();j++) {
				HSSFRow row = hsheets[i].getRow(j);
				
				if (row == null)
					continue;

				int colsCount = row.getLastCellNum();
				ArrayList cellData = new ArrayList();
				
				for(int k=0;k<=colsCount;k++) {
					HSSFCell cell = row.getCell((short)k);
					
					String cellValue = null;
					
					if (cell != null) {
						switch(cell.getCellType()) {
						case HSSFCell.CELL_TYPE_BLANK:
							cellValue = "";
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN:
							cellValue = String.valueOf(cell.getBooleanCellValue());
							break;
						case HSSFCell.CELL_TYPE_ERROR:
							cellValue = "ERROR CELL";
							break;
						case HSSFCell.CELL_TYPE_FORMULA:
							cellValue = cell.getCellFormula();
							break;
						case HSSFCell.CELL_TYPE_NUMERIC:
							cellValue = String.valueOf(cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING:
							cellValue = cell.getRichStringCellValue().toString();
							break;
						}
					}

					cellData.add(cellValue);
				}
				
				sheet.addRow(cellData);
			}
			
			sheets[i] = sheet;
		}
	}
	
	/**
	 * Sheet 갯수를 반환
	 * 
	 * @return
	 */
	public int getSheetsCount() {
		return sheets.length;
	}
	
	/**
	 * Sheet명을 반환
	 * 
	 * @param sheetIndex
	 * @return
	 */
	public String getSheetName(int sheetIndex) {
		return this.sheets[sheetIndex].getSheetName();
	}
	
	/**
	 * Row 수를 반환
	 * 
	 * @param sheetIndex
	 * @return
	 */
	public int getRowsCount(int sheetIndex) {
		return this.sheets[sheetIndex].getRowsCount();
	}

	/**
	 * Column 수를 반환
	 * 
	 * @param sheetIndex
	 * @return
	 */
	public int getColsCount(int sheetIndex) {
		return this.sheets[sheetIndex].getColsCount();
	}
	
	/**
	 * Cell의 Data를 반환
	 * 
	 * @param sheetIndex
	 * @param row
	 * @param col
	 * @return
	 */
	public String getCellData(int sheetIndex, int row, int col) {
		return this.sheets[sheetIndex].getCellData(row, col);
	}
	
	/**
	 * Sheet Data를 ArrayList 형태로 반환
	 * 
	 * @param sheetIndex
	 * @return
	 */
	public ArrayList getSheetData(int sheetIndex) {
		return this.sheets[sheetIndex].getSheetData();
	}

	private class Sheet {
		private String sheetName;
		private ArrayList sheetData;
		private int colsCount = 0;
		
		private Sheet(String sheetName) {
			this.sheetName = sheetName;
			sheetData = new ArrayList();
		}
		
		private void addRow(ArrayList cellData) {
			this.colsCount = this.colsCount < cellData.size() ? cellData.size() : this.colsCount;
			sheetData.add(cellData);
		}
		
		private String getSheetName() {
			return this.sheetName;
		}
		
		private int getRowsCount() {
			return sheetData.size();
		}
		
		private int getColsCount() {
			return this.colsCount;
		}
		
		private String getCellData(int row, int col) {
			if(row >= this.sheetData.size()) return null;
			
			ArrayList selectedRow = (ArrayList)this.sheetData.get(row);
			
			if(col >= selectedRow.size()) return null;
			
			return (String)selectedRow.get(col);
		}
		
		private ArrayList getSheetData() {
			return this.sheetData;
		}
	}
}
