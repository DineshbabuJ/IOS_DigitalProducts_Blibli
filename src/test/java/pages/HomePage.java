package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    AppiumDriver driver;
//    public final String POPUP="/hierarchy/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.ImageView";
    public final String BILLS_TOPUP="//XCUIElementTypeOther[@name=\"HomePersonalisationView.ImageContainer.utilities\"]";
    public final String OTHER_PRODUCTS="//XCUIElementTypeImage[@name=\"DLProductIconImageOther Products\"]";
    public final String PULSA="(//XCUIElementTypeOther[@name=\"DLProductIconViewPulsa\"])[1]";
    WebDriverWait wait;
    public HomePage(AppiumDriver driver){
        this.driver=driver;
        wait= new WebDriverWait(driver, Duration.ofSeconds(15));
    }


    public void chooseBillsTopUpSection() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(BILLS_TOPUP))).click();
    }

    public void clickotherProducts() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OTHER_PRODUCTS))).click();
    }

    public void selectPulsa() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PULSA))).click();
    }

    public boolean isClickable(String locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator))).isEnabled();
    }
}
