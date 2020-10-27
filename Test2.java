import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test2 {
	  //the location of ChromeDriver in localsystem
	  static String pathDriver ="C:\\Selenium\\Tools\\CastleHall\\chromedriver.exe";
	  // url
	  static String url ="https://www.castlehalldiligence.com/careers";
	  //webdriver reference
	  static WebDriver driver;
	  
	@BeforeTest
	  public void beforeTest() {
		  // Test setup
		  System.setProperty("webdriver.chrome.driver",pathDriver);
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  System.out.println("Setup finished ...");
		  
	  }
	
	@Test
	  public void testTwo() {
		driver.get(url);
		
		WebElement searchBox = driver.findElement(By.xpath("//*[@id='hs_cos_wrapper_module_1496357075133374']/input"));  
		searchBox.sendKeys("Senior QA");
		searchBox.sendKeys(Keys.RETURN);
		
		String expectedText = "Senior QA – (Maternity Leave Replacement)";
		
		String actualText = driver.findElement(By.xpath("//*[@id='hs_cos_wrapper_widget_1594904478820']/div[1]/div[2]/h2/strong")).getText();
		
		Assert.assertEquals(actualText, expectedText);
		System.out.println("Job titles match");
		
		WebElement readMore = driver.findElement(By.xpath("//a[contains(text(),'Read more')]")); 
		//readMore.click();
		Actions actions = new Actions(driver);

		actions.moveToElement(readMore).click().perform();
		
		// explicit wait for page to load
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='hs_cos_wrapper_module_14945973230434188']/h1/strong")));
		
		String expectedTextBlob = "Castle Hall is an entrepreneurial, dynamic and fast moving company that is looking to add a Senior Quality Analyst to our team. Reporting to the Director, Platform Development in Montreal, this role is a maternity leave replacement, until November 2021. To respond to increasing demand for Castle Hall’s services, and the firm’s commitment to continued growth, there is a possibility for conversion to a full time, permanent position at the end of the maternity leave replacement period. The successful candidate will take part in the design, test and validation process to deliver the next iteration of our web application and be given the opportunity for personal and professional growth.";
		
		String actualTextBlob = driver.findElement(By.xpath("//*[@id='hs_cos_wrapper_module_14945973230434188']/p[1]")).getText();
		
		Assert.assertEquals(actualTextBlob, expectedTextBlob);
		System.out.println("Job posting text is asserted");
		
		//Teardown
		System.out.println("Teardown ...");
		driver.quit();
	}
}
