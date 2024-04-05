package utilitise;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {

	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentreport=new ExtentReports();
		File externReportFile= new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\ExtentReport.html");
		ExtentSparkReporter sparkreporter = new ExtentSparkReporter(externReportFile);
		
		sparkreporter.config().setTheme(Theme.DARK);
		sparkreporter.config().setReportName("Vijay Report");
		sparkreporter.config().setDocumentTitle("Automation Report");
		sparkreporter.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		
		Properties configprop=new Properties();
		File configfile=new File(System.getProperty("user.dir")+"\\src\\test\\resources\\Configuration\\config.properties");
		
		try {
		FileInputStream fisconfig= new FileInputStream(configfile);
		configprop.load(fisconfig);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		extentreport.attachReporter(sparkreporter);
		//extentreport.setSystemInfo("Application url",configprop.getProperty("url"));
		
		// TODO Auto-generated method stub
		
		extentreport.setSystemInfo("Application URL",configprop.getProperty("url"));
		extentreport.setSystemInfo("Browser Name",configprop.getProperty("browser"));
		extentreport.setSystemInfo("Operating System",System.getProperty("os.name"));
		extentreport.setSystemInfo("Java Version",System.getProperty("java.version"));
		
		return extentreport;

	}

}
