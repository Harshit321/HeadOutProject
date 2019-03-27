package automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	WebDriver driver;
	
	@FindBy(id="search-form")
    WebElement searchFormDiv;

	By searchRes = By.className("searchRes");
	By results = By.className("results");
	By item = By.className("item");
	By div = By.tagName("div");
	By input = By.tagName("input");
	By content_wrap = By.className("content-wrap");
	By content = By.className("content");
	By show_info = By.className("show-info");
	
    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    
    // Select the show from search bar
    public void searchShow(String showName) throws InterruptedException {
    	WebElement searchForm = searchFormDiv.findElement(input);
		Thread.sleep(2000);
		searchForm.sendKeys(showName);
		Thread.sleep(1000);
    }
    
    public Boolean showVisibleinSearchResults(String showName) {
    	List<WebElement> searchResults = driver.findElement(searchRes).findElement(results).findElements(item);
    	Boolean showFound = false;
		for(WebElement e : searchResults) {
			List<WebElement> divList = e.findElements(div);
			for(WebElement div : divList) {
				if(div.getAttribute("class").toString().equalsIgnoreCase("title"))
				{
					if(div.getText().trim().contains(showName))
					{
						div.click();
						showFound = true;
						break;
					}
						
				}
			}
			if(showFound)
				break;
		}
		return showFound;
    }
    
    public void bookShow(String showName) throws InterruptedException {
    	WebElement contentDiv = driver.findElement(content_wrap).findElement(content).findElement(show_info);
		String getTitle = contentDiv.findElement(By.tagName("h1")).getText().trim();
		if(getTitle.equalsIgnoreCase(showName)) {
			WebElement bookNowBtn = contentDiv.findElement(By.className("cta")).findElement(By.className("button"));
			Thread.sleep(2000);
			bookNowBtn.click();
		}
    }
    
    
}
