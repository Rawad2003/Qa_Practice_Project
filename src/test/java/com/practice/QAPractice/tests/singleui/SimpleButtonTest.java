package com.practice.QAPractice.tests.singleui;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.ButtonPage;

public class SimpleButtonTest extends BaseTest {

	// 2.1 SIMPLE BUTTON

	@Test(priority = 4)
	public void test_2_1_SimpleButton() throws Exception {
		ButtonPage page = new ButtonPage(driver);
		page.navigateToSimpleButton();
		waitForPageReady();
		page.clickSimpleButton();
		waitForPageReady();
		Assert.assertFalse(driver.getTitle().contains("404"), "Simple button page returned 404");
	}
}
