package com.practice.QAPractice.tests.singleui;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.practice.QAPractice.BasePage.BaseTest;
import com.practice.QAPractice.SingleUIElementsPages.DragAndDropPage;

public class DragAndDropImagesTest extends BaseTest {

	// 8.2 Images tab

	@Test(priority = 22)
	public void test_8_2_DragAndDrop_ImagePageLoads() throws Exception {
		DragAndDropPage page = new DragAndDropPage(driver);
		page.navigateToImages();
		waitForPageReady();
		Assert.assertFalse(driver.getTitle().contains("404"), "Images page should not return 404");
		Assert.assertFalse(driver.getTitle().isEmpty(), "Images page should load successfullt");
	}

	@Test(priority = 23)
	public void test_8_2_DragAndDrop_ImageVisible() throws Exception {
		DragAndDropPage page = new DragAndDropPage(driver);
		page.navigateToImages();
		waitForPageReady();
		Assert.assertTrue(page.isImageDragAbleVisible(), "Image Draggable square should be visible");
		Assert.assertTrue(page.isImageDropAbleVisible(), "Image Droppable square should be visible");
	}

	@Test(priority = 24)
	public void test_8_2_DragAndDrop_ImagePerformDrag() throws Exception {
		DragAndDropPage page = new DragAndDropPage(driver);
		page.navigateToImages();
		waitForPageReady();
		page.imageDragAndDrop();
		waitForPageReady();
		Assert.assertTrue(page.isImageDragTextDropped(), "Text should change to 'Dropped!' after image drag");
	}
}
