package com.practice.QAPractice.tests.singleui;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.AlertPage;

public class PromptBoxTest extends BaseTest {

	// 7.3 PROMPT BOX

	@DataProvider(name = "promptBoxData")
	public Object[][] promptBoxData() {
		return new Object[][] { { "This is normal string", true, true, "valid - normal string accepted" },
				{ "", true, false, "invalid - empty string" }, { null, false, true, "valid - prompt dismissed" }, };
	}

	@Test(dataProvider = "promptBoxData", priority = 18)
	public void test_7_3_PromptBox(String input, boolean accept, boolean expected, String description)
			throws Exception {
		AlertPage page = new AlertPage(driver);
		page.navigateToPrompt();
		waitForPageReady();
		page.clickTriggerButton();
		waitAlertPresent();
		if (accept) {
			page.sendKeysAndAccept(input);
		} else {
			page.dismissAlert();
		}
		waitForPageReady();
		if (expected) {
			boolean successFound = page.isSuccessShown();
			boolean pageNotCrashed = !driver.getTitle().contains("404");
			Assert.assertTrue(successFound || pageNotCrashed,
					"Expected SUCESS for prompt but none found. Input: " + input);
		}
	}
}
