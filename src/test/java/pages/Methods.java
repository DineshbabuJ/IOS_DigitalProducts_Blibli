package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Methods {


    public static void scrollUpTo(AppiumDriver driver) {
        Dimension size = driver.manage().window().getSize();
        int startY = (int) (size.height * 0.50);
        int endY = (int) (size.height * 0.10);
        int centerX = size.width / 2;
        PointerInput finger = new PointerInput(PointerInput.Kind.
                TOUCH
                ,
                "finger"
        );
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.
                        ofSeconds
                                (0),
                PointerInput.Origin.
                        viewport
                                (),
                centerX,
                (int) startY));
        swipe.addAction(finger.createPointerDown(0));
        swipe.addAction(finger.createPointerMove(Duration.
                        ofMillis
                                (700),
                PointerInput.Origin.
                        viewport
                                (),
                centerX,
                (int) endY));
        swipe.addAction(finger.createPointerUp(0));
        driver.perform(List.
                of
                        (swipe));
    }
}
