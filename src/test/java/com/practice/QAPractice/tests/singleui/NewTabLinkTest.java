package com.practice.QAPractice.tests.singleui;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.NewTabPage;

public class NewTabLinkTest extends BaseTest {

	// 5.1 NEW TAB LINK

	@Test(priority = 12)
	public void test_5_1_NewTabLink() throws Exception {
		NewTabPage page = new NewTabPage(driver);
		page.navigateToTabLink();
		waitForPageReady();
		String mainTab = page.getMainTab();
		page.clickNewPageLink();
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
