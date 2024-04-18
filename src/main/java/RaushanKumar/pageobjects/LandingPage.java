package RaushanKumar.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import RaushanKumar.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail=driver.findElement(By.id("userEmail"));
	//pageFactory
	@FindBy(id="userEmail")
	private WebElement userEmail;
	
	@FindBy(id="userPassword")
	private WebElement userPassword;
	
	@FindBy(id="login")
	private WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	private WebElement errorMessage;
	
	public ProductCatalogue loginApplication( String email,String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submit.click();
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);
		return productCatalogue;
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		 return errorMessage.getText();
		
	}

}
