package amazonAutomationTestCases;

import org.testng.annotations.Test;

import amazonAutomationPOMClasses.CartPage;
import amazonAutomationPOMClasses.HomePage;
import amazonAutomationPOMClasses.ProductPage;
import amazonAutomationPOMClasses.SearchResultsPage;
import net.bytebuddy.description.ModifierReviewable.OfAbstraction;

import org.testng.annotations.BeforeTest;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class UC002_VerifyCartPageFunctionalities {
	WebDriver driver;
	HomePage homePageObject;
	SearchResultsPage searchResultsPageObject;
	ProductPage productPageObject;
	CartPage cartPageObject;

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Inside Before Test - driver object initialized");
	  driver = new FirefoxDriver();
	  driver.manage().window().maximize();
      driver.get("https://www.amazon.in/");
      homePageObject = new HomePage(driver);
      searchResultsPageObject = new SearchResultsPage(driver);
      productPageObject = new ProductPage(driver);
      cartPageObject = new CartPage(driver);
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Inside After Test");
	  //driver.close();
  }
@Test(priority = 0)
public void TC001_verifyIfProductNameAndPriceAcrossPages() {
	// Search a product
	String searchText = "The Theory Of Everything";
	homePageObject.searchProductAndSubmit(searchText);

    // Get product name and price from search results page
    String productNameOfFirstResultInSearchResultsPage = searchResultsPageObject.getFirstResultProductName();
    String productPriceOfFirstResultInSearchResultsPage = searchResultsPageObject.getFirstResultProductPrice();
    System.out.println("productNameOfFirstResultInSearchResultsPage : " + productNameOfFirstResultInSearchResultsPage);
    System.out.println("productPriceOfFirstResultInSearchResultsPage : " + productPriceOfFirstResultInSearchResultsPage);

	
    // Click on the first search result
    //searchResultsPageObject.clickOnFirstResult(); //if this function is used, product opens in new tab and the elements are not read
	driver.get(searchResultsPageObject.getFirstResultURL());
	
	// Get product name and price from product details page
	  String productNameInProductDetailsPage = productPageObject.getProductName();
    String productPriceInProductDetailsPage = productPageObject.getProductPrice();
    System.out.println("productNameInProductDetailsPage : " + productNameInProductDetailsPage);
    System.out.println("productPriceInProductDetailsPage : " + productPriceInProductDetailsPage);
    
    //Assert.assertTrue(firstResultInSearchResultsProductName.toLowerCase().contains("theory"));

    // Add product to cart
    productPageObject.clickAddToCartButton();

    // Go to the cart
    productPageObject.clickCartIcon();

    // Get product name and price from cart page
    String productNameInCartPage = cartPageObject.getProductName();
    String productPriceInCartPage = cartPageObject.getProductPrice();
    System.out.println("productNameInCartPage : " + productNameInCartPage);
    System.out.println("productPriceInCartPage : " + productPriceInCartPage);

    // Verify if product name and price is same in search results page, product details page and cart page
    if (productNameOfFirstResultInSearchResultsPage.equals(productNameInProductDetailsPage) &&
    		productNameOfFirstResultInSearchResultsPage.equals(productNameInCartPage)) {
        System.out.println("Product name is the same across search results page, product details page and cart page");
    } else {
        System.out.println("Mismatch in Product name");
    }
    
    if (Integer.parseInt(productPriceOfFirstResultInSearchResultsPage) == (int)(Float.parseFloat(productPriceInProductDetailsPage)) &&
    		Integer.parseInt(productPriceOfFirstResultInSearchResultsPage) == (int)Float.parseFloat(productPriceInCartPage))  {
        System.out.println("Product price is the same across search results page, product details page and cart page");
    } else {
        System.out.println("Mismatch in Product price");
    }

	
}
@Test(priority = 1)
public void TC002_verifyIfMultipleProductsCanBeAddedToCart() {
	// Search a product
	String searchText1 = "Robinson Crusoe";
	homePageObject.searchProductAndSubmit(searchText1);
    // Click on the first search result
	driver.get(searchResultsPageObject.getFirstResultURL());
    // Add product to cart
    productPageObject.clickAddToCartButton();
    
	// Search another product
	String searchText2 = "Ikigai";
	homePageObject.searchProductAndSubmit(searchText2);
    // Click on the first search result
	driver.get(searchResultsPageObject.getFirstResultURL());
    // Add product to cart
    productPageObject.clickAddToCartButton();
    
	// Search another product
	String searchText3 = "gullivers travels by jonathan swift";
	homePageObject.searchProductAndSubmit(searchText3);
    // Click on the first search result
	driver.get(searchResultsPageObject.getFirstResultURL());
    // Add product to cart
    productPageObject.clickAddToCartButton();

    
    // Go to the cart
    //productPageObject.clickCartIcon();
    //driver.findElement(By.id("nav-cart")).click();//*[@id="nav-cart"]
    productPageObject.clickGoToCartButton();
    
    
    try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    //validate if the products added are listed with correct pricing and total adds up
    //Add each price and verify if it equals total
    List<WebElement> listOfAllProductPrice = cartPageObject.getListOfAllProductPrice();
    int sumOfAllProductPrice = 0;
    for(WebElement priceElement : listOfAllProductPrice) {
    	 System.out.println("priceElement.getText()" + priceElement.getText());
    	 if(!priceElement.getText().equals("  ")) { //try to not fetch the unwanted span tag
    		 sumOfAllProductPrice = sumOfAllProductPrice + (int)Float.parseFloat(priceElement.getText());//need to multiply with quantity if qty  is more than 1
    	 }
    	 }
    
    System.out.println("sumOfAllProductPrice" + sumOfAllProductPrice);
    
    System.out.println("cartPageObject.getSubTotal()" + (int)Float.parseFloat(cartPageObject.getSubTotal()));

    Assert.assertEquals(sumOfAllProductPrice, (int)Float.parseFloat(cartPageObject.getSubTotal()));

}
@Test(priority = 1)
public void TC003_verifyIfProductAddedToCartCanBeEdited() {
	
}
@Test(priority = 1)
public void TC004_verifyIfProductAddedToCartCanBeDeleted() {
	
}
  
}
