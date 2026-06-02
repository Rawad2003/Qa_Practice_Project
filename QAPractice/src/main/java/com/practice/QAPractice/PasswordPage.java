package com.practice.QAPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class PasswordPage extends BasePage {

	private static final String URL = "https://www.qa-practice.com/elements/input/passwd";
	private final By inputField = By.xpath("//input[@type='password']");

	public PasswordPage(WebDriver driver) {
		super(driver);
	}

	public void navigateTo() {
		driver.get(URL);
	}

	public void enterPassword(String input) {
		driver.findElement(inputField).clear();
		driver.findElement(inputField).sendKeys(input);
	}

	public void submit() {
		Actions enterAction = new Actions(driver);
		enterAction.sendKeys(Keys.ENTER).perform();
	}

}
