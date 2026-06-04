package com.practice.QAPractice.Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

	// Modal Methods (CheckBox, Send Button, Close Button):
	public boolean isModalVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(modalShown));
			return driver.findElement(modal).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getModalTitle() {
		return driver.findElement(modalTitle).getText();
	}

	public boolean isCheckboxVisible() {
		return driver.findElement(checkBox).isDisplayed();
	}

	public boolean isCheckboxChecked() {
		return driver.findElement(checkBox).isSelected();
	}

	public void clickCheckbox() {
		driver.findElement(checkBox).click();
	}

	public void clickSendButton() {
		driver.findElement(sendButton).click();
	}

	public void clickCloseButton() {
		driver.findElement(closeButton).click();
	}

	public boolean isResultSectionVisible() {
		return driver.findElements(resultSection).size() > 0;
	}

	public boolean isModalGone() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			return wait.until(ExpectedConditions.invisibilityOfElementLocated(modalShown));
		} catch (Exception e) {
			return driver.findElements(modalShown).size() == 0;
		}
	}

	// iframe inside the modal
	// buttons are reused, same cssSelector as the modal tab
	// (Cancel, Check) here are same in the modal tab (Cancel, Send)
	public boolean isPopupIframePresent() {
		return driver.findElements(popupIframe).size() > 0;
	}

	public void switchToPopupIframe() {
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(popupIframe));
	}

	public void switchToMainPage() {
		driver.switchTo().defaultContent();
	}

	public String getPopupIframeTitle() {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.title;");
	}

	// text to copy Methods:
	public boolean isTextToCopyVisible() {
		return driver.findElements(textCopy).size() > 0 && driver.findElement(textCopy).isDisplayed();
	}

	public String getTextToCopy() {
		return driver.findElement(textCopy).getText();
	}

	// input form (appears after clicking Check b0utton)
	public boolean isPasteInputVisible() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(pasteTextInput)).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void enterPasteText(String text) {
		driver.findElement(pasteTextInput).clear();
		driver.findElement(pasteTextInput).sendKeys(text);
	}
}
