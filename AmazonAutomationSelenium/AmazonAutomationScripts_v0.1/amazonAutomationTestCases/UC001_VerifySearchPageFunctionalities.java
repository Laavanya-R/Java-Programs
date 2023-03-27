package amazonAutomationTestCases;

import org.testng.annotations.Test;

import amazonAutomationPOMClasses.HomePage;
import amazonAutomationPOMClasses.ProductPage;
import amazonAutomationPOMClasses.SearchResultsPage;

import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class UC001_VerifySearchPageFunctionalities {
	WebDriver driver;
	HomePage homePageObject;
	SearchResultsPage searchResultsPageObject;
	ProductPage productPageObject;
	
  @BeforeTest
  public void beforeTest() {
	  System.out.println("Inside Before Test - driver object initialized");
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
      driver.get("https://www.amazon.in/");
      homePageObject = new HomePage(driver);
      searchResultsPageObject = new SearchResultsPage(driver);
      productPageObject = new ProductPage(driver);
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Inside After Test");
	  driver.close();
  }
  @Test(priority = 0)
  public void TC001_verifyProductTitleAndPriceAfterSearch(){
	  
	  String searchText = "The Theory Of Everything";
	  
	  //System.out.println("Title - " + driver.getTitle());
	  
	  homePageObject.searchProductAndSubmit(searchText);
	  
	// Store the current window handle
//	  String winHandleBefore = driver.getWindowHandle();
//	  System.out.println(winHandleBefore);
	  
	  //System.out.println("Title - " + driver.getTitle());
	  
	  //searchResultsPageObject.clickOnFirstResult();
	 driver.get(searchResultsPageObject.getFirstResultURL());
	  
	  // Wait for the new tab to open
//	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//	  wait.until(ExpectedConditions.numberOfWindowsToBe(2));
  
	// Switch to new window opened
//	  for(String winHandle : driver.getWindowHandles()){
//		  System.out.println(winHandle);
//		  if(!winHandle.equals(winHandleBefore)) {
//	      driver.switchTo().window(winHandle);
//		  break;}
//	  }
//
//	  System.out.println("Title - " + driver.getTitle());
	  
      String firstResultProductName = productPageObject.getProductName();
      String firstResultProductPrice = productPageObject.getProductPrice();

      //Assert.assertTrue(firstResultProductName.toLowerCase().contains("theory"));

      
      System.out.println("firstResultProductName : " + firstResultProductName);
      
      System.out.println("firstResultProductPrice : " + firstResultProductPrice);
      
      Assert.assertTrue(firstResultProductName.toLowerCase().contains("theory") && firstResultProductPrice.equals( "138.00"));
	  
  }
  
  @Test(priority = 1)
  public void TC002_verifyAvailableFormatsAndEditions(){
	  List<WebElement> availableFormatsAndEditions = productPageObject.getAvailableFormatsAndEditions();
	  for(WebElement eachAvailableFormatsAndEditions : availableFormatsAndEditions)
		  System.out.println(eachAvailableFormatsAndEditions.getText());
  }

}
