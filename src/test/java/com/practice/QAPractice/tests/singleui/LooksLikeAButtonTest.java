package com.practice.QAPractice.tests.singleui;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.ButtonPage;

public class LooksLikeAButtonTest extends BaseTest {

	// 2.2 LOOKS LIKE A BUTTON

	@Test(priority = 5)
	public void test_2_2_LooksLikeButton() throws Exception {
		ButtonPage page = new ButtonPage(driver);
		page.navigateToLooksLikeButton();
		waitForPageReady();
		page.clickLooksLikeButton();
		waitForPageReady();
		Assert.assertFalse(driver.getTitle().contains("404"), "Like-a-button page returned 404");
	}
}
