package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil {

	Workbook wb;
	
	public ExcelFileUtil(String excelpath)throws Throwable
	{
		FileInputStream fi = new FileInputStream(excelpath);
		
		wb = WorkbookFactory.create(fi);
		
		
	}
	
	public int  RowCount(String sheetName)
	{ 
		return wb.getSheet(sheetName).getLastRowNum();
		
	}
	
	public String getcellData(String sheetName , int row , int col  ) 
	{
		String data = "";
		
		if(wb.getSheet(sheetName).getRow(row).getCell(col).getCellType()==CellType.NUMERIC )
		{
			int celldata = (int) wb.getSheet(sheetName).getRow(row).getCell(col).getNumericCellValue();
			
			data = String.valueOf(celldata);
			
		}
		else 
		{
			data =  wb.getSheet(sheetName).getRow(row).getCell(col).getStringCellValue();
		}
		return data;
	}
	
	
	public void setCellData(String sheetName , int row , int col , String status , String writeExcel) throws Throwable
	{
	     Sheet ws = wb.getSheet(sheetName);
	     
	     Row rc = ws.getRow(row);
	     
	     Cell cell = rc.createCell(col);
	     
	     cell.setCellValue(status);
	     
	     if (status.equalsIgnoreCase("Pass")) 
	     {
	    	 CellStyle style = wb.createCellStyle();
	    	 Font font = wb.createFont();
	    	 
	    	 font.setColor(IndexedColors.GREEN.getIndex());
	    	 font.setBold(true);
	    	 
	    	style.setFont(font);
	    	
	    	ws.getRow(row).getCell(col).setCellStyle(style);
	    	 
	    	 
	     }
	     else if (status.equalsIgnoreCase("Fail")) 
	     {
	    	 CellStyle style = wb.createCellStyle();
	    	 Font font = wb.createFont();
	    	 
	    	 font.setColor(IndexedColors.RED.getIndex());
	    	 font.setBold(true);
	    	 
	    	 style.setFont(font);
	    	 
	    	 ws.getRow(row).getCell(col).setCellStyle(style);
	    	 
	    	 
	     }
	     else if (status.equalsIgnoreCase("Blocked")) 
	     {
	    	 CellStyle style = wb.createCellStyle();
	    	 Font font = wb.createFont();
	    	 
	    	 font.setColor(IndexedColors.BLUE.getIndex());
	    	 font.setBold(true);
	    	 
	    	 style.setFont(font);
	    	 
	    	 ws.getRow(row).getCell(col).setCellStyle(style);
	    	 
	    	 
	     }
	     
	     FileOutputStream fo = new FileOutputStream(writeExcel);
	     wb.write(fo);
	     
	}
	
	
	
	/*public static void main(String[] args) throws Throwable
	{ 
		
		ExcelFileUtil xl = new ExcelFileUtil("d:/demo.xlsx");
		
		int rc = xl.RowCount("Sheet1");
		System.out.println(rc);
		
		for (int i=1; i<=rc ; i++)
		{
		String fname = xl.getcellData("Sheet1", i, 0);
		String mname = xl.getcellData("Sheet1", i, 1);
		String lname = xl.getcellData("Sheet1", i, 2);
		
		String empid = xl.getcellData("Sheet1", i, 3);
		
		System.out.println(fname + " " + mname + "  " + lname + "  " + empid);
		
		//xl.setCellData("Sheet1", i, 4, "Pass", "d:/demo.xlsx");
		xl.setCellData("Sheet1", i, 4, "Fail", "d:/demo.xlsx");
		//xl.setCellData("Sheet1", i, 4, "Pass", "d:/demo.xlsx");

		}
	}
	
	*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
