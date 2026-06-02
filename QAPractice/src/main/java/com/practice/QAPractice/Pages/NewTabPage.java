package com.practice.QAPractice.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.practice.QAPractice.BasePage.BasePage;

public class NewTabPage extends BasePage {
	private static final String URL_LINK = "https://www.qa-practice.com/elements/new_tab/link";
	private static final String URL_BUTTON = "https://www.qa-practice.com/elements/new_tab/button";
	private final By newPageLink = By.xpath("//a[contains(@href,'/new_page')]");
	private final By clickButton = By.xpath("//a[text()='Click']");

	public NewTabPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToTabLink() {
		driver.get(URL_LINK);
	}

	public String getMainTab() {
		return driver.getWindowHandle();
	}

	public void clickNewPageLink() {
		driver.findElement(newPageLink).click();
	}

	public void switchToNewTab(String mainTab) {
		for (String tab : driver.getWindowHandles()) {
			if (!tab.equals(mainTab)) {
				driver.switchTo().window(tab);
				break;
			}
		}
	}

	public void closeCurrentTab() {
		driver.close();
	}

	public void switchBackToMain(String mainTab) {
		driver.switchTo().window(mainTab);
	}

	public void navigateToTabButton() {
		driver.get(URL_BUTTON);
	}

	public void clickNewTabButton() {
		driver.findElement(clickButton).click();
	}
}
