package com.practice.QAPractice.SideBar;

import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

public class SidebarTest extends BaseTest {

	@DataProvider(name = "sidebarNavigationData")
	public Object[][] sidebarNavigationData() {
		return new Object[][] { { "Homepage", "qa-practice.com", true }, { "Inputs", "elements/input", true },
				{ "Buttons", "elements/button", true }, { "Checkbox", "elements/checkbox", true },
				{ "Select", "elements/select", true }, { "New tab", "elements/new_tab", true },
				{ "Text area", "elements/textarea", true }, { "Alerts", "elements/alert", true },
				{ "Drag and Drop", "elements/dragndrop", true }, { "Iframes", "elements/iframe/iframe_page", true },
				{ "Pop-Up", "elements/popup", true }

		};
	}

	@DataProvider(name = "sidebarVisibilityData")
	public Object[][] sidebarVisibilityData() {
		return new Object[][] { { "Homepage", true }, { "Single UI Elements", true }, { "Forms", true },
				{ "Inputs", true }, { "Buttons", true }, { "Checkbox", true }, { "Select", true }, { "New tab", true },
				{ "Text area", true }, { "Alerts", true }, { "Drag and Drop", true }, { "Iframes", true },
				{ "Pop-Up", true }, { "Practice Form", true } };
	}

	@Test(dataProvider = "sidebarVisibilityData", priority = 1)
	public void test_Sidebar_LinkIsVisible(String linkName, boolean expected) throws Exception {
		SidebarPage page = new SidebarPage(driver);
		page.navigateToBase();
		waitForPageReady();
		expandIfNeeded(page, linkName);
		By locator = getLocatorByName(page, linkName);
		boolean isVisible = page.isLinkVisible(locator);
		Assert.assertEquals(isVisible, expected, "Visibility check failed for: " + linkName);
	}

	@Test(dataProvider = "sidebarVisibilityData", priority = 2)
	public void test_Sidebar_LinkIsEnabled(String linkName, boolean expected) throws Exception {
		SidebarPage page = new SidebarPage(driver);
		page.navigateToBase();
		waitForPageReady();
		expandIfNeeded(page, linkName);
		By locator = getLocatorByName(page, linkName);
		boolean isEnabled = page.isLinkEnabled(locator);
		Assert.assertEquals(isEnabled, expected, "Enabled check failed for: " + linkName);
	}//

	@Test(dataProvider = "sidebarNavigationData", priority = 3)
	public void test_Sidebar_LinkNavigatesCorrectly(String linkName, String expectedUrlContains, boolean expected)
			throws Exception {
		SidebarPage page = new SidebarPage(driver);
		page.navigateToBase();
		waitForPageReady();
		expandIfNeeded(page, linkName);
		By locator = getLocatorByName(page, linkName);
		String actualUrl = page.clickAndGetUrl(locator);
		waitForPageReady();
		boolean result = actualUrl.contains(expectedUrlContains);
		Assert.assertEquals(result, expected, "URL check failed for: " + linkName + "\nExpected tp contain: "
				+ expectedUrlContains + "\nActual URL: " + actualUrl);
	}

	@Test(dataProvider = "sidebarNavigationData", priority = 4)
	public void test_Sidebar_LinkIsNotBroken(String linkName, String expectedUrlContains, boolean expected)
			throws Exception {
		SidebarPage page = new SidebarPage(driver);
		page.navigateToBase();
		waitForPageReady();
		expandIfNeeded(page, linkName);
		By locator = getLocatorByName(page, linkName);
		page.clickAndGetUrl(locator);
		waitForPageReady();
		String pageTitle = driver.getTitle();
		Assert.assertFalse(pageTitle.contains("404"),
				"Broken link (404) found for: " + linkName + "\nPage title: " + pageTitle);
	}

	private By getLocatorByName(SidebarPage page, String linkName) {
		switch (linkName) {
		case "Homepage":
			return page.getLinkHomepage();
		case "Single UI Elements":
			return page.getLinkSingleUIElements();
		case "Forms":
			return page.getLinkForms();
		case "Inputs":
			return page.getLinkInputs();
		case "Buttons":
			return page.getLinkButtons();
		case "Checkbox":
			return page.getLinkCheckbox();
		case "Select":
			return page.getLinkSelect();
		case "New tab":
			return page.getLinkNewTab();
		case "Text area":
			return page.getLinkTextArea();
		case "Alerts":
			return page.getLinkAlerts();
		case "Drag and Drop":
			return page.getLinkDragAndDrop();
		case "Iframes":
			return page.getLinkIframes();
		case "Pop-Up":
			return page.getLinkPopUp();
		case "Practice Form":
			return page.getLinkPracticeForm();
		default:
			throw new IllegalArgumentException("Unkown link: " + linkName);
		}

	}

	private static final Set<String> SINGLE_UI_LINKS = new HashSet<>(Arrays.asList("Inputs", "Buttons", "Checkbox",
			"Select", "New tab", "Text area", "Alerts", "Drag and Drop", "Iframes", "Pop-Up"));
	private static final Set<String> FORMS_LINKS = new HashSet<>(Arrays.asList("Practice Form"));

	private void expandIfNeeded(SidebarPage page, String linkName) throws Exception {
		if (SINGLE_UI_LINKS.contains(linkName)) {
			page.expandSingleUIElements();
		} else if (FORMS_LINKS.contains(linkName)) {
			page.expandForms();
		}
	}
}
