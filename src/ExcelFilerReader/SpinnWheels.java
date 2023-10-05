 package ExcelFilerReader;

import org.testng.annotations.Test;

import utility.ExcelFileReader;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

	public class SpinnWheels { 
	    private WebDriver driver;

	    @BeforeClass
	    public void setUp() throws InterruptedException {
	        
	        System.setProperty("webdriver.chrome.driver", "D:\\Browsers Driver\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
	        driver = new ChromeDriver();
	        driver.get("https://beta.kling.game/");
	        driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	        driver.get("https://beta.kling.game/");
	        
	        
	        driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[1]/div[1]/div/div/div/div/button[1]")).click(); 
	        
			driver.findElement(By.xpath("//*[@id=\"controlled-tab-example-tabpane-login\"]/form/div[1]/input")).sendKeys("adityajetester@gmail.com");
			
			driver.findElement(By.xpath("//*[@id=\"controlled-tab-example-tabpane-login\"]/form/div[2]/input")).sendKeys("Aditya@123");
			
			driver.findElement(By.xpath("//*[@id=\"controlled-tab-example-tabpane-login\"]/form/button[2]")).click();
			
			Thread.sleep(12000);
			
			driver.findElement(By.linkText("Spin Wheel")).click();
			Thread.sleep(10000);
			
			/*
			WebDriverWait wait = new WebDriverWait(driver, 10);
			WebElement spinWheelLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Spin Wheel")));
			spinWheelLink.click();
			*/
			
	    }

	    

	    @Test(dataProvider = "inputBoxData")
	    public void testInputBoxes(String inr,String xpath) throws InterruptedException {
	        
	    	
	        driver.findElement(By.xpath("your_xpath_for_first_input")).clear();
	        
	        Thread.sleep(2000);
	        
	        driver.findElement(By.xpath("your_xpath_for_first_input")).sendKeys();
			
	        Thread.sleep(2000);
	    	
	        WebElement inputBox = driver.findElement(By.xpath(xpath));
	        inputBox.click();
	        
	        Thread.sleep(2000);

	        // Perform other actions if needed after clicking each input box

	      
	    }

	    
	    @DataProvider(name = "inputBoxData")
	    public static Object[][] getTestData(ExcelFileReader data, String sheetName) {
		    List<List<Object>> testData = new ArrayList<>();
		    List<Integer> integers = data.readIntegers(sheetName, 0); // Integers in the first column
		    List<String> xpaths = data.readXPaths(sheetName, 1); // Xpaths in the second column

		    int size = Math.min(integers.size(), xpaths.size());

		    for (int i = 0; i < size; i++) {
		        List<Object> rowData = new ArrayList<>();
		        rowData.add(integers.get(i));
		        rowData.add(xpaths.get(i));
		        testData.add(rowData);
		    }

		    Object[][] myData = new Object[testData.size()][2]; // 2 columns: integer and xpath

		    for (int i = 0; i < testData.size(); i++) {
		        myData[i] = testData.get(i).toArray();
		    }

		    return myData;
		}
	   
	

}
