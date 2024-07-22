package LoginFunctionality;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class newtourlogin {
	
	
	public static void main(String args[]) {
		
		
		System.setProperty("webdriver.firefox.marionette", "/Seleniumwebdriver/driver/chromedriver");
		
		WebDriver driver = new FirefoxDriver();
		
		driver.get("https://www.saucedemo.com/");
		
		driver.manage().window().maximize();
		
		driver.findElement(By.name("user-name")).sendKeys("standard_user");

		driver.findElement(By.name("password")).sendKeys("secret_sauce");

		driver.findElement(By.name("login-button")).click();
		
		String expected_title ="Swag Labs";
		
		String actual_title = driver.getTitle();
		
		if(expected_title.equals(actual_title)) {
			
			System.out.println("test is pasedd");
		}
		else {
		    System.out.println("test is failed");
		}
		
		
	}
	
}
