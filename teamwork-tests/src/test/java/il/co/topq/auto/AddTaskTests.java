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

		// Select the task sub menu.
		// HINT: You can use the //li[@id='tab_tasks']/a locator

		// Add task list
		
		// **HINT:** If you will find the *Add Task List* before the *tasks* menu
		// page is loaded, you may catch the *add* button of the *Overview*
		// page. In this case, when you will try to operate on the button you
		// will get a stale element exception. To avoid it you need to wait for
		// the <i>tasks</i> page to be selected. For this, you can use the
		// following xpath: //li[@id='tab_tasks' and @class='first sel']
		

		// Enter new task list name
		final String taskListName = USER_NAME + "'s Task List " + System.currentTimeMillis();

		// Add new task

		final String taskName = "My Task " + System.currentTimeMillis();

		// Getting back to the tasks view by clicking again on the task sub menu

		// Asserting that the new task was created
		driver.findElement(By.xpath("//span[@class='taskName' and contains(.,'" + taskName + "')]"));

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
