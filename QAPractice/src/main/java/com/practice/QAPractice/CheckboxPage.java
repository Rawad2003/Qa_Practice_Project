package com.practice.QAPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckboxPage extends BasePage {
	private static final String URL_SINGLE = "https://www.qa-practice.com/elements/checkbox/single_checkbox";
	private static final String URL_MULTIPLE = "https://www.qa-practice.com/elements/checkbox/mult_checkbox";
	private final By singleCheckbox = By.xpath("//input[@type='checkbox']");
	private final By submitSingle = By.xpath("//input[@type='submit']");
	private final By checkOne = By.xpath("//input[@value='one']");
	private final By checkTwo = By.xpath("//input[@value='two']");
	private final By checkThree = By.xpath("//input[@value='three']");
	private final By submitMulti = By.xpath("//input[@name='submit']");

	public CheckboxPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToSingle() {
		driver.get(URL_SINGLE);
	}

	public void clickSingleCheckbox() {
		driver.findElement(singleCheckbox).click();
	}

	public void submitSingle() {
		driver.findElement(submitSingle).click();
	}

	public void navigateToMulti() {
		driver.get(URL_MULTIPLE);
	}

	public void clickCheckOne() {
		driver.findElement(checkOne).click();
	}

	public void clickCheckTwo() {
		driver.findElement(checkTwo).click();
	}

	public void clickCheckThree() {
		driver.findElement(checkThree).click();
	}

	public void submitMulti() {
		driver.findElement(submitMulti).click();
	}
}
