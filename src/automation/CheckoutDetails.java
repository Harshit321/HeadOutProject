package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutDetails {

	WebDriver driver;
	
	By firstNameProp = By.cssSelector("input[id$='first']");
	By lastNameProp = By.cssSelector("input[id$='last']");
	By emailProp = By.cssSelector("input[name='Email']");
	By confirmProp = By.cssSelector("input[id$='confirm']");
	By phoneProp = By.cssSelector("input[name='Phone']");
	By promoCodeProp = By.className("promo-code-wrapper");
	By promoCodeLink = By.className("notranslate");
	By promoCodeValueInp = By.cssSelector("input[placeholder='Enter promo code']");
	By promoCodeApply = By.className("promo-code-submit-button");
	
	By cardNumberProp = By.id("card-number");
	By cardExpiryProp = By.id("card-expiry");
	By cardCvvProp = By.id("card-cvv");
	By cardHolderNameProp = By.id("card-name");
	
	By completeBookingDivProp = By.className("product-checkout-book-button-wrapper");
	By completeBookingBtnProp = By.className("book-now-wrapper");
	
	
	public CheckoutDetails(WebDriver driver){
        this.driver = driver;
    }
	
	public String getTitle() {
    	return driver.getTitle().toLowerCase().trim();
    }
	
	
	public void enterGuestDetails(String fname, String lname, String emailInput, String phoneInpt, String pcode) {
		WebElement firstName = driver.findElement(firstNameProp);
		WebElement lastName = driver.findElement(lastNameProp);
		WebElement email = driver.findElement(emailProp);
		WebElement confirmEmail = driver.findElement(confirmProp);
		WebElement phone = driver.findElement(phoneProp);
		boolean hasPromoCode = true;
		WebElement promoCode = driver.findElement(promoCodeProp);
		
		firstName.clear();
		firstName.sendKeys(fname);
		
		lastName.clear();
		lastName.sendKeys(lname);
		
		email.clear();
		email.sendKeys(emailInput);
		
		confirmEmail.clear();
		confirmEmail.sendKeys(emailInput);
		
		phone.clear();
		phone.sendKeys(phoneInpt);
		
		if(hasPromoCode) {
			promoCode.findElement(promoCodeLink).click();
			WebElement promoCodeValue = promoCode.findElement(promoCodeValueInp);
			promoCodeValue.clear();
			promoCodeValue.sendKeys(pcode);
			promoCode.findElement(promoCodeApply).click();			
		}
	}

	public void enterCardDetails(String cardNoInp, String cardExpInp, String cardCVVInp, String holderNameInp) {
		WebElement cardNumber = driver.findElement(cardNumberProp);
		WebElement cardExpiry = driver.findElement(cardExpiryProp);
		WebElement cardCvv = driver.findElement(cardCvvProp);
		WebElement cardHolderName = driver.findElement(cardHolderNameProp);
		
		cardNumber.sendKeys(cardNoInp);
		cardExpiry.sendKeys(cardExpInp);
		cardCvv.sendKeys(cardCVVInp);
		cardHolderName.sendKeys(holderNameInp);
	}
	
	public void completeMyBooking() throws InterruptedException {
		WebElement completeBookingDiv = driver.findElement(completeBookingDivProp);
		WebElement completeBookingBtn = completeBookingDiv.findElement(completeBookingBtnProp);
		completeBookingBtn.click();
		Thread.sleep(10000);
	}

}
