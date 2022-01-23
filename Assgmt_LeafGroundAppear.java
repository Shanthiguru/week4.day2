package week4.day2.Tables;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assgmt_LeafGroundAppear {

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://www.leafground.com/pages/appear.html");
		driver.manage().window().maximize();	
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5));
		WebElement element = driver.findElement(By.xpath("//button[@id='btn']"));
		wait.until(ExpectedConditions.visibilityOf(element));
		System.out.println("Hidden Text is: "+element.getText());
		driver.close();
		

	}

}
