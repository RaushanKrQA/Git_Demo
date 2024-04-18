package RaushanKumar.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import RaushanKumar.AbstractComponents.AbstractComponent;

public class ConfermationPage extends AbstractComponent {
	
	WebDriver driver;
	
	@FindBy(css=".hero-primary")
	WebElement confermationMessage;
	
	public ConfermationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	public  String getConfermationMessage() {
		
	return confermationMessage.getText();

	}
}
