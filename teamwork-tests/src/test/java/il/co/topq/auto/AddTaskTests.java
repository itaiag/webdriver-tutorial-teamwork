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
		driver.findElement(By.cssSelector("li#tab_tasks > a")).click();

		// Add task list
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("li#tab_tasks.sel")));
		driver.findElement(By.cssSelector("li#liBFOATL > button")).click();
		
		// Enter new task list name
		final String taskListName = USER_NAME + "'s Task List " + System.currentTimeMillis();
		driver.findElement(By.id("newTaskListName")).sendKeys(taskListName);
		driver.findElement(By.id("btnCreateTaskList")).click();

		// Add new task
		driver.findElement(By.linkText(taskListName)).click();
		driver.findElement(By.cssSelector("button[onclick='TaskLists.AddTaskFromTasklistPage();']")).click();

		final String taskName = "My Task " + System.currentTimeMillis();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.twitter-typeahead input[placeholder='What needs to be done?']"))).sendKeys(taskName);
		driver.findElement(By.cssSelector("input[value='Save my changes']")).click();

		//Getting back to the tasks view by clicking again on the task sub menu
		driver.findElement(By.cssSelector("li#tab_tasks > a")).click();
		
		//Asserting that the new task was created
		driver.findElement(By.xpath("//span[@class='taskName' and contains(.,'"+taskName+"')]"));
		
		// Signing out
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
