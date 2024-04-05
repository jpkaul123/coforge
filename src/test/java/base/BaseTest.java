package base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {
	
	public static WebDriver driver;
	public static Properties prop = new Properties();
	public static Properties loc = new Properties();
	public static FileReader fr;
	public static FileReader fr1;
	
	
	@BeforeMethod
	public void setup() throws IOException {
		
		if (driver==null) {
			System.out.println("The path is" + System.getProperty("user.dir"));
			FileReader fr= new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Configuration\\config.properties");
			FileReader fr1= new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Configuration\\locators.properties");
			prop.load(fr);
			loc.load(fr1);
		}
		
		
		if (prop.getProperty("browser").equalsIgnoreCase("chrome")){
			
			 driver= new ChromeDriver();
			 driver.get(prop.getProperty("url"));
			 		}
		else if(prop.getProperty("browser").equalsIgnoreCase("firefox")){
			
			driver= new FirefoxDriver();
			 driver.get(prop.getProperty("url"));
		}
		
else if(prop.getProperty("browser").equalsIgnoreCase("edge")){
			
			driver= new EdgeDriver();
			 driver.get(prop.getProperty("url"));
		}
	}
	
	@AfterMethod
	public void tearDown() {
		
		driver.close();
		System.out.println("test execution successfully");
	}
	
	

}
