package com.practice.QAPractice.SideBar;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practice.QAPractice.BasePage.BasePage;

public class SidebarPage extends BasePage {
	private static final String BASE_URL = "https://www.qa-practice.com/";

	private final By linkHomepage = By.xpath("//a[@href='/']");
	private final By linkSingleUIElements = By.xpath("//span[text()='Single UI Elements']");
	private final By linkForms = By.xpath("//span[text()='Forms']");

	private final By linkInputs = By.xpath("//a[@href='/elements/input']");
	private final By linkButtons = By.xpath("//a[@href='/elements/button']");
	private final By linkCheckbox = By.xpath("//a[@href='/elements/checkbox']");
	private final By linkSelect = By.xpath("//a[@href='/elements/select']");
	private final By linkNewTab = By.xpath("//a[@href='/elements/new_tab']");
	private final By linkTextArea = By.xpath("//a[@href='/elements/textarea']");
	private final By linkAlerts = By.xpath("//a[@href='/elements/alert']");
	private final By linkDragAndDrop = By.xpath("//a[@href='/elements/dragndrop']");
	private final By linkIframes = By.xpath("//a[text()='Iframes']");
	private final By linkPopUp = By.xpath("//a[text()='Pop-Up']");

	private final By linkPracticeForm = By.xpath("//a[@href='/forms/practice-form']");

	public SidebarPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToBase() {
		driver.get(BASE_URL);
	}

	public void expandSingleUIElements() throws Exception {
		if (!isLinkVisible(linkInputs)) {
			driver.findElement(linkSingleUIElements).click();
			Thread.sleep(500);
		}
	}

	public void expandForms() throws Exception {
		if (!isLinkVisible(linkPracticeForm)) {
			driver.findElement(linkForms).click();
			Thread.sleep(500);
		}
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

	public By getLinkHomepage() {
		return linkHomepage;
	}

	public By getLinkSingleUIElements() {
		return linkSingleUIElements;
	}

	public By getLinkForms() {
		return linkForms;
	}

	public By getLinkInputs() {
		return linkInputs;
	}

	public By getLinkButtons() {
		return linkButtons;
	}

	public By getLinkCheckbox() {
		return linkCheckbox;
	}

	public By getLinkSelect() {
		return linkSelect;
	}

	public By getLinkNewTab() {
		return linkNewTab;
	}

	public By getLinkTextArea() {
		return linkTextArea;
	}

	public By getLinkAlerts() {
		return linkAlerts;
	}

	public By getLinkDragAndDrop() {
		return linkDragAndDrop;
	}

	public By getLinkIframes() {
		return linkIframes;
	}

	public By getLinkPopUp() {
		return linkPopUp;
	}

	public By getLinkPracticeForm() {
		return linkPracticeForm;
	}

}
