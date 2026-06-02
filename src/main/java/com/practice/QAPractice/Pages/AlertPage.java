package com.practice.QAPractice.Pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practice.QAPractice.BasePage.BasePage;

public class AlertPage extends BasePage {
	private static final String URL_ALERT = "https://www.qa-practice.com/elements/alert/alert";
	private static final String URL_CONFIRM = "https://www.qa-practice.com/elements/alert/confirm";
	private static final String URL_PROMPT = "https://www.qa-practice.com/elements/alert/prompt";

	private final By triggerButton = By.xpath("//a[@class='a-button']");
	private final By successResult = By.xpath("//*[contains(@class,'result-text')]");
	private final By errorResult = By.xpath("//*[contains(@class,'invalid-feedback')]");

	public AlertPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToAlert() {
		driver.get(URL_ALERT);
	}

	public void navigateToConfirm() {
		driver.get(URL_CONFIRM);
	}

	public void navigateToPrompt() {
		driver.get(URL_PROMPT);
	}

	public void clickTriggerButton() {
		driver.findElement(triggerButton).click();
	}

	public void acceptAlert() {
		driver.switchTo().alert().accept();
	}

	public void dismissAlert() {
		driver.switchTo().alert().dismiss();
	}

	public void sendKeysToALert(String input) {
		driver.switchTo().alert().sendKeys(input);
	}

	public void sendKeysAndAccept(String input) {
		Alert alert = driver.switchTo().alert();
		if (input != null)
			alert.sendKeys(input);
		alert.accept();
	}

	public boolean isSuccessShown() {
		return driver.findElements(successResult).size() > 0;
	}

	public boolean isErrorShown() {
		return driver.findElements(errorResult).size() > 0;
	}
}
