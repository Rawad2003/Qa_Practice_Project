package com.practice.QAPractice.tests.singleui;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.IframePage;

public class IframeTest extends BaseTest {

	// 9.1 Basic iframe checks

	@Test(priority = 25)
	public void test_9_1_Iframe_IsPresent() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		Assert.assertTrue(page.isIframePresent(), "Iframe should be present on page");
	}

	@Test(priority = 26)
	public void test_9_1_Iframe_IsDisplayed() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		Assert.assertTrue(page.isIframeDisplayed(), "Iframe should be displayed");
	}

	@Test(priority = 27)
	public void test_9_1_Iframe_HasSrc() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		String src = page.getIframeSrc();
		Assert.assertNotNull(src, "Iframe src should be not null");
		Assert.assertFalse(src.trim().isEmpty(), "Iframe src should be notEmpty");
	}

	@Test(priority = 28)
	public void test_9_1_Iframe_Count() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		Assert.assertEquals(page.getIframeCount(), 1, "Exactly 1 iframe should exist on page");
	}

	@Test(priority = 29)
	public void test_9_1_Iframe_SwitchInAndOut() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		try {
			page.switchToIframe();
			waitForPageReady();
			page.switchToMainContent();
			waitForPageReady();
		} catch (Exception e) {
			Assert.fail("Switching in/out of iframe should not throw: " + e.getMessage());
		}
		Assert.assertFalse(driver.getTitle().contains("404"), "Driver should be back on main content");
	}

	// 9.2 Iframe functional test (inside the album)

	@Test(priority = 30)
	public void test_9_2_Iframe_MenuOpenWithAbout() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		page.switchToIframe();
		waitForPageReady();
		page.openMenu();
		Assert.assertTrue(page.isMenuOpen(), "Menu should open after clicking the toggler");
		Assert.assertFalse(page.getAboutText().trim().isEmpty(), "About paragraph should not be empty");
	}

	@Test(priority = 31)
	public void test_9_2_Iframe_TwitterLink() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		page.switchToIframe();
		waitForPageReady();
		page.openMenu();
		page.clickTwitter();
		Assert.assertTrue(page.waitForUrlContains("twitter"), "Twitter link should navigate to Twitter");
	}

	@Test(priority = 32)
	public void test_9_2_Iframe_FacebookLink() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		page.switchToIframe();
		waitForPageReady();
		page.openMenu();
		page.clickFacebook();
		Assert.assertTrue(page.waitForUrlContains("facebook"), "Facebook link should navigate to Facebook");
	}

	@Test(priority = 33)
	public void test_9_2_Iframe_EmailLink() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		page.switchToIframe();
		waitForPageReady();
		page.openMenu();
		page.clickEmail();
		Assert.assertTrue(page.waitForUrlContains("mail"), "Email link should navigate to an email page");
	}

	@Test(priority = 34)
	public void test_9_2_Iframe_AlbumLink() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		page.switchToIframe();
		waitForPageReady();
		page.openMenu();
		page.clickAlbum();
		Assert.assertTrue(page.waitForUrlContains("album"), "Album link should navigate to an album page");
	}

	@Test(priority = 35)
	public void test_9_2_Iframe_ViewButtons_AllCards() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		page.switchToIframe();
		waitForPageReady();
		int count = page.getViewCount();
		Assert.assertTrue(count > 0, "There should be at least one 'View' button in the album");
		boolean allNavigated = true;
		for (int i = 0; i < count; i++) {
			page.scrollToAndClickView(i);
			waitForPageReady();
			if (!page.waitForUrlContains("view"))
				allNavigated = false;
		}
		Assert.assertTrue(allNavigated, "Every 'View' button should perform a view action");
	}

	@Test(priority = 36)
	public void test_9_2_Iframe_EditButtons_AllCards() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		page.switchToIframe();
		waitForPageReady();
		int count = page.getEditCount();
		Assert.assertTrue(count > 0, "There should be at least one 'Edit' button in the album");
		boolean allNavigated = true;
		for (int i = 0; i < count; i++) {
			page.scrollToAndClickEdit(i);
			waitForPageReady();
			if (!page.waitForUrlContains("edit"))
				allNavigated = false;
		}
		Assert.assertTrue(allNavigated, "Every 'Edit' button should perform an edit action");
	}

	@Test(priority = 37)
	public void test_9_2_Iframe_MainCallToActionScrollsTop() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		page.switchToIframe();
		waitForPageReady();
		page.scrollToFrameFooter();
		webWait().until(d -> page.getScrollY() > 5);
		Assert.assertFalse(page.isAtTop(), "Preconditions: page should be scrolled down before clicking");
		page.clickMainCallToAction();
		Assert.assertTrue(page.waitUntilAtTop(), "'Main call to action' should scroll the iframe to the top");
	}

	@Test(priority = 38)
	public void test_9_2_Iframe_SecondaryCallToActionScrollsTop_MenuClosed() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		page.switchToIframe();
		waitForPageReady();
		page.scrollToFrameFooter();
		webWait().until(d -> page.getScrollY() > 5);
		Assert.assertFalse(page.isAtTop(), "Preconditions: page should be scrolled down before clicking");
		page.clickSecondaryAction();
		Assert.assertTrue(page.waitUntilAtTop(),
				"'Secondary action' should scroll the iframe to the top when the menu is CLOSED");
	}

	@Test(priority = 39)
	public void test_9_2_Iframe_SecondaryCallToActionScrollsTop_MenuOpened() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		page.switchToIframe();
		waitForPageReady();
		page.openMenu();
		page.scrollToFrameFooter();
		webWait().until(d -> page.getScrollY() > 5);
		Assert.assertFalse(page.isAtTop(), "Preconditions: page should be scrolled down before clicking");
		page.clickSecondaryAction();
		Assert.assertTrue(page.waitUntilAtTop(),
				"'Secondary action' should scroll the iframe to the top when the menu is OPENED");
	}

	@Test(priority = 40)
	public void test_9_2_Iframe_BackToTop() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		String mainTab = driver.getWindowHandle();
		page.switchToIframe();
		waitForPageReady();
		page.clickBackToTop();
		waitForPageReady();
		if (driver.getWindowHandles().size() > 1) {
			closeExtraTabsAndReturn(mainTab);
			page.switchToIframe();
		}
		Assert.assertTrue(page.isAtTop(), "'Back to top' should scroll the iframe to the top");
	}

	// 9.3 Footer link coverage (two footers: frame + main page footer)

	@Test(priority = 41)
	public void test_9_3_Iframe_FrameFooterLinks() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		page.switchToIframe();
		waitForPageReady();
		page.scrollToFrameFooter();
		webWait().until(d -> page.getScrollY() > 5);
		Assert.assertTrue(page.isLinkPresent("Back to top"), "Frame footer should have 'Back to top'");
		Assert.assertTrue(page.isLinkPresent("Visit the homepage"), "Frame footer should have 'Visit the homepage'");
		Assert.assertTrue(page.isLinkPresent("getting started guide"),
				"Frame footer should have 'getting started guide'");
		Assert.assertTrue(page.getLinkHref("Visit the homepage").endsWith("/"),
				"'Visit the homepage' should point to the site root");
		Assert.assertTrue(page.getLinkHref("getting started guide").endsWith("/"),
				"'getting started guide' should point to the getting started guide");
		String mainTab = driver.getWindowHandle();
		page.clickVisitHomepage();
		waitForPageReady();
		closeExtraTabsAndReturn(mainTab);
		Assert.assertFalse(driver.getTitle().contains("404"), "Should not crash after frame-footer navigation");
	}

	@Test(priority = 42)
	public void test_9_3_Iframe_MainFooterLinks() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		waitForPageReady();
		page.scrollToMainFooter();
		webWait().until(d -> page.getScrollY() > 5);
		Assert.assertTrue(page.isLinkPresent("Contact"), "Main footer should have 'Contact'");
		Assert.assertTrue(page.isLinkPresent("What's new"), "Main footer should have 'What's new'");
		Assert.assertTrue(page.isLinkPresent("www.qa-practice.com"), "Main footer should have the site link");
		Assert.assertTrue(page.getLinkHref("Contact").contains("/contact"),
				"'Contact' should point to the contact page");
		Assert.assertTrue(page.getLinkHref("What's new").contains("whats_new"),
				"'What's new' should point to the what's-new page");
		String mainTab = driver.getWindowHandle();
		page.clickMainContact();
		waitForPageReady();
		closeExtraTabsAndReturn(mainTab);
		page.navigateTo();
		waitForPageReady();
		Assert.assertTrue(driver.getCurrentUrl().contains("iframe_page"),
				"Should return to the main iframe page after visiting a footer link");
	}
}
