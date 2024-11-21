package com.globant.academy.screens;

import com.globant.academy.utils.baseScreen.BaseScreen;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.*;

public class SwipeCardsScreen extends BaseScreen {
    @AndroidFindBy(uiAutomator = "text(\"Swipe horizontal\")")
    private WebElement swipeCardsScreenLabelIdentifier;

    @AndroidFindBy(uiAutomator = "resourceId(\"__CAROUSEL_ITEM_0_READY__\")")
    private WebElement carouselItem;
    @AndroidFindBy(uiAutomator = "resourceId(\"__CAROUSEL_ITEM_4_READY__\")")
    private WebElement carouselSecondLastItem;
    @AndroidFindBy(uiAutomator = "resourceId(\"__CAROUSEL_ITEM_5_READY__\")")
    private WebElement carouselLastItem;

    @AndroidFindBy(uiAutomator = "text(\"You found me!!!\")")
    private WebElement youFoundMeLabel;

    public SwipeCardsScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isFirstCardVisible() {
        return !isElementOutOfScreenHorizontally(carouselItem);
    }

    public boolean isLastCardTheOnlyOneVisible() {
        return (!isElementOutOfScreenHorizontally(carouselSecondLastItem) &&
                isElementOutOfScreenHorizontally(carouselLastItem));
    }

    public void swipeCardsNTimes(int n) throws RuntimeException {
        Dimension screenSize = getDriver().manage().window().getSize();
        Dimension cardSize = carouselItem.getSize();
        Point elementLocation = carouselItem.getLocation();
        int yCoordinateForDragging = elementLocation.getY() + cardSize.getHeight()/2;

        Point source = new Point(
                screenSize.getWidth()/2,
                yCoordinateForDragging
        );
        Point target = new Point(
                0,
                yCoordinateForDragging
        );

        for (int i = 0; i < n; i++) {
            scroolScreen(source, target);
        }
    }

    public void scrollVerticallyNTimes(int n) throws RuntimeException {
        Dimension screenSize = getDriver().manage().window().getSize();

        int cardHeight = carouselLastItem.getSize().getHeight();
        int elementLocationYCoords = carouselLastItem.getLocation().getY();

        int yStarterCoord = cardHeight + elementLocationYCoords + 1;

        Point source = new Point(
                screenSize.getWidth()/2,
                yStarterCoord
        );
        Point target = new Point(
                screenSize.getWidth()/2,
                screenSize.getHeight()*3/4
        );

        for (int i = 0; i < n; i++) {
            scroolScreen(source, target);
        }
    }

    public boolean isSwipeCardsScreenVisible(String expectedText) {
        waitToBeVisible(swipeCardsScreenLabelIdentifier);
        return swipeCardsScreenLabelIdentifier.isDisplayed() &&
                swipeCardsScreenLabelIdentifier.getText().toLowerCase().equals(expectedText.toLowerCase());
    }

    public boolean isYouFoundMeLabelVisible(String expectedText) {
        waitToBeVisible(youFoundMeLabel);
        return youFoundMeLabel.isDisplayed() &&
                youFoundMeLabel.getText().toLowerCase().equals(expectedText.toLowerCase());
    }
}
