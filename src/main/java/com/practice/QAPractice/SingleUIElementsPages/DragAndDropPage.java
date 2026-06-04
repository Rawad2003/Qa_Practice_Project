package com.practice.QAPractice.SingleUIElementsPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.practice.QAPractice.BasePage.BasePage;

public class DragAndDropPage extends BasePage {
	private static final String URL_BOXES = "https://www.qa-practice.com/elements/dragndrop/boxes";
	private static final String URL_IMAGES = "https://www.qa-practice.com/elements/dragndrop/images";

	private final By boxDropAbleBox = By.cssSelector("#rect-droppable");
	private final By boxDragAbleBox = By.cssSelector("#rect-draggable");
	private final By boxDropAbleText = By.cssSelector("#text-droppable");
	private final By imageDropAbleText = By.xpath("//p[@class='text-droppable' and text()='Dropped!']");
	private final By imageDropAbleBox = By.cssSelector("#rect-droppable2");
	private final By imageDragAbleBox = By.cssSelector("#rect-droppable1");

	public DragAndDropPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToBoxes() {
		driver.get(URL_BOXES);
	}

	public void navigateToImages() {
		driver.get(URL_IMAGES);
	}

	public boolean isBoxDragAbleVisible() {
		return driver.findElement(boxDragAbleBox).isDisplayed();
	}

	public boolean isBoxDropAbleVisible() {
		return driver.findElement(boxDropAbleBox).isDisplayed();
	}

	public boolean isImageDragAbleVisible() {
		return driver.findElement(imageDragAbleBox).isDisplayed();
	}

	public boolean isImageDropAbleVisible() {
		return driver.findElement(imageDropAbleBox).isDisplayed();
	}

	public void boxDragAndDrop() {
		WebElement source = driver.findElement(boxDragAbleBox);
		WebElement target = driver.findElement(boxDropAbleBox);
		new Actions(driver).clickAndHold(source).moveToElement(target).moveByOffset(1, 1).release().perform();
	}

	public void imageDragAndDrop() {
		WebElement source = driver.findElement(imageDragAbleBox);
		WebElement target = driver.findElement(imageDropAbleBox);
		new Actions(driver).clickAndHold(source).moveToElement(target).moveByOffset(1, 1).release().perform();
	}

	public String getBoxDropAbleText() {
		return driver.findElement(boxDropAbleText).getText();
	}

	public String getImageDropAbleText() {
		return driver.findElement(imageDropAbleText).getText();
	}

	public boolean isBoxDragTextDropped() {
		return getBoxDropAbleText().contains("Dropped!");
	}

	public boolean isBoxDropHereTextVisible() {
		return getBoxDropAbleText().contains("Drop here");
	}

	public boolean isImageDragTextDropped() {
		return getImageDropAbleText().contains("Dropped!");
	}

}
