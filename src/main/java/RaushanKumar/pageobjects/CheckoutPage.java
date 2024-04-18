package RaushanKumar.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import RaushanKumar.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	
	@FindBy(css="[placeholder='Select Country']")
    private WebElement country;
	
	@FindBy(css=".action__submit")
   private WebElement submit;
	
	@FindBy(xpath="(//button[contains(@class,'ta-item')])[2]")
	private WebElement selectCountry;
	
	private By results=By.cssSelector(".ta-results");

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public void selectCountry(String countryName) {
		Actions a=new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	public ConfermationPage submitOrder() throws InterruptedException {
		int y = submit.getLocation().getY();
		JavascriptExecutor r=(JavascriptExecutor)driver;
		r.executeScript("window.scrollBy(0,"+y+")");
		Thread.sleep(1000);
		submit.click();
		return new ConfermationPage(driver);
		
	}
}
