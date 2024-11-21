package com.globant.academy.utils.baseScreen;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public class BaseScreen {
    private AndroidDriver driver;
    private WebDriverWait wait;

    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(8));
    }

    public AndroidDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void waitToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public boolean isElementOutOfScreenHorizontally(WebElement element) {
        Dimension screenSize = driver.manage().window().getSize();
        Dimension elementSize = element.getSize();

        Point location = element.getLocation();

        boolean outOfScreenX =
                location.getX() + elementSize.getWidth() - 1 <= 0 ||
                location.getX() > screenSize.getWidth();

        return outOfScreenX;
    }

    public boolean isElementOutOfScreenVertically(WebElement element) {
        Dimension screenSize = driver.manage().window().getSize();
        Dimension elementSize = element.getSize();

        Point location = element.getLocation();

        boolean outOfScreenY =
                location.getY() + elementSize.getHeight() < 0 ||
                location.getY() > screenSize.getHeight();

        return outOfScreenY;
    }

    public void scroolScreen(Point source, Point target) {
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(0),
                PointerInput.Origin.viewport(),
                source.getX(),
                source.getY()
        ));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(
                Duration.ofMillis(100),
                PointerInput.Origin.viewport(),
                target.getX(),
                target.getY()
        ));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        getDriver().perform(Arrays.asList(swipe));
    }
}
