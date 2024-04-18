package RaushanKumar.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import RaushanKumar.AbstractComponents.AbstractComponent;
import RaushanKumar.TestComponents.BaseTest;
import RaushanKumar.pageobjects.CartPage;
import RaushanKumar.pageobjects.CheckoutPage;
import RaushanKumar.pageobjects.ConfermationPage;
import RaushanKumar.pageobjects.LandingPage;
import RaushanKumar.pageobjects.OrderPage;
import RaushanKumar.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	
	String productName="ZARA COAT 3";
	
       @Test(dataProvider="getData",groups= {"Purchase"})
       public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		ProductCatalogue productCatalogue=landingpage.loginApplication(input.get("email"),input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage=productCatalogue.goToCartPage();
		boolean match=cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutpage=cartPage.goToCheckout();
		checkoutpage.selectCountry("India");
		ConfermationPage confermationpage=checkoutpage.submitOrder();
		String confirmMessage =confermationpage.getConfermationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
	}
       
       @Test(dependsOnMethods= {"submitOrder"})
       public void OrderHistoryTest() {
    	   ProductCatalogue productCatalogue=landingpage.loginApplication("rana@gmail.com","Ranabhai@1");
    	   OrderPage orderPage = productCatalogue.goToOrderPage();
    	   Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
       }
       
       @DataProvider
       public Object[][] getData() throws IOException {
    	
    	   List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\RaushanKumar\\data\\PurchaseOrder.json");
    	   return new Object[][] {{data.get(0)},{data.get(1)}};
       }
}


/*@DataProvider
   public Object[][] getData() {
	return new Object[][]  {{"rkrana@gmail.com","Ranaabhai@1","ADIDAS ORIGINAL"},{"rana@gmail.com","Ranabhai@1","ZARA COAT 3"}};
  }
  
 */

/*  HashMap<String,String> map=new HashMap<String,String>();
    map.put("email","rana@gmail.com");
    map.put("password","Ranabhai@1");
    map.put("productName","ZARA COAT 3");
    HashMap<String,String> map1=new HashMap<String,String>();
    map1.put("email","rkrana@gmail.com");
    map1.put("password","Ranaabhai@1");
    map1.put("productName","ADIDAS ORIGINAL");
 */
