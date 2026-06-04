package com.practice.QAPractice.MainTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseSetupManager;
import com.practice.QAPractice.SingleUIElementsPages.AlertPage;
import com.practice.QAPractice.SingleUIElementsPages.ButtonPage;
import com.practice.QAPractice.SingleUIElementsPages.CheckboxPage;
import com.practice.QAPractice.SingleUIElementsPages.DragAndDropPage;
import com.practice.QAPractice.SingleUIElementsPages.EmailPage;
import com.practice.QAPractice.SingleUIElementsPages.IframePage;
import com.practice.QAPractice.SingleUIElementsPages.NewTabPage;
import com.practice.QAPractice.SingleUIElementsPages.PasswordPage;
import com.practice.QAPractice.SingleUIElementsPages.SelectPage;
import com.practice.QAPractice.SingleUIElementsPages.TextAreaPage;
import com.practice.QAPractice.SingleUIElementsPages.TextInputPage;

public class QaPractice extends BaseSetupManager {

	private static final By SUCCESS_RESULT = By.xpath("//*[contains(@class,'result-text')]");
	private static final By ERROR_FEEDBACK = By.xpath("//*[@class='invalid-feedback']");
	private static final By ERROR_REQUIRED = By
			.xpath("//*[contains(@class,'alert-danger') or contains(@class,'error')]");

	private void assertResult(boolean expected, String urlBefore) {
		if (expected) {
			boolean successFound = driver.findElements(SUCCESS_RESULT).size() > 0;
			boolean urlChanged = !driver.getCurrentUrl().equals(urlBefore);
			boolean pageNotCrashed = !driver.getTitle().contains("404");
			Assert.assertTrue(successFound || urlChanged || pageNotCrashed,
					"Expected SUCCESS but none found. URL" + driver.getCurrentUrl());
		} else {
			boolean errorFound = driver.findElements(ERROR_FEEDBACK).size() > 0
					|| driver.findElements(ERROR_REQUIRED).size() > 0;
			boolean stayedOnPage = driver.getCurrentUrl().equals(urlBefore);
			Assert.assertTrue(errorFound || stayedOnPage,
					"Expected ERROR but none found. URL" + driver.getCurrentUrl());
		}
	}
	// 1.1 NORMAL TEXT

	@DataProvider(name = "normalTextData")
	public Object[][] normalTextData() {
		return new Object[][] { { "Hello_world", true, "valid - normal string" },
				{ "Test-123", true, "valid - numbers and hyphens" }, { "Ab", true, "valid - boundary min 2 chars" },
				{ "Valid_String-1234567890123", true, "valid - boundary max 25 chars" },
				{ "", false, "invalid - empty required" }, { "A", false, "invalid - below min 1 char" },
				{ "Invalid_String12345678901234", false, "invalid - above max 26 chars" },
				{ "Hello@World!", false, "invalid - special chars" }, { "Hello World", false, "invalid - spaces" },
				{ "مرحبا", false, "invalid - non english" }, };
	}

	@Test(dataProvider = "normalTextData", priority = 1)
	public void test_1_1_NormalText(String input, boolean expected, String description) throws Exception {
		TextInputPage page = new TextInputPage(driver);
		page.navigateTo();
		Thread.sleep(500);
		page.enterText(input);
		Thread.sleep(500);
		String urlBefore = driver.getCurrentUrl();
		page.submit();
		Thread.sleep(500);
		assertResult(expected, urlBefore);
	}

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
		Thread.sleep(500);
		page.enterEmail(input);
		Thread.sleep(500);
		String urlBefore = driver.getCurrentUrl();
		page.submit();
		Thread.sleep(500);
		assertResult(expected, urlBefore);
	}

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
		Thread.sleep(500);
		page.enterPassword(input);
		Thread.sleep(500);
		String urlBefore = driver.getCurrentUrl();
		page.submit();
		Thread.sleep(500);
		assertResult(expected, urlBefore);
	}

	// 2.1 SIMPLE BUTTON

	@Test(priority = 4)
	public void test_2_1_SimpleButton() throws Exception {
		ButtonPage page = new ButtonPage(driver);
		page.navigateToSimpleButton();
		Thread.sleep(500);
		page.clickSimpleButton();
		Thread.sleep(500);
		Assert.assertFalse(driver.getTitle().contains("404"), "Simple button page returned 404");
	}

	// 2.2 LOOKS LIKE A BUTTON

	@Test(priority = 5)
	public void test_2_2_LooksLikeButton() throws Exception {
		ButtonPage page = new ButtonPage(driver);
		page.navigateToLooksLikeButton();
		Thread.sleep(500);
		page.clickLooksLikeButton();
		Thread.sleep(500);
		Assert.assertFalse(driver.getTitle().contains("404"), "Like-a-button page returned 404");
	}

	// 2.3 DISABLED BUTTON

	@DataProvider(name = "disabledButtonData")
	public Object[][] disabledButtonData() {
		return new Object[][] { { "enabled", true, "valid - select enabled state" }, };
	}

	@Test(dataProvider = "disabledButtonData", priority = 6)
	public void test_2_3_DisabledButton(String state, boolean expected, String description) throws Exception {
		ButtonPage page = new ButtonPage(driver);
		page.navigateToDisabledButton();
		Thread.sleep(500);
		page.selectState(state);
		Thread.sleep(500);
		String urlBefore = driver.getCurrentUrl();
		page.submitDisabledButton();
		Thread.sleep(500);
		assertResult(expected, urlBefore);
	}

	// 3.1 SINGLE CHECKBOX

	@DataProvider(name = "singleCheckboxData")
	public Object[][] singleCheckboxData() {
		return new Object[][] { { false, false, "invalid - checkbox empty" },
				{ true, true, "valid - checkbox checked" }, };
	}

	@Test(dataProvider = "singleCheckboxData", priority = 7)
	public void test_3_1_SingleCheckbox(boolean check, boolean expected, String description) throws Exception {
		CheckboxPage page = new CheckboxPage(driver);
		page.navigateToSingle();
		Thread.sleep(500);
		if (check) {
			page.clickSingleCheckbox();
			Thread.sleep(500);
		}
		String urlBefore = driver.getCurrentUrl();
		page.submitSingle();
		Thread.sleep(500);
		assertResult(expected, urlBefore);
	}

	// 3.2 MULTI CHECKBOX

	@DataProvider(name = "multiCheckboxData")
	public Object[][] multiCheckboxData() {
		return new Object[][] { { false, false, false, false, "invalid - all empty" },
				{ true, false, false, true, "valid - first only" }, { false, true, false, true, "valid - second only" },
				{ false, false, true, true, "valid - third only" },
				{ true, true, false, true, "valid - first and second" },
				{ true, false, true, true, "valid - first and third" },
				{ false, true, true, true, "valid - second and third" },
				{ true, true, true, true, "valid - all checked" }, };
	}

// 
	@Test(dataProvider = "multiCheckboxData", priority = 8)
	public void test_3_2_MultiCheckbox(boolean one, boolean two, boolean three, boolean expected, String description)
			throws Exception {
		CheckboxPage page = new CheckboxPage(driver);
		page.navigateToMulti();
		Thread.sleep(500);
		if (one) {
			page.clickCheckOne();
			Thread.sleep(500);
		}
		if (two) {
			page.clickCheckTwo();
			Thread.sleep(500);
		}
		if (three) {
			page.clickCheckThree();
			Thread.sleep(500);
		}
		String urlBefore = driver.getCurrentUrl();
		page.submitMulti();
		Thread.sleep(500);
		assertResult(expected, urlBefore);
	}

	// 4.1 SINGLE SELECT

	@DataProvider(name = "singleSelectData")
	public Object[][] singleSelectData() {
		return new Object[][] { { "", false, "invalid - empty select" }, { "1", true, "valid - option 1" },
				{ "2", true, "valid - option 2" }, { "3", true, "valid - option 3" }, { "4", true, "valid - option 4" },
				{ "5", true, "valid - option 5" }, };
	}

	@Test(dataProvider = "singleSelectData", priority = 9)
	public void test_4_1_SingleSelect(String value, boolean expected, String description) throws Exception {
		SelectPage page = new SelectPage(driver);
		page.navigateToSingle();
		Thread.sleep(500);
		if (value.isEmpty()) {
			page.submitSingle();
			Thread.sleep(500);
		} else {
			page.selectLanguage(value);
			Thread.sleep(500);
			page.submitSingle();

		}
		String urlBefore = driver.getCurrentUrl();
		Thread.sleep(500);
		assertResult(expected, urlBefore);
	}

	// 4.2 MULTI SELECT VALIDATION

	@DataProvider(name = "multiSelectValidationData")
	public Object[][] multiSelectValidationData() {
		return new Object[][] { { "", "", "", false, "invalid - all three empty" },
				{ "1", "", "", false, "invalid - only first filled" },
				{ "1", "1", "", false, "invalid - first and second filled third empty" },
				{ "", "1", "1", false, "invalid - first empty second and third filled" }, };
	}

	@Test(dataProvider = "multiSelectValidationData", priority = 10)
	public void test_4_2_MultiSelectValidation(String place, String transport, String when, boolean expected,
			String description) throws Exception {
		SelectPage page = new SelectPage(driver);
		page.navigateToMulti();
		Thread.sleep(500);
		if (!place.isEmpty()) {
			page.selectPlace(place);
			Thread.sleep(500);
		}
		if (!transport.isEmpty()) {
			page.selectTransport(transport);
			Thread.sleep(500);
		}
		if (!when.isEmpty()) {
			page.selectWhen(when);
			Thread.sleep(500);
		}
		String urlBefore = driver.getCurrentUrl();
		page.submitMulti();
		Thread.sleep(500);
		assertResult(expected, urlBefore);
	}

	// 4.2 MULTI SELECT ALL COMBINATIONS

	@DataProvider(name = "multiSelectAllCombinationsData")
	public Object[][] multiSelectAllCombinationsData() {
		// 5 x 4 x 3 = 60 combinations — all valid
		Object[][] data = new Object[60][5];
		int index = 0;
		for (int i = 1; i <= 5; i++) {
			for (int j = 1; j <= 4; j++) {
				for (int k = 1; k <= 3; k++) {
					data[index][0] = String.valueOf(i);
					data[index][1] = String.valueOf(j);
					data[index][2] = String.valueOf(k);
					data[index][3] = true;
					data[index][4] = "place=" + i + " transport=" + j + " when=" + k;
					index++;
				}
			}
		}
		return data;
	}

	@Test(dataProvider = "multiSelectAllCombinationsData", priority = 11)
	public void test_4_2_MultiSelectAllCombinations(String place, String transport, String when, boolean expected,
			String description) throws Exception {
		SelectPage page = new SelectPage(driver);
		page.navigateToMulti();
		Thread.sleep(500);
		page.selectPlace(place);
		Thread.sleep(500);
		page.selectTransport(transport);
		Thread.sleep(500);
		page.selectWhen(when);
		Thread.sleep(500);
		String urlBefore = driver.getCurrentUrl();
		page.submitMulti();
		Thread.sleep(500);
		assertResult(expected, urlBefore);
	}

	// 5.1 NEW TAB LINK

	@Test(priority = 12)
	public void test_5_1_NewTabLink() throws Exception {
		NewTabPage page = new NewTabPage(driver);
		page.navigateToTabLink();
		Thread.sleep(500);
		String mainTab = page.getMainTab();
		page.clickNewPageLink();
		Thread.sleep(500);
		page.switchToNewTab(mainTab);
		Thread.sleep(500);
		Assert.assertFalse(driver.getTitle().contains("404"), "New tab page returned 404");
		page.closeCurrentTab();
		Thread.sleep(500);
		page.switchBackToMain(mainTab);
		Thread.sleep(500);
		Assert.assertTrue(driver.getWindowHandles().size() == 1, "Expected to be back on main tab only");
	}

	// 5.2 NEW TAB BUTTON

	@Test(priority = 13)
	public void test_5_2_NewTabButton() throws Exception {
		NewTabPage page = new NewTabPage(driver);
		page.navigateToTabButton();
		Thread.sleep(500);
		String mainTab = page.getMainTab();
		page.clickNewTabButton();
		Thread.sleep(500);
		page.switchToNewTab(mainTab);
		Thread.sleep(500);
		Assert.assertFalse(driver.getTitle().contains("404"), "New tab page returned 404");
		page.closeCurrentTab();
		Thread.sleep(500);
		page.switchBackToMain(mainTab);
		Thread.sleep(500);
		Assert.assertTrue(driver.getWindowHandles().size() == 1, "Expected to be back on main tab only");
	}

	// 6.1 SINGLE TEXTAREA

	@DataProvider(name = "singleTextAreaData")
	public Object[][] singleTextAreaData() {
		return new Object[][] { {
				"Hello World! This is a simple test text. It contains numbers like 123 and 456. Special characters: @#$%^&*()_+. Email format: test@gmail.com. Date format: 13/05/2026.",
				true, "valid - text with special chars numbers and email" },
				{ "", false, "invalid - empty required field" }, };
	}

	@Test(dataProvider = "singleTextAreaData", priority = 14)
	public void test_6_1_SingleTextArea(String input, boolean expected, String description) throws Exception {
		TextAreaPage page = new TextAreaPage(driver);
		page.navigateToSingle();
		Thread.sleep(500);
		page.enterSingleText(input);
		Thread.sleep(500);
		String urlBefore = driver.getCurrentUrl();
		page.submitSingle();
		Thread.sleep(500);
		assertResult(expected, urlBefore);
	}

	// 6.2 MULTIPLE TEXTAREAS

	@DataProvider(name = "multipleTextAreaData")
	public Object[][] multipleTextAreaData() {
		return new Object[][] { {
				"The quick brown fox jumps over the lazy dog. Testing numbers: 789 and 321. Special chars: !@#$%. Website: www.test123.com. Time: 10:30 AM.",
				"Automation testing is important for software quality. Phone number: +1-800-555-0199. Address: 123 Main Street, New York, NY 10001. ZIP code: 90210.",
				"Lorem ipsum dolor sit amet. Order ID: ORD-2026-78954. Amount: $1,500.99. Discount: 25%. Expiry date: 31/12/2026. Reference: REF#ABC123XYZ.",
				true, "valid - all three filled" },
				{ "", "Automation testing is important for software quality.", "Lorem ipsum dolor sit amet.", false,
						"invalid - first chapter empty required" },
				{ "", "", "", false, "invalid - all three empty" }, { "The quick brown fox jumps over the lazy dog.",
						"", "", true, "valid - only first filled second and third empty" }, };
	}

	@Test(dataProvider = "multipleTextAreaData", priority = 15)
	public void test_6_2_MultipleTextAreas(String first, String second, String third, boolean expected,
			String description) throws Exception {
		TextAreaPage page = new TextAreaPage(driver);
		page.navigateToMultiple();
		Thread.sleep(500);
		page.enterFirstChapter(first);
		Thread.sleep(500);
		page.enterSecondChapter(second);
		Thread.sleep(500);
		page.enterThirdChapter(third);
		Thread.sleep(500);
		String urlBefore = driver.getCurrentUrl();
		page.submitMultiple();
		Thread.sleep(500);
		assertResult(expected, urlBefore);
	}

	// 7.1 NORMAL ALERT

	@Test(priority = 16)
	public void test_7_1_NormalAlert() throws Exception {
		AlertPage page = new AlertPage(driver);
		page.navigateToAlert();
		Thread.sleep(500);
		page.clickTriggerButton();
		Thread.sleep(500);
		page.acceptAlert();
		Thread.sleep(500);
		Assert.assertFalse(driver.getTitle().contains("404"), "Alert page returned 404 after accept");
	}

	// 7.2 CONFIRM BOX

	@DataProvider(name = "confirmBoxData")
	public Object[][] confirmBoxData() {
		return new Object[][] { { true, true, "valid - accept confirm box" },
				{ false, true, "valid - dismiss confirm box" }, };
	}

	@Test(dataProvider = "confirmBoxData", priority = 17)
	public void test_7_2_ConfirmBox(boolean accept, boolean expected, String description) throws Exception {
		AlertPage page = new AlertPage(driver);
		page.navigateToConfirm();
		Thread.sleep(500);
		page.clickTriggerButton();
		Thread.sleep(500);
		if (accept) {
			page.acceptAlert();
		} else {
			page.dismissAlert();
		}
		Thread.sleep(500);
		Assert.assertFalse(driver.getTitle().contains("404"), "Confirm box page returned 404");
	}

	// 7.3 PROMPT BOX

	@DataProvider(name = "promptBoxData")
	public Object[][] promptBoxData() {
		return new Object[][] { { "This is normal string", true, true, "valid - normal string accepted" },
				{ "", true, false, "invalid - empty string" }, { null, false, true, "valid - prompt dismissed" }, };
	}

	@Test(dataProvider = "promptBoxData", priority = 18)
	public void test_7_3_PromptBox(String input, boolean accept, boolean expected, String description)
			throws Exception {
		AlertPage page = new AlertPage(driver);
		page.navigateToPrompt();
		Thread.sleep(500);
		page.clickTriggerButton();
		Thread.sleep(500);
		if (accept) {
			page.sendKeysAndAccept(input);
		} else {
			page.dismissAlert();
		}
		Thread.sleep(500);
		if (expected) {
			boolean successFound = page.isSuccessShown();
			boolean pageNotCrashed = !driver.getTitle().contains("404");
			Assert.assertTrue(successFound || pageNotCrashed,
					"Expected SUCESS for prompt but none found. Input: " + input);
		}
	}

	// 8.1 Boxes tab

	@Test(priority = 19)
	public void test_8_1_DragAndDrop_BoxesVisible() throws Exception {
		DragAndDropPage page = new DragAndDropPage(driver);
		page.navigateToBoxes();
		Thread.sleep(500);
		Assert.assertTrue(page.isBoxDragAbleVisible(), "Draggable box should be visible");
		Assert.assertTrue(page.isBoxDropAbleVisible(), "Droppable box should be visible");
		Assert.assertTrue(page.isBoxDropHereTextVisible(), "Initial text should be 'Drop here'");
	}

	@Test(priority = 20)
	public void test_8_1_DragAndDrop_BoxPerformDrag() throws Exception {
		DragAndDropPage page = new DragAndDropPage(driver);
		page.navigateToBoxes();
		Thread.sleep(500);
		page.boxDragAndDrop();
		Thread.sleep(500);
		Assert.assertTrue(page.isBoxDragTextDropped(), "Text should change to 'Dropped!' after box drag");
	}

	@Test(priority = 21)
	public void test_8_1_DragAndDrop_CannotdragTwice() throws Exception {
		DragAndDropPage page = new DragAndDropPage(driver);
		page.navigateToBoxes();
		Thread.sleep(500);
		page.boxDragAndDrop();
		Thread.sleep(500);
		Assert.assertTrue(page.isBoxDragTextDropped(), "First drag should show 'Dropped!'");
		page.boxDragAndDrop();
		Thread.sleep(500);
		Assert.assertTrue(page.isBoxDragTextDropped(), "Text should still be 'Dropped!' after second drag");
		Assert.assertFalse(driver.getTitle().contains("404"), "Page should not crash after second drag");
	}

	// 8.2 Images tab

	@Test(priority = 22)
	public void test_8_2_DragAndDrop_ImagePageLoads() throws Exception {
		DragAndDropPage page = new DragAndDropPage(driver);
		page.navigateToImages();
		Thread.sleep(500);
		Assert.assertFalse(driver.getTitle().contains("404"), "Images page should not return 404");
		Assert.assertFalse(driver.getTitle().isEmpty(), "Images page should load successfullt");
	}

	@Test(priority = 23)
	public void test_8_2_DragAndDrop_ImageVisible() throws Exception {
		DragAndDropPage page = new DragAndDropPage(driver);
		page.navigateToImages();
		Thread.sleep(500);
		Assert.assertTrue(page.isImageDragAbleVisible(), "Image Draggable square should be visible");
		Assert.assertTrue(page.isImageDropAbleVisible(), "Image Droppable square should be visible");
	}

	@Test(priority = 24)
	public void test_8_2_DragAndDrop_ImagePerformDrag() throws Exception {
		DragAndDropPage page = new DragAndDropPage(driver);
		page.navigateToImages();
		Thread.sleep(500);
		page.imageDragAndDrop();
		Thread.sleep(500);
		Assert.assertTrue(page.isImageDragTextDropped(), "Text should change to 'Dropped!' after image drag");
	}

	// 9.1 Basic iframe checks

	@Test(priority = 25)
	public void test_9_1_Iframe_IsPresent() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		Assert.assertTrue(page.isIframePresent(), "Iframe should be present on page");
	}

	@Test(priority = 26)
	public void test_9_1_Iframe_IsDisplayed() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		Assert.assertTrue(page.isIframeDisplayed(), "Iframe should be displayed");
	}

	@Test(priority = 27)
	public void test_9_1_Iframe_HasSrc() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		String src = page.getIframeSrc();
		Assert.assertNotNull(src, "Iframe src should be not null");
		Assert.assertFalse(src.trim().isEmpty(), "Iframe src should be notEmpty");
	}

	@Test(priority = 28)
	public void test_9_1_Iframe_Count() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		Assert.assertEquals(page.getIframeCount(), 1, "Exactly 1 iframe should exist on page");
	}

	@Test(priority = 29)
	public void test_9_1_Iframe_SwitchInAndOut() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		try {
			page.switchToIframe();
			Thread.sleep(500);
			page.switchToMainContent();
			Thread.sleep(500);
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
		Thread.sleep(500);
		page.switchToIframe();
		Thread.sleep(500);
		page.openMenu();
		Thread.sleep(500);
		Assert.assertTrue(page.isMenuOpen(), "Menu should open after clicking the toggler");
		Assert.assertFalse(page.getAboutText().trim().isEmpty(), "About paragraph should not be empty");
	}

	@Test(priority = 31)
	public void test_9_2_Iframe_TwitterLink() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		page.switchToIframe();
		Thread.sleep(500);
		page.openMenu();
		Thread.sleep(500);
		page.clickTwitter();
		Assert.assertTrue(page.waitForUrlContains("twitter"), "Twitter link should navigate to Twitter");
	}

	@Test(priority = 32)
	public void test_9_2_Iframe_FacebookLink() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		page.switchToIframe();
		Thread.sleep(500);
		page.openMenu();
		Thread.sleep(500);
		page.clickFacebook();
		Assert.assertTrue(page.waitForUrlContains("facebook"), "Facebook link should navigate to Twitter");
	}

	@Test(priority = 33)
	public void test_9_2_Iframe_EmailLink() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		page.switchToIframe();
		Thread.sleep(500);
		page.openMenu();
		Thread.sleep(500);
		page.clickEmail();
		Assert.assertTrue(page.waitForUrlContains("mail"), "Email link should navigate to Twitter");
	}

	@Test(priority = 34)
	public void test_9_2_Iframe_AlbumLink() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		page.switchToIframe();
		Thread.sleep(500);
		page.openMenu();
		Thread.sleep(500);
		page.clickAlbum();
		Assert.assertTrue(page.waitForUrlContains("album"), "Album link should navigate to Twitter");
	}

	@Test(priority = 35)
	public void test_9_2_Iframe_ViewButtons_AllCards() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		page.switchToIframe();
		Thread.sleep(500);
		int count = page.getViewCount();
		Assert.assertTrue(count > 0, "There should be at least one 'View' button in the album");
		boolean allNavigated = true;
		for (int i = 0; i < count; i++) {
			page.scrollToAndClickView(i);
			Thread.sleep(500);
			if (!page.waitForUrlContains("view"))
				allNavigated = false;
		}
		Assert.assertTrue(allNavigated, "Every 'View' button should perform a view action");
	}

	@Test(priority = 36)
	public void test_9_2_Iframe_EditButtons_AllCards() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		page.switchToIframe();
		Thread.sleep(500);
		int count = page.getEditCount();
		Assert.assertTrue(count > 0, "There should be at least one 'View' button in the album");
		boolean allNavigated = true;
		for (int i = 0; i < count; i++) {
			page.scrollToAndClickEdit(i);
			Thread.sleep(500);
			if (!page.waitForUrlContains("view"))
				allNavigated = false;
		}
		Assert.assertTrue(allNavigated, "Every 'Edit' button should perform a view action");
	}

	@Test(priority = 37)
	public void test_9_2_Iframe_MainCallToActionScrollsTop() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		page.switchToIframe();
		Thread.sleep(500);
		page.scrollToFrameFooter();
		Thread.sleep(500);
		Assert.assertFalse(page.isAtTop(), "Preconditions: page should be scrolled down before clicking");
		page.clickMainCallToAction();
		Thread.sleep(500);
		Assert.assertTrue(page.isAtTop(), "'Main call to action' should scroll the iframe to the top");
	}

	@Test(priority = 38)
	public void test_9_2_Iframe_SecondaryCallToActionScrollsTop() throws Exception {
		IframePage page = new IframePage(driver);
		page.navigateTo();
		Thread.sleep(500);
		page.switchToIframe();
		Thread.sleep(500);
		page.scrollToFrameFooter();
		Thread.sleep(500);
		Assert.assertFalse(page.isAtTop(), "Preconditions: page should be scrolled down before clicking");
		page.clickSecondaryAction();
		Thread.sleep(500);
		Assert.assertTrue(page.isAtTop(), "'Secondary action' should scroll the iframe to the top");
	}
}