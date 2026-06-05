package com.practice.QAPractice.tests.singleui;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.NewTabPage;

public class NewTabButtonTest extends BaseTest {

	// 5.2 NEW TAB BUTTON

	@Test(priority = 13)
	public void test_5_2_NewTabButton() throws Exception {
		NewTabPage page = new NewTabPage(driver);
		page.navigateToTabButton();
		waitForPageReady();
		String mainTab = page.getMainTab();
		page.clickNewTabButton();
		waitWindowCount(2);
		page.switchToNewTab(mainTab);
		waitForPageReady();
		Assert.assertFalse(driver.getTitle().contains("404"), "New tab page returned 404");
		page.closeCurrentTab();
		waitWindowCount(1);
		page.switchBackToMain(mainTab);
		Assert.assertTrue(driver.getWindowHandles().size() == 1, "Expected to be back on main tab only");
	}
}
