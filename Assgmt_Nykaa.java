package week4.day2.Tables;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assgmt_Nykaa {

	public static void main(String[] args) throws InterruptedException 
	{
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		ChromeDriver driver = new ChromeDriver(options);
		driver.get("https://www.nykaa.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebElement brand = driver.findElement(By.xpath("//a[text() = 'brands']"));
		Actions builder = new Actions(driver);
		builder.moveToElement(brand).perform();
		Thread.sleep(2000);
		driver.findElement(By.id("brandSearchBox")).sendKeys("L'Oreal Paris");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(), 'Paris')]")).click();
		String title = driver.getTitle();
		if(title.contains("L'Oreal Paris"))
		{
			System.out.println("Title of page is:"+title);
		}
		else
			System.out.println("Not in correct page");
		
		driver.findElement(By.xpath("//button[@class= ' css-p2rfnw']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//label[@for = 'radio_customer top rated_undefined']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Category']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Hair']")).click();
		driver.findElement(By.xpath("//span[text()='Hair Care']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Shampoo']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//span[text()='Concern']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//label[@class = 'control control-checkbox'])[3]")).click();
		
		Thread.sleep(2000);
		List<WebElement> checkFilter = driver.findElements(By.xpath("//div[@class='css-rtde4j']"));
		for(WebElement filterName:checkFilter) {
			String filterApplied = filterName.getText();
			System.out.println("Applied Filters are:"+filterApplied);
			if(filterApplied.contains("Shampoo"))
				System.out.println("Filter Applied To Shampoo");
			else System.out.println("Filter Not Applied");
		}
		driver.findElement(By.xpath("//div[@class='css-43m2vm']/img")).click();
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windows = new ArrayList<String>(windowHandles);
		driver.switchTo().window(windows.get(1));
		WebElement dropdown = driver.findElement(By.xpath("//select[@title='SIZE']"));
		Select size= new Select(dropdown);
		size.selectByValue("1");
		Thread.sleep(1000);
		String rate = driver.findElement(By.xpath("//span[@class='css-12x6n3h']")).getText();
		System.out.println("MRP of the selected shampoo is:"+rate);
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@type='button']")).click();
		Thread.sleep(1000);
		String grandTotal = driver.findElement(By.xpath("(//div[@class='value'])[4]")).getText();	
		String replaceAll = grandTotal.replaceAll("[^0-9]","");
		int total = Integer.parseInt(replaceAll);
		System.out.println("Grand Total in Cart: "+total);
		driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[@class='btn full big']")).click();
		String grandTotal1 = driver.findElement(By.xpath("(//div[@class='value'])[2]")).getText();
		
		String replaceAll1 = grandTotal1.replaceAll("[^0-9]","");
		int finalVal = Integer.parseInt(replaceAll1);
		System.out.println("Final Value:"+finalVal);
			if(total==finalVal)
				System.out.println("Price same in both page");
			else
				System.out.println("Price not same");
			driver.quit();
		
	}

}
