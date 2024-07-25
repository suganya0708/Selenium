package FormValidation;

import org.testng.annotations.Test;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FormValidations {

	@Test
	public void testcase() {
		
		 System.setProperty("webdriver.chrome.driver", "path_to_chromedriver");

	        // Optional: Set Chrome options like headless mode
//	        ChromeOptions options = new ChromeOptions();
//	        // options.setHeadless(true); // Uncomment this line to run in headless mode
//
//	        // Initialize ChromeDriver
//	        WebDriver driver = new ChromeDriver(options);
//	        
	        WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	        // Navigate to the website
	        driver.get("https://www.globalsqa.com/samplepagetest/");

	        // Scenario 1: Positive Test Case
	        positiveTestCase(driver);

	        // Scenario 2: Negative Test Case
	        negativeTestCase(driver);

	        // Close the browser
	        driver.quit();
		
		
		
	}
	
	
	  public static void positiveTestCase(WebDriver driver) {
	        // Fill required fields with valid data
	        fillForm(driver, "John Doe", "johndoe@example.com","https://www.globalsqa.com/samplepagetest/", "Option 1", true, true);

	        // Handle file upload
	       // uploadFile(driver);

	        // Handle alert box
	        handleAlert(driver);

	        // Submit form
	        submitForm(driver);

	        // Wait for success message (if any) - Example assumes there's a success message element
	       // WebDriverWait wait = new WebDriverWait(driver,10);
	       // WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success_message")));
	       // System.out.println("Positive Test Case: " + successMessage.getText());
	    }
	  
	  
	   public static void negativeTestCase(WebDriver driver) {
	        // Leave required fields empty
	        fillForm(driver, "", "", "","Option 2", false, false);

	        // Submit form
	        submitForm(driver);

	        // Handle alert box (if any for validation)
	        handleAlert(driver);

	        // Wait for error message (if any) - Example assumes there's an error message element
	       // WebDriverWait wait = new WebDriverWait(driver, 10);
	       // WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error_message")));
	       // System.out.println("Negative Test Case: " + errorMessage.getText());
	    }
	   
	   
	   public static void fillForm(WebDriver driver, String name, String email,String site, String dropdownOption, boolean checkCheckbox, boolean selectRadioButton) {
	        // Fill name
	        WebElement nameField = driver.findElement(By.id("g2599-name"));
	        nameField.sendKeys(name);

	        // Fill email
	        WebElement emailField = driver.findElement(By.id("g2599-email"));
	        emailField.sendKeys(email);
	        
	        // Fill email
	        WebElement website_url = driver.findElement(By.name("g2599-website"));
	        website_url.sendKeys(site);
	        

	        // Select dropdown
	      //  Select dropdown = new Select(driver.findElement(By.id("g2599-experienceinyears")));
	        //dropdown.selectByVisibleText(dropdownOption);

	        // Select checkbox
	      //  if (checkCheckbox) {
	       //     WebElement checkbox = driver.findElement(By.id("g2599-checkbox"));
	       //     if (!checkbox.isSelected()) {
	       //         checkbox.click();
	       //     }
	       // }
	      //  if (selectRadioButton) {
	       //     WebElement radioButton = driver.findElement(By.id("g2599-radio-button"));
	       //     radioButton.click();
	      //  }
	        }
	   
//	   public static void uploadFile(WebDriver driver) {
//	        // Upload file from resource folder
//	        String filePath = new File("/Users/shsug/selenium/Seleniumwebdriver/Resource").getAbsolutePath();
//	        WebElement fileUpload = driver.findElement(By.id("g2599-file-upload"));
//	        fileUpload.sendKeys(filePath);
//	    }
//
	    public static void handleAlert(WebDriver driver) {
	        // Handle alert box
	        try {
	           // WebDriverWait wait = new WebDriverWait(driver, 10);
	           // wait.until(ExpectedConditions.alertIsPresent());
	            driver.switchTo().alert().accept();
	        } catch (Exception e) {
	            System.out.println("No alert present.");
	        }
	    }

	    public static void submitForm(WebDriver driver) {
	        // Submit form
	        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
	        
	       
	        submitButton.click();
	    }


}
