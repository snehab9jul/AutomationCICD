package SnehaBabaAutomation.Tests;

import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import SnehaBabaAutomation.TestComponents.BaseTest;
import SnehaBabaAutomation.TestComponents.Retry;
import SnehaBabaAutomation.pageobjects.cartPage;
import SnehaBabaAutomation.pageobjects.productCatalogue;
import junit.framework.Assert;

public class errorValidationsTest extends BaseTest{

	@Test(groups= {"errorHandling"},retryAnalyzer=Retry.class)
	public void LoginPageErrorValidation() throws IOException, InterruptedException
	{
		
		String email = "chetnasharma@gmail.com";
		String pwd = "Chetn@123";
		LP.loginApplication(email,pwd);
		Assert.assertEquals("Incorrect email or password.", LP.getErrorMessage());

	}
	
	
	@Test
	public void submitOrder() throws IOException, InterruptedException
	{
		
		String productname = "ADIDAS ORIGINAL";
		String countryName = "India";
		String email = "chetnasharma@gmail.com";
		String pwd = "Chetna@123";
		productCatalogue PC = LP.loginApplication(email,pwd);
		
		List<WebElement> products = PC.getProductlist();
		PC.addProductToCart(productname);
		cartPage CP = PC.goToCartPage();
		
		Boolean Match = CP.verifyProductDisplay("ADIDAS ORIGINAL1");
		Assert.assertFalse(Match);
		

	}

}
