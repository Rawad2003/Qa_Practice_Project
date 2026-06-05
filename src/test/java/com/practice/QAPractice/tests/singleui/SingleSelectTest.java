package com.practice.QAPractice.tests.singleui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.SelectPage;

public class SingleSelectTest extends BaseTest {

	// 4.1 SINGLE SELECT

	@DataProvider(name = "singleSelectData")
	public Object[][] singleSelectData() {
		return new Object[][] { { "", false, "invalid - empty select" }, { "1", true, "valid - option 1" },
				{ "2", true, "valid - option 2" }, { "3", true, "valid - option 3" }, { "4", true, "valid - option 4" },
				{ "5", true, "valid - option 5" }, };
	}

	@Test(dataProvider = "singleSelectData", priority = 9)
	public void test_4_1_SingleSelect(String value, boolean expected, String description) throws Exception {
		SelectPage page = new SelectPage(driver);
		page.navigateToSingle();
		waitForPageReady();
		if (value.isEmpty()) {
			page.submitSingle();
			waitForPageReady();
		} else {
			page.selectLanguage(value);
			waitForPageReady();
			page.submitSingle();

		}
		String urlBefore = driver.getCurrentUrl();
		waitForPageReady();
		assertResult(expected, urlBefore);
	}
}
