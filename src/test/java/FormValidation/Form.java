package FormValidation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Form {

	private WebDriver driver;
	private static final Logger logger = Logger.getLogger(Fomr.class.getName());

	@BeforeClass
	public void setup() {
		// Set up Chrome driver
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.log(Level.INFO, "WebDriver initialized and maximized");
	}

	@Test(priority = 1)
	public void formUpload() {

		logger.log(Level.INFO, "Starting formUpload test");
		driver.get("https://www.globalsqa.com/samplepagetest/");
		logger.log(Level.INFO, "Navigated to the URL");

		// Provide directory path for random file selection
		String directoryPath = "/Users/shsug/selenium/Seleniumwebdriver/Resource";
		logger.log(Level.INFO, "Directory path for file selection: " + directoryPath);

		// Locate file input element and upload a random file
		WebElement fileInput = driver.findElement(By.name("file-553"));
		File randomFile = getRandomFile(directoryPath);
		fileInput.sendKeys(randomFile.getAbsolutePath());
		logger.log(Level.INFO, "Uploaded file: " + randomFile.getName());

		// Submit the form after file upload
		submitForm();
		logger.log(Level.INFO, "Form submitted successfully");
	}

	@Test(priority = 2)
	public void positiveAndNegativeTestCases() {

		logger.log(Level.INFO, "Starting positiveAndNegativeTestCases");
		// Positive test case
		fillForm("John Doe", "johndoe@example.com", "https://www.globalsqa.com/samplepagetest/",
				"Sample comment to be added in the form page");
		submitForm();
		logger.log(Level.INFO, "Filled form with valid data and submitted");
		// Add assertions or verifications for success

		// Negative test case
		fillForm("", "", "", "");
		submitForm();
		logger.log(Level.INFO, "Filled form with invalid data and submitted");

	}

	@Test(priority = 3)

	public void checkboxTest() {

		try {
			// Navigate to the page
			List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

			// Get the number of checkboxes
			int numberOfCheckboxes = checkboxes.size();

			// Generate a random index to select a checkbox
			Random random = new Random();
			int randomIndex = random.nextInt(numberOfCheckboxes);

			// Select the checkbox at the random index
			WebElement randomCheckbox = checkboxes.get(randomIndex);
			randomCheckbox.click();

			logger.log(Level.INFO, "Selected checkbox option index: " + randomIndex);

		} catch (Exception e) {
			logger.log(Level.INFO, "Exception occured " + e.getMessage());
		}
	}

	@Test(priority = 4)

	public void radiobutton() {
		List<WebElement> radiobutton = driver.findElements(By.xpath("//label[@for='g2599-education']"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		int numberofRadiobutton = radiobutton.size();

		Random random = new Random();
		int randomIndex = random.nextInt(numberofRadiobutton);

		// Click on the randomly selected radio button
		WebElement randomRadioButton = radiobutton.get(randomIndex);
		randomRadioButton.click();

		logger.log(Level.INFO, "Selected Radio button option index: " + randomIndex);
	}

	@Test(priority = 5)

	public void selectDropdownOption() {
		WebElement dropdown = driver.findElement(By.name("g2599-experienceinyears"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		Select select = new Select(dropdown);
		List<WebElement> options = select.getOptions();
		int index = new Random().nextInt(options.size());
		select.selectByIndex(index);
		logger.log(Level.INFO, "Selected dropdown option index: " + index);
	}

	@Test(priority = 6)
	public void alertButton() {
		WebElement alertButton = driver.findElement(By.xpath("//button[normalize-space()='Alert Box : Click Here']"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		alertButton.click();
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		System.out.println("Alert text: " + alertText);
		alert.accept();
		logger.log(Level.INFO, "checked OK the alert button ");
		alert.dismiss();
		logger.log(Level.INFO, "checked CANCEL the alert button ");

	}

	private File getRandomFile(String directoryPath) {
		File directory = new File(directoryPath);
		File[] files = directory.listFiles();

		if (files == null || files.length == 0) {
			throw new RuntimeException("No files found in the directory: " + directoryPath);
		}

		Random random = new Random();
		int index = random.nextInt(files.length);
		return files[index];
	}

	public void fillForm(String name, String email, String site, String comment) {
		driver.findElement(By.id("g2599-name")).sendKeys(name);
		driver.findElement(By.id("g2599-email")).sendKeys(email);
		driver.findElement(By.name("g2599-website")).sendKeys(site);
		driver.findElement(By.id("contact-form-comment-g2599-comment")).sendKeys(comment);
		logger.log(Level.INFO, "Filled form with Name: " + name + ", Email: " + email + ", Website: " + site,
				", Comment + comment + ");

	}

	public void submitForm() {
		WebElement submitButton = driver.findElement(By
				.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[2]/div[2]/form[1]/div[7]/textarea[1]"));

		// Scroll the Submit button into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

		// Click on the Submit button
		submitButton.click();
		logger.log(Level.INFO, "Clicked Submit button");
	}

	@AfterClass
	public void teardown() {
		// Close the browser
		if (driver != null) {
			driver.quit();
			logger.log(Level.INFO, "WebDriver closed");
		}
	}

}
