package amazonAutomationPOMClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    WebDriver driver;
    
    By searchBoxByObj = By.id("twotabsearchtextbox");
    By searchButtonByObj = By.id("nav-search-submit-button");
    By helpLinkByObj = By.xpath("//*[@id=\"navFooter\"]/div[1]/div/div[7]/ul/li[6]/a");
    
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void searchProductAndSubmit(String searchProductName) {
        driver.findElement(searchBoxByObj).sendKeys(searchProductName);
        driver.findElement(searchButtonByObj).click();
    }
    
    public void clickOnHelpLinkInFooter() {
    	driver.findElement(helpLinkByObj).click();
    }

}
