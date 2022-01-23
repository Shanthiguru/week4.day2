package week4.day2.Tables;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Assgmt_LeafGroundSortable {

	public static void main(String[] args) 
	{
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.get("http://www.leafground.com/pages/sorttable.html");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		int size = driver.findElements(By.xpath("//table[@id='table_id']//tbody/tr")).size();
		List<String> nameList= new ArrayList<String>();
		
		for(int i=1;i<=size;i++)
		{
			String name = driver.findElement(By.xpath("//table[@id='table_id']//tbody/tr["+ i +"]/td[2]")).getText();
			
			nameList.add(name);
		}
		System.out.println("Names before sorted: "+nameList);
		
		Collections.sort(nameList);
		System.out.println("Sorted Namelist: "+nameList);
					
		driver.findElement(By.xpath("//th[text()='Name']")).click();
		List<String>nameList1 = new ArrayList<String>();
		
		for(int i=1;i<=size;i++)
		{
			String name1 = driver.findElement(By.xpath("//table[@id='table_id']//tbody/tr["+ i +"]/td[2]")).getText();
			nameList1.add(name1);
		}
		System.out.println("Sorted Namelist: "+nameList1);
		
		if(nameList1.equals(nameList))
			System.out.println("Names Sorted");
		else
			System.out.println("Names Not Sorted");
		
		driver.close();
		

	}

}
