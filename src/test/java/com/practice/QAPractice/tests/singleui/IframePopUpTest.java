package com.practice.QAPractice.tests.singleui;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.PopUpPage;

public class IframePopUpTest extends BaseTest {

	// 10.2 Iframe pop-up — IMPORTANT CASE: Check -> submit with EMPTY text

	@Test(priority = 49)
	public void test_10_2_PopUp_IframeSubmitEmptyText() throws Exception {
		PopUpPage page = new PopUpPage(driver);
		page.navigateToIframePopup();
		waitForPageReady();
		page.clickLaunchButton();
		Assert.assertTrue(page.isModalVisible(), "Iframe pop-up modal should open");
		// "Check" submits the empty form and reloads the page WITH the input form
		page.clickSendButton();
		waitForPageReady();
		Assert.assertTrue(page.isPasteInputVisible(), "Input form should appear after Check");
		page.enterPasteText("");
		page.submitPasteForm();
		waitForPageReady();
		Assert.assertFalse(page.isCorrectResultShown(), "Empty text must NOT be accepted as 'Correct!'");
		boolean rejected = page.isNopeResultShown() || page.isPasteInputVisible();
		Assert.assertTrue(rejected, "Empty submit should be rejected (required field) or show 'Nope...'");
	}

	// 10.2 Iframe pop-up — happy path: copy the text, paste it, expect "Correct!"

	@Test(priority = 50)
	public void test_10_2_PopUp_IframeSubmitCorrectText() throws Exception {
		PopUpPage page = new PopUpPage(driver);
		page.navigateToIframePopup();
		waitForPageReady();
		page.clickLaunchButton();
		Assert.assertTrue(page.isModalVisible(), "Iframe pop-up modal should open");
		// Read the text to copy from INSIDE the iframe (while the modal is open)
		page.switchToPopupIframe();
		String textToCopy = page.getTextToCopy();
		page.switchToMainPage();
		// "Check" submits the empty form and reloads the page WITH the input form
		page.clickSendButton();
		waitForPageReady();
		Assert.assertTrue(page.isPasteInputVisible(), "Input form should appear after Check");
		// Paste the copied text into the form and submit
		page.enterPasteText(textToCopy);
		page.submitPasteForm();
		waitForPageReady();
		Assert.assertTrue(page.isCorrectResultShown(),
				"Pasting the correct copied text should show the green 'Correct!' result");
	}
}
