package SnehaBabaAutomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SnehaBabaAutomation.abstractComponents.abstractComponent;


public class confirmationPage extends abstractComponent{
	
WebDriver driver;
	
	public confirmationPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmMessage;
	
	public String getConfirmationMessage() {
		return confirmMessage.getText();
	}

}
