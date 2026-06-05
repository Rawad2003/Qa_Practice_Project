package com.practice.QAPractice.tests.singleui;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.EmailPage;

public class EmailTest extends BaseTest {

	// 1.2 EMAIL FIELD

	@DataProvider(name = "emailData")
	public Object[][] emailData() {
		return new Object[][] { { "test@gmail.com", true, "valid - standard format" },
				{ "test@localhost", true, "valid - localhost allowed" },
				{ "test@mail.company.com", true, "valid - subdomain" }, { "", false, "invalid - empty required" },
				{ "testgmail.com", false, "invalid - missing @" },
				{ "test@gmail", false, "invalid - missing extension" },
				{ "@gmail.com", false, "invalid - missing username" },
				{ "test@@gmail.com", false, "invalid - double @" }, { "test @gmail.com", false, "invalid - spaces" },
				{ "plaintext", false, "invalid - no format at all" }, };
	}

	@Test(dataProvider = "emailData", priority = 2)
	public void test_1_2_EmailField(String input, boolean expected, String description) throws Exception {
		EmailPage page = new EmailPage(driver);
		page.navigateTo();
		waitForPageReady();
		page.enterEmail(input);
		waitForPageReady();
		String urlBefore = driver.getCurrentUrl();
		page.submit();
		waitForPageReady();
		assertResult(expected, urlBefore);
	}
}
