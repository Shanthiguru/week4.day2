package week4.day2.Tables;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assgmt_SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.snapdeal.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement menFashion = driver.findElement(By.xpath("//span[text()=\"Men's Fashion\"]"));
		Actions builder = new Actions(driver);
		builder.moveToElement(menFashion).perform();
		driver.findElement(By.xpath("//span[text()='Sports Shoes']")).click();
		WebElement count = driver.findElement(By.xpath("//span[@class='category-name category-count']"));
		System.out.println("Total Sports Shoes:"+count.getText());
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//i[@class='sd-icon sd-icon-expand-arrow sort-arrow']")).click();
		driver.findElement(By.xpath("//li[@data-sorttype='plth']")).click();
		
		WebElement fromVal = driver.findElement(By.name("fromVal"));
		fromVal.clear();
		fromVal.sendKeys("900");
		WebElement toVal = driver.findElement(By.name("toVal"));
		toVal.clear();
		toVal.sendKeys("1200");
		driver.findElement(By.xpath("//div[@class='price-go-arrow btn btn-line btn-theme-secondary']")).click();
        Thread.sleep(3000);
        driver.findElement(By.xpath("//label[@for='Color_s-Navy']")).click();
        Thread.sleep(500);
		String price = driver.findElement(By.xpath("//div[text()='Price: ']/a")).getText();
		System.out.println("The Price Range Selected is:"+price);
		String color = driver.findElement(By.xpath("//div[text()='Color: ']/a")).getText();
		System.out.println("The Color Selected is:"+color);
		if ((price.contains("1200")) && (color.contains("Navy"))) {
			System.out.println("Filters Applied");
		} else {
			System.out.println("Filters not Applied");
		}
		Thread.sleep(2000);
		WebElement firstResult = driver.findElement(By.xpath("(//div[contains(@class,'product-tuple-image')])[1]"));
        builder.moveToElement(firstResult).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//div[@class='clearfix row-disc'])[1]/div")).click();
        Thread.sleep(3000);
        String cost = driver.findElement(By.xpath("(//div[@class='product-price pdp-e-i-PAY-l clearfix']/span)[1]")).getText();
        Thread.sleep(2000);
        String discount = driver.findElement(By.xpath("(//div[@class='product-price pdp-e-i-PAY-l clearfix']/span)[2]")).getText();
        Thread.sleep(2000);
        System.out.println("Price of the product is : " + cost);
		System.out.println("Discount of the product is : " + discount);
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/QuickView.png");
		FileUtils.copyFile(source, destination);
		driver.findElement(By.xpath("//div[@id='sidebar_modal_right']/div[2]//i")).click();
		driver.close();

	}

}
