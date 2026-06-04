package com.practice.QAPractice.SingleUIElementsPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.practice.QAPractice.BasePage.BasePage;

public class TextAreaPage extends BasePage {
	private static final String URL_SINGLE = "https://www.qa-practice.com/elements/textarea/single";
	private static final String URL_MULTIPLE = "https://www.qa-practice.com/elements/textarea/textareas";

	private final By singleArea = By.xpath("//textarea[@name='text_area']");
	private final By firstChapter = By.xpath("//textarea[@name='first_chapter']");
	private final By secondChapter = By.xpath("//textarea[@name='second_chapter']");
	private final By thirdChapter = By.xpath("//textarea[@name='third_chapter']");
	private final By submitButton = By.xpath("//input[starts-with(@type,'sub')]");

	public TextAreaPage(WebDriver driver) {
		super(driver);
	}

	public void navigateToSingle() {
		driver.get(URL_SINGLE);
	}

	public void enterSingleText(String input) {
		driver.findElement(singleArea).clear();
		driver.findElement(singleArea).sendKeys(input);
	}

	public void submitSingle() {
		WebElement btn = driver.findElement(submitButton);
		Actions scrollAction = new Actions(driver);
		scrollAction.scrollToElement(btn).perform();
		try {
			Thread.sleep(300);
		} catch (Exception e) {
		}
		btn.click();

	}

	public void navigateToMultiple() {
		driver.get(URL_MULTIPLE);
	}

	public void enterFirstChapter(String input) {
		driver.findElement(firstChapter).clear();
		driver.findElement(firstChapter).sendKeys(input);
	}

	public void enterSecondChapter(String input) {
		driver.findElement(secondChapter).clear();
		driver.findElement(secondChapter).sendKeys(input);
	}

	public void enterThirdChapter(String input) {
		driver.findElement(thirdChapter).clear();
		driver.findElement(thirdChapter).sendKeys(input);
	}

	public void submitMultiple() {
		WebElement btn = driver.findElement(submitButton);
		Actions scrollAction = new Actions(driver);
		scrollAction.scrollToElement(btn).perform();
		try {
			Thread.sleep(300);
		} catch (Exception e) {
		}
		btn.click();
	}
}
