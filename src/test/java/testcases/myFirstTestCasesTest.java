package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import utilitise.Readxlsdata;

public class myFirstTestCasesTest extends BaseTest {

	@Test(dataProviderClass=Readxlsdata.class,dataProvider="bdd")
	public static void login(String username, String password) throws InterruptedException {
		
		driver.manage().window().maximize();
		driver.findElement(By.linkText(loc.getProperty("SigninLink"))).click();
		Thread.sleep(5000);
		//driver.findElement(By.name(loc.getProperty("emailFiled"))).clear();
		driver.findElement(By.name(loc.getProperty("emailFiled"))).sendKeys(username);
		Thread.sleep(5000);
		driver.findElement(By.xpath(loc.getProperty("nextbutton"))).click();
		Thread.sleep(5000);
		driver.findElement(By.name(loc.getProperty("passwordfield"))).sendKeys(password);
		Thread.sleep(5000);
		driver.findElement(By.xpath(loc.getProperty("loginnextbtn"))).click();
		Thread.sleep(5000);
//		String text1="dashboard";
//		String text2=driver.getTitle();
//		Assert.assertEquals(text2, text1);
		
	}
	
//	@DataProvider(name="testdata")
//	public Object[][] tdata(){
//		
//		return new Object[][] {
//			{"jpkaul","password1"},
//			{"jpkaul2","password2"},
//			{"jpkaul2","password2"},
//			{"jpthekaul@gmail.com","Dochangeme1!"}
//			
//		};
//	}

}
