/**
 * 
 */
package automation;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author harshitkumar
 *
 */
public class DatePicker {
	WebDriver driver;
	WebElement selectedDate;
	String[] date = new String[3];
	
	@FindBy(className="date-picker-big")
    WebElement datePickerBig;

	By month_list = By.className("month-list");
	By month_wrapper = By.className("month-wrapper");
	By month_title = By.className("month-title");
	By date_list = By.className("date-list");
	By date_big_wrapper = By.className("date-big-wrapper");
	By date_label = By.className("date-label");
	
    public DatePicker(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    public String getTitle() {
    	return driver.getTitle().toLowerCase().trim();
    }
    
    public void switchToDatePickerWindow() throws InterruptedException {
    	ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        Thread.sleep(4000);       
    }
    
    public Boolean showOnSelectedDate(String givenDate) {
    	date = givenDate.toLowerCase().split("\\s+");
    	Boolean showOnDate = false;
    	List<WebElement> months = datePickerBig.findElement(month_list).findElements(month_wrapper);
    	for(WebElement month : months) {
    		String getMonth = month.findElement(month_title).getText();
	    	if(getMonth.toLowerCase().contains(date[1]) && getMonth.toLowerCase().contains(date[2]))
	    	{
	    		List<WebElement> dates = month.findElement(date_list).findElements(date_big_wrapper);
	    		for(WebElement dte : dates) {
	    			if(!dte.getText().isEmpty()) {
	    				String dt = dte.findElement(date_label).getText();
	    				if(dt.equalsIgnoreCase(date[0])) {
	    					selectedDate = dte;
	    					showOnDate = true;
	    				}
	    			}
	    		}
	    	}
    	}
    	return showOnDate;
    }
    
    public void clickSelectedDate() {
    	selectedDate.click();
    }
    
}
