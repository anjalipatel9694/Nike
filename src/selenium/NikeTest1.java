package selenium;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NikeTest1 {

	Xls_Reader excelreader = new Xls_Reader("C:\\QA\\Testing\\wetransfer_build-xml_2022-12-03_2020\\Practise excel data.xlsx");
	WebDriver driver;

	@BeforeClass
	public void testSetup() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https://www.nike.com/ca/");

	}
	


	@Test
	public void signin() {
		driver.findElement(By.id("hf_title_signin_membership")).click();
		driver.findElement(By.name("emailAddress")).sendKeys(excelreader.getCellData("Nike", 0, 2));
		driver.findElement(By.name("password")).sendKeys(excelreader.getCellData("Nike", 1, 2));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//form[@id='nike-unite-loginForm']/div[6]/input")).click();
		
		/*String expected = "https://www.nike.com/ca/";
		String actual = driver.getCurrentUrl();
		System.out.println(actual);
		if(expected.equals(actual)) {
			System.out.println("Sign in successful.");
		}*/
		
		List<WebElement> logout = driver.findElements(By.xpath("//button[contains(text(),'Log Out')]"));
		if(logout.size()>0) {
			System.out.println("Test Pass - sign in successful.");
		}
		else {
			System.out.println("Test Fail - wrong sign in credential or no account.");
		}

	}
	

	@AfterClass
	public void afterClass()
	{
	driver.quit();
	}
}
