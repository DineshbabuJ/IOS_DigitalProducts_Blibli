package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class OrderList {
    AppiumDriver driver;
    public final String ORDER_LIST="TabBarOrderHistoryTab";
    public final String BILLS_SECTION="//XCUIElementTypeStaticText[@name=\"Bills & Top-ups\"]";
    public final String CANCEL_ORDER_BTN="name == \"Cancel order\"";
    public final String CANCEL_OK_POP="CUIAlertBT2";
    public final String ORDER_DETAIL_TITLE="Order detail";
    public final String ORDERED_PRODUCT="//XCUIElementTypeTable/XCUIElementTypeCell[1]";
    public final String NAVIGATE_BACK="//XCUIElementTypeButton[@name=\"EnquiryBack\"]";
    public final String CANCELED_SECTION="//XCUIElementTypeStaticText[@name=\"Canceled\"]";
    public final String NOMINAL_IN_ORDERED="nominalDisplayValue";
    public final String CANCELLED_ORDER="(//XCUIElementTypeStaticText[@name=\"Indosat Auto_switch_prepaid\"])[1]";
    WebDriverWait wait;
    public OrderList(AppiumDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void navigateToOrderList() {
        driver.findElement(AppiumBy.accessibilityId(ORDER_LIST)).click();
    }

    public void goToBillsSection() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BILLS_SECTION)));
        driver.findElement(By.xpath(BILLS_SECTION)).click();
    }

    public void viewOrderedProduct(){
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(ORDERED_PRODUCT))).click();
    }

    public void cancelOrder() throws InterruptedException {
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(ORDER_DETAIL_TITLE)));
        Methods.scrollUpTo(driver);
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.iOSNsPredicateString(CANCEL_ORDER_BTN)));
        driver.findElement(AppiumBy.iOSNsPredicateString(CANCEL_ORDER_BTN)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId(CANCEL_OK_POP))).click();
    }

    public void navigateToCancelledSection() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(NAVIGATE_BACK))).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CANCELED_SECTION))).click();
    }


    public String getNominal() {
        Methods.scrollUpTo(driver);
        return driver.findElement(AppiumBy.accessibilityId(NOMINAL_IN_ORDERED)).getText();
    }

    public boolean verifyProduct(String nominal) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(CANCELLED_ORDER)));
        return driver.findElement(By.xpath(CANCELLED_ORDER)).getAttribute("value").contains(nominal);
    }
}
