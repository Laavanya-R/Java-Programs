package amazonAutomationPOMClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {
    WebDriver driver;
    By productNameInCartPageByObj = By.cssSelector(".a-truncate-cut");
    By productPriceInCartPageByObj = By.cssSelector(".sc-product-price");
    By listOfAllProductPriceByObj = By.xpath("//div[@class='sc-item-price-block']//span");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }
    public String getProductName() {
    	System.out.println("Inside getProductName function - Product name is - " + driver.findElement(productNameInCartPageByObj).getText());
    	return driver.findElement(productNameInCartPageByObj).getText();
    }
    
    public String getProductPrice() {
    	System.out.println("Inside getProductPrice function - Product price is - " + driver.findElement(productPriceInCartPageByObj).getText());
    	return driver.findElement(productPriceInCartPageByObj).getText();
    }
    
    public void getListOfAllProductPrice() {
    	System.out.println("Inside getListOfAllProductPrice function");
    	
    	List<WebElement> listOfAllProductPrice = driver.findElements(listOfAllProductPriceByObj);
    	System.out.println("count" + listOfAllProductPrice.size());
    	for(WebElement productPrice : listOfAllProductPrice)
    		System.out.println("price - " + productPrice.getText());
    }

}
