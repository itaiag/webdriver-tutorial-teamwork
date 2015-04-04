package il.co.topq.auto;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestLogin {

	private static final String URL = "https://topq.teamwork.com";
	private static final CharSequence USER_NAME = "fake01";
	private static final CharSequence PASSWORD = "fake";

	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new FirefoxDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

	}

	@Test
	public void testLogin() {
		// Login
		driver.findElement(By.id("userLogin")).sendKeys(USER_NAME);
		driver.findElement(By.id("password")).sendKeys(PASSWORD);
		driver.findElement(By.id("lRemMeLbl")).click();
		driver.findElement(By.id("ordLoginSubmitBtn")).click();

		// Making sure that the login was successful
		driver.findElement(By.id("pageTitle"));

		// Logout
		driver.findElement(By.cssSelector("#trUserPic > a")).click();
		driver.findElement(By.cssSelector("a[href='?action=logout']")).click();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
