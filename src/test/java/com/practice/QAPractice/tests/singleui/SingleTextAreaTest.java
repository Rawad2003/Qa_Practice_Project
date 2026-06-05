package com.practice.QAPractice.tests.singleui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.TextAreaPage;

public class SingleTextAreaTest extends BaseTest {

	// 6.1 SINGLE TEXTAREA

	@DataProvider(name = "singleTextAreaData")
	public Object[][] singleTextAreaData() {
		return new Object[][] { {
				"Hello World! This is a simple test text. It contains numbers like 123 and 456. Special characters: @#$%^&*()_+. Email format: test@gmail.com. Date format: 13/05/2026.",
				true, "valid - text with special chars numbers and email" },
				{ "", false, "invalid - empty required field" }, };
	}

	@Test(dataProvider = "singleTextAreaData", priority = 14)
	public void test_6_1_SingleTextArea(String input, boolean expected, String description) throws Exception {
		TextAreaPage page = new TextAreaPage(driver);
		page.navigateToSingle();
		waitForPageReady();
		page.enterSingleText(input);
		waitForPageReady();
		String urlBefore = driver.getCurrentUrl();
		page.submitSingle();
		waitForPageReady();
		assertResult(expected, urlBefore);
	}
}
