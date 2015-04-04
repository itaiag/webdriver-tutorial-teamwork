package il.co.topq.auto;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddTaskTests {

	private static final String URL = "https://topq.teamwork.com";
	private static final CharSequence USER_NAME = "fake01";
	private static final CharSequence PASSWORD = "fake";
	private WebDriverWait wait;

	private WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new FirefoxDriver();
		driver.get(URL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 60);

	}

	@Test
	public void testAddTask() {
		// Login
		driver.findElement(By.id("userLogin")).sendKeys(USER_NAME);
		driver.findElement(By.id("password")).sendKeys(PASSWORD);
		driver.findElement(By.id("ordLoginSubmitBtn")).click();

		// Select the task sub menu

		// Add task list
		
		// Enter new task list name

		// Add new task

		//Getting back to the tasks view by clicking again on the task sub menu
		
		//Asserting that the new task was created
		
		// Signing out
		driver.findElement(By.xpath("//*[@id='trUserPic']/a")).click();
		driver.findElement(By.xpath("//a[@href='?action=logout']")).click();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
