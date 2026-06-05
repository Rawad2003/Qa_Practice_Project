package com.practice.QAPractice.tests.singleui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.CheckboxPage;

public class SingleCheckboxTest extends BaseTest {

	// 3.1 SINGLE CHECKBOX

	@DataProvider(name = "singleCheckboxData")
	public Object[][] singleCheckboxData() {
		return new Object[][] { { false, false, "invalid - checkbox empty" },
				{ true, true, "valid - checkbox checked" }, };
	}

	@Test(dataProvider = "singleCheckboxData", priority = 7)
	public void test_3_1_SingleCheckbox(boolean check, boolean expected, String description) throws Exception {
		CheckboxPage page = new CheckboxPage(driver);
		page.navigateToSingle();
		waitForPageReady();
		if (check) {
			page.clickSingleCheckbox();
			waitForPageReady();
		}
		String urlBefore = driver.getCurrentUrl();
		page.submitSingle();
		waitForPageReady();
		assertResult(expected, urlBefore);
	}
}
