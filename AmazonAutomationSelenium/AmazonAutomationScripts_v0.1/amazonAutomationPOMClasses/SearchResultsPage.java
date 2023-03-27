package amazonAutomationPOMClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchResultsPage {
    WebDriver driver;
    
    //By firstResultByObj = By.cssSelector("div.sg-col-20-of-24:nth-child(3)"); //not workig
    //By firstResultByObj = By.cssSelector(".widgetId\\=search-results_1 > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > div:nth-child(2) > div:nth-child(1) > div:nth-child(1) > div:nth-child(1) > h2:nth-child(2) > a:nth-child(1)");//working
    //By firstResultByObj = By.xpath("(//div[@class='s-result-list sg-row']/div[@data-component-type='s-search-result'])[1]");//not working
    By firstResultByObj = By.xpath("//div[@class='sg-col-20-of-24 s-result-item s-asin sg-col-0-of-12 sg-col-16-of-20 sg-col s-widget-spacing-small sg-col-12-of-16']//div[@class='sg-col sg-col-4-of-12 sg-col-8-of-16 sg-col-12-of-20 sg-col-12-of-24 s-list-col-right']//a");
    By firstResultProductNameByObj = By.xpath("//div[@class='sg-col-20-of-24 s-result-item s-asin sg-col-0-of-12 sg-col-16-of-20 sg-col s-widget-spacing-small sg-col-12-of-16']//div[@class='sg-col sg-col-4-of-12 sg-col-8-of-16 sg-col-12-of-20 sg-col-12-of-24 s-list-col-right']//a//span");   
    By firstResultProductPriceByObj = By.xpath("/html/body/div[1]/div[2]/div[1]/div[1]/div/span[1]/div[1]/div[3]/div/div/div/div/div/div[2]/div/div/div[3]/div[1]/div/div[1]/div[2]/a/span[1]/span[2]/span[2]");
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
    }
    
    public void clickOnFirstResult() {
    	System.out.println("Inside clickOnFirstResult function");
        driver.findElement(firstResultByObj).click();
        
    }
    public String getFirstResultURL() {
    	System.out.println("Inside getFirstResultURL function - URL is - "+driver.findElement(firstResultByObj).getAttribute("href"));
        return driver.findElement(firstResultByObj).getAttribute("href");
    }
    public String getFirstResultProductName() {
    	System.out.println("Inside getFirstResultProductName function - Product name is - " + driver.findElement(firstResultProductNameByObj).getText());
    	return driver.findElement(firstResultProductNameByObj).getText();
    }
    public String getFirstResultProductPrice() {
    	System.out.println("Inside getFirstResultProductPrice function - Price is - " + driver.findElement(firstResultProductPriceByObj).getText());
    	return driver.findElement(firstResultProductPriceByObj).getText();
    }
    
    

}
