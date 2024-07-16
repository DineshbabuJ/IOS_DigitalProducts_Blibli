package pages;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class DigitalPage {
    AppiumDriver driver;
    public final String PULSA_TITLE_NAV="//XCUIElementTypeStaticText[@name=\"Pulsa & Data Packages\"]";
    public final String BILLS_NAV="//XCUIElementTypeStaticText[@name=\"Bills & Top-ups\"]";
    public final String TOP_UP_TITLE="//XCUIElementTypeStaticText[@name=\"DLProductHeaderTop-ups\"]";
    public final String PHONE_NO="Phone numberTextField";
    public final String NOMINAL_CHOICE="//XCUIElementTypeStaticText[@name=\"nominalTitleLabel\" and @label=\"NOMINAL\"]";
    public final String MAKE_PAYMENT_BTN="Make payment";
    //    public final String NOMINALS_LIST="//XCUIElementTypeTable/XCUIElementTypeCell[6]/XCUIElementTypeButton[16]";
    WebDriverWait wait;
    public DigitalPage(AppiumDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void enterPhoneNumber(String number) {
        driver.findElement(AppiumBy.accessibilityId(PHONE_NO)).sendKeys(number);
    }

//    public boolean isNominalsListed() {
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(NOMINALS_LIST)));
//        Methods.scrollUpTo(driver);
//        List<WebElement> nominalList=driver.findElements(By.xpath(NOMINALS_LIST));
//        return !nominalList.isEmpty();
//    }

    public void chooseNominal(String nominal) {
        Methods.scrollUpTo(driver);
        driver.findElement(By.xpath(NOMINAL_CHOICE.replace("NOMINAL",nominal))).click();
    }

    public void makePayment() {
        Methods.scrollUpTo(driver);
        driver.findElement(AppiumBy.accessibilityId(MAKE_PAYMENT_BTN)).click();
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    public boolean isDisplayed(String xpathLocator){
        return driver.findElement(By.xpath(xpathLocator)).isDisplayed();
    }

    public boolean isTitleVisible(String pulsaTitleNav) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pulsaTitleNav))).isDisplayed();
    }

    public String getInput() {
        return driver.findElement(AppiumBy.accessibilityId(PHONE_NO)).getText();
    }

    public boolean isEnabled(String phoneNo) {
        return driver.findElement(AppiumBy.accessibilityId(phoneNo)).isEnabled();
    }

}
