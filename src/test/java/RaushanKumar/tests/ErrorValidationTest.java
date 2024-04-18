package RaushanKumar.tests;

import java.io.IOException;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import RaushanKumar.TestComponents.BaseTest;
import RaushanKumar.pageobjects.CartPage;
import RaushanKumar.pageobjects.ProductCatalogue;
import RaushanKumar.TestComponents.Retry;


public class ErrorValidationTest extends BaseTest {
	
       @Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
       public void LoginErrorValidation() throws IOException, InterruptedException {
    	  
		landingpage.loginApplication("ranabhai@gmail.com","Ranahai@1");
		Assert.assertEquals("Incorrect email or password.",landingpage.getErrorMessage());
		
	}
       @Test
       public void ProductErrorValidation() throws IOException, InterruptedException {
		String productName="ZARA COAT 3";
		ProductCatalogue productCatalogue=landingpage.loginApplication("rkrana@gmail.com","Ranaabhai@1");
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		boolean match=cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
       }
}
