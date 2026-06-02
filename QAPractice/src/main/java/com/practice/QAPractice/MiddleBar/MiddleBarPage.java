package com.practice.QAPractice.MiddleBar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practice.QAPractice.BasePage.BasePage;

public class MiddleBarPage extends BasePage {
	private static final String BASE_URL = "https://www.qa-practice.com/";

	private final By linkTextInput = By.xpath("//a[@href='/elements/input/simple']");
	private final By linkSimpleButton = By.xpath("//a[@href='/elements/button/simple']");
	private final By linkSingleCheckBox = By.xpath("//a[@href='/elements/checkbox/single_checkbox']");
	private final By linkTextArea = By.xpath("//a[@href='/elements/textarea/single']");
	private final By linkSelectInput = By.xpath("//a[@href='/elements/select/single_select']");

	public MiddleBarPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToBase() {
		driver.get(BASE_URL);
	}

	public boolean isLinkVisible(By locator) {
		try {
			return driver.findElement(locator).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLinkEnabled(By locator) {
		try {
			return driver.findElement(locator).isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	public String clickAndGetUrl(By locator) {
		driver.findElement(locator).click();
		return driver.getCurrentUrl();
	}

	public By getLinkTextInput() {
		return linkTextInput;
	}

	public By getLinkSimpleButton() {
		return linkSimpleButton;
	}

	public By getLinkSingleCheckBox() {
		return linkSingleCheckBox;
	}

	public By getLinkTextArea() {
		return linkTextArea;
	}

	public By getLinkSelectInput() {
		return linkSelectInput;
	}

}
