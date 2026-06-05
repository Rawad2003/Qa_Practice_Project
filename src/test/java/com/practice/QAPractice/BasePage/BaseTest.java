package com.practice.QAPractice.BasePage;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Shared base for every test class. Extends {@link BaseSetupManager} so the
 * static {@code driver}, {@code @BeforeSuite} and {@code @AfterSuite} keep
 * working unchanged.
 *
 * Holds the helpers that used to live inside MainTest.QaPractice
 * ({@link #assertResult} and {@link #closeExtraTabsAndReturn}) plus a set of
 * reusable explicit-wait helpers that replace the old Thread.sleep pacing.
 */
public class BaseTest extends BaseSetupManager {

	private static final By SUCCESS_RESULT = By.xpath("//*[contains(@class,'result-text')]");
	private static final By ERROR_FEEDBACK = By.xpath("//*[@class='invalid-feedback']");
	private static final By ERROR_REQUIRED = By
			.xpath("//*[contains(@class,'alert-danger') or contains(@class,'error')]");

	/** Single place to configure the default explicit-wait timeout. */
	protected static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(10);

	protected WebDriverWait webWait() {
		return new WebDriverWait(driver, DEFAULT_TIMEOUT);
	}

	// ── Shared result helper (verbatim from the original QaPractice) ──────────
	protected void assertResult(boolean expected, String urlBefore) {
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

	// ── Shared tab helper (verbatim from the original QaPractice) ─────────────
	protected void closeExtraTabsAndReturn(String mainTab) {
		for (String handle : driver.getWindowHandles()) {
			if (!handle.equals(mainTab)) {
				driver.switchTo().window(handle);
				driver.close();
			}
		}
		driver.switchTo().window(mainTab);
	}

	// ── Reusable explicit-wait helpers (replace Thread.sleep) ─────────────────

	/** Waits until the document has finished loading (replaces post-navigate sleeps). */
	protected void waitForPageReady() {
		webWait().until(d -> "complete".equals(((JavascriptExecutor) d).executeScript("return document.readyState")));
	}

	protected WebElement waitVisible(By locator) {
		return webWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	protected WebElement waitClickable(By locator) {
		return webWait().until(ExpectedConditions.elementToBeClickable(locator));
	}

	protected WebElement waitPresent(By locator) {
		return webWait().until(ExpectedConditions.presenceOfElementLocated(locator));
	}

	protected void waitUrlChange(String urlBefore) {
		webWait().until(d -> !d.getCurrentUrl().equals(urlBefore));
	}

	protected void waitAlertPresent() {
		webWait().until(ExpectedConditions.alertIsPresent());
	}

	protected void waitWindowCount(int n) {
		webWait().until(ExpectedConditions.numberOfWindowsToBe(n));
	}
}
