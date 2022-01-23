package week4.day2.Tables;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assgmt_Amazon {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("twotabsearchtextbox")).sendKeys("oneplus 9 pro");
		driver.findElement(By.id("nav-search-submit-button")).click();
		String firstPrice = driver.findElement(By.xpath("(//span[@class ='a-price-whole'])[1]")).getText();
		String price =firstPrice.replaceAll("[^1-9]","");
		int firstProductPrice = Integer.parseInt(price);
		System.out.println("Rate of first displaying product: "+firstProductPrice);
		WebElement customerRating = driver.findElement(By.xpath("(//span[@class ='a-size-base'])[1]"));
		System.out.println("Customer Rating on selected product: "+customerRating.getText());
		driver.findElement(By.xpath("(//div/span/span/a/i)[1]")).click();
		WebElement fiveStars = driver.findElement(By.xpath("//table[@id='histogramTable']//tr/td[3]//a"));
		System.out.println("Percentage of 5 star rating: "+fiveStars.getText());
		driver.findElement(By.xpath("(//span[@class='a-size-medium a-color-base a-text-normal'])[1]")).click();
		Thread.sleep(1000);
		
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> window = new ArrayList<String>(windowHandles);
		driver.switchTo().window(window.get(1));
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/OnePlus9Pro.png");
		FileUtils.copyFile(source, destination);
		
		driver.findElement(By.id("add-to-cart-button")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.id("attach-view-cart-button-form")).click();
		Thread.sleep(2000);
		
		String subTotal = driver.findElement(By.xpath("//span[@id='sc-subtotal-amount-activecart']/span")).getText();
		String price1 = subTotal.replace(".00","").replaceAll("[^0-9]", "");
		int cartPrice = Integer.parseInt(price1);
		System.out.println("Total Price of the Product : "+cartPrice);
		if(firstProductPrice == cartPrice) 
		{
			System.out.println("Price of the product matches");
		}
		else 
		{
			System.out.println("Price of the product mismatched");
		}
		
		driver.quit();
		

	}

}
