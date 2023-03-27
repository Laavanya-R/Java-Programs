package amazonAutomationPOMClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HelpPage {
	
	WebDriver driver;
	
    By amazonPrimeMenuElementByObj = By.xpath("/html/body/div[1]/div[2]/article/div/div[2]/div/div/div[1]/ul/li[3]/a");
    By aboutAmazonPrimeSubMenuByObj = By.xpath("/html/body/div[1]/div[2]/article/div/div[2]/div/div/div[4]/div/div/div/ul/li[2]/span/a");
	
    //try to construct xpath - following is not working
    //By amazonPrimeMenuElementByObj = By.xpath("//a[@id='nav-link-prime']");
	//By aboutAmazonPrimeSubMenuByObj = By.xpath("//div[@id='hmenu-content']//a[contains(text(),'About Amazon Prime')]");
	public WebElement amazonPrimeMenuElement;
	public WebElement aboutAmazonPrimeSubMenuElement;
	
	
	public HelpPage(WebDriver driver) {
    	this.driver = driver;
		
	}
    
    public void navigateToAboutAmazonPrimeSubMenu() {
	  WebElement amazonPrimeMenuElement = driver.findElement(amazonPrimeMenuElementByObj); // locate the menu element
	  Actions actions = new Actions(driver);
	  actions.moveToElement(amazonPrimeMenuElement).build().perform(); // hover over the menu element

	  WebElement aboutAmazonPrimeSubMenuElement = driver.findElement(aboutAmazonPrimeSubMenuByObj); // locate the sub-menu element
	  aboutAmazonPrimeSubMenuElement.click(); // click on the sub-menu element
    
    }
    
    public void clickOnAmazonPrimeMenu() {
    	amazonPrimeMenuElement = driver.findElement(amazonPrimeMenuElementByObj); // locate the menu element
    	amazonPrimeMenuElement.click(); 
		
	}
    
    public String getAboutAmazonPrimeSubMenuElementURL() {
    	return driver.findElement(aboutAmazonPrimeSubMenuByObj).getAttribute("href");
    }
    
    public void clickOnAboutAmazonPrimeSubMenu() {
    	aboutAmazonPrimeSubMenuElement = driver.findElement(aboutAmazonPrimeSubMenuByObj); // locate the sub-menu element
    	aboutAmazonPrimeSubMenuElement.click(); 
        
		
	}

}
