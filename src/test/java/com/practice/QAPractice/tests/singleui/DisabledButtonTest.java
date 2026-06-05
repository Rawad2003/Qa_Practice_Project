package com.practice.QAPractice.tests.singleui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.ButtonPage;

public class DisabledButtonTest extends BaseTest {

	// 2.3 DISABLED BUTTON

	@DataProvider(name = "disabledButtonData")
	public Object[][] disabledButtonData() {
		return new Object[][] { { "enabled", true, "valid - select enabled state" }, };
	}

	@Test(dataProvider = "disabledButtonData", priority = 6)
	public void test_2_3_DisabledButton(String state, boolean expected, String description) throws Exception {
		ButtonPage page = new ButtonPage(driver);
		page.navigateToDisabledButton();
		waitForPageReady();
		page.selectState(state);
		waitForPageReady();
		String urlBefore = driver.getCurrentUrl();
		page.submitDisabledButton();
		waitForPageReady();
		assertResult(expected, urlBefore);
	}
}
