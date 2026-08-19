package SnehaBabaAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SnehaBabaAutomation.abstractComponents.abstractComponent;

public class orderPage extends abstractComponent{
	
WebDriver driver;
	
	public orderPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
@FindBy(css="tr td:nth-child(3)")
List<WebElement> productNames;

@FindBy(css=".totalRow button")
WebElement checkoutEle;

public Boolean verifyOrderDisplay(String productname)
{
	Boolean Match = productNames.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));
	return Match;
}

public checkoutPage goToCheckout()
{
	checkoutEle.click();
	checkoutPage COP = new checkoutPage(driver);
	return COP;
}

}
