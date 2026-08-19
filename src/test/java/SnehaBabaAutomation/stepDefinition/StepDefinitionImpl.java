package SnehaBabaAutomation.stepDefinition;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;

import SnehaBabaAutomation.TestComponents.BaseTest;
import SnehaBabaAutomation.pageobjects.cartPage;
import SnehaBabaAutomation.pageobjects.checkoutPage;
import SnehaBabaAutomation.pageobjects.confirmationPage;
import SnehaBabaAutomation.pageobjects.landingPage;
import SnehaBabaAutomation.pageobjects.productCatalogue;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class StepDefinitionImpl extends BaseTest{
	
	public landingPage LP;
	public productCatalogue PC;
	public confirmationPage CFP;
	@Given("I landed on Ecommerce page")
	public void I_lanced_on_Ecommerce_page() throws IOException
	{
		LP = launchApplicaion();
	}


@Given("^Logged in with username (.+) and password (.+)$")
public void logged_in_username_and_password(String username, String password)
{
	PC = LP.loginApplication(username,password);
}

@When("^I add the product (.+) to Cart$")
public void add_product_to_cart(String productname) throws InterruptedException
{
	List<WebElement> products = PC.getProductlist();
	PC.addProductToCart(productname);
}

@When("^Checkout (.+) and submit the order$")
public void checkout_submit_the_order(String productname)
{
	cartPage CP = PC.goToCartPage();
	
	Boolean Match = CP.verifyProductDisplay(productname);
	Assert.assertTrue(Match);
	checkoutPage COP = CP.goToCheckout();
	
	COP.selectCountry("india");
	CFP = COP.submitOrder();
}

@Then("{string} message is displayed on Confirmation page")
public void message_displayed_confirmation_page(String string)
{
	String confirmMessage = CFP.getConfirmationMessage();
	Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	driver.close();
}

}