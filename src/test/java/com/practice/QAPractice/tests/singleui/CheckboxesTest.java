package com.practice.QAPractice.tests.singleui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.CheckboxPage;

public class CheckboxesTest extends BaseTest {

	// 3.2 MULTI CHECKBOX

	@DataProvider(name = "multiCheckboxData")
	public Object[][] multiCheckboxData() {
		return new Object[][] { { false, false, false, false, "invalid - all empty" },
				{ true, false, false, true, "valid - first only" }, { false, true, false, true, "valid - second only" },
				{ false, false, true, true, "valid - third only" },
				{ true, true, false, true, "valid - first and second" },
				{ true, false, true, true, "valid - first and third" },
				{ false, true, true, true, "valid - second and third" },
				{ true, true, true, true, "valid - all checked" }, };
	}

	@Test(dataProvider = "multiCheckboxData", priority = 8)
	public void test_3_2_MultiCheckbox(boolean one, boolean two, boolean three, boolean expected, String description)
			throws Exception {
		CheckboxPage page = new CheckboxPage(driver);
		page.navigateToMulti();
		waitForPageReady();
		if (one) {
			page.clickCheckOne();
			waitForPageReady();
		}
		if (two) {
			page.clickCheckTwo();
			waitForPageReady();
		}
		if (three) {
			page.clickCheckThree();
			waitForPageReady();
		}
		String urlBefore = driver.getCurrentUrl();
		page.submitMulti();
		waitForPageReady();
		assertResult(expected, urlBefore);
	}
}
