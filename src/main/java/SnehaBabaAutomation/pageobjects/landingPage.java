package SnehaBabaAutomation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SnehaBabaAutomation.abstractComponents.abstractComponent;

public class landingPage extends abstractComponent{
	
	WebDriver driver;
	
	public landingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public productCatalogue loginApplication(String email,String pwd)
	{
		userEmail.sendKeys(email);
		password.sendKeys(pwd);
		submit.click();
		productCatalogue PC = new productCatalogue(driver);
		return PC;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	

}
