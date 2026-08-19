package SnehaBabaAutomation.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import SnehaBabaAutomation.TestComponents.BaseTest;
import SnehaBabaAutomation.pageobjects.cartPage;
import SnehaBabaAutomation.pageobjects.checkoutPage;
import SnehaBabaAutomation.pageobjects.confirmationPage;
import SnehaBabaAutomation.pageobjects.landingPage;
import SnehaBabaAutomation.pageobjects.orderPage;
import SnehaBabaAutomation.pageobjects.productCatalogue;
import junit.framework.Assert;

public class submitOrderTest extends BaseTest{

	String productname = "ADIDAS ORIGINAL";
	String countryName = "India";
	String email = "chetnasharma@gmail.com";
	String pwd = "Chetna@123";
	
	@Test(dataProvider="getData",groups="purchase")
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
		productCatalogue PC = LP.loginApplication(input.get("email"),input.get("pwd"));
		
		List<WebElement> products = PC.getProductlist();
		PC.addProductToCart(input.get("productname"));
		cartPage CP = PC.goToCartPage();
		
		Boolean Match = CP.verifyProductDisplay(input.get("productname"));
		Assert.assertTrue(Match);
		checkoutPage COP = CP.goToCheckout();
		
		COP.selectCountry(countryName);
		confirmationPage CFP = COP.submitOrder();
		
		String confirmMessage = CFP.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		

	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest()
	{
		productCatalogue PC = LP.loginApplication(email,pwd);
		orderPage OP = PC.goToOrdersPage();
		Assert.assertTrue(OP.verifyOrderDisplay(productname));
		
	}
	
	public String getScreenshot(String testCaseName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "chetnasharma@gmail.com");
//		map.put("pwd", "Chetna@123");
//		map.put("productname", "ADIDAS ORIGINAL");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "gunjasharma@gmail.com");
//		map1.put("pwd", "Gunja@123");
//		map1.put("productname", "ZARA COAT 3");
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//SnehaBabaAutomation//Data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)} ,{data.get(1)}};
		
		//return new Object[][] {{"chetnasharma@gmail.com","Chetna@123","ADIDAS ORIGINAL"} ,{"gunjasharma@gmail.com","Gunja@123","ZARA COAT 3"}};
		
	}

}
