package pages;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    AppiumDriver driver;
    public final String EMAIL_FIELD="//XCUIElementTypeTextField[@value=\"Nomor HP atau email\"]";
    public final String PASSWORD_FIELD="//XCUIElementTypeSecureTextField[@value=\"Kata sandi*\"]";
    public final String FINAL_LOGINBTN="Log in";
    WebDriverWait wait;
    public LoginPage(AppiumDriver driver){
        this.driver=driver;
        wait=new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public boolean isEnabled(String emailField) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(emailField)));
        return driver.findElement(By.xpath(emailField)).isEnabled();
    }

    public void EnterEmail(String email) {
        driver.findElement(By.xpath(EMAIL_FIELD)).sendKeys(email);
    }


    public void clickLogin() {
        driver.findElement(AppiumBy.accessibilityId(FINAL_LOGINBTN)).click();
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(PASSWORD_FIELD)));
        driver.findElement(By.xpath(PASSWORD_FIELD)).sendKeys(password);
    }

    public void continueLogin() {
        driver.findElement(AppiumBy.accessibilityId(FINAL_LOGINBTN)).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(AppiumBy.accessibilityId("Let's go"))).click();

    }

    public String getEmail() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(EMAIL_FIELD)));
        return driver.findElement(By.xpath(EMAIL_FIELD)).getText();
    }
}
