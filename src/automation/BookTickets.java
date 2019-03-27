package automation;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BookTickets {
	public WebDriver driver;
	
	HomePage objHomePage;
	DatePicker objDatePicker;
	SeatMap objSeatMap;
	CheckoutDetails objCheckoutDetails;
	
	String showTitle = "The Simon and Garfunkel Story";
	String givenDate = "29 April 2019";
	String givenSeat = " STALLS-Q-7";
	String firstname = "Rajat";
	String lastName = "Kapoor";
	String emailInput = "abc@xyz.com";
	String phoneInput = "+919001987789";
	String promoCodeInput = "FIRST";
	String cardNo = "1000900080007000";
	String cardExpiryInput = "03/29";
	String cardCvvInput = "001";
	String cardHolderName = "HARSHIT KUMAR";
	
	@Test(priority=0)
	public void selectShow() throws InterruptedException {
		objHomePage = new HomePage(driver);
		objHomePage.searchShow(showTitle);
		Assert.assertTrue(objHomePage.showVisibleinSearchResults(showTitle), "Show is not visible in search results.");
		objHomePage.bookShow(showTitle);
	}

	@Test(priority=1)
	public void selectDate() throws InterruptedException {
		objDatePicker = new DatePicker(driver);
		String title = objDatePicker.getTitle();
		System.out.println(title);
		Assert.assertTrue(true);
		objDatePicker.switchToDatePickerWindow();
		Assert.assertTrue(objDatePicker.showOnSelectedDate(givenDate), "Show is not present on the given date");
		objDatePicker.clickSelectedDate();		
	}
	
	@Test(priority=2)
	public void selectSeat() {
		objSeatMap = new SeatMap(driver);
		String title = objSeatMap.getTitle();
		System.out.println(title);
		Assert.assertTrue(true);
		objSeatMap.swtichToFrame();
		Assert.assertTrue(objSeatMap.isSeatAvailaible(givenSeat), "Seat is not bookable on the given date");
		objSeatMap.selectSeat();
		objSeatMap.ClickNextButton();	
	}
	
	@Test(priority=3)
	public void checkOut() throws InterruptedException {
		objCheckoutDetails = new CheckoutDetails(driver);
		String title = objCheckoutDetails.getTitle();
		System.out.println(title);
		Assert.assertTrue(true);
		objCheckoutDetails.enterGuestDetails(firstname, lastName, emailInput, phoneInput, promoCodeInput);
		objCheckoutDetails.enterCardDetails(cardNo, cardExpiryInput, cardCvvInput, cardHolderName);
		objCheckoutDetails.completeMyBooking();
	}
	
	@BeforeClass
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.london-theater-tickets.com");
	}

	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}

}
