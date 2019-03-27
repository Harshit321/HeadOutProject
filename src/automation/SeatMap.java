package automation;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeatMap {
	WebDriver driver;
	
	WebElement selectedSeat;
	String[] seatDetails = new String[3];
	
	
	@FindBy(id="seatmap")
    WebElement seatmap;
	
	By outer_svg = By.className("outer-svg");
	By rotatedSvgProp = By.id("rotatedSvg");
	By coverSvgProp = By.id("coverSvg");
	By svgForeground = By.className("svg-wrapper-foreground");
	
	By date_list = By.className("date-list");
	By date_big_wrapper = By.className("date-big-wrapper");
	By date_label = By.className("date-label");
	By sectionProp = By.className("section");
	
	public SeatMap(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
	
	public String getTitle() {
    	return driver.getTitle().toLowerCase().trim();
    }
	
	public void swtichToFrame() {
		driver.switchTo().frame(0);
	}
	
	public Boolean isSeatAvailaible(String givenSeat) {
		Boolean seatAvailaible = false;
		seatDetails = givenSeat.toLowerCase().trim().split("-");
		
		
		WebElement outerSvg = seatmap.findElement(outer_svg);
		WebElement rotatedSvg = outerSvg.findElement(rotatedSvgProp);
		WebElement coverSvg = rotatedSvg.findElement(coverSvgProp);
		WebElement svgForeGround = coverSvg.findElement(svgForeground);
		List<WebElement> sections = svgForeGround.findElements(sectionProp);
		
		for(WebElement section : sections) {
			System.out.println(section.getAttribute("display"));
			if(section.getAttribute("display").toLowerCase().contentEquals(seatDetails[0])) {
				List<WebElement> rows = section.findElements(By.className("row"));
				for(WebElement row : rows) {
					System.out.println(row.getAttribute("display"));
					if(row.getAttribute("display").toLowerCase().contentEquals(seatDetails[1])) {
						List<WebElement> circles = row.findElements(By.className("bookable"));
						for(WebElement circle : circles) {
							if(circle.getAttribute("display").toLowerCase().contentEquals(seatDetails[2])) {
								seatAvailaible = true;
								selectedSeat = circle;
								break;
							}
						}
					}
					if(seatAvailaible)
						break;
				}
			}
			if(seatAvailaible)
				break;
		}
		return seatAvailaible;		
	}
	
	public void selectSeat() {
		selectedSeat.click();
	}
	
	public void ClickNextButton() {
		driver.switchTo().defaultContent();
		WebElement seatsColumnDiv = driver.findElement(By.className("seats-column-wrapper"));
		WebElement NextBtn = seatsColumnDiv.findElement(By.className("next-button"));
		if(NextBtn.getAttribute("class").contains("selectable"))
			NextBtn.click();
	}
	
}
