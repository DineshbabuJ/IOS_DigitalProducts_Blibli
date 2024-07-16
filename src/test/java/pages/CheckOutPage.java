package pages;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CheckOutPage {
    AppiumDriver driver;
    public final String PAYMENT_NAV="//XCUIElementTypeStaticText[@name=\"Payment\"]";
    public final String VIRTUAL_ACCOUNT="//XCUIElementTypeStaticText[@name=\"Transfer ke Virtual Account\"]";
    public final String BANK_SELECTED="//XCUIElementTypeStaticText[@name=\"BANK\"]";
    public final String PAY_BTN="//XCUIElementTypeButton[@name=\"DigitalPaymentBuyNowButton\"]";
    public final String RETURN_TO_HOME="Return home";
    public final String BANK_DESCRIPTION="//XCUIElementTypeButton[@name=\"Bank BCA\"]";
    public final String NOMINAL="PYDetailsNominalValue";
    public final String ORDER_STATUS_TITLE="Order status";
    WebDriverWait wait;
    public CheckOutPage(AppiumDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void selectVirtualAccount() {

        try {
            driver.findElement(AppiumBy.accessibilityId("TRLBIcon")).click();
        } catch (Exception e) {
        }
        Methods.scrollUpTo(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(VIRTUAL_ACCOUNT))).click();
    }

    public void chooseBank(String bank) {
        driver.findElement(By.xpath(BANK_SELECTED.replace("BANK",bank))).click();
    }


    public void clickPayBtn() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PAY_BTN)));
        driver.findElement(By.xpath(PAY_BTN)).click();
    }

    public void goToHome(){
        Methods.scrollUpTo(driver);
        driver.findElement(AppiumBy.accessibilityId(RETURN_TO_HOME)).click();
    }

    public boolean isVisible(String paymentNav) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PAYMENT_NAV)));
        return driver.findElement(By.xpath(paymentNav)).isDisplayed();
    }

    public String getBank() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BANK_DESCRIPTION))).getAttribute("label");
    }

    public String getNominal() {
        Methods.scrollUpTo(driver);
        Methods.scrollUpTo(driver);
        return driver.findElement(AppiumBy.accessibilityId(NOMINAL)).getAttribute("value");
    }

    public boolean isDisplayed(String orderStatusTitle) {
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(orderStatusTitle)));
        return driver.findElement(AppiumBy.accessibilityId(orderStatusTitle)).isDisplayed();
    }
}
