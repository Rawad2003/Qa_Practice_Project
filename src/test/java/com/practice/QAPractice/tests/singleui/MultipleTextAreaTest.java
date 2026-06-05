package com.practice.QAPractice.tests.singleui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.TextAreaPage;

public class MultipleTextAreaTest extends BaseTest {

	// 6.2 MULTIPLE TEXTAREAS

	@DataProvider(name = "multipleTextAreaData")
	public Object[][] multipleTextAreaData() {
		return new Object[][] { {
				"The quick brown fox jumps over the lazy dog. Testing numbers: 789 and 321. Special chars: !@#$%. Website: www.test123.com. Time: 10:30 AM.",
				"Automation testing is important for software quality. Phone number: +1-800-555-0199. Address: 123 Main Street, New York, NY 10001. ZIP code: 90210.",
				"Lorem ipsum dolor sit amet. Order ID: ORD-2026-78954. Amount: $1,500.99. Discount: 25%. Expiry date: 31/12/2026. Reference: REF#ABC123XYZ.",
				true, "valid - all three filled" },
				{ "", "Automation testing is important for software quality.", "Lorem ipsum dolor sit amet.", false,
						"invalid - first chapter empty required" },
				{ "", "", "", false, "invalid - all three empty" }, { "The quick brown fox jumps over the lazy dog.",
						"", "", true, "valid - only first filled second and third empty" }, };
	}

	@Test(dataProvider = "multipleTextAreaData", priority = 15)
	public void test_6_2_MultipleTextAreas(String first, String second, String third, boolean expected,
			String description) throws Exception {
		TextAreaPage page = new TextAreaPage(driver);
		page.navigateToMultiple();
		waitForPageReady();
		page.enterFirstChapter(first);
		waitForPageReady();
		page.enterSecondChapter(second);
		waitForPageReady();
		page.enterThirdChapter(third);
		waitForPageReady();
		String urlBefore = driver.getCurrentUrl();
		page.submitMultiple();
		waitForPageReady();
		assertResult(expected, urlBefore);
	}
}
