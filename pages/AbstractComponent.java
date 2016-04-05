package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	private static WebDriver		wd;
	private static WebDriverWait	w;

	public void setDrivers(WebDriver wd, WebDriverWait w) {
		AbstractComponent.wd = wd;
		AbstractComponent.w = w;
	}
	
	public boolean verifyPageText(By selector, String text) {
		try {
			WebElement h = w.until(ExpectedConditions.elementToBeClickable(selector));
			return h.getText().contains(text);
		} catch (TimeoutException e) {
			System.out.println("ELEMENT NOT FOUND " + text + " ---> " + selector);
			return false;
		}
	}

	public boolean verifyButton(By selector) {
		try {
			WebElement b = w.until(ExpectedConditions.elementToBeClickable(selector));
			if (b.isDisplayed() && b.isEnabled()) {
				return true;
			}
			else return false;
		} catch (TimeoutException e) {
			System.out.println("ELEMENT NOT FOUND ERROR: " + selector);
			return false;
		}
	}

	public void select(By selector, int i) {
		WebElement selectDropdown = wd.findElement(selector);
		Select select = new Select(selectDropdown);
		select.selectByIndex(i);
	}

}
