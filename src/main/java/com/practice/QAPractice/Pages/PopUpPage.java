package com.practice.QAPractice.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.practice.QAPractice.BasePage.BasePage;

public class PopUpPage extends BasePage {

	private static final String URL_MODAL = "https://www.qa-practice.com/elements/popup/modal";
	private static final String IFRAME_POPUP = "https://www.qa-practice.com/elements/popup/iframe_popup";

	// Shared Elements:
	private final By launchButton = By.cssSelector("button[data-bs-target='#exampleModal']");
	private final By modal = By.id("exampleModal");
	private final By modalShown = By.cssSelector(".modal.show");
	private final By modalTitle = By.id("exampleModalLabel");
	private final By closeButton = By.cssSelector(".modal-footer .btn-secondary");
	private final By sendButton = By.cssSelector(".modal-footer .btn-primary");
	// Modal Elements:
	private final By checkBox = By.id("id_checkbox_0");
	private final By resultSection = By.xpath("//p[contains(text(),'Selected checkboxes')]");
	// Iframe Pop-Up Elements:
	private final By textCopy = By.id("text-to-copy");
	private final By pasteTextInput = By.xpath("//input[contains(@class,'textinput')]");
	private final By popupIframe = By.cssSelector("#exampleModal iframe");
	private final By correctResult = By.xpath("//*[contains(@role,'alert') and contains(text(),'Correct')]");
	private final By nopeResult = By.xpath("//*[contains(@role,'alert') and contains(text(),'Nope')]");

	public PopUpPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToModal() {
		driver.get(URL_MODAL);
	}

	public void navigateToIframePopup() {
		driver.get(IFRAME_POPUP);
	}

	// Launch Pop-Up Button Methods:
	public boolean isLaunchButtonVisible() {
		return driver.findElement(launchButton).isDisplayed();
	}

	public boolean isLaunchButtonEnabled() {
		return driver.findElement(launchButton).isEnabled();
	}

	public void clickLaunchButton() {
		driver.findElement(launchButton).click();
	}

	// Modal Methods:
	public boolean isModalVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(modalShown));
			return driver.findElement(modal).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
