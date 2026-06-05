package com.practice.QAPractice.tests.singleui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.SelectPage;

public class MultipleSelectTest extends BaseTest {

	// 4.2 MULTI SELECT VALIDATION

	@DataProvider(name = "multiSelectValidationData")
	public Object[][] multiSelectValidationData() {
		return new Object[][] { { "", "", "", false, "invalid - all three empty" },
				{ "1", "", "", false, "invalid - only first filled" },
				{ "1", "1", "", false, "invalid - first and second filled third empty" },
				{ "", "1", "1", false, "invalid - first empty second and third filled" }, };
	}

	@Test(dataProvider = "multiSelectValidationData", priority = 10)
	public void test_4_2_MultiSelectValidation(String place, String transport, String when, boolean expected,
			String description) throws Exception {
		SelectPage page = new SelectPage(driver);
		page.navigateToMulti();
		waitForPageReady();
		if (!place.isEmpty()) {
			page.selectPlace(place);
			waitForPageReady();
		}
		if (!transport.isEmpty()) {
			page.selectTransport(transport);
			waitForPageReady();
		}
		if (!when.isEmpty()) {
			page.selectWhen(when);
			waitForPageReady();
		}
		String urlBefore = driver.getCurrentUrl();
		page.submitMulti();
		waitForPageReady();
		assertResult(expected, urlBefore);
	}

	// 4.2 MULTI SELECT ALL COMBINATIONS

	@DataProvider(name = "multiSelectAllCombinationsData")
	public Object[][] multiSelectAllCombinationsData() {
		// 5 x 4 x 3 = 60 combinations — all valid
		Object[][] data = new Object[60][5];
		int index = 0;
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 4; j++) {
				for (int k = 1; k <= 3; k++) {
					data[index][0] = String.valueOf(i);
					data[index][1] = String.valueOf(j);
					data[index][2] = String.valueOf(k);
					data[index][3] = true;
					data[index][4] = "place=" + i + " transport=" + j + " when=" + k;
					index++;
				}
			}
		}
		return data;
	}

	@Test(dataProvider = "multiSelectAllCombinationsData", priority = 11)
	public void test_4_2_MultiSelectAllCombinations(String place, String transport, String when, boolean expected,
			String description) throws Exception {
		SelectPage page = new SelectPage(driver);
		page.navigateToMulti();
		waitForPageReady();
		page.selectPlace(place);
		waitForPageReady();
		page.selectTransport(transport);
		waitForPageReady();
		page.selectWhen(when);
		waitForPageReady();
		String urlBefore = driver.getCurrentUrl();
		page.submitMulti();
		waitForPageReady();
		assertResult(expected, urlBefore);
	}
}
