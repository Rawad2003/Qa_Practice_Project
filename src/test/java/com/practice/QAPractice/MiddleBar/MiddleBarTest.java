package com.practice.QAPractice.MiddleBar;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;

public class MiddleBarTest extends BaseTest {
	@DataProvider(name = "middleBarVisibilityData")
	public Object[][] middleBarVisibilityData() {
		return new Object[][] { { "Text input", true }, { "Simple button", true }, { "Single checkbox", true },
				{ "Text area", true }, { "Select input", true } };
	}

	@DataProvider(name = "middleBarNavigationData")
	public Object[][] middleBarNavigationData() {
		return new Object[][] { { "Text input", "/elements/input/simple", true },
				{ "Simple button", "/elements/button/simple", true },
				{ "Single checkbox", "/elements/checkbox/single_checkbox", true },
				{ "Text area", "/elements/textarea/single", true },
				{ "Select input", "/elements/select/single_select", true } };
	}

	@DataProvider(name = "middleBarOrderData")
	public Object[][] middleBarOrderData() {
		return new Object[][] { { 1, "Text input", true }, { 2, "Simple button", true }, { 3, "Single checkbox", true },
				{ 4, "Text area", true }, { 5, "Select input", true }, };
	}

	@Test(dataProvider = "middleBarVisibilityData", priority = 1)
	public void test_MiddleBar_LinkIsVisible(String linkName, boolean expected) throws Exception {
		MiddleBarPage page = new MiddleBarPage(driver);
		page.navigateToBase();
		waitForPageReady();
		By locator = getLocatorByName(page, linkName);
		boolean isVisible = page.isLinkVisible(locator);
		Assert.assertEquals(isVisible, expected, "Visibility failed for: " + linkName);
	}

	@Test(dataProvider = "middleBarVisibilityData", priority = 2)
	public void test_MiddleBar_LinkIsEnabled(String linkName, boolean expected) throws Exception {
		MiddleBarPage page = new MiddleBarPage(driver);
		page.navigateToBase();
		waitForPageReady();
		By locator = getLocatorByName(page, linkName);
		boolean isEnabled = page.isLinkEnabled(locator);
		Assert.assertEquals(isEnabled, expected, "Enabled failed for: " + linkName);
	}

	@Test(dataProvider = "middleBarNavigationData", priority = 3)
	public void test_MiddleBar_LinkNavigatesCorrectly(String linkName, String expectedUrlContains, boolean expected)
			throws Exception {
		MiddleBarPage page = new MiddleBarPage(driver);
		page.navigateToBase();
		waitForPageReady();
		By locator = getLocatorByName(page, linkName);
		String actualUrl = page.clickAndGetUrl(locator);
		waitForPageReady();
		boolean result = actualUrl.contains(expectedUrlContains);
		Assert.assertEquals(result, expected, "URL check failed for: " + linkName + "\nExpected tp contain: "
				+ expectedUrlContains + "\nActual URL: " + actualUrl);
	}

	@Test(dataProvider = "middleBarNavigationData", priority = 4)
	public void test_MiddleBar_LinkIsNotBroken(String linkName, String expectedUrlContains, boolean expected)
			throws Exception {
		MiddleBarPage page = new MiddleBarPage(driver);
		page.navigateToBase();
		waitForPageReady();
		By locator = getLocatorByName(page, linkName);
		page.clickAndGetUrl(locator);
		waitForPageReady();
		String pageTitle = driver.getTitle();
		Assert.assertFalse(pageTitle.contains("404"),
				"Broken link (404) found for: " + linkName + "\nPage title: " + pageTitle);
	}

	@Test(priority = 5)
	public void test_MiddleBar_CorrectNumberOfLinks() throws Exception {
		MiddleBarPage page = new MiddleBarPage(driver);
		page.navigateToBase();
		waitForPageReady();
		int count = 0;
		if (page.isLinkVisible(page.getLinkTextInput()))
			count++;
		if (page.isLinkVisible(page.getLinkSimpleButton()))
			count++;
		if (page.isLinkVisible(page.getLinkSingleCheckBox()))
			count++;
		if (page.isLinkVisible(page.getLinkTextArea()))
			count++;
		if (page.isLinkVisible(page.getLinkSelectInput()))
			count++;
		Assert.assertEquals(count, 5, "Expected 5 linkes in middlebar but found: " + count);
	}

	@Test(dataProvider = "middleBarOrderData", priority = 6)
	public void test_MiddleBar_CorrectOrderOfLinks(int position, String linkName, boolean expected) throws Exception {
		MiddleBarPage page = new MiddleBarPage(driver);
		page.navigateToBase();
		waitForPageReady();
		By locator = By.xpath("//ol");
		String actualText = driver.findElement(locator).getText().trim();
		boolean result = actualText.contains(linkName);
		Assert.assertEquals(result, expected,
				"Order check failed at position " + position + "\nExpected: " + linkName + "\nActual: " + actualText);
	}

	private By getLocatorByName(MiddleBarPage page, String linkName) {
		switch (linkName) {
		case "Text input":
			return page.getLinkTextInput();
		case "Simple button":
			return page.getLinkSimpleButton();
		case "Single checkbox":
			return page.getLinkSingleCheckBox();
		case "Text area":
			return page.getLinkTextArea();
		case "Select input":
			return page.getLinkSelectInput();
		default:
			throw new IllegalArgumentException("Unkown link: " + linkName);
		}
	}
}
