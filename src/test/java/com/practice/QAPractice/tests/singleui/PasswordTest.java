package com.practice.QAPractice.tests.singleui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.PasswordPage;

public class PasswordTest extends BaseTest {

	// 1.3 PASSWORD FIELD

	@DataProvider(name = "passwordData")
	public Object[][] passwordData() {
		return new Object[][] { { "Admin123$", true, "valid - all conditions met" },
				{ "", false, "invalid - empty required" }, { "Ad1$", false, "invalid - less than 8 chars" },
				{ "admin123$", false, "invalid - no uppercase" }, { "ADMIN123$", false, "invalid - no lowercase" },
				{ "AdminAdmin$", false, "invalid - no digit" }, { "Admin123", false, "invalid - no special char" },
				{ "Admin12$", true, "valid - boundary exactly 8 chars" },
				{ "Admi1$x", false, "invalid - boundary exactly 7 chars" },
				{ "   ", false, "invalid - spaces only" }, };
	}

	@Test(dataProvider = "passwordData", priority = 3)
	public void test_1_3_PasswordField(String input, boolean expected, String description) throws Exception {
		PasswordPage page = new PasswordPage(driver);
		page.navigateTo();
		waitForPageReady();
		page.enterPassword(input);
		waitForPageReady();
		String urlBefore = driver.getCurrentUrl();
		page.submit();
		waitForPageReady();
		assertResult(expected, urlBefore);
	}
}
