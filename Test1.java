import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Test1 {

	//the location of ChromeDriver in localsystem
	  static String pathDriver ="C:\\Selenium\\Tools\\CastleHall\\chromedriver.exe";
	  // url
	  static String url ="https://www.castlehalldiligence.com/";
	  //webdriver reference
	  static WebDriver driver;
	  
	  
	  @Test
	  public void testOne() {
		driver.get(url);
		
		WebElement aboutItem = driver.findElement(By.xpath("//div[contains(@class,'row-fluid-wrapper row-depth-1 row-number-6')]//div[contains(@class,'row-fluid')]//div[@class='span12 widget-span widget-type-cell all-inline tr']//div[contains(@class,'row-fluid-wrapper row-depth-2 row-number-1')]//div[contains(@class,'row-fluid')]//div[@class='span12 widget-span widget-type-menu menu-bar first-not-active flyouts-fade flyouts-slide md-hidden']//div[@class='cell-wrapper layout-widget-wrapper']//span[@id='hs_cos_wrapper_module_146731076570911']//div[@id='hs_menu_wrapper_module_146731076570911']//ul//li[@class='hs-menu-item hs-menu-depth-1 hs-item-has-children']//a[@href='javascript:;'][contains(text(),'About')]"));  
		aboutItem.click();
		
		String[] expected = {"Our Story", "Why Castle Hall", "Our Team", "Careers"};
		String[] actual = new String[4];
		
		//retrieving menu items
		actual[0] =  driver.findElement(By.xpath("//*[@id='hs_menu_wrapper_module_146731076570911']/ul/li[3]/ul/li[1]/a")).getText();
		actual[1] =  driver.findElement(By.xpath("//*[@id='hs_menu_wrapper_module_146731076570911']/ul/li[3]/ul/li[2]/a")).getText();
		actual[2] =  driver.findElement(By.xpath("//*[@id='hs_menu_wrapper_module_146731076570911']/ul/li[3]/ul/li[3]/a")).getText();
		actual[3] =  driver.findElement(By.xpath("//*[@id='hs_menu_wrapper_module_146731076570911']/ul/li[3]/ul/li[4]/a")).getText();
		//asserting all menu items
		for(int i=0; i<actual.length; i++) {
			System.out.println(actual[i]);
			Assert.assertEquals(actual[i], expected[i]);
		}
		System.out.println("Menu items are asserted");
		
		//going to 2nd item in the menu list
		driver.findElement(By.xpath("//*[@id='hs_menu_wrapper_module_146731076570911']/ul/li[3]/ul/li[2]/a")).click();
		
		
		// explicit wait for page to load
		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='hs_cos_wrapper_module_1497992439167416']/p")));
		
		String[] expectedNums = {"80+", "5,000+", "6"};
		String[] actualNums = new String[3];
		actualNums[0] = driver.findElement(By.xpath("//*[@id='hs_cos_wrapper_module_1497992439167416']/h2")).getText();
		actualNums[1] = driver.findElement(By.xpath("//*[@id='hs_cos_wrapper_module_1497992500232433']/h2")).getText();
		actualNums[2] = driver.findElement(By.xpath("//*[@id='hs_cos_wrapper_module_1497992497318429']/h2")).getText();
		//asserting all numbers
		for(int i=0; i<actualNums.length; i++) {
			System.out.println(actual[i]);
			Assert.assertEquals(actualNums[i], expectedNums[i]);
		}
		System.out.println("At glance values are asserted");
		
		//Teardown
		System.out.println("Teardown ...");
		driver.quit();
	  }
	  
	  @BeforeTest
	  public void beforeTest() {
		  // Test setup
		  System.setProperty("webdriver.chrome.driver",pathDriver);
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  System.out.println("Setup finished ...");
		  
	  }
	  

}
