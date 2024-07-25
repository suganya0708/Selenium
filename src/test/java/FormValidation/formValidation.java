package FormValidation;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class formValidation {
	
	 private static WebDriver driver;
	@Test
	public void formUpload(){
		

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.globalsqa.com/samplepagetest/");
		driver.manage().window().maximize();
		String directoryPath = "/Users/shsug/selenium/Seleniumwebdriver/Resource";
		WebElement fileInput = driver.findElement(By.name("file-553")); 
		File randomFile = getRandomFile(directoryPath);
		
		driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
		
		fileInput.sendKeys(randomFile.getAbsolutePath());
		
		
		
		 positiveTestCase(driver);

	        // Scenario 2: Negative Test Case
	    negativeTestCase(driver);
	    dropdown(driver);

	        // Close the browser
	        driver.quit();
	
	}
	
	
	
	
	
	 public static File getRandomFile(String directoryPath) {
	        File directory = new File(directoryPath);
	        File[] files = directory.listFiles();

	        if (files == null || files.length == 0) {
	            throw new RuntimeException("No files found in the directory: " + directoryPath);
	        }

	        Random random = new Random();
	        int index = random.nextInt(files.length);
	        return files[index];
	    }
	
	 
	 @Test
	 public static void positiveTestCase(WebDriver driver) {
	        // Fill required fields with valid data
	        fillForm(driver, "John Doe", "johndoe@example.com","https://www.globalsqa.com/samplepagetest/",  true, true);

	        // Handle file upload
	       // uploadFile(driver);

	        // Handle alert box
	       // handleAlert(driver);

	        // Submit form
	        submitForm(driver);

	        // Wait for success message (if any) - Example assumes there's a success message element
	       // WebDriverWait wait = new WebDriverWait(driver,10);
	       // WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("success_message")));
	       // System.out.println("Positive Test Case: " + successMessage.getText());
	    }
	  
	 @Test
	 public static void negativeTestCase(WebDriver driver) {
	        // Leave required fields empty
	        fillForm(driver, "", "", "", false, false);

	        // Submit form
	        submitForm(driver);

	        // Handle alert box (if any for validation)
	       // handleAlert(driver);

	        // Wait for error message (if any) - Example assumes there's an error message element
	       // WebDriverWait wait = new WebDriverWait(driver, 10);
	       // WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("error_message")));
	       // System.out.println("Negative Test Case: " + errorMessage.getText());
	    }
	   
	 
	   public static void fillForm(WebDriver driver, String name, String email,String site,  boolean checkCheckbox, boolean selectRadioButton) {
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
	   
	   
//	   public static void handleAlert(WebDriver driver) {
//	        // Handle alert box
//	        try {
//	           // WebDriverWait wait = new WebDriverWait(driver, 10);
//	           // wait.until(ExpectedConditions.alertIsPresent());
//	            driver.switchTo().alert().accept();
//	        } catch (Exception e) {
//	            System.out.println("No alert present.");
//	        }
//	    }
	   
	   public static void dropdown(WebDriver driver) {
		   
		   WebElement dropdown = driver.findElement(By.name("g2599-experienceinyears"));

	      
		   Select select = new Select(dropdown);

	        // Get all the options in the dropdown
	        List<WebElement> options = select.getOptions();

	        // Generate a random index between 0 and options.size() - 1
	        Random rand = new Random();
	        int index = rand.nextInt(options.size());

	        // Select the option at the random index
	        select.selectByIndex(index);

		   
	   }
	   
	   
	    public static void submitForm(WebDriver driver) {
	        // Submit form
	        WebElement submitButton = driver.findElement(By.xpath("//button[@type='submit']"));
	        
	       
	        submitButton.click();
	    }

	 
	
		

		 

	 }
	 
	 
	 
	
  
