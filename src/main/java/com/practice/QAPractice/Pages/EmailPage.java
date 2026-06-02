package com.practice.QAPractice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.practice.QAPractice.BasePage.BasePage;

public class EmailPage extends BasePage {
	private static final String URL = "https://www.qa-practice.com/elements/input/email";
	private final By inputField = By.xpath("//input[@type='text']");

	public EmailPage(WebDriver driver) {
		super(driver);
	}

	public void navigateTo() {
		driver.get(URL);
	}

	public void enterEmail(String input) {
		driver.findElement(inputField).clear();
		driver.findElement(inputField).sendKeys(input);
	}

	public void submit() {
		Actions enterAction = new Actions(driver);
		enterAction.sendKeys(Keys.ENTER).perform();
	}
}
