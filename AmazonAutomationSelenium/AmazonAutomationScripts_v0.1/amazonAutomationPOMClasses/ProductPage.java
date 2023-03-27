package amazonAutomationPOMClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductPage {
	WebDriver driver;
	
    By productNameByObj = By.id("productTitle");
    By productPriceByObj = By.id("price");
    By addToCartByObj = By.id("add-to-cart-button");
    By cartIconByObj = By.id("nav-cart");
    
    By formatsAndEditionsByObj = By.className("");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public String getProductName() {
    	System.out.println("inside getProductName function - Product name is - " + driver.findElement(productNameByObj).getText());
    	return driver.findElement(productNameByObj).getText();
    }
    
    public String getProductPrice() {
    	System.out.println("inside getProductPrice function - Product price is - " + driver.findElement(productPriceByObj).getText());
    	return driver.findElement(productPriceByObj).getText().substring(1);//substring to remove ₹ symbol
    }
    
    public void clickAddToCartButton() {
    	System.out.println("inside clickAddToCartButton function");
    	driver.findElement(addToCartByObj).click();
    }
    public void clickGoToCartIcon() {
    	System.out.println("inside clickGoToCartIcon function");
    	driver.findElement(cartIconByObj).click();
    }
    
    public List<WebElement> getAvailableFormatsAndEditions() {
    	List<WebElement> availableFormatsAndEditions = driver.findElements(formatsAndEditionsByObj);
    	return availableFormatsAndEditions;
    }

}
