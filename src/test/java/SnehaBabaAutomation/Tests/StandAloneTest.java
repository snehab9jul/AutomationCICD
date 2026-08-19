package SnehaBabaAutomation.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import junit.framework.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//add comments//
		String productname = "ADIDAS ORIGINAL";
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("chetnasharma@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Chetna@123");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname))
		.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		Boolean Match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productname));
		Assert.assertTrue(Match);
		driver.findElement(By.cssSelector(".totalRow button")).click();
		driver.findElement(By.cssSelector(".form-group input")).click();
		driver.findElement(By.cssSelector(".form-group input")).sendKeys("Ind");
		List<WebElement> dropdown = driver.findElements(By.cssSelector("span.ng-star-inserted"));
		WebElement selection = dropdown.stream().filter(s->s.getText().equalsIgnoreCase("India")).findFirst().orElse(null);
		selection.click();
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		System.out.println(driver.findElement(By.cssSelector("label.ng-star-inserted")).getText());
		
		

	}

}
