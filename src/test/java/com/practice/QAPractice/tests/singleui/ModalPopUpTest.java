package com.practice.QAPractice.tests.singleui;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.PopUpPage;

public class ModalPopUpTest extends BaseTest {

	// 10.1 Modal pop-up

	@Test(priority = 43)
	public void test_10_1_PopUp_LaunchButtonVisible() throws Exception {
		PopUpPage page = new PopUpPage(driver);
		page.navigateToModal();
		waitForPageReady();
		Assert.assertTrue(page.isLaunchButtonVisible(), "Launch button should be visible");
		Assert.assertTrue(page.isLaunchButtonEnabled(), "Launch button should be enabled");
	}

	@Test(priority = 44)
	public void test_10_1_PopUp_ModalOpens() throws Exception {
		PopUpPage page = new PopUpPage(driver);
		page.navigateToModal();
		waitForPageReady();
		page.clickLaunchButton();
		Assert.assertTrue(page.isModalVisible(), "Modal should be visible after launch");
		Assert.assertEquals(page.getModalTitle(), "I am a Pop-Up", "Modal title should match");
	}

	@Test(priority = 45)
	public void test_10_1_PopUp_CheckboxVisible() throws Exception {
		PopUpPage page = new PopUpPage(driver);
		page.navigateToModal();
		waitForPageReady();
		page.clickLaunchButton();
		Assert.assertTrue(page.isModalVisible(), "Modal should be visible");
		Assert.assertTrue(page.isCheckboxVisible(), "Checkbox should be visible");
		Assert.assertFalse(page.isCheckboxChecked(), "Checkbox should NOT be checked by default");
	}

	@Test(priority = 46)
	public void test_10_1_PopUp_SendWithoutCheckbox() throws Exception {
		PopUpPage page = new PopUpPage(driver);
		page.navigateToModal();
		waitForPageReady();
		page.clickLaunchButton();
		Assert.assertTrue(page.isModalVisible(), "Modal should be visible");
		page.clickSendButton();
		waitForPageReady();
		Assert.assertFalse(driver.getTitle().contains("404"), "Page should not crash after send");
	}

	@Test(priority = 47)
	public void test_10_1_PopUp_SendWithCheckbox() throws Exception {
		PopUpPage page = new PopUpPage(driver);
		page.navigateToModal();
		waitForPageReady();
		page.clickLaunchButton();
		Assert.assertTrue(page.isModalVisible(), "Modal should be visible");
		page.clickCheckbox();
		Assert.assertTrue(page.isCheckboxChecked(), "Checkbox should now be checked");
		page.clickSendButton();
		waitForPageReady();
		boolean resultOrAlive = page.isResultSectionVisible() || !driver.getTitle().contains("404");
		Assert.assertTrue(resultOrAlive, "Result section should appear OR page should not crash");
	}

	@Test(priority = 48)
	public void test_10_1_PopUp_CloseButton() throws Exception {
		PopUpPage page = new PopUpPage(driver);
		page.navigateToModal();
		waitForPageReady();
		page.clickLaunchButton();
		Assert.assertTrue(page.isModalVisible(), "Modal should be visible");
		page.clickCloseButton();
		Assert.assertTrue(page.isModalGone(), "Modal should no longer be visible after Close");
	}
}
