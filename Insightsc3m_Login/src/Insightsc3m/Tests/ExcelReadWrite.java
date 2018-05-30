package Insightsc3m.Tests;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class ExcelReadWrite
{
	public static String readPath = "D:\\Selenium Tool\\Excel Inputs\\Content.xls"; //Read Path
	public static String writePath = "D:\\Selenium Tool\\Excel Outputs\\ContentResult.xls"; //Write Path
	static WritableWorkbook outputworkBook;
	public static WritableSheet writableSheet;
	
	public static int PassValue = 0;
	public static int FailValue = 0;
	public static int NotAvailableValue = 0;
	public static int LastRowIndex = 2;
	
	public static void CopyContentToOutputFile() {

		File source = new File(readPath);
		File dest = new File(writePath);
		
		Workbook workbook;
		try {
			workbook = Workbook.getWorkbook(source);
			outputworkBook = Workbook.createWorkbook(dest, workbook);
			writableSheet = outputworkBook.getSheet(0);
		} catch (BiffException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	public static String GetCellData(Sheet s, int r,int c) throws BiffException, IOException 
	{
		String GetCellContent = s.getCell(c,r).getContents();
		return GetCellContent;
	}
	
	
	public static void WriteCellData(WritableSheet sheet, int wrow,int wcol,String result) throws RowsExceededException, WriteException, BiffException, IOException
	{
		WritableFont cellFont = null;
		WritableCellFormat cellFormat = null;
		Label content = new Label(wcol,wrow,result);
		
		String Texthighlight = GetCellData(sheet,wrow,wcol-1);		
		Label Contenthighlight = new Label(wcol-1,wrow,Texthighlight);
		
		cellFont = new WritableFont(WritableFont.TIMES, 12);
		cellFont.setBoldStyle(WritableFont.BOLD);		
		
		if(result == "PASS")
		{
			cellFont.setColour(Colour.GREEN);			
			cellFormat = new WritableCellFormat(cellFont);	
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			content = new Label(wcol,wrow,result, cellFormat);
		} 
		else if(result == "FAIL")
		{
			cellFont.setColour(Colour.RED);			
			cellFormat = new WritableCellFormat(cellFont);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			content = new Label(wcol,wrow,result, cellFormat);
			WritableCellFormat cellFormat1 = null;
			WritableFont cellFont1 = null;
			cellFont1 = new WritableFont(WritableFont.TIMES, 12);
			cellFormat1 = new WritableCellFormat(cellFont1);
			cellFormat1.setBackground(Colour.YELLOW);
			cellFormat1.setWrap(true);
			cellFormat1.setAlignment(Alignment.LEFT);
			cellFormat1.setVerticalAlignment(VerticalAlignment.TOP);
			Contenthighlight = new Label(wcol-1,wrow,Texthighlight,cellFormat1);
			sheet.addCell(Contenthighlight);
		}
		else
		{
			cellFont.setColour(Colour.BLACK);				
			cellFormat = new WritableCellFormat(cellFont);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		}		
		
		sheet.addCell(content);
	}
	
	public static void WriteCellData(WritableSheet sheet, int wrow,int wcol,String result, int hcol) throws RowsExceededException, WriteException, BiffException, IOException
	{
		WritableFont cellFont = null;
		WritableCellFormat cellFormat = null;
		Label content = new Label(wcol,wrow,result);
		
		cellFont = new WritableFont(WritableFont.TIMES, 12);
		cellFont.setBoldStyle(WritableFont.BOLD);		
		
		if(result == "PASS")
		{
			cellFont.setColour(Colour.GREEN);			
			cellFormat = new WritableCellFormat(cellFont);	
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			content = new Label(wcol,wrow,result, cellFormat);
		} 
		else if(result == "FAIL")
		{
			String Texthighlight = GetCellData(sheet,wrow,hcol);		
			Label Contenthighlight = new Label(hcol,wrow,Texthighlight);
			cellFont.setColour(Colour.RED);			
			cellFormat = new WritableCellFormat(cellFont);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
			content = new Label(wcol,wrow,result, cellFormat);
			WritableCellFormat cellFormat1 = null;
			WritableFont cellFont1 = null;
			cellFont1 = new WritableFont(WritableFont.TIMES, 12);
			cellFormat1 = new WritableCellFormat(cellFont1);
			cellFormat1.setBackground(Colour.YELLOW);
			cellFormat1.setWrap(true);
			cellFormat1.setAlignment(Alignment.LEFT);
			cellFormat1.setVerticalAlignment(VerticalAlignment.TOP);
			Contenthighlight = new Label(hcol,wrow,Texthighlight,cellFormat1);
			sheet.addCell(Contenthighlight);
		}
		else
		{
			cellFont.setColour(Colour.BLACK);				
			cellFormat = new WritableCellFormat(cellFont);
			cellFormat.setAlignment(Alignment.CENTRE);
			cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
		}		
		
		sheet.addCell(content);
	}
	
	public static void WriteExcel(){
		try{
			outputworkBook.write();
		}catch(IOException e){
		e.printStackTrace();
		}
		try{
			outputworkBook.close();
		}catch(WriteException e){
		e.printStackTrace();
		}catch(IOException e){
		e.printStackTrace();
		}
		}
	
	public static void OpenOutputExcel()
	{
		if (Desktop.isDesktopSupported()) {
		    try {
		        File myFile = new File(writePath);
		        Desktop.getDesktop().open(myFile);
		    } catch (IOException ex) {
		        // no application registered for PDFs
		    }
		}
	}
	
	public static void CheckContentText(Sheet currentSheet, int row, String idSelector, String ActualMyReportingCenter) throws BiffException, IOException, RowsExceededException, WriteException
	{
		 String ExpectedContent = GetCellData(currentSheet, row, 1);		 
		 
		 if(ActualMyReportingCenter.equals(ExpectedContent)) 
		 {	
			 WriteCellData(writableSheet, row, 2, ActualMyReportingCenter);
			 WriteCellData(writableSheet, row, 3, "PASS");	
			 PassValue++;
		 }
		 else if(ExpectedContent != "")
		 {	
				 WriteCellData(writableSheet, row, 2, ActualMyReportingCenter);
				 WriteCellData(writableSheet, row, 3, "FAIL");	
				 FailValue++;
		}
		 else
		 {
			 	NotAvailableValue++;
		 }
	}
	
	public static void PieChartReport() throws InterruptedException, BiffException, IOException, RowsExceededException, WriteException
	{
		// Creating a simple pie chart with 
		 DefaultPieDataset pieDataset = new DefaultPieDataset();
		 pieDataset.setValue("PASS " + "(" + PassValue + ")", PassValue);
		 pieDataset.setValue("FAIL " + "(" + FailValue + ")", FailValue);
		 JFreeChart piechart = ChartFactory.createPieChart("Content Execution Status", pieDataset, true, true, false);
		 try {
			 String screenshotLocation = "D:\\Selenium Tool\\Excel Outputs\\Graph.jpg";	
			 File pieChartFile = new File(screenshotLocation);
			 if(pieChartFile.exists())
			 {
				 pieChartFile.delete();
			 }
			 pieChartFile = new File(screenshotLocation);
			 ChartUtilities.saveChartAsPNG(pieChartFile, piechart, 600, 600); 
			 Thread.sleep(10000);
			 File imageFile = new File(screenshotLocation);
		     BufferedImage input = ImageIO.read(imageFile);
		     ByteArrayOutputStream baos = new ByteArrayOutputStream();
		     ImageIO.write(input, "PNG", baos);	
		     int row = LastRowIndex;
		     int heightInPoints = 300*20;
		     writableSheet.setRowView(row, heightInPoints);
		     //writableSheet.mergeCells(0, row, 4, row);
			 writableSheet.addImage(new WritableImage(0,row,1,1,baos.toByteArray()));	
			 pieChartFile = new File(screenshotLocation);
			 if(pieChartFile.exists())
			 {
				 pieChartFile.delete();
			 }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		 
	}
	
	public static Sheet GetSheet() throws IndexOutOfBoundsException, BiffException, IOException, RowsExceededException, WriteException, InterruptedException 
	{
		Sheet currentSheet = null;  
		try {
			FileInputStream inputFileStream = new FileInputStream(readPath);
			currentSheet = Workbook.getWorkbook(inputFileStream).getSheet(0);  
		} catch(Exception ex) {
			throw ex;
		}
		return currentSheet;		
	}
	
		
}

