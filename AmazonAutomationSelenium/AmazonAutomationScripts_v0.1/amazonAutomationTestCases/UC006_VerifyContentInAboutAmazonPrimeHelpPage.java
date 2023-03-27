package amazonAutomationTestCases;

import org.testng.annotations.Test;

import amazonAutomationPOMClasses.HelpAndCustomerServicePage;
import amazonAutomationPOMClasses.HelpPage;
import amazonAutomationPOMClasses.HomePage;
import amazonAutomationPOMClasses.ProductPage;
import amazonAutomationPOMClasses.SearchResultsPage;

import org.testng.annotations.BeforeTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class UC006_VerifyContentInAboutAmazonPrimeHelpPage {

	WebDriver driver;
	HomePage homePageObject;
	SearchResultsPage searchResultsPageObject;
	ProductPage productPageObject;
	HelpPage helpPageObject;
	HelpAndCustomerServicePage helpAndCustomerServicePageObject;
	
	
  @BeforeTest
  public void beforeTest() {
	  System.out.println("Inside Before Test - driver object initialized");
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
      driver.get("https://www.amazon.in/");
      homePageObject = new HomePage(driver);
      helpPageObject = new HelpPage(driver);
      helpAndCustomerServicePageObject = new HelpAndCustomerServicePage(driver);
     
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Inside After Test");
  }
  
  @Test
  public void TC001_verifyLeftNavElementsInHelpAndCustomerService() {
	  
	  homePageObject.clickOnHelpLinkInFooter();
	  
	  // Wait for the page to load
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/article/div/div[2]/div/div/div[1]/ul/li[3]/a")));
	  //wait.until(ExpectedConditions.titleIs("Help - Amazon Customer Service"));
	  
	  //helpPageObject.navigateToAboutAmazonPrimeSubMenu(); //not working - does not hover and click
	  //helpPageObject.clickOnAmazonPrimeMenu();
	  //helpPageObject.clickOnAboutAmazonPrimeSubMenu();//navigates, but does not get the left nav element from the new tab
	  
	  driver.get(helpPageObject.getAboutAmazonPrimeSubMenuElementURL());
	  List<WebElement> leftNavElements = helpAndCustomerServicePageObject.getLeftNavElements();
	  
	  List<String> leftNavElementsText = new ArrayList<String>();
	  System.out.println("leftNavElements size - " + leftNavElements.size());
	  

	  
	  for(WebElement leftNavElement:leftNavElements) {
		  
		  leftNavElementsText.add(leftNavElement.getText());
		  }
	  
	  List<String> expectedLeftNavElementsList = Arrays.asList(
			  "Amazon Prime Benefits",
			  "Amazon Prime Membership Benefits",
			  "Sign Up for Amazon Prime",
			  "End Your Amazon Prime Membership",
			  "The Amazon Prime Membership Fee",
			  "Amazon Prime Terms and Conditions",
			  "Recurring Payments for Amazon Prime - Terms",
			  "Manage Your Amazon Prime Membership",
			  "About Using a Promotional Code for Amazon Prime");
	  //Assert.assertEquals(leftNavElementsText, expectedLeftNavElementsList);//not working
	  
	// Assert that the text of each left navigation menu item matches the expected text
	  int i=0;
	  for(WebElement leftNavElement:leftNavElements) {
	  		System.out.println("leftNavElement.getText() - " + leftNavElement.getText());
	  		System.out.println("next line - ");
	  		System.out.println("expectedLeftNavElementsList.get(i) - " + expectedLeftNavElementsList.get(i));
	  		assert expectedLeftNavElementsList.get(i).equals(leftNavElement.getText()); i++;
  }
//      for (int i = 0; i < leftNavElementsText.size(); i++) {
//         String actualText = leftNavElementsText.get(i);
//         System.out.println("leftNavElementsText size - " + leftNavElementsText.size());
//         System.out.println("expectedLeftNavElementsList size - " + expectedLeftNavElementsList.size());
//         
//         System.out.println("actualText - " + actualText);
//         System.out.println("expectedLeftNavElementsList.get(i) - " + expectedLeftNavElementsList.get(i));
//         assert actualText.equals(expectedLeftNavElementsList.get(i));
//      }
	  System.out.println("Assertion complete");

	  
	  
	  
  }
 

}
