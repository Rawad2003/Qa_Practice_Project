package com.practice.QAPractice.tests.singleui;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.DragAndDropPage;

public class DragAndDropBoxTest extends BaseTest {

	// 8.1 Boxes tab

	@Test(priority = 19)
	public void test_8_1_DragAndDrop_BoxesVisible() throws Exception {
		DragAndDropPage page = new DragAndDropPage(driver);
		page.navigateToBoxes();
		waitForPageReady();
		Assert.assertTrue(page.isBoxDragAbleVisible(), "Draggable box should be visible");
		Assert.assertTrue(page.isBoxDropAbleVisible(), "Droppable box should be visible");
		Assert.assertTrue(page.isBoxDropHereTextVisible(), "Initial text should be 'Drop here'");
	}

	@Test(priority = 20)
	public void test_8_1_DragAndDrop_BoxPerformDrag() throws Exception {
		DragAndDropPage page = new DragAndDropPage(driver);
		page.navigateToBoxes();
		waitForPageReady();
		page.boxDragAndDrop();
		waitForPageReady();
		Assert.assertTrue(page.isBoxDragTextDropped(), "Text should change to 'Dropped!' after box drag");
	}

	@Test(priority = 21)
	public void test_8_1_DragAndDrop_CannotdragTwice() throws Exception {
		DragAndDropPage page = new DragAndDropPage(driver);
		page.navigateToBoxes();
		waitForPageReady();
		page.boxDragAndDrop();
		waitForPageReady();
		Assert.assertTrue(page.isBoxDragTextDropped(), "First drag should show 'Dropped!'");
		page.boxDragAndDrop();
		waitForPageReady();
		Assert.assertTrue(page.isBoxDragTextDropped(), "Text should still be 'Dropped!' after second drag");
		Assert.assertFalse(driver.getTitle().contains("404"), "Page should not crash after second drag");
	}
}
