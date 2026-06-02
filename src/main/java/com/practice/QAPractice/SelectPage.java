package com.practice.QAPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SelectPage extends BasePage {
	private static final String URL_SINGLE = "https://www.qa-practice.com/elements/select/single_select";
	private static final String URL_MULTIPLE = "https://www.qa-practice.com/elements/select/mult_select";
	private final By languageDropdown = By.xpath("//select[@name='choose_language']");
	private final By placeDropdown = By.xpath("//select[@name='choose_the_place_you_want_to_go']");
	private final By transportDropdown = By.xpath("//select[@name='choose_how_you_want_to_get_there']");
	private final By whenDropdown = By.xpath("//select[@name='choose_when_you_want_to_go']");
	private final By submitButton = By.xpath("//input[@name='submit']");

	public SelectPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToSingle() {
		driver.get(URL_SINGLE);
	}

	public void selectLanguage(String value) {
		new Select(driver.findElement(languageDropdown)).selectByValue(value);
	}

	public void submitSingle() {
		driver.findElement(submitButton).click();
	}

	public void navigateToMulti() {
		driver.get(URL_MULTIPLE);
	}

	public void selectPlace(String value) {
		new Select(driver.findElement(placeDropdown)).selectByValue(value);
	}

	public void selectTransport(String value) {
		new Select(driver.findElement(transportDropdown)).selectByValue(value);
	}

	public void selectWhen(String value) {
		new Select(driver.findElement(whenDropdown)).selectByValue(value);
	}

	public void submitMulti() {
		driver.findElement(submitButton).click();
	}
}
