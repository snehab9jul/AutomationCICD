package SnehaBabaAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SnehaBabaAutomation.abstractComponents.abstractComponent;

public class cartPage extends abstractComponent{
	
WebDriver driver;
	
	public cartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
@FindBy(css=".cartSection h3")
List<WebElement> cartProducts;

@FindBy(css=".totalRow button")
WebElement checkoutEle;

public Boolean verifyProductDisplay(String productname)
{
	Boolean Match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));
	return Match;
}

public checkoutPage goToCheckout()
{
	checkoutEle.click();
	checkoutPage COP = new checkoutPage(driver);
	return COP;
}

}
