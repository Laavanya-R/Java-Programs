package amazonAutomationPOMClasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HelpAndCustomerServicePage {
	
	WebDriver driver;
	// Get the list of left navigation menu items
	//By leftNavElementsByObj = By.cssSelector("div.inner:nth-child(2)");//not working
	//By leftNavElementsByObj = By.cssSelector(".a-unordered-list.a-nostyle.a-vertical.a-spacing-base li");//not working
	By leftNavElementsByObj = By.className("nav-topics");//working
    //By leftNavElementsByObj = By.xpath("/html/body/div[1]/div[2]/div[2]/div[2]/div[1]/div[2]/ul"); //not working

    public HelpAndCustomerServicePage(WebDriver driver){
    	this.driver = driver;
    }
    
    public List<WebElement> getLeftNavElements() {
    	
    	List<WebElement> leftNavElements = driver.findElements(leftNavElementsByObj);
    	System.out.println("leftNavElements size from inside the function - " + leftNavElements.size());
    	for(WebElement leftNavElement:leftNavElements)
    		System.out.println(leftNavElement.getText());
    	System.out.println("leftNavElements size from inside the function - " + leftNavElements.size());
    	return leftNavElements;
    	
    }
}
