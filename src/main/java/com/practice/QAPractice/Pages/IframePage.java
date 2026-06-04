package com.practice.QAPractice.Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.practice.QAPractice.BasePage.BasePage;

public class IframePage extends BasePage {
	private static final String IFRAMES_URL = "https://www.qa-practice.com/elements/iframe/iframe_page";

	private final By iframe = By.cssSelector("iframe[class='embed-responsive-item']");
	private final By anyIframe = By.tagName("iframe");
	// links of the navBarToggler:
	private final By navbarToggler = By.cssSelector(".navbar-toggler");
	private final By navbarMenu = By.id("navbarHeader");
	private final By aboutParagraph = By.cssSelector("#navbarHeader h4 + p, #navbarHeader p.text-muted");
	private final By twitterLink = By.linkText("Follow on Twitter");
	private final By facebookLink = By.linkText("Like on Facebook");
	private final By emailLink = By.linkText("Email me");
	private final By albumBrand = By.cssSelector("a.navbar-brand");
	// buttons in the main frame:
	private final By mainCallToActionButton = By.linkText("Main call to action");
	private final By secondaryActionButton = By.linkText("Secondary action");
	// buttons in the sub-frames:
	private final By viewButtons = By.xpath("//button[normalize-space()='View']");
	private final By editButtons = By.xpath("//button[normalize-space()='Edit']");

	private final By footer = By.cssSelector("footer");
	private final By footerLinks = By.cssSelector("footer a");
	// Frame footer links
	private final By backToTopLink = By.linkText("Back to top");
	private final By visitHomepageLink = By.linkText("Visit the homepage");
	private final By gettingStartedGuideLink = By.linkText("getting started guide");
	// Main footer links
	private final By mainContactLink = By.linkText("Contact");
	private final By mainWhatsNewLink = By.linkText("What's new");
	private final By mainSiteLink = By.linkText("www.qa-practice.com");

	// Basic iframe helpers
	public IframePage(WebDriver driver) {
		super(driver);
	}

	public void navigateTo() {
		driver.get(IFRAMES_URL);
	}

	public boolean isIframePresent() {
		return driver.findElements(iframe).size() > 0;
	}

	public int getIframeCount() {
		return driver.findElements(iframe).size();
	}

	public void switchToIframe() {
		driver.switchTo().frame(0);
	}

	public void switchToMainContent() {
		driver.switchTo().defaultContent();
	}

	public String getIframeSrc() {
		return driver.findElement(iframe).getAttribute("src");
	}

	public boolean isIframeDisplayed() {
		return driver.findElement(iframe).isDisplayed();
	}

	// Menu (Bootsrap navbar collapse)
	public void openMenu() {
		driver.findElement(navbarToggler).click();
		new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOfElementLocated(navbarMenu));
	}

	public boolean isMenuOpen() {
		try {
			return driver.findElement(navbarMenu).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public String getAboutText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement about = wait.until(ExpectedConditions.visibilityOfElementLocated(aboutParagraph));
		return about.getText();
	}

	// Menu Links
	public void clickTwitter() {
		driver.findElement(twitterLink).click();
	}

	public void clickFacebook() {
		driver.findElement(facebookLink).click();
	}

	public void clickEmail() {
		driver.findElement(emailLink).click();
	}

	public void clickAlbum() {
		driver.findElement(albumBrand).click();
	}

	// Header call-to-action links
	public void clickMainCallToAction() {
		driver.findElement(mainCallToActionButton).click();
	}

	public void clickSecondaryAction() {
		driver.findElement(secondaryActionButton).click();
	}

	// Album card buttons (All Cards)
	public int getViewCount() {
		return driver.findElements(viewButtons).size();
	}

	public int getEditCount() {
		return driver.findElements(editButtons).size();
	}

	public void scrollToAndClickView(int index) {
		List<WebElement> buttons = driver.findElements(viewButtons);
		WebElement button = buttons.get(index);
		new Actions(driver).scrollToElement(button).perform();
		button.click();
	}

	public void scrollToAndClickEdit(int index) {
		List<WebElement> buttons = driver.findElements(editButtons);
		WebElement button = buttons.get(index);
		new Actions(driver).scrollToElement(button).perform();
		button.click();
	}

	// shared method to (A) and (B):
	public void customScrollToElement(By locator) {
		WebElement element = driver.findElement(locator);
		new Actions(driver).scrollToElement(element).perform();
	}

	// (A) Frame footer - call After switchToIframe()
	public void scrollToFrameFooter() {
		customScrollToElement(footer);
	}

	public int getFrameFooterLinkCount() {
		return driver.findElements(footerLinks).size();
	}

	public void clickBackToTop() {
		driver.findElement(backToTopLink).click();
	}

	public void clickVisitHomepage() {
		driver.findElement(visitHomepageLink).click();
	}

	public void clickGettingStartedGuide() {
		driver.findElement(gettingStartedGuideLink).click();
	}

	// (B) Main page footer - call on default content (NOT in iframe)
	public void scrollToMainFooter() {
		customScrollToElement(footer);
	}

	public int getMainFooterLinkCount() {
		return driver.findElements(footerLinks).size();
	}

	public void clickMainContact() {
		driver.findElement(mainContactLink).click();
	}

	public void clickMainWhatsNew() {
		driver.findElement(mainWhatsNewLink).click();
	}

	public void clickMainSiteLink() {
		driver.findElement(mainSiteLink).click();
	}

	// Shared: read a link's href by its visible text
	// (works in either footer depending on the current frame context)
	public String getLinkHref(String linkText) {
		return driver.findElement(By.linkText(linkText)).getAttribute("href");
	}

	public boolean isLinkPresent(String linkText) {
		return driver.findElements(By.linkText(linkText)).size() > 0;
	}

	// Explicit-wait URL check:
	public boolean waitForUrlContains(String expected) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			return wait.until(ExpectedConditions.urlContains(expected));
		} catch (Exception e) {
			return false;
		}
	}
}
