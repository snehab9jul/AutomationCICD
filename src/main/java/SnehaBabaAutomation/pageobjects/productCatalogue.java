package SnehaBabaAutomation.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SnehaBabaAutomation.abstractComponents.abstractComponent;

public class productCatalogue extends abstractComponent{
	
WebDriver driver;
	
	public productCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
@FindBy(css=".mb-3")
List<WebElement> products;

@FindBy(css=".ng-animating")
WebElement loader;


By productBy = By.cssSelector(".mb-3");
By addToCart = By.cssSelector(".card-body button:last-of-type");
By toastMessage = By.cssSelector("#toast-container");

public List<WebElement> getProductlist()
{
	waitForElementToAppear(productBy);
	return products;
}

public WebElement getProductByName(String productname)
{
	WebElement prod = getProductlist().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname))
			.findFirst().orElse(null);
	return prod;
}

public void addProductToCart(String productname) throws InterruptedException
{
	WebElement prod = getProductByName(productname);
	prod.findElement(addToCart).click();
	waitForElementToAppear(toastMessage);
	waitForElementToDisappear(loader);
}

}
