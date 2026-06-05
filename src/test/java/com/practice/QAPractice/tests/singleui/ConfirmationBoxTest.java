package com.practice.QAPractice.tests.singleui;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.AlertPage;

public class ConfirmationBoxTest extends BaseTest {

	// 7.2 CONFIRM BOX

	@DataProvider(name = "confirmBoxData")
	public Object[][] confirmBoxData() {
		return new Object[][] { { true, true, "valid - accept confirm box" },
				{ false, true, "valid - dismiss confirm box" }, };
	}

	@Test(dataProvider = "confirmBoxData", priority = 17)
	public void test_7_2_ConfirmBox(boolean accept, boolean expected, String description) throws Exception {
		AlertPage page = new AlertPage(driver);
		page.navigateToConfirm();
		waitForPageReady();
		page.clickTriggerButton();
		waitAlertPresent();
		if (accept) {
			page.acceptAlert();
		} else {
			page.dismissAlert();
		}
		waitForPageReady();
		Assert.assertFalse(driver.getTitle().contains("404"), "Confirm box page returned 404");
	}
}
