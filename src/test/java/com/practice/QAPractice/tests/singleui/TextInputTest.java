package com.practice.QAPractice.tests.singleui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.TextInputPage;

public class TextInputTest extends BaseTest {

	// 1.1 NORMAL TEXT

	@DataProvider(name = "normalTextData")
	public Object[][] normalTextData() {
		return new Object[][] { { "Hello_world", true, "valid - normal string" },
				{ "Test-123", true, "valid - numbers and hyphens" }, { "Ab", true, "valid - boundary min 2 chars" },
				{ "Valid_String-1234567890123", true, "valid - boundary max 25 chars" },
				{ "", false, "invalid - empty required" }, { "A", false, "invalid - below min 1 char" },
				{ "Invalid_String12345678901234", false, "invalid - above max 26 chars" },
				{ "Hello@World!", false, "invalid - special chars" }, { "Hello World", false, "invalid - spaces" },
				{ "مرحبا", false, "invalid - non english" }, };
	}

	@Test(dataProvider = "normalTextData", priority = 1)
	public void test_1_1_NormalText(String input, boolean expected, String description) throws Exception {
		TextInputPage page = new TextInputPage(driver);
		page.navigateTo();
		waitForPageReady();
		page.enterText(input);
		waitForPageReady();
		String urlBefore = driver.getCurrentUrl();
		page.submit();
		waitForPageReady();
		assertResult(expected, urlBefore);
	}
}
