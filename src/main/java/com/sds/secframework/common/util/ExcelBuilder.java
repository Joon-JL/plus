package com.sds.secframework.common.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFooter;
import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

/**
<PRE>
* ExcelBuilder class

* Constructor
 ExcelBuilder(String sheetName)
 ExcelBuilder(String[] sheetNames)

* Provide Method List
 int  getSheetsCount()
 void setLandscape(int, boolean)
 void setSheetProtect(int, boolean)
 void setSheetProtect(int, String)
 void setDefaultStyle()
 void setAlign(short)
 void setAlign(short[])
 void setVAlign(short)
 void setVAlign(short[])
 void setBold(boolean)
 void setBold(boolean[])
 void setItalic(boolean)
 void setItalic(boolean[])
 void setStrikeout(boolean)
 void setStrikeout(boolean[])
 void setUnderline(int)
 void setUnderline(int[])
 void setFontColor(short)
 void setFontColor(short[])
 void setFontName(String)
 void setFontName(String[])
 void setFontSize(short)
 void setFontSize(short[])
 void setBgColor(short)
 void setBgColor(shorts[])
 void setBorder(boolean)
 void setBorder(boolean[])
 void setColumnWidth(double[])
 void setColumnWidth(int, double[])
 void setRowHeight(double)
 void save(String) throws IOException
 void download(String, HttpServletResponse, JspWriter)
 void setHeader(int, String, String, String)
 void setFooter(int, String, String, String)
 static String buildHFString(String, short, int, String)
 void addTitleRow(int, String[])
 void addRow(int, String[])
 void addRow(int, String[], int, boolean)
 void addRow(int, String[], Object)
 void addRows(int, String[], Object[])
 
 Ex2. Style을 사용하여 작성할 경우
     ExcelBuilder excel = new ExcelBuilder(new String[] {"Sheet1", "Sheet2"});    
                                                                            // 두개의 Sheet를 생성한다.
     int sheetsCount = excel.getSheetsCount();                              // Sheet의 갯수를 얻어온다.

     for(int i=0;i<sheetsCount;i++) {                                       // Sheet의 수 만큼 Loop
       excel.setHeader(i, "왼쪽 머릿글", "중앙 머릿글", null);              // i번째 Sheet의 머릿글을 설정한다. 
       String rightFooter = ExcelBuilder.buildHFString("굴림", ExcelBuilder.FONTSTYLE_BOLD, 10, "오른쪽 바닥글");
                                                                            // 바닥글로 사용할 문자열을 스타일을 적용해 생성한다.
       excel.setFooter(i, null, null, rightFooter);                         // i번째 Sheet의 바닥글을 설정한다. 
       excel.setAlign(ExcelBuilder.ALIGN_CENTER);                           // 추가될 Row의 모든 Cell을 Center 정렬한다.
       excel.setBold(true);                                                 // 추가될 Row의 모든 Cell을 Bold로 설정한다.
       excel.setItalic(true);                                               // 추가될 Row의 모든 Cell을 Italic으로 설정한다.
       excel.setStrikeout(true);                                            // 추가될 Row의 모든 Cell에 취소선을 설정한다.
       excel.setUnderline(2);                                               // 추가될 Row의 모든 Cell에 밑줄을 설정한다. (2줄)
       excel.setFontColor(ExcelBuilder.COLOR_WHITE);                        // 추가될 Row의 모든 Cell의 글자색을 White로 설정한다.
       excel.setFontName("굴림체");                                         // 추가될 Row의 모든 Cell의 Font명을 굴림체로 설정한다.
       excel.setFontSize((short)11);                                        // 추가될 Row의 모든 Cell의 Font크기를 11pt로 설정한다.
       excel.setBgColor(ExcelBuilder.COLOR_BLUE);                           // 추가될 Row의 모든 Cell의 배경색을 Blue로 설정한다.
       excel.setBorder(true);                                               // 추가될 Row의 모든 Cell의 테두리를 설정한다.
       excel.addTitleRow(i, new String[] {"순번", "첫번째", "두번째", ExcelBuilder.MERGE_LEFT});
                                                                            // i번째 Sheet에 제목을 설정한다. ExcelBuilder.MERGE_LEFT 가 들어가면 앞 Cell과 Merge된다.
       
       excel.setDefaultStyle();                                             // 추가될 Row의 모든 Style을 기본값으로 복원한다.
       excel.setAlign(new short[] {ExcelBuilder.ALIGN_CENTER, ExcelBuilder.ALIGN_RIGHT});
                                                                            // 추가될 Row의 Cell 순서대로 Center, Right 정렬한다. 이후 Cell은 기본값
       excel.setVAlign(ExcelBuilder.VALIGN_CENTER);                         // 추가될 Row의 모든 Cell을 Vertical Center 정렬한다.
       excel.setFontColor(new short[] {0, ExcelBuilder.COLOR_RED, 0});      // 추가될 Row의 Cell 순서대로 Font색을 기본값, Red, 기본값으로 설정한다.
       excel.setFontSize(new short[] {8,0,10});                             // 추가될 Row의 Cell 순서대로 Font크기를 8pt, 기본값, 10pt로 설정한다.
       excel.setBorder(new boolean[] {true});                               // 추가될 Row의 모든 Cell의 테두리를 설정한다. (cause. 배열의 크기가 1)
       excel.addRows(i, new String[] {ExcelBuilder.ROW_INDEX, "first", "second", "third"}, vos);
                                                                            // VO배열(vos)의 first, second, third Field값을 각 Row로 추가한다.
                                                                            // (ROW_INDEX는 Field와 무관하게 vo배열 수 만큼 일련번호를 지정한다. Base : 1) 
       
       excel.setDefaultStyle();                                             // 추가될 Row의 모든 Style을 기본값으로 복원한다.
       excel.addRow(i, new String[] {"시작", ExcelBuilder.MERGE_LEFT, ExcelBuilder.MERGE_LEFT, ExcelBuilder.MERGE_LEFT}, 1, true);
                                                                            // 두번째 Row(RowNum=1)에 Row를 추가하고 이후 Row를 아래로 Shift한다. (3개의 Cell이 Merge됨)
       excel.addRow(i, new String[] {ExcelBuilder.MERGE_UP, "", "", "끝"}); // 마지막 Row에 Row를 추가한다. (ExcelBuilder.MERGE_UP 가 들어간 Cell이 윗 Cell과 Merge됨)
       
       if(i%2 == 1) {
         excel.setLandscape(i, true);                                       // 짝수번째 Sheet는 가로방향 출력으로 설정
         excel.setSheetProtect(i, true);                                    // 짝수번째 Sheet를 보호설정한다. (Password 없음)
       } else {
         excel.setSheetProtect(i, "lock");                                  // 홀수번째 Sheet를 보호설정한다. (Password : lock)
       }
     }
     
     excel.download("usage.xls", response);                            // usage.xls 파일로 다운로드한다.

* Required Method List
 N/A

* Dependency 3rd-party Library List
 poi-3.0.2-CUSTOM-20080402.jar (Modified from poi-3.0.2-FINAL-20080204.jar)
 poi-contrib-3.0.2-FINAL-20080204.jar
 poi-scratchpad-3.0.2-FINAL-20080204.jar

* AWT Troubleshooting
 내부적으로 awt 클래스를 사용하므로 unix 등과 같이 window api를 사용하지 않는 환경에서는 
 WAS 실행시에 -Djava.awt.headless=true 설정을 해주어야 한다. 
</PRE>

* @author 김판기

* @since
<PRE>
2008.01.30 created
2008.04.08 lastest updated
</PRE>
*/
public class ExcelBuilder {

	HSSFWorkbook wb;
	
	HSSFCellStyle style[];
	short[] styleAlign;
	short[] styleVAlign;
	boolean[] styleBold;
	boolean[] styleItalic;
	boolean[] styleStrikeout;
	int[] styleUnderline;
	short[] styleFontColor;
	String[] styleFontName = new String[] {"돋움"};
	short[] styleFontSize;
	short[] styleBgColor;
	boolean[] styleBorder;
	
	boolean styleModified = true;
	
	float rowHeight;
	short[][] columnWidth;
	
	int[] countCell;
	
	public final static String MERGE_LEFT = "MERGE_LEFT";
	public final static String MERGE_UP = "MERGE_UP";

	public final static String ROW_INDEX = "ROW_INDEX";

	public final static short ALIGN_LEFT = HSSFCellStyle.ALIGN_LEFT;
	public final static short ALIGN_CENTER = HSSFCellStyle.ALIGN_CENTER;
	public final static short ALIGN_RIGHT = HSSFCellStyle.ALIGN_RIGHT;
	public final static short ALIGN_JUSTIFY = HSSFCellStyle.ALIGN_JUSTIFY;
	
	public final static short VALIGN_TOP = HSSFCellStyle.VERTICAL_TOP;
	public final static short VALIGN_CENTER = HSSFCellStyle.VERTICAL_CENTER;
	public final static short VALIGN_BOTTOM = HSSFCellStyle.VERTICAL_BOTTOM;
	public final static short VALIGN_JUSTIFY = HSSFCellStyle.VERTICAL_JUSTIFY;

	public final static short COLOR_AQUA = HSSFColor.AQUA.index;
	public final static short COLOR_AUTOMATIC = HSSFColor.AUTOMATIC.index;
	public final static short COLOR_BLACK = HSSFColor.BLACK.index;
	public final static short COLOR_BLUE = HSSFColor.BLUE.index;
	public final static short COLOR_BLUE_GREY = HSSFColor.BLUE_GREY.index;
	public final static short COLOR_BRIGHT_GREEN = HSSFColor.BRIGHT_GREEN.index;
	public final static short COLOR_BROWN = HSSFColor.BROWN.index;
	public final static short COLOR_CORAL = HSSFColor.CORAL.index;
	public final static short COLOR_CORNFLOWER_BLUE = HSSFColor.CORNFLOWER_BLUE.index;
	public final static short COLOR_DARK_BLUE = HSSFColor.DARK_BLUE.index;
	public final static short COLOR_DARK_GREEN = HSSFColor.DARK_GREEN.index;
	public final static short COLOR_DARK_RED = HSSFColor.DARK_RED.index;
	public final static short COLOR_DARK_TEAL = HSSFColor.DARK_TEAL.index;
	public final static short COLOR_DARK_YELLOW = HSSFColor.DARK_YELLOW.index;
	public final static short COLOR_GOLD = HSSFColor.GOLD.index;
	public final static short COLOR_GREEN = HSSFColor.GREEN.index;
	public final static short COLOR_GREY_25_PERCENT = HSSFColor.GREY_25_PERCENT.index;
	public final static short COLOR_GREY_40_PERCENT = HSSFColor.GREY_40_PERCENT.index;
	public final static short COLOR_GREY_50_PERCENT = HSSFColor.GREY_50_PERCENT.index;
	public final static short COLOR_GREY_80_PERCENT = HSSFColor.GREY_80_PERCENT.index;
	public final static short COLOR_INDIGO = HSSFColor.INDIGO.index;
	public final static short COLOR_LAVENDER = HSSFColor.LAVENDER.index;
	public final static short COLOR_LEMON_CHIFFON = HSSFColor.LEMON_CHIFFON.index;
	public final static short COLOR_LIGHT_BLUE = HSSFColor.LIGHT_BLUE.index;
	public final static short COLOR_LIGHT_CORNFLOWER_BLUE = HSSFColor.LIGHT_CORNFLOWER_BLUE.index;
	public final static short COLOR_LIGHT_GREEN = HSSFColor.LIGHT_GREEN.index;
	public final static short COLOR_LIGHT_ORANGE = HSSFColor.LIGHT_ORANGE.index;
	public final static short COLOR_LIGHT_TURQUOISE = HSSFColor.LIGHT_TURQUOISE.index;
	public final static short COLOR_LIGHT_YELLOW = HSSFColor.LIGHT_YELLOW.index;
	public final static short COLOR_LIME = HSSFColor.LIME.index;
	public final static short COLOR_MAROON = HSSFColor.MAROON.index;
	public final static short COLOR_OLIVE_GREEN = HSSFColor.OLIVE_GREEN.index;
	public final static short COLOR_ORANGE = HSSFColor.ORANGE.index;
	public final static short COLOR_ORCHID = HSSFColor.ORCHID.index;
	public final static short COLOR_PALE_BLUE = HSSFColor.PALE_BLUE.index;
	public final static short COLOR_PINK = HSSFColor.PINK.index;
	public final static short COLOR_PLUM = HSSFColor.PLUM.index;
	public final static short COLOR_RED = HSSFColor.RED.index;
	public final static short COLOR_ROSE = HSSFColor.ROSE.index;
	public final static short COLOR_ROYAL_BLUE = HSSFColor.ROYAL_BLUE.index;
	public final static short COLOR_SEA_GREEN = HSSFColor.SEA_GREEN.index;
	public final static short COLOR_SKY_BLUE = HSSFColor.SKY_BLUE.index;
	public final static short COLOR_TAN = HSSFColor.TAN.index;
	public final static short COLOR_TEAL = HSSFColor.TEAL.index;
	public final static short COLOR_TURQUOISE = HSSFColor.TURQUOISE.index;
	public final static short COLOR_VIOLET = HSSFColor.VIOLET.index;
	public final static short COLOR_WHITE = HSSFColor.WHITE.index;
	public final static short COLOR_YELLOW = HSSFColor.YELLOW.index;
	
	public final static short FONTSTYLE_NONE = (short)0;
	public final static short FONTSTYLE_BOLD = (short)1;
	public final static short FONTSTYLE_ITALIC = (short)2;
	public final static short FONTSTYLE_BOLD_ITALIC = (short)3;
	
	/**
	 * Constructor : 한개의 Sheet를 생성한다.
	 * 
	 * @param sheetName Sheet명
	 */
	public ExcelBuilder(String sheetName) {
		if(sheetName != null) {
			wb = new HSSFWorkbook();
			HSSFSheet[] sheets = new HSSFSheet[1];
			sheets[0] = wb.createSheet(sheetName);
			this.columnWidth = new short[1][];
			this.countCell = new int[] {0};
		}
	}
	
	/**
	 * Constructor : 여러개의 Sheet를 생성한다.
	 * 
	 * @param sheetNames Sheet명
	 */
	public ExcelBuilder(String[] sheetNames) {
		if(sheetNames != null && sheetNames.length > 0) {
			wb = new HSSFWorkbook();
			int sheetsCount = sheetNames.length;
			HSSFSheet[] sheets = new HSSFSheet[sheetsCount];
			this.columnWidth = new short[sheetsCount][];
			this.countCell = new int[sheetsCount];
			
			for(int i=0;i<sheetsCount;i++) {
				sheets[i] = wb.createSheet(sheetNames[i]);
				this.countCell[i] = 0;
			}
		}
	}
	
	/**
	 * Sheet의 갯수를 반환한다.
	 * 
	 * @return Sheet의 갯수
	 */
	public int getSheetsCount() {
		return this.wb.getNumberOfSheets();
	}
	
	/**
	 * 프린트 방향을 설정한다.
	 * 
	 * @param sheetIndex Sheet 번호 (Base: 0)
	 * @param isLandscape 가로방향출력여부 (Default: false)
	 */
	public void setLandscape(int sheetIndex, boolean isLandscape) {
		HSSFSheet sheet = this.wb.getSheetAt(sheetIndex);
		
		HSSFPrintSetup ps = sheet.getPrintSetup();
		ps.setLandscape(isLandscape);
	}
	
	/**
	 * Sheet 보호여부를 설정한다. (password 미지정)
	 * 
	 * @param sheetIndex Sheet 번호 (Base: 0)
	 * @param isProtect Sheet 보호여부 (Default: false)
	 */
//	public void setSheetProtect(int sheetIndex, boolean isProtect) {
//		HSSFSheet sheet = this.wb.getSheetAt(sheetIndex);
//		
//		sheet.setProtect(isProtect);
//	}
	
	/**
	 * Sheet 보호를 설정한다. (password 포함)
	 * 
	 * @param sheetIndex Sheet 번호 (Base: 0)
	 * @param password 보호 비밀번호
	 */
	public void setSheetProtect(int sheetIndex, String password) {
		HSSFSheet sheet = this.wb.getSheetAt(sheetIndex);
		
		sheet.protectSheet(password);
	}

	/**
	 * 셀스타일을 기본값으로 복원한다.
	 */
	public void setDefaultStyle() {
		style = null;
		styleAlign = null;
		styleVAlign = null;
		styleBold = null;
		styleItalic = null;
		styleStrikeout = null;
		styleUnderline = null;
		styleFontColor = null;
		styleFontName = new String[] {"돋움"};
		styleFontSize = null;
		styleBgColor = null;
		styleBorder = null;
		rowHeight = 0;
		
		styleModified = true;
	}
	
	/**
	 * Cell의 Align을 설정한다. (Row의 모든 Cell에 적용)
	 * 
	 * @param align
	 */
	public void setAlign(short align) {
		setAlign(new short[] {align});
	}
	/**
	 * Cell의 Align을 설정한다. (배열의 크기가 1이면 Row의 모든 Cell에 적용)
	 * 
	 * @param align
	 */
	public void setAlign(short[] align) {
		this.styleAlign = align;
		styleModified = true;
	}
	
	/**
	 * Cell의 Vertical Align을 설정한다. (Row의 모든 Cell에 적용)
	 * 
	 * @param vAlign
	 */
	public void setVAlign(short vAlign) {
		setVAlign(new short[] {vAlign});
	}
	/**
	 * Cell의 Vertical Align을 설정한다. (배열의 크기가 1이면 Row의 모든 Cell에 적용)
	 * 
	 * @param vAlign
	 */
	public void setVAlign(short[] vAlign) {
		this.styleVAlign = vAlign;
		styleModified = true;
	}
	
	/**
	 * Cell의 Bold값을 설정한다. (Row의 모든 Cell에 적용)
	 * 
	 * @param bold
	 */
	public void setBold(boolean bold) {
		setBold(new boolean[] {bold});
	}
	/**
	 * Cell의 Bold값을 설정한다. (배열의 크기가 1이면 Row의 모든 Cell에 적용)
	 * 
	 * @param bold
	 */
	public void setBold(boolean[] bold) {
		this.styleBold = bold;
		styleModified = true;
	}

	/**
	 * Cell의 Italic값을 설정한다. (Row의 모든 Cell에 적용)
	 * 
	 * @param italic
	 */
	public void setItalic(boolean italic) {
		setItalic(new boolean[] {italic});
	}
	/**
	 * Cell의 Italic값을 설정한다. (배열의 크기가 1이면 Row의 모든 Cell에 적용)
	 * 
	 * @param italic
	 */
	public void setItalic(boolean[] italic) {
		this.styleItalic = italic;
		styleModified = true;
	}

	/**
	 * Cell의 Strikeout(취소선)값을 설정한다. (Row의 모든 Cell에 적용)
	 * 
	 * @param strikeout
	 */
	public void setStrikeout(boolean strikeout) {
		setStrikeout(new boolean[] {strikeout});
	}
	/**
	 * Cell의 Strikeout(취소선)값을 설정한다. (배열의 크기가 1이면 Row의 모든 Cell에 적용)
	 * 
	 * @param strikeout
	 */
	public void setStrikeout(boolean[] strikeout) {
		this.styleStrikeout = strikeout;
		styleModified = true;
	}

	/**
	 * Cell의 Underline값을 설정한다. (Row의 모든 Cell에 적용)
	 * 
	 * @param underline (0: 없음, 1: 한줄, 2: 두줄)
	 */
	public void setUnderline(int underline) {
		setUnderline(new int[] {underline});
	}
	/**
	 * Cell의 Underline값을 설정한다. (배열의 크기가 1이면 Row의 모든 Cell에 적용)
	 * 
	 * @param underline (0: 없음, 1: 한줄, 2: 두줄)
	 */
	public void setUnderline(int[] underline) {
		this.styleUnderline = underline;
		styleModified = true;
	}

	/**
	 * Cell의 Font색을 설정한다. (Row의 모든 Cell에 적용)
	 * 
	 * @param color
	 */
	public void setFontColor(short color) {
		setFontColor(new short[] {color});
	}
	/**
	 * Cell의 Font색을 설정한다. (배열의 크기가 1이면 Row의 모든 Cell에 적용)
	 * 
	 * @param color
	 */
	public void setFontColor(short[] color) {
		this.styleFontColor = color;
		styleModified = true;
	}

	/**
	 * Cell의 Font명을 설정한다. (배열의 크기가 1이면 Row의 모든 Cell에 적용)
	 * 
	 * @param fontName
	 */
	public void setFontName(String fontName) {
		setFontName(new String[] {fontName});
	}
	/**
	 * Cell의 Font명을 설정한다. (배열의 크기가 1이면 Row의 모든 Cell에 적용)
	 * 
	 * @param fontName
	 */
	public void setFontName(String[] fontName) {
		this.styleFontName = fontName;
		styleModified = true;
	}

	/**
	 * Cell의 Font크기(pt)를 설정한다. (Row의 모든 Cell에 적용)
	 * 
	 * @param size
	 */
	public void setFontSize(short size) {
		setFontSize(new short[] {size});
	}
	/**
	 * Cell의 Font크기(pt)를 설정한다. (배열의 크기가 1이면 Row의 모든 Cell에 적용)
	 * 
	 * @param size
	 */
	public void setFontSize(short[] size) {
		this.styleFontSize = size;
		styleModified = true;
	}

	/**
	 * Cell의 배경색을 설정한다. (Row의 모든 Cell에 적용)
	 * 
	 * @param color
	 */
	public void setBgColor(short color) {
		setBgColor(new short[] {color});
	}
	/**
	 * Cell의 배경색을 설정한다. (배열의 크기가 1이면 Row의 모든 Cell에 적용)
	 * 
	 * @param color
	 */
	public void setBgColor(short[] color) {
		this.styleBgColor = color;
		styleModified = true;
	}
	
	/**
	 * Cell의 테두리여부를 설정한다. (Row의 모든 Cell에 적용)
	 * 
	 * @param border
	 */
	public void setBorder(boolean border) {
		setBorder(new boolean[] {border});
	}
	/**
	 * Cell의 테두리여부를 설정한다. (배열의 크기가 1이면 Row의 모든 Cell에 적용)
	 * 
	 * @param border
	 */
	public void setBorder(boolean[] border) {
		this.styleBorder = border;
		styleModified = true;
	}

	/**
	 * 첫번째 Sheet의 각 Cell별 너비를 지정한다. (0 이하 이면 기본값)
	 * 
	 * @param width
	 */
	public void setColumnWidth(double[] width) {
		setColumnWidth(0, width);
	}
	/**
	 * sheetIndex번째 Sheet의 각 Cell별 너비를 지정한다. (0 이하 이면 기본값)
	 * 
	 * @param sheetIndex
	 * @param width
	 */
	public void setColumnWidth(int sheetIndex, double[] width) {
		int columnCount = width.length;
		this.columnWidth[sheetIndex] = new short[columnCount];
		
		for (int i=0;i<columnCount;i++) {
			double w = 256 * width[i];
			this.columnWidth[sheetIndex][i] = (short)w;
		}
	}

	/**
	 * Row의 높이를 지정한다. (setDefaultStyle() 호출 전이나 높이 재지정 전까지 유지)
	 * 
	 * @param height
	 */
	public void setRowHeight(double height) {
		this.rowHeight = (float)height;
	}
	
	private void setCountCell(int sheetIndex, int count) {
		if (countCell[sheetIndex] < count)
			countCell[sheetIndex] = count;
	}
	
	private void setColumnWidth() {
		int sheetCount = this.wb.getNumberOfSheets();
		
		for (int i=0;i<sheetCount;i++) {
			HSSFSheet sheet = this.wb.getSheetAt(i);
			
			int cellCount = countCell[i];
			
			for (int j=0;j<cellCount;j++) {
				if (this.columnWidth[i] == null)
					sheet.autoSizeColumn((short)j);
				else if (this.columnWidth[i].length == 1)
					sheet.setColumnWidth((short)j, this.columnWidth[i][0]);
				else if (this.columnWidth[i].length > j && this.columnWidth[i][j] > 0)
					sheet.setColumnWidth((short)j, this.columnWidth[i][j]);
				else
					sheet.autoSizeColumn((short)j);
			}
		}
	}
	
	private void setRowHeight(HSSFRow row) {
		if (this.rowHeight <= 0)
			return;
		
		row.setHeightInPoints(this.rowHeight);
	}

	/**
	 * Excel File을 FileSystem에 저장한다.
	 * 
	 * @param filePath 저장할 파일의 Full Path (확장자 포함)
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save(String filePath) throws IOException {
		setColumnWidth();

		FileOutputStream fileOut = null;
		
		try {
			fileOut = new FileOutputStream(filePath);
			wb.write(fileOut);
		} catch(FileNotFoundException e) {
			//throw e;
		} catch(IOException e) {
			throw e;
		} finally {
			if(fileOut != null)	fileOut.close();
		}
	}
	
	/**
	 * Excel File을 Download한다.
	 * 
	 * @param fileName 다운로드할 파일명 (확장자 포함)
	 * @param response
	 * @param out
	 */
	public void download(String fileName, HttpServletResponse response) {
		setColumnWidth();

		try {
			fileName = new String(fileName.getBytes("EUC-KR"), "8859_1");
		} catch(Exception e) {
			
		}

		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		response.setHeader("Content-Description", "JSP Generated Data");
		response.setHeader("Content-Transfer-Encoding", "binary;");
		response.setContentType("application/vnd.ms-excel"); 

		ServletOutputStream outStream = null;

		try {
			outStream = response.getOutputStream();
			wb.write(outStream);
		} catch(IOException e) {
			//throw e;
		} finally {
			try {
				if(outStream != null)	outStream.close();
			} catch(IOException e) {
				//throw e;
			}
		}
	}
	
	/**
	 * Sheet의 머릿글을 설정한다.
	 * 
	 * @param sheetIndex
	 * @param leftHeader 왼쪽 머릿글 (null 이면 설정하지 않음)
	 * @param centerHeader 중앙 머릿글 (null 이면 설정하지 않음)
	 * @param rightHeader 오른쪽 머릿글 (null 이면 설정하지 않음)
	 */
	public void setHeader(int sheetIndex, String leftHeader, String centerHeader, String rightHeader) {
		HSSFSheet sheet = this.wb.getSheetAt(sheetIndex);
		HSSFHeader header = sheet.getHeader();
		
		if(leftHeader != null) {
			header.setLeft(leftHeader);
		}
		
		if(centerHeader != null) {
			header.setCenter(centerHeader);
		}
		
		if(rightHeader != null) {
			header.setRight(rightHeader);
		}
	}
	
	
	/**
	 * Sheet의 바닥글을 설정한다.
	 * 
	 * @param sheetIndex
	 * @param leftFooter 왼쪽 바닥글 (null 이면 설정하지 않음)
	 * @param centerFooter 중앙 바닥글 (null 이면 설정하지 않음)
	 * @param rightFooter 오른쪽 바닥글 (null 이면 설정하지 않음)
	 */
	public void setFooter(int sheetIndex, String leftFooter, String centerFooter, String rightFooter) {
		HSSFSheet sheet = this.wb.getSheetAt(sheetIndex);
		HSSFFooter footer = sheet.getFooter();
		
		if(leftFooter != null) {
			footer.setLeft(leftFooter);
		}
		
		if(centerFooter != null) {
			footer.setCenter(centerFooter);
		}
		
		if(rightFooter != null) {
			footer.setRight(rightFooter);
		}
	}
	
	/**
	 * 머릿글/바닥글에 사용할 문자열을 스타일을 적용해 만든다.
	 * 
	 * @param fontName 폰트명
	 * @param fontStyle 폰트스타일
	 * @param fontSize 폰트사이즈(pt)
	 * @return 스타일이 적용된 문자열
	 */
	public static String buildHFString(String fontName, short fontStyle, int fontSize, String text) {
		String hfString = "&";
		
		if(fontName != null) {
			hfString += "\"" + fontName.replaceAll(",", "") + ",";
		} else {
			hfString += "\",";
		}
		
		switch(fontStyle) {
			case FONTSTYLE_BOLD :
				hfString += "Bold\"";
				break;
			case FONTSTYLE_ITALIC :
				hfString += "Italic\"";
				break;
			case FONTSTYLE_BOLD_ITALIC :
				hfString += "Bold Italic\"";
				break;
			default :
				hfString += "\"";
				break;
		}
		
		if(fontSize > 0) {
			hfString += "&" + fontSize;
		}
		
		return hfString+text;
	}

	/**
	 * 첫번째 Row에 Title Row를 추가한다.
	 * 
	 * @param sheetIndex
	 * @param titles (ExcelBuilder.MERGE_LEFT 일 경우 앞 Cell과 Merge, ExcelBuilder.MERGE_UP 일 경우 윗 Cell과 Merge)
	 */
	/*public void addTitleRow(int sheetIndex, String[] titles) {
		HSSFSheet sheet = this.wb.getSheetAt(sheetIndex);
		
		setCountCell(sheetIndex, titles.length);

		if(sheet.rowIterator().hasNext()) {
			sheet.shiftRows(sheet.getFirstRowNum()-1, sheet.getLastRowNum(), 1, true, true);
		}

		HSSFRow row = sheet.createRow(0);
		setRowHeight(row);
		
		initCellStyle(titles.length);
		
		int maxLines = 1;

		if(titles != null && titles.length > 0) {
			int cellCount = titles.length;
			HSSFCell[] cells = new HSSFCell[cellCount];
			
			int mergeStart = 0;

			for(int i=0;i<cellCount;i++) {
				if(titles[i] == MERGE_LEFT) {
					if(i > 0) {
						cells[i] = row.createCell((short)i);
						cells[i].setCellStyle(applyCellStyle(cells[i], mergeStart));
						sheet.addMergedRegion(new Region(0, (short)(i-1), 0, (short)i));
					}
				} else if(titles[i].equals(MERGE_UP)) {
					// titleRow는 윗 Row가 없으므로 MERGE할 수 없음.
				} else {
					mergeStart = i;
					
					cells[i] = row.createCell((short)i);
					cells[i].setCellValue(new HSSFRichTextString(titles[i]));
					cells[i].setCellStyle(applyCellStyle(cells[i],i));
					
					if (titles[i].indexOf("\n") > 0) {
						cells[i].getCellStyle().setWrapText(true);
						
						if (this.rowHeight == 0) {
							String[] lines = titles[i].split("\n");
							if (lines.length > maxLines) {
								row.setHeight((short)(row.getHeight() * lines.length));
								maxLines = lines.length;
							}
						}
					}
				}
			}
		}
	}*/
	public void addTitleRow(int sheetIndex, String[] titles) {
		HSSFSheet sheet = this.wb.getSheetAt(sheetIndex);
		
		setCountCell(sheetIndex, titles.length);

		if(sheet.rowIterator().hasNext()) {
			sheet.shiftRows(sheet.getFirstRowNum()-1, sheet.getLastRowNum(), 1, true, true);
		}
		
		//	2010.10.02 첫 번째 row에 Confidential 문구 삽입
		HSSFRow confidentialRow = sheet.createRow(0);
		//HSSFCell confidentialCell = confidentialRow.createCell((short)(titles.length-1));
		HSSFCell confidentialCell = confidentialRow.createCell((int)(0),(int)(0));		
		HSSFCellStyle confidentialStyle = this.wb.createCellStyle(); 
		HSSFFont confidentialFont = this.wb.createFont();
		confidentialFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		confidentialFont.setColor(COLOR_RED);
		confidentialStyle.setFont(confidentialFont);
		confidentialStyle.setFillForegroundColor(HSSFColor.WHITE.index);
		confidentialStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		confidentialCell.setCellStyle(confidentialStyle);
		confidentialCell.setCellValue(new HSSFRichTextString("Confidential"));

		
		HSSFRow row = sheet.createRow(1);
		setRowHeight(row);
		
		initCellStyle(titles.length);
		
		int maxLines = 1;

		if(titles != null && titles.length > 0) {
			int cellCount = titles.length;
			HSSFCell[] cells = new HSSFCell[cellCount];
			
			int mergeStart = 0;

			for(int i=0;i<cellCount;i++) {
				if(titles[i] == MERGE_LEFT) {
					if(i > 0) {
						cells[i] = row.createCell((short)i);
						cells[i].setCellStyle(applyCellStyle(cells[i], mergeStart));
						sheet.addMergedRegion(new Region(0, (short)(i-1), 0, (short)i));
					}
				} else if(titles[i].equals(MERGE_UP)) {
					// titleRow는 윗 Row가 없으므로 MERGE할 수 없음.
				} else {
					mergeStart = i;
					
					cells[i] = row.createCell((short)i);
					cells[i].setCellValue(new HSSFRichTextString(titles[i]));
					cells[i].setCellStyle(applyCellStyle(cells[i],i));
					
					if (titles[i].indexOf("\n") > 0) {
						cells[i].getCellStyle().setWrapText(true);
						
						if (this.rowHeight == 0) {
							String[] lines = titles[i].split("\n");
							if (lines.length > maxLines) {
								row.setHeight((short)(row.getHeight() * lines.length));
								maxLines = lines.length;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 마지막 Row에 Row 추가
	 * 
	 * @param sheetIndex
	 * @param cellValues (null일 경우 앞 Cell과 Merge)
	 */
	public void addRow(int sheetIndex, String[] cellValues) {
		addRow(sheetIndex, cellValues, -1, false);
	}
	/**
	 * 원하는 Row에 Row 추가
	 * 
	 * @param sheetIndex
	 * @param cellValues (ExcelBuilder.MERGE_LEFT 일 경우 앞 Cell과 Merge, ExcelBuilder.MERGE_UP 일 경우 윗 Cell과 Merge)
	 * @param rowNum 추가할 RowNum (Base: 0)
	 * @param isShift RowNum 이하의 Rows의 Shift여부 
	 */
	public void addRow(int sheetIndex, String[] cellValues, int rowNum, boolean isShift) {
		HSSFSheet sheet = this.wb.getSheetAt(sheetIndex);

		setCountCell(sheetIndex, cellValues.length);

		if(rowNum > -1) {
			if(isShift && sheet.rowIterator().hasNext() && sheet.getLastRowNum() > rowNum) {
				sheet.shiftRows(rowNum, sheet.getLastRowNum(), 1, true, true);
			}
		} else {
			rowNum = sheet.rowIterator().hasNext() ? sheet.getLastRowNum()+1 : 0;
		}

		HSSFRow row = sheet.createRow(rowNum);
		setRowHeight(row);
		
		initCellStyle(cellValues.length);
		
		int maxLines = 1;

		if(cellValues != null && cellValues.length > 0) {
			int cellCount = cellValues.length;
			HSSFCell[] cells = new HSSFCell[cellCount];
			
			int mergeStart = 0;

			for(int i=0;i<cellCount;i++) {
				if(cellValues[i] == MERGE_LEFT) {
					if(i > 0) {
						cells[i] = row.createCell((short)i);
						cells[i].setCellStyle(applyCellStyle(cells[i], mergeStart));
						sheet.addMergedRegion(new Region(rowNum, (short)(i-1), rowNum, (short)i));
					}
				} else if(cellValues[i].equals(MERGE_UP)) {
					if(rowNum > 0) {
						cells[i] = row.createCell((short)i);
						cells[i].setCellStyle(applyCellStyle(cells[i], i));
						sheet.addMergedRegion(new Region(rowNum-1, (short)i, rowNum, (short)i));
					}
				} else {
					mergeStart = i;
					
					cells[i] = row.createCell((short)i);
					cells[i].setCellValue(new HSSFRichTextString(cellValues[i]));
					cells[i].setCellStyle(applyCellStyle(cells[i],i));
					
					if (cellValues[i].indexOf("\n") > 0) {
						cells[i].getCellStyle().setWrapText(true);
						
						if (this.rowHeight == 0) {
							String[] lines = cellValues[i].split("\n");
							if (lines.length > maxLines) {
								row.setHeight((short)(row.getHeight() * lines.length));
								maxLines = lines.length;
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 마지막 Row에 VO로 부터 읽은 값을 추가
	 * 
	 * @param sheetIndex
	 * @param fieldNames 출력할 VO의 Field명 (ExcelBuilder.MERGE_LEFT 일 경우 앞 Cell과 Merge, ExcelBuilder.MERGE_UP 일 경우 윗 Cell과 Merge)
	 * @param vo
	 */
	public void addRow(int sheetIndex, String[] fieldNames, Object vo) {
		HSSFSheet sheet = this.wb.getSheetAt(sheetIndex);
		int currentRowNum = sheet.rowIterator().hasNext() ? sheet.getLastRowNum()+1 : 0;
		
		setCountCell(sheetIndex, fieldNames.length);

		if(fieldNames != null && vo != null && fieldNames.length > 0) {
			int fieldCount = fieldNames.length;
			
			HSSFRow row = sheet.createRow(currentRowNum);
			setRowHeight(row);
			
			initCellStyle(fieldCount);
			
			int maxLines = 1;

			HSSFCell[] cells = new HSSFCell[fieldCount];
			
			int mergeStart = 0;

			for(int i=0;i<fieldCount;i++) {
				if(fieldNames[i] == MERGE_LEFT) {
					if(i > 0) {
						cells[i] = row.createCell((short)i);
						cells[i].setCellStyle(applyCellStyle(cells[i], mergeStart));
						sheet.addMergedRegion(new Region(currentRowNum, (short)(i-1), currentRowNum, (short)i));
					}
				} else if(fieldNames[i].equals(MERGE_UP)) {
					if(currentRowNum > 0) {
						cells[i] = row.createCell((short)i);
						cells[i].setCellStyle(applyCellStyle(cells[i], i));
						sheet.addMergedRegion(new Region(currentRowNum-1, (short)i, currentRowNum, (short)i));
					}
				} else {
					mergeStart = i;
					
					String methodName = "get" + fieldNames[i].substring(0,1).toUpperCase();
					methodName += fieldNames[i].length() > 1 ? fieldNames[i].substring(1) : "";
					
					Object value = null;
					try {
						value = vo.getClass().getMethod(methodName, new Class[] {}).invoke(vo, new Object[] {});
						value = value == null ? "" : value;
					} catch(Exception e) {
						value = "";
					}

					cells[i] = row.createCell((short)i);
					cells[i].setCellStyle(applyCellStyle(cells[i],i));
					if(value.getClass().equals(Short.class)
							|| value.getClass().equals(Long.class)
							|| value.getClass().equals(Integer.class)
							|| value.getClass().equals(BigDecimal.class)) {
						cells[i].setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cells[i].setCellValue(Long.parseLong(value.toString()));
					} else if(value.getClass().equals(Double.class)
							|| value.getClass().equals(Float.class)) {
						cells[i].setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cells[i].setCellValue(Double.parseDouble(value.toString()));
					} else {
						String cellValue = value.toString(); 
						cells[i].setCellValue(new HSSFRichTextString(cellValue));
						
						if (cellValue.indexOf("\n") > 0) {
							cells[i].getCellStyle().setWrapText(true);
							
							if (this.rowHeight == 0) {
								String[] lines = cellValue.split("\n");
								if (lines.length > maxLines) {
									row.setHeight((short)(row.getHeight() * lines.length));
									maxLines = lines.length;
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * 마지막 Row에 VO배열로부터 읽은 값들을 추가
	 * 
	 * @param sheetIndex
	 * @param fieldNames 출력할 VO의 Field명 (ExcelBuilder.MERGE_LEFT 일 경우 앞 Cell과 Merge, ExcelBuilder.MERGE_UP 일 경우 윗 Cell과 Merge, ExcelBuilder.ROW_INDEX 일 경우 rowIndex 표시 Base : 1)
	 * @param 
	 */
	public void addRows(int sheetIndex, String[] fieldNames, List list) {
		HSSFSheet sheet = this.wb.getSheetAt(sheetIndex);
		int currentRowNum = sheet.rowIterator().hasNext() ? sheet.getLastRowNum()+1 : 0;
		
		setCountCell(sheetIndex, fieldNames.length);
		initCellStyle(fieldNames.length);

		if(fieldNames != null && list != null && fieldNames.length > 0 && list.size() > 0) {
			int listCount = list.size();
			int fieldCount = fieldNames.length;
			
			long rowIndex = 0;

			for(int i=0;i<listCount;i++) {
				
				ListOrderedMap lom = (ListOrderedMap)list.get(i);
				
				HSSFRow row = sheet.createRow(currentRowNum);
				setRowHeight(row);
				
				int maxLines = 1;

				HSSFCell[] cells = new HSSFCell[fieldCount];
				
				int mergeStart = 0;
				rowIndex++;
				
				for(int j=0;j<fieldCount;j++) {
					if(fieldNames[j] == MERGE_LEFT) {
						if(j > 0) {
							cells[j] = row.createCell((short)j);
							cells[j].setCellStyle(applyCellStyle(cells[j], mergeStart));
							sheet.addMergedRegion(new Region(currentRowNum, (short)(j-1), currentRowNum, (short)j));
						}
					} else if(fieldNames[j].equals(MERGE_UP)) {
						if(currentRowNum > 0) {
							cells[j] = row.createCell((short)j);
							cells[j].setCellStyle(applyCellStyle(cells[j], j));
							sheet.addMergedRegion(new Region(currentRowNum-1, (short)j, currentRowNum, (short)j));
						}
					} else if(fieldNames[j].equals(ROW_INDEX)) {
						cells[j] = row.createCell((short)j);
						cells[j].setCellType(HSSFCell.CELL_TYPE_NUMERIC);
						cells[j].setCellValue(rowIndex);
						cells[j].setCellStyle(applyCellStyle(cells[j],j));
					} else {
						mergeStart = j;
						
						String columnName = fieldNames[j].toLowerCase();
						
						Object value = null;
						try {
							
							value = lom.get(columnName);
							
							value = value == null ? "" : value;
							
							if(columnName.equals("cnsdmans")) {
								value = String.valueOf(value).replaceAll("<br/>", ", ") ;
							}
						} catch(Exception e) {
							value = "";
						}
	
						cells[j] = row.createCell((short)j);
						cells[j].setCellStyle(applyCellStyle(cells[j],j));
						if(value.getClass().equals(Short.class)
								|| value.getClass().equals(Long.class)
								|| value.getClass().equals(Integer.class)) {
							cells[j].setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cells[j].setCellValue(Long.parseLong(value.toString()));
						} else if(value.getClass().equals(BigDecimal.class) 
								|| value.getClass().equals(Double.class)
								|| value.getClass().equals(Float.class)) {
							cells[j].setCellType(HSSFCell.CELL_TYPE_NUMERIC);
							cells[j].setCellValue(Double.parseDouble(value.toString()));
						} else {
							String cellValue = value.toString(); 
							cells[j].setCellValue(new HSSFRichTextString(cellValue));
							
							if (cellValue.indexOf("\n") > 0) {
								cells[j].getCellStyle().setWrapText(true);
								
								if (this.rowHeight == 0) {
									String[] lines = cellValue.split("\n");
									if (lines.length > maxLines) {
										row.setHeight((short)(row.getHeight() * lines.length));
										maxLines = lines.length;
									}
								}
							}
						}
					}
				}
				
				currentRowNum++;
			}
		}
	}
	
	private HSSFCellStyle applyCellStyle(HSSFCell cell, int index) {
		return style[index];
	}
	
	private void initCellStyle(int length) {
		if (!styleModified)
			return;
		
		this.style = null;
		this.style = new HSSFCellStyle[length];
		
		for (int index=0;index<length;index++) {
			HSSFCellStyle style = this.wb.createCellStyle();
			HSSFFont font = this.wb.createFont();
			
			if(styleAlign != null && styleAlign.length > 0) {
				if(styleAlign.length == 1)
					style.setAlignment(styleAlign[0]);
				else if(styleAlign.length > index)
					style.setAlignment(styleAlign[index]);
			}
			
			if(styleVAlign != null && styleVAlign.length > 0) {
				if(styleVAlign.length == 1)
					style.setVerticalAlignment(styleVAlign[0]);
				else if(styleVAlign.length > index)
					style.setVerticalAlignment(styleVAlign[index]);
			}
			
			if(styleBold != null && styleBold.length > 0) {
				if(styleBold.length == 1) {
					if(styleBold[0])
						font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					else
						font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
					style.setFont(font);
				} else if(styleBold.length > index) {
					if(styleBold[index])
						font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
					else
						font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
					style.setFont(font);
				}
			}
			
			if(styleItalic != null && styleItalic.length > 0) {
				if(styleItalic.length == 1) {
					font.setItalic(styleItalic[0]);
					style.setFont(font);
				} else if(styleItalic.length > index) {
					font.setItalic(styleItalic[index]);
					style.setFont(font);
				}
			}
			
			if(styleStrikeout != null && styleStrikeout.length > 0) {
				if(styleStrikeout.length == 1) {
					font.setStrikeout(styleStrikeout[0]);
					style.setFont(font);
				} else if(styleStrikeout.length > index) {
					font.setStrikeout(styleStrikeout[index]);
					style.setFont(font);
				}
			}
			
			if(styleUnderline != null && styleUnderline.length > 0) {
				if(styleUnderline.length == 1) {
					if(styleUnderline[0] == 1)
						font.setUnderline(HSSFFont.U_SINGLE);
					else if(styleUnderline[0] == 2)
						font.setUnderline(HSSFFont.U_DOUBLE);
					else
						font.setUnderline(HSSFFont.U_NONE);
					style.setFont(font);
				} else if(styleUnderline.length > index) {
					if(styleUnderline[index] == 1)
						font.setUnderline(HSSFFont.U_SINGLE);
					else if(styleUnderline[index] == 2)
						font.setUnderline(HSSFFont.U_DOUBLE);
					else
						font.setUnderline(HSSFFont.U_NONE);
					style.setFont(font);
				}
			}
			
			if(styleFontColor != null && styleFontColor.length > 0) {
				if(styleFontColor.length == 1) {
					if(styleFontColor[0] > 0) {
						font.setColor(styleFontColor[0]);
						style.setFont(font);
					}
				} else if(styleFontColor.length > index) {
					if(styleFontColor[index] > 0) {
						font.setColor(styleFontColor[index]);
						style.setFont(font);
					}
				}
			}

			if(styleFontName != null && styleFontName.length > 0) {
				if(styleFontName.length == 1) {
					if(styleFontName[0] != null) {
						font.setFontName(styleFontName[0]);
						style.setFont(font);
					}
				} else if(styleFontName.length > index) {
					if(styleFontName[index] != null) {
						font.setFontName(styleFontName[index]);
						style.setFont(font);
					}
				}
			}

			if(styleFontSize != null && styleFontSize.length > 0) {
				if(styleFontSize.length == 1) {
					if(styleFontSize[0] > 0) {
						font.setFontHeightInPoints(styleFontSize[0]);
						style.setFont(font);
					}
				} else if(styleFontSize.length > index) {
					if(styleFontSize[index] > 0) {
						font.setFontHeightInPoints(styleFontSize[index]);
						style.setFont(font);
					}
				}
			}

			if(styleBgColor != null && styleBgColor.length > 0) {
				if(styleBgColor.length == 1) {
					style.setFillForegroundColor(styleBgColor[0]);
					if(styleBgColor[0] > 0)
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					else
						style.setFillPattern(HSSFCellStyle.NO_FILL);
				} else if(styleBgColor.length > index) {
					style.setFillForegroundColor(styleBgColor[index]);
					if(styleBgColor[index] > 0)
						style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
					else
						style.setFillPattern(HSSFCellStyle.NO_FILL);
				}
			}
			
			if(styleBorder != null && styleBorder.length > 0) {
				if(styleBorder.length == 1) {
					if(styleBorder[0]) {
						style.setBorderTop(HSSFCellStyle.BORDER_THIN);
						style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
						style.setBorderRight(HSSFCellStyle.BORDER_THIN);
					}
				} else if(styleBorder.length > index) {
					if(styleBorder[index]) {
						style.setBorderTop(HSSFCellStyle.BORDER_THIN);
						style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
						style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
						style.setBorderRight(HSSFCellStyle.BORDER_THIN);
					}
				}
			}
			
			this.style[index] = style;
		}
		
		styleModified = false;
	}
}
