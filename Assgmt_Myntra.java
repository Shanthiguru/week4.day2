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
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assgmt_Myntra {

	public static void main(String[] args) throws InterruptedException, IOException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		Actions build = new Actions(driver);
		WebElement men = driver.findElement(By.xpath("//a[text()='Men']"));
		build.moveToElement(men).perform();
		driver.findElement(By.linkText("Jackets")).click();
		String count = driver.findElement(By.xpath("//span[@class='title-count']")).getText();
		String replace = count.replaceAll("[^0-9]", "");
		int totalJacketCount = Integer.parseInt(replace);
		System.out.println("Total Jackets for Men: "+totalJacketCount);
		
		String category1=driver.findElement(By.xpath("(//span[@class='categories-num'])[1]")).getText();
		String repAll=category1.replaceAll("[^0-9]","");
		int categoryValue1 = Integer.parseInt(repAll);
		System.out.println("Value in Jacket Category:"+categoryValue1);
		
		String category2 = driver.findElement(By.xpath("(//span[@class='categories-num'])[2]")).getText();
		String repAll1=category2.replaceAll("[^0-9]","");
		int categoryValue2 = Integer.parseInt(repAll1);
		System.out.println("Value in Rain Jacket Category:"+categoryValue2);
		
		int categoryTotal= categoryValue1+categoryValue2;
		System.out.println("Total Jackets on Category:"+categoryTotal);
		if(categoryTotal==totalJacketCount)
		{
			System.out.println("Count of Jackets matched");
		}
			
		else
		{
			
			System.out.println("Count of Jackets mismatched");
		}
		
		driver.findElement(By.xpath("(//div[@class='common-checkboxIndicator'])[1]")).click();
		driver.findElement(By.xpath("//div[@class='brand-more']")).click();
		Thread.sleep(1000);
		driver.findElement(By.className("FilterDirectory-searchInput")).sendKeys("Duke");
		driver.findElement(By.xpath("//ul[@class='FilterDirectory-list']/li[2]//div[@class='common-checkboxIndicator']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']")).click();
		
		List<WebElement> dukeBrand = driver.findElements(By.xpath("//h3[@class='product-brand']"));
		int count1=0;
		for (WebElement w : dukeBrand)
		{
			String str = w.getText();			
			if(str.equals("Duke"))
			{
				count1++;
			}
		}
		
		if (count1 == dukeBrand.size())
		{
			System.out.println("All items are sorted to Duke");
		}
		else
		{
			System.out.println("Items contains different brands");
		}
		
		driver.findElement(By.xpath("//span[text()='Recommended']")).click();
		
		driver.findElement(By.xpath("//label[text()='Better Discount']")).click();
		Thread.sleep(1000);
		String firstProductPrice = driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])[1]")).getText();
		System.out.println("Price of the First Displaying Jacket : "+firstProductPrice);
		driver.findElement(By.xpath("(//span[@class='product-discountedPrice'])[1]")).click();
		Thread.sleep(3000);
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));
		
		File source = driver.getScreenshotAs(OutputType.FILE);
		File destination = new File("./images/MyntraFirstJacket.png");
		FileUtils.copyFile(source, destination);
		
		driver.findElement(By.xpath("//a[@class='desktop-wishlist']/span[2]")).click();
		Thread.sleep(3000);
		driver.quit();	
		
		

	}

}
