package LoginFunctionality;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginWithMultipleSetsofData {
	
	@Test(dataProvider = "credentilas")
	public void verifyLoginCredentials(String scenerio, String username, String password) {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.saucedemo.com/");
		
		driver.manage().window().maximize();
		
		driver.findElement(By.name("user-name")).sendKeys(username);

		driver.findElement(By.name("password")).sendKeys(password);

		driver.findElement(By.name("login-button")).click();

     
         
         switch(scenerio) {
         
         case "Invalid_UserName":
        	 
        	  String errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
       	   
       	       AssertJUnit.assertEquals(errorMessage, "Epic sadface: Sorry, this user has been locked out.");
       	       break;
       	       
       	       
       	 case "valid_UserName1":
        
        	 String getUrl = driver.getCurrentUrl();
        	 Assert.assertEquals(getUrl, "https://www.saucedemo.com/inventory.html");
        	 break;
      	       
       	      
        	 
       	 case "valid_UserName2":
             
        	 String getUrl1 = driver.getCurrentUrl();
        	 Assert.assertEquals(getUrl1, "https://www.saucedemo.com/inventory.html");
        	 break;
      	       

        	 
       	 case "valid_UserName3":
             
        	 String getUrl2 = driver.getCurrentUrl();
        	 Assert.assertEquals(getUrl2, "https://www.saucedemo.com/inventory.html");
        	 break;
      	       

        	 
       	 case "valid_UserName4":
             
        	 String attr1 = driver.getCurrentUrl();
        	 Assert.assertEquals(attr1, "https://www.saucedemo.com/inventory.html");
        	 break;
      	       

        	 
       	 case "Both_Valid_UserName_Password":
             
        	 String attr = driver.getCurrentUrl();
        	 Assert.assertEquals(attr, "https://www.saucedemo.com/inventory.html");
        	 break;
      	      }
         }
	
	
	@DataProvider(name = "credentilas")
	public Object[][] getData(){
		
		return new Object[][] {
			
			
			 {"Invalid_UserName","locked_out_user","secret_sauce"},
			 {"Both_Valid_UserName_Password","standard_user","secret_sauce"},
			 {"valid_UserName1","problem_user","secret_sauce"},
			 {"valid_UserName2","performance_glitch_user","secret_sauce"},
	         {"valid_UserName3","error_user","secret_sauce"},
	         {"valid_UserName4","visual_user","secret_sauce"}
			
				
				
		};
	
		
		
	}
	

}
