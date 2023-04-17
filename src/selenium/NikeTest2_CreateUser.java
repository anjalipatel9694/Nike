package selenium;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NikeTest2_CreateUser {

	Xls_Reader excelreader = new Xls_Reader(
			"C:\\QA\\Testing\\wetransfer_build-xml_2022-12-03_2020\\Practise excel data.xlsx");
	WebDriver driver;

	@BeforeClass
	public void openbrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.nike.com/ca/");
	}

	@Test
	public void createuser_account() {
		driver.findElement(By.id("hf_title_signin_membership")).click();
		driver.findElement(By.linkText("Join Us.")).click();
		driver.findElement(By.name("emailAddress")).sendKeys(excelreader.getCellData("Nike", 0, 2));
		driver.findElement(By.name("password")).sendKeys(excelreader.getCellData("Nike", 1, 2));
		driver.findElement(By.name("firstName")).sendKeys(excelreader.getCellData("Nike", 2, 2));
		driver.findElement(By.name("lastName")).sendKeys(excelreader.getCellData("Nike", 3, 2));
		driver.findElement(By.name("dateOfBirth")).sendKeys(excelreader.getCellData("Nike", 4, 2));

		Select s = new Select(driver.findElement(By.name("country")));
		s.selectByValue("CA");
		driver.findElement(By.xpath("//span[contains(text(),'Male')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		driver.findElement(By.xpath("//form[@id='nike-unite-joinForm']/div[9]/input")).click();

		List<WebElement> logout = driver.findElements(By.xpath("//button[contains(text(),'Log Out')]"));
		if (logout.size() > 0) {
			System.out.println("Test Pass - User Account created.");
		} else {
			System.out.println("Test Fail - Unable to create account.");
		}

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
