package FormValidation;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.io.File;
import java.util.List;
import java.util.Random;

public class Fomr {

    private WebDriver driver;

   
    @BeforeClass
    public void setup() {
        // Set up Chrome driver
    	 WebDriverManager.chromedriver().setup();
         driver = new ChromeDriver();
         driver.manage().window().maximize();
    }
       
    

    @Test
    public void formUpload() {
    	
    	
        driver.get("https://www.globalsqa.com/samplepagetest/");

        // Provide directory path for random file selection
        String directoryPath = "/Users/shsug/selenium/Seleniumwebdriver/Resource";

        // Locate file input element and upload a random file
        WebElement fileInput = driver.findElement(By.name("file-553"));
        File randomFile = getRandomFile(directoryPath);
        fileInput.sendKeys(randomFile.getAbsolutePath());

        // Perform positive and negative test cases
        

        // Close the browser
       
    }
    
    @Test
    public void postivetestcases() {
    	
    	positiveTestCase();
        negativeTestCase();
        selectDropdownOption();
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

    public void positiveTestCase() {
        fillForm("John Doe", "johndoe@example.com", "https://www.globalsqa.com/samplepagetest/");
        submitForm();
        // Add assertions or verifications for success
    }

    public void negativeTestCase() {
        fillForm("", "", "");
        submitForm();
        // Add assertions or verifications for errors
    }

    public void selectDropdownOption() {
        WebElement dropdown = driver.findElement(By.name("g2599-experienceinyears"));
        Select select = new Select(dropdown);
        List<WebElement> options = select.getOptions();
        int index = new Random().nextInt(options.size());
        select.selectByIndex(index);
    }

    public void fillForm(String name, String email, String site) {
        driver.findElement(By.id("g2599-name")).sendKeys(name);
        driver.findElement(By.id("g2599-email")).sendKeys(email);
        driver.findElement(By.name("g2599-website")).sendKeys(site);
    }

    public void submitForm() {
    	
        WebElement submitButton = driver.findElement(By.xpath("//button[contains(text(),'Submit')]"));

        // Scroll the Submit button into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submitButton);

        // Wait for the button to be clickable
       // WebDriverWait wait = new WebDriverWait(driver, 10);
       // wait.until(ExpectedConditions.elementToBeClickable(submitButton));

        // Click on the Submit button
        submitButton.click();

        
    }
    
    
    @AfterClass
    public void teardown() {
        // Close the browser
        if (driver != null) {
             driver.quit();
        }

}
}
