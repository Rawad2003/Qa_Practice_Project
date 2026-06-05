package com.practice.QAPractice.tests.singleui;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.AlertPage;

public class AlertBoxTest extends BaseTest {

	// 7.1 NORMAL ALERT

	@Test(priority = 16)
	public void test_7_1_NormalAlert() throws Exception {
		AlertPage page = new AlertPage(driver);
		page.navigateToAlert();
		waitForPageReady();
		page.clickTriggerButton();
		waitAlertPresent();
		page.acceptAlert();
		waitForPageReady();
		Assert.assertFalse(driver.getTitle().contains("404"), "Alert page returned 404 after accept");
	}
}
