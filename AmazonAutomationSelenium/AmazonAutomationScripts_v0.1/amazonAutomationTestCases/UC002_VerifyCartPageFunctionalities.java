package amazonAutomationTestCases;

import org.testng.annotations.Test;

import amazonAutomationPOMClasses.CartPage;
import amazonAutomationPOMClasses.HomePage;
import amazonAutomationPOMClasses.ProductPage;
import amazonAutomationPOMClasses.SearchResultsPage;

import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
    productPageObject.clickGoToCartIcon();

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
	String searchText1 = "The Theory Of Everything";
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
	String searchText3 = "OnePlus 11 5G";
	homePageObject.searchProductAndSubmit(searchText3);
    // Click on the first search result
	driver.get(searchResultsPageObject.getFirstResultURL());
    // Add product to cart
    productPageObject.clickAddToCartButton();

    
    // Go to the cart
    //productPageObject.clickGoToCartIcon();
    driver.findElement(By.xpath("//*[@id=\"nav-cart\"]"));
    
    try {
		Thread.sleep(10000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
    //validate if the products added are listed with correct pricing and total adds up
    cartPageObject.getListOfAllProductPrice();
    //Add each price and verify if it equals total



}
@Test(priority = 1)
public void TC003_verifyIfProductAddedToCartCanBeEdited() {
	
}
@Test(priority = 1)
public void TC004_verifyIfProductAddedToCartCanBeDeleted() {
	
}
  
}
