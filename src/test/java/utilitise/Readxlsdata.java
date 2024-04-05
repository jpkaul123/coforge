package utilitise;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.DataProvider;

public class Readxlsdata {
	
	
//	public static void main (String[] agrs) throws EncryptedDocumentException, IOException {
//		
//		Readxlsdata red = new Readxlsdata();
//		red.getData("login");
//		
//	}
	@DataProvider(name="bdd")
	
	public String[][] getData(Method m) throws EncryptedDocumentException, IOException {
		
		String excelSheetName=m.getName();
		File f = new File(System.getProperty("user.dir")+ "\\src\\test\\resources\\Test Data\\Perpectual2.xlsx");
		FileInputStream fis=new FileInputStream(f);
		
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sheetname=wb.getSheet(excelSheetName);
		
		int rowTotal = sheetname.getLastRowNum();
		
		System.out.println(rowTotal);
		
		Row rowcell=sheetname.getRow(0);
		
		int totcol=rowcell.getLastCellNum();
		System.out.println(totcol);
		DataFormatter format= new DataFormatter();
		
		String testData[][]=new String[rowTotal][totcol];
		
		for(int i=1;i<=rowTotal;i++) {
		
		for(int j=0;j<totcol;j++) {
			
			testData[i-1][j]=format.formatCellValue(sheetname.getRow(i).getCell(j));
			System.out.println(testData[i-1][j]);
		}
	
		}
		return testData;
	}
public static String captureScreenshot(WebDriver driver,String testName) {
		
		File srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\"+testName+".png";
		
		try {
			FileHandler.copy(srcScreenshot,new File(destinationScreenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return destinationScreenshotPath;
	}
}
