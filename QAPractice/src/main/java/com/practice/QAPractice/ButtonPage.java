package com.practice.QAPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ButtonPage extends BasePage {
	private static final String URL_SIMPLE = "https://www.qa-practice.com/elements/button/simple";
	private static final String URL_LOOKSLIKE = "https://www.qa-practice.com/elements/button/like_a_button";
	private static final String URL_DISABLED = "https://www.qa-practice.com/elements/button/disabled";
	private final By simpleButton = By.xpath("//input[contains(@value,'lick')]");
	private final By aButton = By.xpath("//a[@class='a-button']");
	private final By stateDropdown = By.xpath("//select[@name='select_state']");
	private final By submitButton = By.xpath("//input[@name='submit']");

	public ButtonPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToSimpleButton() {
		driver.get(URL_SIMPLE);
	}

	public void clickSimpleButton() {
		driver.findElement(simpleButton).click();
	}

	public void navigateToLooksLikeButton() {
		driver.get(URL_LOOKSLIKE);
	}

	public void clickLooksLikeButton() {
		driver.findElement(aButton).click();
	}

	public void navigateToDisabledButton() {
		driver.get(URL_DISABLED);
	}

	public void selectState(String state) {
		WebElement dropEnDis = driver.findElement(stateDropdown);
		Select sel = new Select(dropEnDis);
		sel.selectByValue(state);
	}

	public void submitDisabledButton() {
		driver.findElement(submitButton).click();
	}
}
